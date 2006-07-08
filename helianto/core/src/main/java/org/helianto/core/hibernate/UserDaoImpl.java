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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.UserLog;
import org.helianto.core.dao.UserDao;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.util.Assert;

public class UserDaoImpl extends CredentialDaoImpl implements UserDao {

    public void persistUser(User user) {
        merge(user);
    }

    public void removeUser(User user) {
        remove(user);
    }

    public User findUserByEntityAliasAndPrincipal(String alias, String principal) {
        return (User) findUnique(USER_QRY, alias, principal);
    }
    
    static final String USER_QRY = 
        "from User user " +
        "where user.entity.alias = ? " +
        "and user.identity.principal = ?";

    public List<User> findUserByEntity(Entity entity) {
        return (ArrayList<User>) find(USER_QRY_ENTITY, entity);
    }
    
    static final String USER_QRY_ENTITY = 
        "from User user " +
        "where user.entity = ?";

    public void persistUserLog(UserLog userLog) {
        Assert.notNull(userLog);
        merge(userLog);
    }

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
        merge(userLog);
        return userLog;
    }
    
    public List<UserLog> findUserLogByUser(User user) {
        return (ArrayList<UserLog>) find(USERLOG_QUERY, user);
    }
    
    static final String USERLOG_QUERY = "from UserLog userLog " +
    "  where userLog.user = ? ";

    public Date findLastIdentityLogDate(String principal) {
        Date lastLogin = (Date) findUnique(LASTUSERLOGDATE_QUERY, principal);
        return lastLogin;
    }
    
    static final String LASTUSERLOGDATE_QUERY = "select max(userLog.lastLogin) " +
        "  from UserLog userLog " +
        "  where userLog.user.identity.principal = ? ";
    
    public UserLog findLastUserLog(Identity identity) {
        if (identity.getLastLogin()!=null) {
            return (UserLog) findUnique(LASTUSERLOG_QRY, identity, identity.getLastLogin());
        }
        return null;
    }

    static final String LASTUSERLOG_QRY = "from UserLog userLog " +
        "where userLog.user.identity = ? " +
        "and userLog.lastLogin = ? ";

    @Deprecated
    public UserLog findLastUserLog(String principal) {
        try {
        	Date lastLogin = findLastIdentityLogDate(principal);
            List<UserLog> userLogList = (ArrayList<UserLog>) find(LASTUSERLOGPRINCIPAL_QUERY, principal, lastLogin );
            if (logger.isDebugEnabled()) {
                for (UserLog ul: userLogList) {
                    logger.debug("Found UserLog(s) with principal "+principal+"[" +ul.getUser().getIdentity().getPrincipal()+"]: "+ul.getUser()+" - "+ul.getLastLogin());
                }
            }
            if (userLogList.size()>0) {
                return userLogList.get(0);
            }
            return null; 
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.debug("Exception "+e.toString());
            }
            throw new DataRetrievalFailureException("Unable to find UserLogs for principal: "+principal);
        }
    }
    

    private static final String LASTUSERLOGPRINCIPAL_QUERY = "from UserLog lastUserLog " +
    "  where lastUserLog.user.identity.principal = ? and" +
    "  lastUserLog.lastLogin = ?";

}
