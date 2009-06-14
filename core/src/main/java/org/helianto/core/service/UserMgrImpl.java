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
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.CreateIdentity;
import org.helianto.core.Entity;
import org.helianto.core.EventType;
import org.helianto.core.Identity;
import org.helianto.core.IdentityFilter;
import org.helianto.core.Operator;
import org.helianto.core.Province;
import org.helianto.core.ProvinceFilter;
import org.helianto.core.User;
import org.helianto.core.UserAssociation;
import org.helianto.core.UserFilter;
import org.helianto.core.UserGroup;
import org.helianto.core.UserLog;
import org.helianto.core.UserLogFilter;
import org.helianto.core.UserRole;
import org.helianto.core.UserState;
import org.helianto.core.dao.BasicDao;
import org.helianto.core.dao.FilterDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * Default <code>UserMgr</code> implementation.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Service("userMgr")
@Transactional
public class UserMgrImpl implements UserMgr {
    
    public Identity findIdentityByPrincipal(String principal) {
        if (logger.isDebugEnabled()) {
            logger.debug("Finding unique with principal "+principal);
        }
        return (Identity) identityDao.findUnique(principal);
    }

    public List<Identity> findIdentities(IdentityFilter filter, Collection<Identity> exclusions) {
        List<Identity> identityList = (List<Identity>) identityDao.find(filter);
        if (logger.isDebugEnabled()) {
            logger.debug("Found "+identityList.size()+" item(s)");
        }
        identityList.removeAll(exclusions);
        if (logger.isDebugEnabled()) {
            logger.debug("Removed "+exclusions.size()+" item(s)");
        }
        return identityList ;
    }

	public Identity storeIdentity(Identity identity) {
		int attemptCount = 0;
		principalGenerationStrategy.generatePrincipal(identity, attemptCount);
		return identityDao.merge(identity);
	}
	
    public UserGroup prepareNewUserGroup(Entity entity) {
    	UserGroup userGroup = UserGroup.userGroupFactory(entity, "");
    	entity.getUsers().add(userGroup);
    	return userGroup;
	}
	
	@Transactional(readOnly=true)
    public UserGroup prepareUserGroup(UserGroup userGroup) {
    	UserGroup managedUserGroup = userGroupDao.merge(userGroup);
		List<UserRole> roleList = new ArrayList<UserRole>(recurseUserRoles(managedUserGroup.getRoles(), managedUserGroup.getParentAssociations()));
		managedUserGroup.setRoleList(roleList );
    	userGroupDao.evict(managedUserGroup);
    	return managedUserGroup;
    }

    /**
	 * Recurse into parent user groups to create a complete userRole List.
	 */
	protected Set<UserRole> recurseUserRoles(Set<UserRole> roles, Set<UserAssociation> parentAssociations) {
		Set<UserRole> localRoles = new HashSet<UserRole>(roles);
		if (logger.isDebugEnabled()) {
			logger.debug("Found "+localRoles.size()+" local role(s).");
		}
		for (UserAssociation parentAssociation: parentAssociations) {
			UserGroup parent = parentAssociation.getParent();
			localRoles.addAll(recurseUserRoles(parent.getRoles(), parent.getParentAssociations()));
		}
		return localRoles;
	}

	public UserGroup storeUserGroup(UserGroup userGroup) {
		if (userGroup.getUserKey()==null || userGroup.getUserKey().length()==0) {
			throw new IllegalArgumentException("Unable to create user or group, null or empty user key.");
		}
    	if (userGroup instanceof User && !validateIdentity((User) userGroup)) {
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
    protected boolean validateIdentity(User user) {
    	if (user.getIdentity().getId()==0) {
    		if (logger.isDebugEnabled()) {
    			logger.debug("Looking for a candidate identity");
    		}
    		String principal = user.getUserKey();
    		if (logger.isDebugEnabled()) {
    			logger.debug("Using principal "+principal);
    		}
    		Identity identity = identityDao.findUnique(principal);
    		if (identity!=null) {
    			user.setIdentity(identity);
    		}
    		else if (user.getCreateIdentity()==CreateIdentity.AUTO.getValue()) {
        		if (logger.isDebugEnabled()) {
        			logger.debug("Identity not found, creating one");
        		}
        		identity = identityDao.merge(Identity.identityFactory(principal));
    			user.setIdentity(identity);
    		}
    		else {
    			if (logger.isDebugEnabled()) {
    				logger.debug("Unable to validate identity, identity not found and not created.");
    			}
    			return false;    			
    		}
    	}
		if (logger.isDebugEnabled()) {
			logger.debug("User identified with "+user.getIdentity());
		}
		return true;
    }

    public UserAssociation storeUserAssociation(UserAssociation parentAssociation) {
    	return userAssociationDao.merge(parentAssociation);
    }

	public List<UserGroup> findUsers(UserFilter userFilter) {
		List<UserGroup> userList = (List<UserGroup>) userGroupDao.find(userFilter);
    	if (logger.isDebugEnabled() && userList!=null) {
    		logger.debug("Found user list of size "+userList.size());
    	}
        return userList;
	}

    public List<UserGroup> findUsers(Identity identity) {
    	UserFilter userFilter = new UserFilter(identity, true);
    	userFilter.setUserState(UserState.ACTIVE.getValue());
        if (logger.isDebugEnabled()) {
            logger.debug("Filter users having state "+userFilter.getUserState());
        }
        try {
    		return findUsers(userFilter);
        } catch (Exception e) {
        	logger.warn("Unable to find users ", e);
        }
        return null;
    }

	public UserAssociation prepareNewUserAssociation(UserGroup parent) {
		UserAssociation userAssociation = UserAssociation.userAssociationFactory(parent);
    	if (logger.isDebugEnabled()) {
    		logger.debug("Created user association ".concat(userAssociation.toString()));
    	}
		return userAssociation;
	}

    public UserLog storeUserLog(User user, Date date) {
        Assert.notNull(user.getIdentity());
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
    private FilterDao<UserGroup, UserFilter> userGroupDao;
    private BasicDao<UserAssociation> userAssociationDao;
    private FilterDao<UserLog, UserLogFilter> userLogDao;
    private PrincipalGenerationStrategy principalGenerationStrategy;
    private FilterDao<Province, ProvinceFilter> provinceDao;
	

    @Resource(name="identityDao")
    public void setIdentityDao(FilterDao<Identity, IdentityFilter> identityDao) {
        this.identityDao = identityDao;
    }

    @Resource(name="userGroupDao")
	public void setUserGroupDao(FilterDao<UserGroup, UserFilter> userGroupDao) {
		this.userGroupDao = userGroupDao;
	}

    @Resource(name="userAssociationDao")
	public void setUserAssociationDao(BasicDao<UserAssociation> userAssociationDao) {
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

    private static final Log logger = LogFactory.getLog(UserMgrImpl.class);


}
