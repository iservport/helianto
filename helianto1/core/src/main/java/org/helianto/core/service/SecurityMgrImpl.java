package org.helianto.core.service;

import java.util.Date;

import org.helianto.core.Credential;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.UserLog;
import org.springframework.util.Assert;

public class SecurityMgrImpl extends UserMgrImpl implements SecurityMgr {

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
    
}
