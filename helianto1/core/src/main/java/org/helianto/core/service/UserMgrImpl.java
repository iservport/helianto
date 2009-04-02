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

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.Credential;
import org.helianto.core.Entity;
import org.helianto.core.EventType;
import org.helianto.core.Identity;
import org.helianto.core.IdentityFilter;
import org.helianto.core.Operator;
import org.helianto.core.Province;
import org.helianto.core.User;
import org.helianto.core.UserAssociation;
import org.helianto.core.UserFilter;
import org.helianto.core.UserGroup;
import org.helianto.core.UserLog;
import org.helianto.core.UserLogFilter;
import org.helianto.core.UserState;
import org.helianto.core.dao.BasicDao;
import org.helianto.core.dao.FilterDao;
import org.helianto.core.dao.ProvinceDao;
import org.springframework.util.Assert;

/**
 * Default <code>UserMgr</code> implementation.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class UserMgrImpl extends AbstractCoreMgr implements UserMgr {
    
    public Identity findIdentityByPrincipal(String principal) {
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
	
	// user

	public User createUser(Entity entity) {
        Identity identity = Identity.identityFactory("");
        return createUser(identity, entity);
    }
    
    public User createUser(Identity identity, Entity entity) {
        return User.userFactory(entity, identity);
    }

    public void persistUser(User user) {
        String principal = user.getIdentity().getPrincipal();
        Locale locale = user.getEntity().getOperator().getLocale();
        user.getIdentity().setPrincipal(
                convertToLowerCase(locale, principal));
        userGroupDao.persist(user);
    }

    public UserGroup storeUserGroup(UserGroup userGroup) {
        return userGroupDao.merge(userGroup);
    }

    public UserGroup storeUserGroup(UserAssociation parentAssociation) {
    	UserAssociation managedUserAssociation = userAssociationDao.merge(parentAssociation);
        return managedUserAssociation.getChild();
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

//	public UserAssociation createUserAssociation(UserGroup parent, String principal) {
//		// does identity exist?
//		Identity identity = identityDao.findIdentityByNaturalId(principal);
//		if (identity==null) {
//			// No! create one with a creential.
//			Credential credential = Credential.credentialFactory(Identity.identityFactory(principal), "");
//			Credential managedCredential = credentialDao.mergeCredential(credential);
//			identity = managedCredential.getIdentity();
//		}
//		UserAssociation userAssociation = parent.childUserAssociationFactory(identity);
//    	if (logger.isDebugEnabled()) {
//    		logger.debug("Created user association ".concat(userAssociation.toString()));
//    	}
//    	UserLog userLog = ((User) userAssociation.getChild()).userLogFactory(EventType.CREATE);
//    	if (logger.isDebugEnabled()) {
//    		logger.debug("Created user log ".concat(userLog.toString()));
//    	}
//		return userAssociation;
//	}
//
	public UserAssociation createUserAssociation(UserGroup parent, String principal) {
		// does identity exist?
		Identity identity = identityDao.findUnique(principal);
		if (identity==null) {
			// No! create one with a creential.
			Credential credential = Credential.credentialFactory(Identity.identityFactory(principal), "");
			identity = credential.getIdentity();
		}
		UserAssociation userAssociation = parent.childUserAssociationFactory(identity);
    	if (logger.isDebugEnabled()) {
    		logger.debug("Created user association ".concat(userAssociation.toString()));
    	}
    	UserLog userLog = ((User) userAssociation.getChild()).userLogFactory(EventType.CREATE);
    	if (logger.isDebugEnabled()) {
    		logger.debug("Created user log ".concat(userLog.toString()));
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
    
    //deprecated methods

    /**
     * @deprecated in favor of storeIdentity
     */
	public void writeIdentity(Identity identity) {
		int attemptCount = 0;
		principalGenerationStrategy.generatePrincipal(identity, attemptCount);
		identityDao.merge(identity);
	}
	
    public List<Province> findProvinceByOperator(Operator operator) {
        return provinceDao.findProvinceByOperator(operator);
    }

    //- collaborators
    
    private FilterDao<Identity, IdentityFilter> identityDao;
    private BasicDao<Credential> credentialDao;
    private FilterDao<UserGroup, UserFilter> userGroupDao;
    private BasicDao<UserAssociation> userAssociationDao;
    private FilterDao<UserLog, UserLogFilter> userLogDao;
//    private IdentitySelectionStrategy identitySelectionStrategy;
    private PrincipalGenerationStrategy principalGenerationStrategy;
    private ProvinceDao provinceDao;
	

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
	public void setUserAssociationDao(BasicDao<UserAssociation> userAssociationDao) {
		this.userAssociationDao = userAssociationDao;
	}

    @Resource(name="userLogDao")
    public void setUserLogDao(FilterDao<UserLog, UserLogFilter> userLogDao) {
        this.userLogDao = userLogDao;
    }
    
//    @Resource
//    public void setIdentitySelectionStrategy(
//            IdentitySelectionStrategy identitySelectionStrategy) {
//        this.identitySelectionStrategy = identitySelectionStrategy;
//    }

    @Resource
	public void setPrincipalGenerationStrategy(
			PrincipalGenerationStrategy principalGenerationStrategy) {
		this.principalGenerationStrategy = principalGenerationStrategy;
	}

    @Resource
    public void setProvinceDao(ProvinceDao provinceDao) {
        this.provinceDao = provinceDao;
    }

    private static final Log logger = LogFactory.getLog(UserMgrImpl.class);


}
