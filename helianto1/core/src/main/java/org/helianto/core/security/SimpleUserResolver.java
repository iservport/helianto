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

import java.util.Date;

import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.UserGroup;
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
    
    /**
     * Load or create an <code>User</code>
     * 
     * @param identity
     * @return a valid User
     */
	public User loadOrCreateUser(Identity identity) {
        User user = null;
        UserLog userLog = securityMgr.findLastUserLog(identity);
        if (userLog==null) {
            if (logger.isDebugEnabled()) {
                logger.debug("First login");
            }
            user = guessOrCreateUser(identity);
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("Last login "+userLog.getLastEvent());
            }
            user = userLog.getUser();
        }
        securityMgr.writeUserLog(user, new Date());
        return user;
	}
    
    /**
     * Take the first available matching <code>User</code>.
     * 
     */
    final User guessOrCreateUser(Identity identity) {
        for (UserGroup u: identity.getUsers()) {
            if (u instanceof User) {
                if (logger.isDebugEnabled()) {
                    logger.debug("First available user from identity "+identity+" selected");
                }
                return (User) u;
            }
        }
        if (securityMgr.isAutoCreateEnabled()) {
            return securityMgr.autoCreateUser(identity);
        }
        throw new UsernameNotFoundException("No User defined for Credential with principal: ");
    }
    
    //- collabs

    @Required
    public void setSecurityMgr(SecurityMgr securityMgr) {
        this.securityMgr = securityMgr;
    }

    private final Log logger = LogFactory.getLog(SimpleUserResolver.class);

}
