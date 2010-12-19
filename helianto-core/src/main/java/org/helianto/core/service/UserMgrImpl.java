/* Copyright 2005 I Serv Consultoria Empresarial Ltda.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.helianto.core.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.annotation.Resource;

import org.helianto.core.ActivityState;
import org.helianto.core.CreateIdentity;
import org.helianto.core.Credential;
import org.helianto.core.DuplicateIdentityException;
import org.helianto.core.Entity;
import org.helianto.core.EventType;
import org.helianto.core.Identity;
import org.helianto.core.Operator;
import org.helianto.core.Province;
import org.helianto.core.User;
import org.helianto.core.UserAssociation;
import org.helianto.core.UserGroup;
import org.helianto.core.UserLog;
import org.helianto.core.UserRole;
import org.helianto.core.UserState;
import org.helianto.core.filter.classic.IdentityFilter;
import org.helianto.core.filter.classic.ProvinceFilter;
import org.helianto.core.filter.classic.UserAssociationFilter;
import org.helianto.core.filter.classic.UserFilter;
import org.helianto.core.filter.classic.UserLogFilter;
import org.helianto.core.repository.BasicDao;
import org.helianto.core.repository.FilterDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Default <code>UserMgr</code> implementation.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Service("userMgr")
public class UserMgrImpl implements UserMgr {
    
    public Identity findIdentityByPrincipal(String principal) {
        logger.debug("Finding unique with principal {}", principal);
        return (Identity) identityDao.findUnique(principal);
    }
    
    public Identity loadIdentity(long id) {
    	Identity sample = new Identity();
    	sample.setId(id);
    	Identity identity = identityDao.load(sample);
    	if (identity!=null && identity.getPhoto()!=null) {
    		logger.debug("Identity {} photo size is {}.", identity, identity.getPhoto().length);
    	}
    	return identity;
    }
    
    public byte[] loadIdentityPhoto(Identity identity) {
    	if (identity!=null && identity.getPhoto()!=null) {
    		logger.debug("Identity {} photo size is {}.", identity, identity.getPhoto().length);
    		return identity.getPhoto();
    	}
    	logger.debug("Identity {} photo not available.", identity);
    	return null;
    }

    public List<Identity> findIdentities(IdentityFilter filter, Collection<Identity> exclusions) {
        List<Identity> identityList = (List<Identity>) identityDao.find(filter);
        logger.debug("Found {} item(s)", identityList.size());
        identityList.removeAll(exclusions);
        logger.debug("Removed {} item(s)", exclusions.size());
        return identityList ;
    }

    /**
     * Store the given <code>Identity</code> and return a managed object.
     * 
     * <p>
     * This implementation also checks for a previous identity with the same 
     * principal and raises an <code>DuplicateIdentityException</code> accordingly.
     * Note that the persistence context (or Hibernate session) is not affected by the latter
     * because no integrity violation exception is allowed.</p>
     * 
     * @param identity
     */
	public Identity storeIdentity(Identity identity) {
		int attemptCount = 0;
		principalGenerationStrategy.generatePrincipal(identity, attemptCount);
		if (identity.getId()==0) {
			logger.debug("Identity with principal {} is likely new.", identity.getPrincipal());
			Identity checkForPreviousIdentity = (Identity) identityDao.findUnique(identity.getPrincipal());
			if (checkForPreviousIdentity!=null) {
				logger.warn("Found previous identity with same principal as new indentity: {}, rejecting.", checkForPreviousIdentity);
				throw new DuplicateIdentityException(checkForPreviousIdentity, "Found previous identity with same principal as new indentity");
			}
		}
		return identityDao.merge(identity);
	}
	
    public UserGroup prepareNewUserGroup(Entity entity) {
    	UserGroup userGroup = UserGroup.userGroupFactory(entity, "");
    	entity.getUsers().add(userGroup);
    	return userGroup;
	}
	
    public UserGroup prepareUserGroup(UserGroup userGroup) {
    	UserGroup managedUserGroup = userGroupDao.merge(userGroup);
    	// child list
		logger.debug("About to load child association(s).");
    	Set<UserAssociation> childAssociations = managedUserGroup.getChildAssociations();
		logger.debug("Loaded {} child association(s).", childAssociations.size());
		List<UserAssociation> childAssociationList = new ArrayList<UserAssociation>(childAssociations);
		logger.debug("Listed {} child association(s).", childAssociationList.size());
		Collections.sort(childAssociationList);
		managedUserGroup.setChildAssociationList(childAssociationList);
		logger.debug("Ready to recursively add user roles");
		List<UserRole> roleList = new ArrayList<UserRole>(recurseUserRoles(managedUserGroup.getRoles(), managedUserGroup.getParentAssociations()));
		managedUserGroup.setRoleList(roleList);
    	userGroupDao.evict(managedUserGroup);
    	return managedUserGroup;
    }
	
    /**
	 * Recurse into parent user groups to create a complete userRole List.
	 */
	protected Set<UserRole> recurseUserRoles(Set<UserRole> roles, Set<UserAssociation> parentAssociations) {
		Set<UserRole> localRoles = new HashSet<UserRole>(roles);
		logger.debug("Found {} local role(s).", localRoles.size());
		for (UserAssociation parentAssociation: parentAssociations) {
			UserGroup parent = parentAssociation.getParent();
			localRoles.addAll(recurseUserRoles(parent.getRoles(), parent.getParentAssociations()));
		}
		return localRoles;
	}

	public UserGroup storeUserGroup(UserGroup userGroup) {
    	if (userGroup.isKeyEmpty() || userGroup instanceof User && validateIdentity((User) userGroup)!=null) {
    		throw new IllegalArgumentException("Unable to create user, null or invalid identity");
    	}
        return userGroupDao.merge(userGroup);
    }
    
    /**
     * Assure each user have a valid identity.
     * 
     * <p>Validation is ready to detect identity (with principal 
     * equals user key) not found.</p>
     * <p>If the create flag is set, a candidate principal may be used
     * to create a new identity.</p>
     * 
     * @param user
     * @param createIdentity
     */
    protected Identity validateIdentity(User user) {
    	if (user.getIdentity().getId()==0) {
    		logger.debug("Looking for a candidate identity");
    		String principal = user.getUserKey();
    		logger.debug("Using principal {}", principal);
    		Identity identity = identityDao.findUnique(principal);
    		if (identity!=null) {
    			logger.debug("Identity found: {}", identity);
    			user.setIdentity(identity);
    		}
    		else if (user.getCreateIdentity()==CreateIdentity.AUTO.getValue()) {
        		identity = Identity.identityFactory(principal);
        		logger.debug("Identity created: {}", identity);
        		Credential credential = Credential.credentialFactory(identity, "default");
        		identity.getCredentials().add(credential);
        		logger.debug("Credential created: {}", credential);
    			user.setIdentity(identity);
    		}
    		else {
    			logger.debug("Unable to validate identity, identity not found and not created.");			
    		}
    		return identity;
    	}
		logger.debug("User identified with {}", user.getIdentity());
		return user.getIdentity();
    }
    /**
     * <p>Store <code>UserAssociation</code> and return a managed instance.</p>
     * 
     */
    public UserAssociation storeUserAssociation(UserAssociation parentAssociation) {
    	if (!parentAssociation.isKeyEmpty() && parentAssociation.getChild() instanceof User) {
        	logger.debug("Ready to validate {}", parentAssociation.getChild());
        	User child = (User) parentAssociation.getChild();
    		child.setIdentity(validateIdentity(child));
    		child.setAccountNonExpired(true);
    		UserGroup managedChild = userGroupDao.merge(child);
    		parentAssociation.setChild(managedChild);
        	return userAssociationDao.merge(parentAssociation);
    	}
		throw new IllegalArgumentException("Unable to create user, null or invalid identity");
    }

	public List<UserGroup> findUsers(UserFilter userFilter) {
		List<UserGroup> userList = (List<UserGroup>) userGroupDao.find(userFilter);
    	logger.debug("Found user list of size {}", userList.size());
        return userList;
	}

    public List<UserGroup> findUsers(Identity identity) {
    	UserFilter userFilter = new UserFilter(identity, true);
    	userFilter.setUserState(UserState.ACTIVE.getValue());
        logger.debug("Filter users having state {}", userFilter.getUserState());
        try {
    		return findUsers(userFilter);
        } catch (Exception e) {
        	logger.warn("Unable to find users ", e);
        }
        return null;
    }

	public UserAssociation prepareNewUserAssociation(UserGroup parent) {
		UserAssociation userAssociation = new UserAssociation(parent);
    	logger.debug("Created user association {}.", userAssociation);
		return userAssociation;
	}

	public List<UserAssociation> findUserAssociations(UserAssociationFilter userAssociationFilter) {
		List<UserAssociation> userAssociationList = (List<UserAssociation>) userAssociationDao.find(userAssociationFilter);
    	logger.debug("Found user association list of size {}", userAssociationList.size());
        return userAssociationList;
	}

	public UserAssociation installUser(UserGroup parent, String principal, boolean accountNonExpired) {
		
		logger.info("Check user installation with 'principal={}' as member of {}.", principal, parent);
		User user = (User) userGroupDao.findUnique(parent.getEntity(), principal);
		if (user==null) {
			Credential credential = installIdentity(principal);
			user = new User(parent.getEntity(), credential);
		}
		
		user.setAccountNonExpired(true);
		logger.warn("User {} set to {} expired.", user, accountNonExpired ? "non" : "");

		userGroupDao.saveOrUpdate(user);
		logger.info("User AVAILABLE as {}.", user);
		
		UserAssociation association = userAssociationDao.findUnique(parent, user);
		if(association==null) {
			logger.info("Will install user association for user group {} and {}.", parent, user);
			association = new UserAssociation(parent, user);
			userAssociationDao.saveOrUpdate(association);
		}
		logger.info("User {} available as part of association {}.", user, association);
		return association;
	}
	
	public UserAssociation installUser(UserGroup parent, Credential credential, boolean accountNonExpired) {
		
		logger.info("Check user installation with 'principal={}' as member of {}.", credential.getPrincipal(), parent);
		User user = (User) userGroupDao.findUnique(parent.getEntity(), credential.getPrincipal());
		if (user==null) {
			user = new User(parent.getEntity(), credential);
		}
		
		user.setAccountNonExpired(true);
		logger.warn("User {} set to {} expired.", user, accountNonExpired ? "non" : "");

		userGroupDao.saveOrUpdate(user);
		logger.info("User AVAILABLE as {}.", user);
		
		UserAssociation association = userAssociationDao.findUnique(parent, user);
		if(association==null) {
			logger.info("Will install user association for user group {} and {}.", parent, user);
			association = new UserAssociation(parent, user);
			userAssociationDao.saveOrUpdate(association);
		}
		logger.info("User {} available as part of association {}.", user, association);
		return association;
	}
	
	public Credential installIdentity(String principal) {
		Identity identity = identityDao.findUnique(principal);
		if (identity==null) {
			logger.info("Will install identity for {}.", principal);
			identity = new Identity(principal);
			identityDao.saveOrUpdate(identity);
		}
		else {
			logger.debug("Found existing identity for {}.", principal);
		}
		return installCredential(identity);
	}
	
	public Credential installCredential(Identity identity) {
		Credential credential = credentialDao.findUnique(identity);
		if (credential==null) {
			logger.info("Will install credential for {}.", identity);
			credential = new Credential(identity);
			// TODO make it INITIAL
			credential.setCredentialState(ActivityState.ACTIVE);
			credentialDao.saveOrUpdate(credential);
		}
		else {
			logger.debug("Found existing credential for {}.", identity);
		}
		return credential;
	}
	
    public UserLog storeUserLog(User user, Date date) {
        if (user.getIdentity()==null) {
            throw new IllegalArgumentException("Must have an identity");
        }
        if (date==null) {
            date = new Date();
        }
        UserLog userLog = UserLog.userLogFactory(user, date, EventType.LOGIN_ATTEMPT);
        return userLogDao.merge(userLog);
    }
    
    /**
     * Helper method to convert principal to lower case.
     */
    static String convertToLowerCase(Locale locale, String principal) {
        if ((principal != null && principal.length() > 0)) {
            return principal.toLowerCase(locale);
        }
        throw new RuntimeException("Principal should not be null or empty.");
    }
    
    public List<Province> findProvinceByOperator(Operator operator) {
    	ProvinceFilter filter = new ProvinceFilter(operator);
        return (List<Province>) provinceDao.find(filter);
    }

    //- collaborators
    
    private FilterDao<Identity, IdentityFilter> identityDao;
    private BasicDao<Credential> credentialDao;
    private FilterDao<UserGroup, UserFilter> userGroupDao;
    private FilterDao<UserAssociation, UserAssociationFilter> userAssociationDao;
    private FilterDao<UserLog, UserLogFilter> userLogDao;
    private PrincipalGenerationStrategy principalGenerationStrategy;
    private FilterDao<Province, ProvinceFilter> provinceDao;
	

    @Resource(name="identityDao")
    public void setIdentityDao(FilterDao<Identity, IdentityFilter> identityDao) {
        this.identityDao = identityDao;
    }
    
    @Resource(name="credentialDao")
    public void setCredentialDao(BasicDao<Credential> credentialDao) {
		this.credentialDao = credentialDao;
	}

    @Resource(name="userGroupDao")
	public void setUserGroupDao(FilterDao<UserGroup, UserFilter> userGroupDao) {
		this.userGroupDao = userGroupDao;
	}

    @Resource(name="userAssociationDao")
	public void setUserAssociationDao(FilterDao<UserAssociation, UserAssociationFilter> userAssociationDao) {
		this.userAssociationDao = userAssociationDao;
	}

    @Resource(name="userLogDao")
    public void setUserLogDao(FilterDao<UserLog, UserLogFilter> userLogDao) {
        this.userLogDao = userLogDao;
    }
    
    @Resource
	public void setPrincipalGenerationStrategy(PrincipalGenerationStrategy principalGenerationStrategy) {
		this.principalGenerationStrategy = principalGenerationStrategy;
	}

    @Resource(name="provinceDao")
    public void setProvinceDao(FilterDao<Province, ProvinceFilter> provinceDao) {
        this.provinceDao = provinceDao;
    }

    private static final Logger logger = LoggerFactory.getLogger(UserMgrImpl.class);


}
