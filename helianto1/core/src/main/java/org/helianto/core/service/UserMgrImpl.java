package org.helianto.core.service;

import java.util.List;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.Credential;
import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.UserGroup;
import org.helianto.core.creation.AuthenticationCreator;
import org.helianto.core.creation.AuthorizationCreator;
import org.helianto.core.dao.AuthenticationDao;
import org.helianto.core.dao.AuthorizationDao;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.core.security.PublicUserDetailsSwitcher;
import org.helianto.core.security.UserDetailsAdapter;

public class UserMgrImpl implements UserMgr {
	
    protected AuthenticationDao authenticationDao;
    
    protected AuthorizationDao authorizationDao;
    
	/* 
	 * Create and persist Identity
	 */

	public Identity createIdentity() {
		return AuthenticationCreator.identityFactory("", "");
	}

	public void persistIdentity(Identity identity) {
		// TODO Auto-generated method stub
		
	}

	/* 
	 * Create and persist Credential
	 */

	public Credential createCredential(Identity identity) {
		return AuthenticationCreator.credentialFactory(identity, "empty");
	}

	public void persistCredential(Credential credential) {
		// TODO Auto-generated method stub
		
	}

	/* 
	 * Create and persist User
	 */

	public User createUser(Entity entity) {
        Identity identity = createIdentity();
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

    public PublicUserDetails findSecureUser() {
        return UserDetailsAdapter.retrievePublicUserDetailsFromSecurityContext();
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
    
    //~ collaborators

    private final Log logger = LogFactory.getLog(SecurityMgrImpl.class);

    public void setAuthenticationDao(AuthenticationDao authenticationDao) {
        this.authenticationDao = authenticationDao;
    }

    public void setAuthorizationDao(AuthorizationDao authorizationDao) {
        this.authorizationDao = authorizationDao;
    }

}
