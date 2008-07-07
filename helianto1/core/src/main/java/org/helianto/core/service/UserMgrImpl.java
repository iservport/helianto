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
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.UserAssociation;
import org.helianto.core.UserFilter;
import org.helianto.core.UserGroup;
import org.helianto.core.dao.IdentitySelectionStrategy;
import org.helianto.core.dao.UserSelectionStrategy;
import org.helianto.core.filter.IdentityFilter;

/**
 * Default <code>UserMgr</code> implementation.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class UserMgrImpl extends AbstractCoreMgr implements UserMgr {
    
    private IdentitySelectionStrategy identitySelectionStrategy;
    private UserSelectionStrategy userSelectionStrategy;
    private PrincipalGenerationStrategy principalGenerationStrategy;
	
    public Identity findIdentityByPrincipal(String principal) {
        return identityDao.findIdentityByNaturalId(principal);
    }

    public List<Identity> findIdentities(IdentityFilter filter, Collection<Identity> exclusions) {
        String criteria = identitySelectionStrategy.createCriteriaAsString(filter, "identity");
        List<Identity> identityList = identityDao.findIdentities(criteria);
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
		return identityDao.mergeIdentity(identity);
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
        userGroupDao.persistUserGroup(user);
    }

    public UserGroup storeUserGroup(UserGroup userGroup) {
        return userGroupDao.mergeUserGroup(userGroup);
    }

    public UserGroup storeUserGroup(UserAssociation parentAssociation) {
    	UserAssociation managedUserAssociation = userGroupDao.mergeUserAssociation(parentAssociation);
        return managedUserAssociation.getChild();
    }

	public List<User> findUsers(UserFilter userFilter) {
		String criteria = userSelectionStrategy.createCriteriaAsString(userFilter, "user");
		List<User> userList = userGroupDao.findUserByCriteria(criteria);
    	if (logger.isDebugEnabled() && userList!=null) {
    		logger.debug("Found user list of size "+userList.size());
    	}
        return userList;
	}

    public List<User> findUsers(String criteria) {
        return userGroupDao.findUserByCriteria(criteria);
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
		identityDao.mergeIdentity(identity);
	}
	
    /**
     * @deprecated in favor of storeUserGroup
     */
    public void writeUser(User user) {
        userGroupDao.mergeUserGroup(user);
    }

    /**
     * @deprecated in favor of storeUserGroup
     */
    public void writeUser(UserGroup userGroup) {
        userGroupDao.mergeUserGroup(userGroup);
    }

    /**
     * @deprecated in favor of storeUserGroup
     */
	public User storeUser(User user) {
        return (User) userGroupDao.mergeUserGroup(user);
	}

    //- collaborators
    
    @Resource
    public void setIdentitySelectionStrategy(
            IdentitySelectionStrategy identitySelectionStrategy) {
        this.identitySelectionStrategy = identitySelectionStrategy;
    }

    @Resource
    public void setUserSelectionStrategy(
    		UserSelectionStrategy userSelectionStrategy) {
        this.userSelectionStrategy = userSelectionStrategy;
    }

    @Resource
	public void setPrincipalGenerationStrategy(
			PrincipalGenerationStrategy principalGenerationStrategy) {
		this.principalGenerationStrategy = principalGenerationStrategy;
	}

    private static final Log logger = LogFactory.getLog(UserMgrImpl.class);

}
