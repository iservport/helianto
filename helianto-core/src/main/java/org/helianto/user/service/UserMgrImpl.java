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

package org.helianto.user.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.annotation.Resource;

import org.helianto.core.IdentityMgr;
import org.helianto.core.PublicEntityMgr;
import org.helianto.core.def.EventType;
import org.helianto.core.domain.Credential;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Identity;
import org.helianto.core.domain.Service;
import org.helianto.core.filter.Filter;
import org.helianto.core.filter.UserAssociationFormFilterAdapter;
import org.helianto.core.form.AssociationForm;
import org.helianto.core.repository.FilterDao;
import org.helianto.user.UserMgr;
import org.helianto.user.domain.User;
import org.helianto.user.domain.UserAssociation;
import org.helianto.user.domain.UserGroup;
import org.helianto.user.domain.UserLog;
import org.helianto.user.domain.UserRole;
import org.helianto.user.filter.UserFormFilterAdapter;
import org.helianto.user.filter.UserRoleFormFilterAdapter;
import org.helianto.user.form.UserGroupForm;
import org.helianto.user.form.UserRoleForm;
import org.helianto.user.repository.UserGroupRepository;
import org.helianto.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;


/**
 * Default <code>UserMgr</code> implementation.
 * 
 * @author Mauricio Fernandes de Castro
 */
