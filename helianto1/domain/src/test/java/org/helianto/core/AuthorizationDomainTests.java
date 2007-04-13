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

import java.util.HashSet;

import junit.framework.TestCase;

import org.helianto.core.test.DomainTestSupport;
import org.helianto.core.type.ActivityState;
import org.helianto.core.type.UserType;

public class AuthorizationDomainTests extends TestCase {

    public void testUserGroup() {
        UserGroup userGroup = new UserGroup();
        userGroup.setId(Long.MAX_VALUE);
        userGroup.setId(Long.MIN_VALUE);

        userGroup.setEntity(new Entity());

        userGroup.setIdentity(new Identity());

        userGroup.setParents(new HashSet<UserAssociation>());
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
    
    public void testUserAssociation() {
        UserAssociation association = new UserAssociation();
        
        association.setId(Long.MAX_VALUE);
        association.setId(Long.MIN_VALUE);

        association.setParent(new User());
        association.setParent(new UserGroup());
        association.setChild(new User());
        association.setChild(new UserGroup());
    }

    public void testUserAssociationEquals() {
        UserAssociation copy, association = new UserAssociation();
        association.setParent(new User());
        association.setChild(new User());
        copy = (UserAssociation) DomainTestSupport.minimalEqualsTest(association);

        copy.setParent(association.getParent());
        assertFalse(association.equals(copy));

        copy.setParent(null);
        copy.setChild(association.getChild());
        assertFalse(association.equals(copy));

        copy.setParent(association.getParent());
        copy.setChild(association.getChild());
        assertTrue(association.equals(copy));
    }

}
