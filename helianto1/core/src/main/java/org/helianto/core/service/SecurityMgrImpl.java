package org.helianto.core.service;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.Credential;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.UserLog;
import org.helianto.core.dao.AuthenticationDao;
import org.helianto.core.dao.AuthorizationDao;
import org.springframework.util.Assert;

public class SecurityMgrImpl implements SecurityMgr {

    private AuthenticationDao authenticationDao;
    
    private AuthorizationDao authorizationDao;
    
	public Identity findIdentityByPrincipal(String principal) {
		return authenticationDao.findIdentityByPrincipal(principal);
	}

	public Credential findCredentialByIdentity(Identity identity) {
		return authenticationDao.findCredentialByIdentity(identity);
	}

	public UserLog findLastUserLog(Identity identity) {
		return authorizationDao.findLastUserLog(identity);
	}
    
    public void persistUserLog(User user, Date date) {
        Assert.notNull(user.getIdentity());
        UserLog userLog = new UserLog();
        userLog.setUser(user);
        if (date==null) {
            date = new Date();
        }
        userLog.setLastEvent(date);
        user.getIdentity().setLastLogin(date);
        authorizationDao.persistUserLog(userLog);
        authenticationDao.persistIdentity(user.getIdentity());
    }
    
    public boolean isAutoCreateEnabled() {
        return false;
    }
    
    public User autoCreateUser(Identity identity) {
    	// TODO implement auto creation
        return null;
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
