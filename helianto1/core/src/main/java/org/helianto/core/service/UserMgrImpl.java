package org.helianto.core.service;

import java.util.Collection;
import java.util.List;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.ActivityState;
import org.helianto.core.Credential;
import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.UserGroup;
import org.helianto.core.dao.CredentialDao;
import org.helianto.core.dao.IdentitySelectionStrategy;
import org.helianto.core.filter.IdentityFilter;
import org.springframework.beans.factory.annotation.Required;

/**
 * Default <code>UserMgr</code> implementation.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class UserMgrImpl extends AbstractCoreMgr implements UserMgr {
    
    protected CredentialDao credentialDao;
    
    private IdentitySelectionStrategy identitySelectionStrategy;
	
	// identity

	public void writeIdentity(Identity identity) {
		identityDao.mergeIdentity(identity);
	}
    
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

	// credential

	public void writeCredential(Credential credential) {
        credentialDao.mergeCredential(credential);
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
        authorizationDao.persistUserGroup(user);
    }
    
    public List<UserGroup> findUserByEntity(Entity entity) {
        return authorizationDao.findUserGroupByEntity(entity);
    }
    
    public List<User> findUsers(String criteria) {
        return authorizationDao.findUserByCriteria(criteria);
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

    public void activateUser(UserGroup user, Credential credential) {
        if (credential.getCredentialState()==ActivityState.ACTIVE.getValue()) {
            user.setUserState(ActivityState.ACTIVE.getValue());
        }
    }
    
    public void cancelUser(UserGroup user) {
        user.setUserState(ActivityState.CANCELLED.getValue());
    }
    
    public void suspendUser(UserGroup user) {
        user.setUserState(ActivityState.SUSPENDED.getValue());
    }
    
    //- collaborators
    
    @Required
    public void setCredentialDao(CredentialDao credentialDao) {
        this.credentialDao = credentialDao;
    }

    @Required
    public void setIdentitySelectionStrategy(
            IdentitySelectionStrategy identitySelectionStrategy) {
        this.identitySelectionStrategy = identitySelectionStrategy;
    }

    private static final Log logger = LogFactory.getLog(UserMgrImpl.class);

}
