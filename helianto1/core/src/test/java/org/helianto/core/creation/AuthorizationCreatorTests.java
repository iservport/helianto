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

import junit.framework.TestCase;

import org.helianto.core.ActivityState;
import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.UserAssociation;
import org.helianto.core.UserGroup;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
@Deprecated
public class AuthorizationCreatorTests extends TestCase {

    public void testUserFactoryEntityIdentity() {
        Entity entity = new Entity();
        Identity identity = new Identity();
        User user = User.userFactory(entity, identity);
        
        assertSame(entity, user.getEntity());
        assertSame(identity, user.getIdentity());
        assertEquals(ActivityState.ACTIVE.getValue(), user.getUserState());
        assertEquals(0, user.getParentAssociations().size());
        assertEquals(0, user.getRoles().size());
    }

    public void testUserFactoryUserGroupIdentity() {
        UserGroup parent = new UserGroup();
        parent.setEntity(new Entity());
        Identity identity = new Identity();
        User user = User.userFactory(parent, identity);

        assertSame(parent.getEntity(), user.getEntity());
        UserAssociation association = user.getParentAssociations().iterator().next();
        assertSame(parent, association.getParent());
        assertSame(user, association.getChild());
        assertSame(identity, user.getIdentity());
    }

    public void testUserGroupFactoryEntityIdentity() {
        Entity entity = new Entity();
        Identity identity = new Identity();
        UserGroup userGroup = UserGroup.userGroupFactory(entity, identity);
        
        assertSame(entity, userGroup.getEntity());
        assertSame(identity, userGroup.getIdentity());
    }

    public void testUserGroupFactoryUserGroupIdentity() {
        Identity identity = new Identity();
        UserGroup parent = new UserGroup();
        parent.setEntity(new Entity());
        UserGroup userGroup = UserGroup.userGroupFactory(parent, identity);
        
        UserAssociation association = userGroup.getParentAssociations().iterator().next();
        assertSame(parent, association.getParent());
        assertSame(userGroup, association.getChild());
        assertSame(parent.getEntity(), userGroup.getEntity());
        assertSame(identity, userGroup.getIdentity());

    }
    
}
