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

package org.helianto.core.dao;

import java.util.Date;
import java.util.List;

import org.helianto.core.User;
import org.helianto.core.UserLog;

/**
 * <code>UserLog</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface UserLogDao extends CommonOrmDao {
     
    /**
     * Persist <code>UserLog</code>.
     */
    public void persistUserLog(UserLog userLog);
    
    /**
     * Merge <code>UserLog</code>.
     */
    public UserLog mergeUserLog(UserLog userLog);
    
    /**
     * Remove <code>UserLog</code>.
     */
    public void removeUserLog(UserLog userLog);
    
    /**
     * Find <code>UserLog</code> by <code>User</code> and lastEvent.
     */
    public UserLog findUserLogByNaturalId(User user, Date lastEvent);
    
    /**
     * Find <code>UserLog</code> by <code>User</code>.
     */
    public List<UserLog> findUserLogByUser(User user);

    /**
     * Find last <code>UserLog</code> by <code>User</code> list.
     */
    public UserLog findLastUserLog(List<User> users);
    
    
    
}
