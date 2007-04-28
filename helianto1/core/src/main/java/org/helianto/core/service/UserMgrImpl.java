package org.helianto.core.service;

import java.util.Collection;
import java.util.List;
import java.util.Locale;

import org.helianto.core.ActivityState;
import org.helianto.core.Credential;
import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.UserGroup;
import org.helianto.core.creation.AuthorizationCreator;
import org.helianto.core.dao.CredentialDao;
import org.helianto.core.filter.IdentityFilter;
import org.springframework.beans.factory.annotation.Required;

/**
 * Default <code>UserMgr</code> implementation.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class UserMgrImpl extends AbstractCoreMgr implements UserMgr {
    
    protected CredentialDao credentialDao;
	
	/* 
	 * Create and persist Identity
	 */

    public Identity createEmptyIdentity() {
		return Identity.identityFactory("", "");
	}

	public void persistIdentity(Identity identity) {
		identityDao.persistIdentity(identity);
	}
    
    public List<Identity> findIdentities(IdentityFilter filter, Collection<Identity> exclusions) {
        List<Identity> identityList = identityDao.findIdentityByCriteria(filter);
        identityList.removeAll(exclusions);
        return identityList ;
    }

	/* 
	 * Create and persist Credential
	 */

    public Credential createCredential(Identity identity) {
        return Credential.credentialFactory(identity, "empty");
    }

    public Credential createCredentialAndIdentity() {
        Identity identity = createEmptyIdentity();
        return createCredential(identity);
    }

	public void persistCredential(Credential credential) {
        credentialDao.persistCredential(credential);
	}

	/* 
	 * Create and persist User
	 */

	public User createUser(Entity entity) {
        Identity identity = createEmptyIdentity();
        return createUser(identity, entity);
    }
    
    public User createUser(Identity identity, Entity entity) {
        return AuthorizationCreator.userFactory(entity, identity);
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
    
}
