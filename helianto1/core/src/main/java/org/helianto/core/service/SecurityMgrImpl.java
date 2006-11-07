package org.helianto.core.service;

import java.util.Date;

import org.helianto.core.Credential;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.UserLog;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.core.security.UserDetailsAdapter;
import org.helianto.core.type.ActivityState;
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

    public boolean verifyPassword(Credential credential) {
        if (credential.getPassword().compareTo(credential.getVerifyPassword())!=0) {
            credential.setPassword("");
            credential.setVerifyPassword("");
            credential.setPasswordDirty(true);
            credential.setCredentialState(ActivityState.SUSPENDED.getValue());
            return false;
        }
        credential.setVerifyPassword("");
        credential.setPasswordDirty(false);
        credential.setCredentialState(ActivityState.ACTIVE.getValue());
        return true;
    }
    
    public PublicUserDetails findSecureUser() {
        return UserDetailsAdapter.retrievePublicUserDetailsFromSecurityContext();
    }
    
}
