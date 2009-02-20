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

package org.helianto.core.orm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.helianto.core.User;
import org.helianto.core.UserGroup;
import org.helianto.core.UserLog;
import org.helianto.core.dao.UserLogDao;
import org.helianto.core.hibernate.GenericDaoImpl;
/**
 * Default implementation of <code>UserLog</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 * @deprecated in favour of DefaultUserLogDao
 */
public class UserLogDaoImpl extends GenericDaoImpl implements UserLogDao {
     
    public void persistUserLog(UserLog userLog) {
        if (logger.isDebugEnabled()) {
            logger.debug("Persisting "+userLog);
        }
        persist(userLog);
    }
    
    public UserLog mergeUserLog(UserLog userLog) {
        if (logger.isDebugEnabled()) {
            logger.debug("Merging "+userLog);
        }
        return (UserLog) merge(userLog);
    }
    
    public void removeUserLog(UserLog userLog) {
        if (logger.isDebugEnabled()) {
            logger.debug("Removing "+userLog);
        }
        remove(userLog);
    }
    
    public UserLog findUserLogByNaturalId(User user, Date lastEvent) {
        if (logger.isDebugEnabled()) {
            logger.debug("Finding unique userLog with user='"+user+"' and lastEvent='"+lastEvent+"' ");
        }
        return (UserLog) findUnique(UserLog.getUserLogNaturalIdQueryString(), user, lastEvent);
    }
    
    
	static String USERLOG_USER_QRY = "select userLog from UserLog userLog "+
	    "where userLog.user = ? ";


    @SuppressWarnings("unchecked")
	public List<UserLog> findUserLogByUser(User user) {
        if (logger.isDebugEnabled()) {
            logger.debug("Finding userLog list with user='"+user);
        }
        return (ArrayList<UserLog>) find(USERLOG_USER_QRY, user);
    }
    
    public UserLog findLastUserLog(List<User> users) {
        if (!users.isEmpty()) {
        	StringBuilder sb = new StringBuilder()
    			.append(LASTUSERLOG_USER_QRY)
    			.append(elements(users))
            	.append("and userLog.lastEvent = ? ");
            return (UserLog) findUnique(sb.toString(), findLastUserLogDate(users));
        }
        return null;
    }

    static final StringBuilder LASTUSERLOG_USER_QRY = 
    	new StringBuilder("select userLog from UserLog userLog ")
    		.append("where userLog.user.id in ");
    
    public Date findLastUserLogDate(List<User> users) {
        if (!users.isEmpty()) {
        	StringBuilder sb = new StringBuilder()
        		.append(LASTUSERLOGDATE_QRY)
        		.append(elements(users));
            return (Date) findUnique(sb.toString());
        }
        throw new IllegalArgumentException("At least one user should be supplied to find the last user log date.");
    }

    static final StringBuilder LASTUSERLOGDATE_QRY = 
    	new StringBuilder("select max(lastUserLog.lastEvent) as lastEvent from UserLog lastUserLog ")
			.append("where lastUserLog.user.id in ");
    
    /**
     * Mimic the elements function from hibernate ...
     * @param users
     * @return (id0, id1, ... idn)
     */
    private String elements(List<User> users) {
    	StringBuilder sb = new StringBuilder("(");
    	for (UserGroup user: users) {
    		sb.append(user.getId()).append(",");
    	}
    	sb.append("0) ");
        if (logger.isDebugEnabled()) {
            logger.debug("User elemets are "+sb.toString());
        }
    	return sb.toString();
    }

}
