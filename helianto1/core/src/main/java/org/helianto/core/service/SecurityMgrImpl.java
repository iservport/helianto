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

import java.util.Date;

import org.helianto.core.ActivityState;
import org.helianto.core.Credential;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.UserLog;
import org.helianto.core.dao.CredentialDao;
import org.helianto.core.dao.UserLogDao;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.core.security.UserDetailsAdapter;
import org.springframework.util.Assert;

/**
 * Default implementatio for <code>SecurityMgr</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class SecurityMgrImpl extends UserMgrImpl implements SecurityMgr {
    
    private UserLogDao userLogDao;
    private CredentialDao credentialDao;

	public Identity findIdentityByPrincipal(String principal) {
		return authenticationDao.findIdentityByPrincipal(principal);
	}

	public Credential findCredentialByIdentity(Identity identity) {
		return credentialDao.findCredentialByNaturalId(identity);
	}

	public UserLog findLastUserLog(Identity identity) {
		return userLogDao.findLastUserLog(identity);
	}
    
    public void persistUserLog(User user, Date date) {
        Assert.notNull(user.getIdentity());
        if (date==null) {
            date = new Date();
        }
        UserLog userLog = UserLog.userLogFactory(user, date);
        userLogDao.persistUserLog(userLog);
        authenticationDao.persistIdentity(user.getIdentity());
    }
    
    public void writeUserLog(User user, Date date) {
        Assert.notNull(user.getIdentity());
        if (date==null) {
            date = new Date();
        }
        UserLog userLog = UserLog.userLogFactory(user, date);
        userLogDao.mergeUserLog(userLog);
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

    public void setUserLogDao(UserLogDao userLogDao) {
        this.userLogDao = userLogDao;
    }
    
    public void setCredentialDao(CredentialDao credentialDao) {
        this.credentialDao = credentialDao;
    }
    
}
