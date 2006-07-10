package org.helianto.core.service;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.Credential;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.UserLog;
import org.helianto.core.dao.UserDao;
import org.springframework.util.Assert;

public class SecurityMgrImpl implements SecurityMgr {

    private UserDao userDao;
    
	public Identity findIdentityByPrincipal(String principal) {
		return userDao.findIdentityByPrincipal(principal);
	}

	public Credential findCredentialByIdentity(Identity identity) {
		return userDao.findCredentialByIdentity(identity);
	}

	public UserLog findLastUserLog(Identity identity) {
		return userDao.findLastUserLog(identity);
	}
    
    public void persistUserLog(User user, Date date) {
        Assert.notNull(user.getIdentity());
        UserLog userLog = new UserLog();
        userLog.setUser(user);
        if (date==null) {
            date = new Date();
        }
        userLog.setLastLogin(date);
        user.getIdentity().setLastLogin(date);
        userDao.persistUserLog(userLog);
        userDao.persistIdentity(user.getIdentity());
    }
    
    public boolean isAutoCreateEnabled() {
        return false;
    }
    
    public User autoCreateUser(Identity identity) {
    	// TODO implement auto creation
        return null;
    }
    
    //~ collaborators

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    private final Log logger = LogFactory.getLog(SecurityMgrImpl.class);

}
