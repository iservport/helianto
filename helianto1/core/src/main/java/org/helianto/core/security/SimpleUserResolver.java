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

package org.helianto.core.security;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.UserLog;
import org.helianto.core.service.SecurityMgr;
import org.springframework.beans.factory.annotation.Required;

/**
 * Strategy to resolve an <code>User</code>.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class SimpleUserResolver implements UserResolutionStrategy {

    private SecurityMgr securityMgr;
    
	public List<User> loadUsers(Identity identity) {
        return securityMgr.findUsers(getUserFromIdentityMatchSelectionCriteria(identity));
	}
    
	public User selectUserFromPreviousLogin(List<User> users) {
        UserLog userLog = securityMgr.findLastUserLog(users);
        if (userLog!=null) {
            if (logger.isDebugEnabled()) {
                logger.debug("Selected user matching the last login "+userLog.getLastEvent());
            }
            return userLog.getUser();
        } 
        logger.warn("User has never logged in!");
        return null;
	}
    
	public User selectUserIfAny(List<User> users) {
        for (User user: users) {
            if (logger.isDebugEnabled()) {
                logger.debug("First available user selected");
            }
            return user;
        }
        return null;
	}

	public User createUser(Identity identity) {
        if (securityMgr.isAutoCreateEnabled()) {
            return securityMgr.autoCreateUser(identity);
        }
        return null;
	}

    protected String getUserFromIdentityMatchSelectionCriteria(Identity identity) {
    	return new StringBuilder()
    	.append("user.identity.id = ")
    	.append(identity.getId())
    	.append(" ")
    	.toString();
    }
    
    //- collabs

    @Required
    public void setSecurityMgr(SecurityMgr securityMgr) {
        this.securityMgr = securityMgr;
    }

    private final Log logger = LogFactory.getLog(SimpleUserResolver.class);

}