@org.springframework.stereotype.Service("userMgr")
public class UserMgrImpl 
	implements UserMgr {
    
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

	/**
	 * This implementation is able to detect if the owning entity has changed its nature and
	 * will create a public entity accordingly.
	 */
	@Transactional
	public UserGroup storeUserGroup(UserGroup userGroup) {
    	if (userGroup.isKeyEmpty()) {
    		throw new IllegalArgumentException("Unable to create user, null or invalid identity");
    	}
    	userGroupRepository.saveAndFlush(userGroup);
    	publicEntityMgr.installPublicEntity(userGroup.getEntity());
        return userGroup;
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
     * @deprecated
     */
    protected Identity validateIdentity(User user) {
    	if (user.getIdentity().getId()==0) {
    		logger.debug("Looking for a candidate identity");
    		String principal = user.getUserKey();
    		logger.debug("Using principal {}", principal);
    		Identity identity = identityMgr.findIdentityByPrincipal(principal);
//    		if (identity!=null) {
//    			logger.debug("Identity found: {}", identity);
//    			user.setIdentity(identity);
//    		}
//    		else if (user.getCreateIdentity()==CreateIdentity.AUTO.getValue()) {
//        		identity = Identity.identityFactory(principal);
//        		logger.debug("Identity created: {}", identity);
//        		Credential credential = Credential.credentialFactory(identity, "default");
//        		identity.getCredentials().add(credential);
//        		logger.debug("Credential created: {}", credential);
//    			user.setIdentity(identity);
//    		}
//    		else {
//    			logger.debug("Unable to validate identity, identity not found and not created.");			
//    		}
    		return identity;
    	}
		logger.debug("User identified with {}", user.getIdentity());
		return user.getIdentity();
    }

	@Transactional(readOnly=true)
	public List<? extends UserGroup> findUsers(UserGroupForm form) {
		UserFormFilterAdapter filter = new UserFormFilterAdapter(form);
		List<UserGroup> userList = (List<UserGroup>) userGroupRepository.find(filter);
    	logger.debug("Found user list of size {}", userList.size());
        return userList;
	}

	@Transactional(readOnly=true)
	public List<? extends UserGroup> findUsers(String userKey) {
		List<UserGroup> userList = userGroupRepository.findByUserKeyOrderByLastEventDesc(userKey);
    	logger.debug("Found user list of size {}", userList.size());
        return userList;
	}

//    public List<? extends UserGroup> findUsers(Identity identity) {
//    	CompositeUserForm form = new CompositeUserForm();
//		form.setIdentity(identity);
//		form.setUserState(UserState.ACTIVE.getValue());
//        logger.debug("Filter users having state {}", form.getUserState());
//        try {
//    		return findUsers(new UserFormFilterAdapter(form));
//        } catch (Exception e) {
//        	logger.warn("Unable to find users ", e);
//        }
//        return null;
//    }
//
	@Transactional(readOnly=true)
	public List<UserAssociation> findUserAssociations(AssociationForm form) {
		Filter filter = new UserAssociationFormFilterAdapter(form);
		List<UserAssociation> userAssociationList = (List<UserAssociation>) userAssociationDao.find(filter);
    	logger.debug("Found user association list of size {}", userAssociationList.size());
        return userAssociationList;
	}

	@Transactional
    public UserAssociation storeUserAssociation(UserAssociation parentAssociation) {
//    	if (parentAssociation.getChild() instanceof User) {
//        	Identity identity = identityMgr.loadIdentity(((User) parentAssociation.getChild()).getIdentity().getId());
//        	((User) parentAssociation.getChild()).setIdentity(identity);
//    	}
//    	if (parentAssociation.getChild()!=null && parentAssociation.getChild().getId()>0) {
//    		int id = parentAssociation.getChild().getId();
//        	UserGroup child = userRepository.get(UserGroup.class, id);
//        	if (child!=null) {
//        		parentAssociation.setChild(child);
//        	}
//    	}
    	userAssociationDao.saveOrUpdate(parentAssociation);
    	return parentAssociation;
    }
    
    /**
     * Limted (so far) to one level.
     * 
     * @param userGroup
     */
	@Transactional
    public List<UserGroup> findParentChain(UserGroup userGroup) {
		userGroupRepository.refresh(userGroup);
    	List<UserGroup> parentList = new ArrayList<UserGroup>();
//    	parentList.add(userGroup);
    	if(userGroup.getParentAssociations()!=null) {
    		for (UserAssociation association: userGroup.getParentAssociations()) {
    			if (association.getParent()!=null) {
        			parentList.add(association.getParent());
            		logger.debug("{} is child of {}.", userGroup,  association.getParent());
    			}
    		}
    	}
    	else {
    		logger.warn("Parent list for {} is null.", userGroup);
    	}
    	return parentList;
    }

	@Transactional
	public UserGroup installUserGroup(Entity defaultEntity, String userGroupName, boolean reinstall) {

		logger.debug("Check user (group) {} installation with 'reinstall={}'", userGroupName, reinstall);
		UserGroup userGroup = null;
		if (!reinstall) {
			userGroup = userRepository.findByEntityAndUserKey(defaultEntity, userGroupName);
		}
		if (userGroup==null) {
			logger.debug("Will install user (group) {} ...", userGroupName);
			userGroup = userGroupRepository.save(new UserGroup(defaultEntity, userGroupName));
		}
		logger.debug("UserGroup AVAILABLE as {}.", userGroup);
		userRepository.flush();
		return userGroup;
	}
	
	@Transactional
	public UserAssociation installUser(UserGroup parent, String principal, boolean accountNonExpired) {
		
		logger.info("Check user installation with 'principal={}' as member of {}.", principal, parent);
		User user = (User) userRepository.findByEntityAndUserKey(parent.getEntity(), principal);
		if (user==null) {
			Credential credential = identityMgr.installIdentity(principal);
			user = new User(parent.getEntity(), credential);
		}
		
		user.setAccountNonExpired(true);
		logger.warn("User {} set to {} expired.", user, accountNonExpired ? "non" : "");

		userRepository.save(user);
		logger.info("User AVAILABLE as {}.", user);
		
		UserAssociation association = userAssociationDao.findUnique(parent, user);
		if(association==null) {
			logger.info("Will install user association for user group {} and {}.", parent, user);
			association = userAssociationDao.merge(new UserAssociation(parent, user));
		}
		logger.info("User {} available as part of association {}.", user, association);
		userAssociationDao.flush();
		return association;
	}
	
	@Transactional
	public UserAssociation installUser(UserGroup parent, Credential credential, boolean accountNonExpired) {
		
		logger.info("Check user installation with 'principal={}' as member of {}.", credential.getPrincipal(), parent);
		User user = (User) userRepository.findByEntityAndUserKey(parent.getEntity(), credential.getPrincipal());
		if (user==null) {
			user = (User) userRepository.save(new User(parent.getEntity(), credential));
		}
		
		user.setAccountNonExpired(true);
		userRepository.flush();
		logger.warn("User {} set to {} expired.", user, accountNonExpired ? "non" : "");

		
		logger.info("User AVAILABLE as {}.", user);
		
		UserAssociation association = userAssociationDao.findUnique(parent, user);
		if(association==null) {
			logger.info("Will install user association for user group {} and {}.", parent, user);
			association = userAssociationDao.merge(new UserAssociation(parent, user));
		}
		logger.info("User {} available as part of association {}.", user, association);
		userAssociationDao.flush();
		return association;
	}
	
	@Transactional
	public UserRole installUserRole(UserGroup userGroup, Service service, String extension) {
		
		UserRole userRole = userRoleDao.findUnique(userGroup, service, extension);
		if (userRole==null) {
			logger.debug("Will install required user role {} for user group {} ...", userRole, userGroup);
			userRole = userRoleDao.merge(new UserRole(userGroup, service, extension));
		}
		logger.debug("User role AVAILABLE as {}.", userRole);
		userRoleDao.flush();
		return userRole;
	}
	
	@Transactional
    public UserLog storeUserLog(User user, Date date) {
        if (user.getIdentity()==null) {
            throw new IllegalArgumentException("Must have an identity");
        }
        if (date==null) {
            date = new Date();
        }
        UserLog userLog = new UserLog(user, date, EventType.LOGIN_ATTEMPT);
        userLogDao.saveOrUpdate(userLog);
        return userLog;
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
    
	@Transactional(readOnly=true)
	public List<UserRole> findUserRoles(Filter filter) {
		List<UserRole> userRoleList = (List<UserRole>) userRoleDao.find(filter);
    	if (userRoleList!=null && userRoleList.size()>0) {
    		logger.debug("Loaded user role list of size {}", userRoleList.size());
    	}
    	return userRoleList;
	}

	@Transactional(readOnly=true)
	public List<UserRole> findUserRoles(UserRoleForm form) {
		Filter filter = new UserRoleFormFilterAdapter(form);
		List<UserRole> userRoleList = (List<UserRole>) userRoleDao.find(filter);
    	if (userRoleList!=null && userRoleList.size()>0) {
    		logger.debug("Loaded user role list of size {}", userRoleList.size());
    	}
    	return userRoleList;
	}

	@Transactional
	public UserRole storeUserRole(UserRole userRole) {
		userRoleDao.saveOrUpdate(userRole);
		userRoleDao.flush();
		return userRole;
	}
	
	@Transactional
	public void removeUserRole(UserRole userRole, UserGroup userGroup) {
		if (userGroup!=null ) {
			UserRole[] rr = (UserRole[]) userGroup.getRoles().toArray();
			for (UserRole r: rr) {
				System.out.println("User role: "+r);
				if (r.getUserGroup().equals(userGroup)) {
					userRoleDao.remove(userRole);
					userGroup.getRoles().remove(userRole);
//					userRoleDao.flush();
					System.out.println("= "+userRole);
				}
				else {
					System.out.println("!= "+userRole);
				}
			}
		}
		throw new IllegalArgumentException("User role " +
				userRole+"does not belong to user group " + userGroup+".");
	}

    //- collaborators
    
    private UserRepository userRepository;
    private UserGroupRepository userGroupRepository;
    private FilterDao<UserAssociation> userAssociationDao;
    private FilterDao<UserRole> userRoleDao;
    private FilterDao<UserLog> userLogDao;
	private IdentityMgr identityMgr;
	private PublicEntityMgr publicEntityMgr;
	

    @Resource
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
    
    @Resource
    public void setUserGroupRepository(UserGroupRepository userGroupRepository) {
		this.userGroupRepository = userGroupRepository;
	}

    @Resource(name="userAssociationDao")
	public void setUserAssociationDao(FilterDao<UserAssociation> userAssociationDao) {
		this.userAssociationDao = userAssociationDao;
	}

	@javax.annotation.Resource(name="userRoleDao")
	public void setUserRoleDao(FilterDao<UserRole> userRoleDao) {
		this.userRoleDao = userRoleDao;
	}
	
    @Resource(name="userLogDao")
    public void setUserLogDao(FilterDao<UserLog> userLogDao) {
        this.userLogDao = userLogDao;
    }
    
    @Resource
    public void setIdentityMgr(IdentityMgr identityMgr) {
		this.identityMgr = identityMgr;
	}
    
    @Resource
    public void setPublicEntityMgr(PublicEntityMgr publicEntityMgr) {
		this.publicEntityMgr = publicEntityMgr;
	}

    private static final Logger logger = LoggerFactory.getLogger(UserMgrImpl.class);


}
