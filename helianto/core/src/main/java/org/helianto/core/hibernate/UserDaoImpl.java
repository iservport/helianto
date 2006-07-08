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
	
	/*
	 * Persist, remove and find user.
	 */

    public void persistUser(User user) {
    	Assert.notNull(user);
        merge(user);
    }

    public void removeUser(User user) {
    	Assert.notNull(user);
        remove(user);
    }

    public User findUserByEntityAndIdentity(Entity requiredEntity, Identity requiredIdentity) {
    	Assert.notNull(requiredEntity, "An entity is required");
    	Assert.notNull(requiredIdentity, "An identity is required");
        return (User) findUnique(USER_ENTITY_QRY+USER_IDENTITY_FILTER, requiredEntity, requiredIdentity);
    }
    
    static final String USER_ENTITY_QRY = "from User user " +
        "where user.entity = ? ";

    static final String USER_IDENTITY_FILTER = "and user.identity = ?";

    public List<User> findUserByEntity(Entity entity) {
        return (ArrayList<User>) find(USER_ENTITY_QRY, entity);
    }
    
    /*
     * Persist and find UserLog
     */
    
    public void persistUserLog(UserLog userLog) {
        Assert.notNull(userLog);
        merge(userLog);
    }

    public UserLog findLastUserLog(Identity identity) {
		if (identity.getLastLogin() != null) {
			return (UserLog) findUnique(LASTUSERLOG_QRY, identity, identity
					.getLastLogin());
		}
		return null;
	}

	static final String LASTUSERLOG_QRY = "from UserLog userLog "
			+ "where userLog.user.identity = ? " + "and userLog.lastLogin = ? ";

    public List<UserLog> findUserLogByUser(User user) {
        return (ArrayList<UserLog>) find(USERLOG_QUERY, user);
    }
    
    static final String USERLOG_QUERY = "from UserLog userLog " +
    	"where userLog.user = ? ";

//    public Date findLastIdentityLogDate(String principal) {
//        Date lastLogin = (Date) findUnique(LASTUSERLOGDATE_QUERY, principal);
//        return lastLogin;
//    }
//    
//    static final String LASTUSERLOGDATE_QUERY = "select max(userLog.lastLogin) " +
//        "  from UserLog userLog " +
//        "  where userLog.user.identity.principal = ? ";
//    

	public Date findLastIdentityLogDate(String principal) {
		// TODO Auto-generated method stub
		return null;
	}

}
