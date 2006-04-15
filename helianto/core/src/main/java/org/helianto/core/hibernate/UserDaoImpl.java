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

package org.helianto.core.hibernate;

import java.util.Date;
import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.User;
import org.helianto.core.UserLog;
import org.helianto.core.dao.UserDao;
import org.springframework.dao.DataRetrievalFailureException;

public class UserDaoImpl extends CredentialDaoImpl implements UserDao {

    public void persistUser(User user) {
        merge(user);
    }

    public void removeUser(User user) {
        remove(user);
    }

    public User findUserByEntityAliasAndPrincipal(String alias, String principal) {
        return (User) findUnique(USER_QRY, new Object[] {alias, principal});
    }
    
    static final String USER_QRY = 
        "from User user " +
        "where user.entity.alias = ? " +
        "and user.credential.principal = ?";

    @SuppressWarnings("unchecked")
    public List<User> findUserByEntity(Entity entity) {
        return (List<User>) findUnique(USER_QRY_ENTITY, entity);
    }
    
    static final String USER_QRY_ENTITY = 
        "from User user " +
        "where user.entity = ?";

    /**
     * Subclasses should override this method to provide 
     * <code>User</code> auto-create functionality.
     * 
     * @return null
     */
    public User autoCreateAndPersistUser(String principal) {
        return null;
    }
    
    public UserLog createAndPersistUserLog(User user) {
        return createAndPersistUserLog(user, new Date());
    }
    
    /**
     * Not in interface <code>UserDao</code>, but usefull for tests.
     */
    public UserLog createAndPersistUserLog(User user, Date date) {
        UserLog userLog = new UserLog();
        userLog.setUser(user);
        userLog.setLastLogin(date);
        save(userLog);
        return userLog;
    }
    
    public UserLog findLastUserLog(String principal) {
        try {
            return (UserLog) findUnique(LASTUSERLOG_QUERY, principal);
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.debug("Exception "+e.toString());
            }
            throw new DataRetrievalFailureException("Unable to find UserLogs for principal: "+principal);
        }
    }

    static final String LASTUSERLOG_QUERY = "from UserLog userLog " +
        "where userLog.user.credential.principal = ? " +
        "and userLog.lastLogin = (" +
        "  select max(lastLogin) from UserLog lastUserLog where lastUserLog.id = userLog.id" +
        ")";

}
