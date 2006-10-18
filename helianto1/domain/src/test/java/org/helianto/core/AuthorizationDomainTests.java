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

package org.helianto.core;

import java.util.Date;

import org.helianto.core.test.DomainTestSupport;
import org.helianto.core.type.ActivityState;
import org.helianto.core.type.UserEventType;
import org.helianto.core.type.UserType;

import junit.framework.TestCase;

public class AuthorizationDomainTests extends TestCase {

    public void testUserGroup() {
        UserGroup userGroup = new UserGroup();
        userGroup.setId(Long.MAX_VALUE);
        userGroup.setId(Long.MIN_VALUE);

        userGroup.setEntity(new Entity());

        userGroup.setIdentity(new Identity());

        userGroup.setParent(new User());
        
        userGroup.getChildren().add(new UserGroup());
        
        userGroup.setUserState(ActivityState.ACTIVE.getValue());
        userGroup.setUserState(ActivityState.CANCELLED.getValue());
        userGroup.setUserState(ActivityState.INITIAL.getValue());
        userGroup.setUserState(ActivityState.SUSPENDED.getValue());

        userGroup.getRoles().add(new UserRole());
    }

    public void testUserGroupEquals() {
        UserGroup copy, userGroup = new UserGroup();
        userGroup.setEntity(new Entity());
        userGroup.setIdentity(new Identity());
        copy = (UserGroup) DomainTestSupport.minimalEqualsTest(userGroup);

        copy.setEntity(userGroup.getEntity());
        assertFalse(userGroup.equals(copy));

        copy.setEntity(null);
        copy.setIdentity(userGroup.getIdentity());
        assertFalse(userGroup.equals(copy));

        copy.setEntity(userGroup.getEntity());
        copy.setIdentity(userGroup.getIdentity());
        assertTrue(userGroup.equals(copy));
    }

    public void testUser() {
        User user = new User();

        user.setUserType(UserType.ADMINISTRATOR.getValue());
        user.setUserType(UserType.EXTERNAL.getValue());
        user.setUserType(UserType.INTERNAL.getValue());
        user.setUserType(UserType.MODERATOR.getValue());

        user.setAccountNonExpired(true);
        user.setAccountNonExpired(false);
    }

    public void testUserLog() {
        UserLog userLog = new UserLog();
        userLog.setId(Long.MAX_VALUE);
        userLog.setId(Long.MIN_VALUE);
        
        userLog.setUser(new User());
        
        userLog.setLastEvent(new Date());
        
        userLog.setEventType(UserEventType.LOGIN_FAILURE.getValue());
        userLog.setEventType(UserEventType.LOGIN_SUCCESS.getValue());
        userLog.setEventType(UserEventType.LOGOUT_SUCCESS.getValue());
        userLog.setEventType(UserEventType.LOGOUT_TIMEOUT.getValue());
    }

    public void testUserLogEquals() {
        UserLog copy, userLog = new UserLog();
        userLog.setUser(new User());
        userLog.setLastEvent(new Date());
        copy = (UserLog) DomainTestSupport.minimalEqualsTest(userLog);

        copy.setUser(userLog.getUser());
        assertFalse(userLog.equals(copy));

        copy.setUser(null);
        copy.setLastEvent(userLog.getLastEvent());
        assertFalse(userLog.equals(copy));

        copy.setUser(userLog.getUser());
        copy.setLastEvent(userLog.getLastEvent());
        assertTrue(userLog.equals(copy));
    }

    public void testUserRole() {
        UserRole userRole = new UserRole();
        userRole.setId(Long.MAX_VALUE);
        userRole.setId(Long.MIN_VALUE);
        
        userRole.setUser(new User());
        
        userRole.setService(new Service());
        
        userRole.setServiceExtension("");
    }

    public void testUserRoleEquals() {
        UserRole copy, userRole = new UserRole();
        userRole.setUser(new User());
        userRole.setService(new Service());
        copy = (UserRole) DomainTestSupport.minimalEqualsTest(userRole);

        copy.setUser(userRole.getUser());
        assertFalse(userRole.equals(copy));

        copy.setUser(null);
        copy.setService(userRole.getService());
        assertFalse(userRole.equals(copy));

        copy.setUser(userRole.getUser());
        copy.setService(userRole.getService());
        assertTrue(userRole.equals(copy));
    }

}