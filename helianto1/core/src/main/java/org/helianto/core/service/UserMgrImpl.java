package org.helianto.core.service;

import java.util.List;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.Credential;
import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.InternalEnumerator;
import org.helianto.core.User;
import org.helianto.core.UserGroup;
import org.helianto.core.creation.AuthenticationCreator;
import org.helianto.core.creation.AuthorizationCreator;
import org.helianto.core.dao.AuthenticationDao;
import org.helianto.core.dao.AuthorizationDao;
import org.helianto.core.dao.IdentityFilter;
import org.helianto.core.dao.IdentitySelectionStrategy;
import org.helianto.core.hibernate.DefaultIdentitySelectionStrategy;
import org.helianto.core.security.PublicUserDetailsSwitcher;
import org.helianto.core.type.ActivityState;

/**
 * Default <code>UserMgr</code> implementation.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class UserMgrImpl implements UserMgr, CoreMgr {
	
    protected AuthenticationDao authenticationDao;
    
    protected AuthorizationDao authorizationDao;
    
    private IdentitySelectionStrategy identitySelectionStrategy;
    
	/* 
	 * Create and persist Identity
	 */

    public Identity createEmptyIdentity() {
		return AuthenticationCreator.identityFactory("", "");
	}

	public void persistIdentity(Identity identity) {
        authenticationDao.persistIdentity(identity);
	}
    
    public List<Identity> findIdentities(IdentityFilter filter) {
        String criteria = identitySelectionStrategy.createCriteriaAsString(filter);
        List<Identity> identityList = authenticationDao.findIdentityByCriteria(criteria);
        return identityList ;
    }

	/* 
	 * Create and persist Credential
	 */

    public Credential createCredential(Identity identity) {
        return AuthenticationCreator.credentialFactory(identity, "empty");
    }

    public Credential createCredentialAndIdentity() {
        Identity identity = createEmptyIdentity();
        return createCredential(identity);
    }

	public void persistCredential(Credential credential) {
        authenticationDao.persistCredential(credential);
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
    
    public boolean switchAuthorizedUser(PublicUserDetailsSwitcher secureUser, String entityAlias) {
        //FIXME
//        if (!(secureUser.getUsers().size() > 1)) {
//            return false;
//        }
//        User newUser = null;    /**
//        for (User u: secureUser.getUsers()) {
//             if (u.getEntity().getAlias().compareTo(entityAlias)==0) {
//                 newUser = u;
//             }
//        }
//        if (newUser==null) {
//            throw new IllegalArgumentException("Unable to change to entity " +
//                    entityAlias+": there is no corresponding user for " +
//                    "credential "+secureUser.getUser().getIdentity().getPrincipal());
//        } 
//        secureUser.setUser(userDao.createAndPersistUserLog(newUser));
        return true;
    }
    
    public long findNextInternalNumber(Entity entity, String typeName) {
        InternalEnumerator enumerator = authorizationDao.findInternalEnumerator(entity, typeName);
        if (enumerator!=null) {
            long lastNumber = enumerator.getLastNumber();
            enumerator.setLastNumber(lastNumber+1);
            authorizationDao.persistInternalEnumerator(enumerator);
            return lastNumber;
        } else  {
            enumerator = new InternalEnumerator();
            enumerator.setEntity(entity);
            enumerator.setTypeName(typeName);
            enumerator.setLastNumber(2);    
            authorizationDao.persistInternalEnumerator(enumerator);
            return 1;
        }
    }
    
    //
    
    public void init() {
        if (authenticationDao==null) throw new IllegalArgumentException("AuthenticationDao property required");
        if (authorizationDao==null) throw new IllegalArgumentException("AuthorizationDao property required");
        if (identitySelectionStrategy==null) {
            identitySelectionStrategy = new DefaultIdentitySelectionStrategy();
        }
    }
    
    //~ collaborators

    private final Log logger = LogFactory.getLog(SecurityMgrImpl.class);

    public void setAuthenticationDao(AuthenticationDao authenticationDao) {
        this.authenticationDao = authenticationDao;
    }

    public void setAuthorizationDao(AuthorizationDao authorizationDao) {
        this.authorizationDao = authorizationDao;
    }

    public void setIdentitySelectionStrategy(
            IdentitySelectionStrategy identitySelectionStrategy) {
        this.identitySelectionStrategy = identitySelectionStrategy;
    }

}
