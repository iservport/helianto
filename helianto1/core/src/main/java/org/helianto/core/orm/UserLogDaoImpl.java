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

import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.UserLog;
import org.helianto.core.dao.UserLogDao;
import org.helianto.core.hibernate.GenericDaoImpl;
import org.springframework.util.Assert;
/**
 * Default implementation of <code>UserLog</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
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


    public List<UserLog> findUserLogByUser(User user) {
        if (logger.isDebugEnabled()) {
            logger.debug("Finding userLog list with user='"+user);
        }
        return (ArrayList<UserLog>) find(USERLOG_USER_QRY, user);
    }
    
    public UserLog findLastUserLog(Identity requiredIdentity) {
        Assert.notNull(requiredIdentity);
        if (requiredIdentity.getLastLogin() != null) {
            return (UserLog) findUnique(LASTUSERLOG_QRY, requiredIdentity, 
                    requiredIdentity.getLastLogin());
        }
        return null;
    }

    static final String LASTUSERLOG_QRY = "select userLog from UserLog userLog "
            + "where userLog.user.identity = ? " + "and userLog.lastEvent = ? ";

}
