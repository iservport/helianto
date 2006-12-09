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

package org.helianto.core.creation;

import java.util.Date;

import junit.framework.TestCase;

import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.Service;
import org.helianto.core.User;
import org.helianto.core.UserAssociation;
import org.helianto.core.UserGroup;
import org.helianto.core.UserLog;
import org.helianto.core.UserRole;
import org.helianto.core.type.ActivityState;
import org.helianto.core.type.UserEventType;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class AuthorizationCreatorTests extends TestCase {

    public void testUserFactoryEntityIdentity() {
        Entity entity = new Entity();
        Identity identity = new Identity();
        User user = AuthorizationCreator.userFactory(entity, identity);
        
        assertSame(entity, user.getEntity());
        assertSame(identity, user.getIdentity());
        assertTrue(user.getIdentity().getUsers().contains(user));
        assertEquals(ActivityState.ACTIVE.getValue(), user.getUserState());
        assertEquals(0, user.getParents().size());
        assertEquals(0, user.getRoles().size());
    }

    public void testUserFactoryEntityIdentityDefaults() {
        Entity entity = new Entity();
        User user = AuthorizationCreator.userFactory(entity, null);
        
        assertSame(entity, user.getEntity());
        assertEquals("", user.getIdentity().getPrincipal());
        assertEquals("", user.getIdentity().getOptionalAlias());
    }

    public void testUserFactoryUserGroupIdentity() {
        UserGroup parent = new UserGroup();
        parent.setEntity(new Entity());
        Identity identity = new Identity();
        User user = AuthorizationCreator.userFactory(parent, identity);

        assertSame(parent.getEntity(), user.getEntity());
        UserAssociation association = user.getParents().iterator().next();
        assertSame(parent, association.getParent());
        assertSame(user, association.getChild());
        assertSame(identity, user.getIdentity());
    }

    public void testUserGroupFactoryEntityIdentity() {
        Entity entity = new Entity();
        Identity identity = new Identity();
        UserGroup userGroup = AuthorizationCreator.userGroupFactory(entity, identity);
        
        assertSame(entity, userGroup.getEntity());
        assertSame(identity, userGroup.getIdentity());
    }

    public void testUserGroupFactoryUserGroupIdentity() {
        Identity identity = new Identity();
        UserGroup parent = new UserGroup();
        parent.setEntity(new Entity());
        UserGroup userGroup = AuthorizationCreator.userGroupFactory(parent, identity);
        
        UserAssociation association = userGroup.getParents().iterator().next();
        assertSame(parent, association.getParent());
        assertSame(userGroup, association.getChild());
        assertSame(parent.getEntity(), userGroup.getEntity());
        assertSame(identity, userGroup.getIdentity());

    }
    
    public void testCreateUserAssociation() {
        UserGroup parent = new UserGroup();
        UserGroup user = new UserGroup();
        AuthorizationCreator.createUserAssociation(parent, user);
        assertSame(parent, user.getParents().iterator().next().getParent());
    }

    public void testUserFactoryError() {
        Entity entity = null;
        try {
            AuthorizationCreator.userFactory(entity, null); fail();
        } catch (IllegalArgumentException e) {
        } catch (Exception e) { fail(); }
        UserGroup parent = null;
        try {
            AuthorizationCreator.userFactory(parent, null); fail();
        } catch (IllegalArgumentException e) {
        } catch (Exception e) { fail(); }
    }

    public void testUserLogFactory() {
        User user = new User();
        Date date = new Date();
        UserLog userLog = AuthorizationCreator.userLogFactory(user, UserEventType.LOGOUT_TIMEOUT, date);
        
        assertSame(user, userLog.getUser());
        assertSame(date, userLog.getLastEvent());
        assertEquals(UserEventType.LOGOUT_TIMEOUT.getValue(), userLog.getEventType());
    }

    public void testUserLogFactoryNullDate() {
        // TODO use currentDate()
        User user = AuthorizationCreator.userFactory(new Entity(), new Identity());
        Date date = new Date();
        UserLog userLog = AuthorizationCreator.userLogFactory(user, UserEventType.LOGIN_FAILURE, null);
        assertSame(user, userLog.getUser());
        assertTrue(Math.abs(date.getTime() - userLog.getLastEvent().getTime()) < 1000);
    }
    
    public void testUserLogFactoryError() {
        try {
            AuthorizationCreator.userLogFactory(null, null, null); fail();
        } catch (IllegalArgumentException e) {
        } catch (Exception e) { fail(); }
        try {
            AuthorizationCreator.userLogFactory(new User(), null, null); fail();
        } catch (IllegalArgumentException e) {
        } catch (Exception e) { fail(); }
        try {
            AuthorizationCreator.userLogFactory(null, UserEventType.LOGIN_FAILURE, null); fail();
        } catch (IllegalArgumentException e) {
        } catch (Exception e) { fail(); }
    }

    /*
     * Test method for 'org.helianto.core.creation.AuthorizationCreator.userRoleFactory(User, Service, String)'
     */
    public void testUserRoleFactory() {
        Service service = new Service();
        User user = new User();
        UserRole userRole = AuthorizationCreator.userRoleFactory(user, service, "EXT");
        
        assertSame(service, userRole.getService());
        assertSame(user, userRole.getUserGroup());
        assertEquals("EXT", userRole.getServiceExtension());
        assertTrue(user.getRoles().contains(userRole));
    }

    public void testUserRoleFactoryError() {
        try {
            AuthorizationCreator.userRoleFactory(null, new Service(), ""); fail();
        } catch (IllegalArgumentException e) {
        } catch (Exception e) { fail(); }
        try {
            AuthorizationCreator.userRoleFactory(new User(), null, ""); fail();
        } catch (IllegalArgumentException e) {
        } catch (Exception e) { fail(); }
        try {
            AuthorizationCreator.userRoleFactory(new User(), new Service(), null);
        } catch (Exception e) { fail(); }
    }
    
}
