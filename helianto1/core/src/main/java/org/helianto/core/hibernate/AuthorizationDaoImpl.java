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
import org.helianto.core.Service;
import org.helianto.core.User;
import org.helianto.core.UserGroup;
import org.helianto.core.UserLog;
import org.helianto.core.UserRole;
import org.helianto.core.dao.AuthorizationDao;
import org.springframework.util.Assert;

public class AuthorizationDaoImpl extends GenericDaoImpl implements AuthorizationDao  {

    //userGroup
    
    public void persistUserGroup(UserGroup userGroup) {
        persist(userGroup);
    }
    
    public UserGroup mergeUserGroup(UserGroup userGroup) {
        return (UserGroup) merge(userGroup);
    }
    
    public void removeUserGroup(UserGroup userGroup) {
        remove(userGroup);
    }
    
    public UserGroup findUserGroupByNaturalId(Entity entity, Identity identity) {
        Assert.notNull(entity);
        Assert.notNull(identity);
        return (UserGroup) findUnique(USERGROUP_QRY, entity, identity);
    }
    
    static String USERGROUP_QRY = "from UserGroup userGroup "+
        "where userGroup.entity = ? and userGroup.identity = ? ";

    public List<UserGroup> findUserGroupByEntity(Entity entity) {
        Assert.notNull(entity);
        return (ArrayList<UserGroup>) find(USERGROUP_QRY_BY_ENTITY, entity);
    }

    static String USERGROUP_QRY_BY_ENTITY = "from UserGroup userGroup "+
        "where userGroup.entity = ? ";

    public User findUserByNaturalId(Entity requiredEntity, Identity requiredIdentity) {
        Assert.notNull(requiredEntity, "An entity is required");
        Assert.notNull(requiredIdentity, "An identity is required");
        return (User) findUnique(USER_ENTITY_QRY+USER_IDENTITY_FILTER, requiredEntity, requiredIdentity);
    }
    
    static final String USER_ENTITY_QRY = "from User user " +
        "where user.entity = ? ";

    static final String USER_IDENTITY_FILTER = "and user.identity = ?";

    public List<User> findUserByEntity(Entity requiredEntity) {
        Assert.notNull(requiredEntity, "An entity is required");
        return (ArrayList<User>) find(USER_ENTITY_QRY, requiredEntity);
    }
    
    public List<UserGroup> findUserGroupByCriteria(String criteria) {
        return (ArrayList<UserGroup>) find("from UserGroup userGroup "+criteria);
    }
    
    public List<User> findUserByCriteria(String criteria) {
        return (ArrayList<User>) find("from User user where "+criteria);
    }
    
    public void removeUser(User user) {
        Assert.notNull(user);
        remove(user);
    }
    
    // user log

    public void persistUserLog(UserLog userLog) {
        persist(userLog);
    }
    
    public UserLog mergeUserLog(UserLog userLog) {
        return (UserLog) merge(userLog);
    }
    
    public void removeUserLog(UserLog userLog) {
        remove(userLog);
    }
    
    public UserLog findUserLogByNaturalId(User user, Date lastEvent) {
        return (UserLog) findUnique(USERLOG_QRY+LASTEVENT_FILTER, user, lastEvent);
    }
    
    static String USERLOG_QRY = "select userLog from UserLog userLog "+
        "where userLog.user = ? ";

    static String LASTEVENT_FILTER = "and userLog.lastEvent = ? ";

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

    public List<UserLog> findUserLogByUser(User requiredUser) {
        Assert.notNull(requiredUser);
        return (ArrayList<UserLog>) find(USERLOG_QRY, requiredUser);
    }
    
    // user role
    
    public void persistUserRole(UserRole userRole) {
        persist(userRole);
    }

    public UserRole mergeUserRole(UserRole userRole) {
        return (UserRole) merge(userRole);
    }

    public UserRole findUserRoleByNaturalId(UserGroup userGroup, Service service, String serviceExtension) {
        return (UserRole) findUnique(USER_ROLE_QUERY, userGroup, service, serviceExtension);
    }

    static final String USER_ROLE_QUERY = "from UserRole userRole " +
            "where userRole.userGroup = ? " +
            "and userRole.service = ? " +
            "and userRole.serviceExtension = ? ";

    public void removeUserRole(UserRole userRole) {
        remove(userRole);
    }

}
