package org.helianto.core.service;

import java.util.List;
import java.util.Locale;

import org.helianto.core.Credential;
import org.helianto.core.Entity;
import org.helianto.core.Home;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.creation.UserCreator;
import org.helianto.core.dao.UserDao;

public class UserMgrImpl implements UserMgr {
	
	private UserDao userDao;
	
	/* 
	 * Criar e persistir identidade
	 */

	public Identity createIdentity() {
		return UserCreator.identityFactory("", "");
	}

	public void persistIdentity(Identity identity) {
		// TODO Auto-generated method stub
		
	}

	/* 
	 * Criar e persistir credencial
	 */

	public Credential createCredential(Identity identity) {
		return UserCreator.credentialFactory(identity);
	}

	public void persistCredential(Credential credential) {
		// TODO Auto-generated method stub
		
	}

	/* 
	 * Criar e persistir usuário
	 */

	public User createUser(Entity entity) {
        Identity identity = createIdentity();
        return createUser(identity, entity);
    }
    
    public User createUser(Identity identity, Entity entity) {
        return UserCreator.userFactory(entity, identity);
    }

    public void persistUser(User user) {
        String principal = user.getIdentity().getPrincipal();
        Locale locale = getLocale(user.getEntity().getHome());
        user.getIdentity().setPrincipal(
                convertToLowerCase(locale, principal));
        userDao.persistUser(user);
    }
    
    public List<User> findUserByEntity(Entity entity) {
        return userDao.findUserByEntity(entity);
    }
    
    /**
     * Helper method to find home <code>Locale</code>.
     */
    static Locale getLocale(Home home) {
        Locale locale = null;
        try {
            locale = home.getLocale();
        } catch (Exception e) {
            locale = Locale.getDefault();
        }
        return locale;
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

    //~ collaborators

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
