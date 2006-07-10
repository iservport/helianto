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

import org.helianto.core.Credential;
import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.PersonalData;
import org.helianto.core.User;
import org.helianto.core.UserGroup;
import org.helianto.core.UserLog;
import org.helianto.core.type.Appellation;
import org.helianto.core.type.CredentialState;
import org.helianto.core.type.CredentialType;
import org.helianto.core.type.Gender;
import org.helianto.core.type.IdentityType;
import org.helianto.core.type.Notification;
import org.helianto.core.type.UserType;

public class UserCreatorTests extends TestCase {
    
    public void testPersonalDataFactory() {
        PersonalData personalData = 
            UserCreator.personalDataFactory();
        assertEquals("", personalData.getFirstName());
        assertEquals("", personalData.getLastName());
        assertEquals(Appellation.NOT_SUPPLIED.getValue(),
                personalData.getAppellation());
        assertEquals(Gender.NOT_SUPPLIED.getValue(),
                personalData.getGender());
    }

    public void testIdentityFactory() {
        Identity identity = UserCreator.identityFactory("PRINCIPAL", "OPTIONAL_ALIAS");
        assertEquals("PRINCIPAL", identity.getPrincipal());
        assertEquals("OPTIONAL_ALIAS", identity.getOptionalAlias());
        assertTrue(identity.getCreated().compareTo(new Date()) < 1000);
        assertEquals(CredentialType.NOT_ADDRESSABLE.getValue(), identity.getIdentityType());
        assertEquals(Notification.BY_REQUEST.getValue(), identity.getNotification());
        assertEquals(Appellation.NOT_SUPPLIED.getValue(),
                identity.getPersonalData().getAppellation());
        assertEquals(Gender.NOT_SUPPLIED.getValue(),
                identity.getPersonalData().getGender());
        assertEquals(0, identity.getUsers().size());
    }

    public void testCredentialFactoryError() {
        try {
            UserCreator.credentialFactory(null); fail();
        } catch (IllegalArgumentException e) {}
    }

    public void testCredentialFactory() {
        Identity identity = UserCreator.identityFactory("PRINCIPAL", "OPTIONAL_ALIAS");
        Credential credential = UserCreator.credentialFactory(identity);
        assertSame(identity, credential.getIdentity());
        assertEquals(8, credential.getPassword().length());
        assertEquals("", credential.getVerifyPassword());
        assertTrue(credential.getLastModified().compareTo(new Date()) < 1000);
        assertNull(credential.getExpired());
        assertEquals(CredentialState.IDLE.getValue(), credential.getCredentialState());
    }
    
    public void testUserGroupFactoryError() {
        try {
            UserCreator.userGroupFactory(null, ""); fail();
        } catch (IllegalArgumentException e) {}
    }

    public void testUserGroupFactory() {
        Entity entity = new Entity();
        UserGroup userGroup = UserCreator.userGroupFactory(entity, "PRINCIPAL");
        assertSame(entity, userGroup.getEntity());
        assertSame("PRINCIPAL", userGroup.getIdentity().getPrincipal());
        assertSame(IdentityType.GROUP.getValue(), userGroup.getIdentity().getIdentityType());
        assertNull(userGroup.getParent());
        assertEquals(0, userGroup.getRoles().size());
    }
    
    public void testUserFactoryError() {
        try {
            UserCreator.userFactory(new Entity(), null); fail();
        } catch (IllegalArgumentException e) {}
        try {
            UserCreator.userFactory(new UserGroup(), null); fail();
        } catch (IllegalArgumentException e) {}
    }

    public void testUserFactory() {
        Identity identity = UserCreator.identityFactory("PRINCIPAL", "OPTIONAL_ALIAS");
        Entity entity = new Entity();
        User user = UserCreator.userFactory(entity, identity);
        assertSame(entity, user.getEntity());
        assertSame(identity, user.getIdentity());
        assertNull(user.getParent());
        assertEquals(0, user.getRoles().size());
        assertEquals(UserType.INTERNAL.getValue(), user.getUserType());
        assertTrue(user.getIdentity().getUsers().contains(user));
    }
    
    public void testDerivedUserFactory() {
        Identity identity = UserCreator.identityFactory("PRINCIPAL", "OPTIONAL_ALIAS");
        Entity entity = new Entity();
        UserGroup userGroup = UserCreator.userGroupFactory(entity, "GROUP");
        User user = UserCreator.userFactory(userGroup, identity);
        assertSame(entity, user.getEntity());
        assertEquals(userGroup, user.getParent());
    }
    
    public void testGeneratePassword() {
        String password = UserCreator.generatePassword(8);
        assertEquals(8, password.length());
    }
    
    public void testUserLogFactoryError() {
        try {
            UserCreator.userLogFactory(null, null); fail();
        } catch (IllegalArgumentException e) {}
        try {
            UserCreator.userLogFactory(null, new Date()); fail();
        } catch (IllegalArgumentException e) {}
    }

    public void testUserLogFactory() {
        Identity identity = UserCreator.identityFactory("PRINCIPAL", "OPTIONAL_ALIAS");
        Entity entity = new Entity();
        User user = UserCreator.userFactory(entity, identity);
        Date date = new Date();
        UserLog userLog = UserCreator.userLogFactory(user, date);
        assertSame(user, userLog.getUser());
        assertSame(date, userLog.getLastLogin());
    }

    public void testUserLogFactoryNullDate() {
        Identity identity = UserCreator.identityFactory("PRINCIPAL", "OPTIONAL_ALIAS");
        Entity entity = new Entity();
        User user = UserCreator.userFactory(entity, identity);
        Date date = new Date();
        UserLog userLog = UserCreator.userLogFactory(user, null);
        assertSame(user, userLog.getUser());
        assertTrue(Math.abs(date.getTime() - userLog.getLastLogin().getTime()) < 1000);
    }
    
}
