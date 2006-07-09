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

import org.helianto.core.Credential;
import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.PersonalData;
import org.helianto.core.User;
import org.helianto.core.UserGroup;
import org.helianto.core.UserLog;
import org.helianto.core.creation.Appellation;
import org.helianto.core.creation.CredentialState;
import org.helianto.core.creation.CredentialType;
import org.helianto.core.creation.Gender;
import org.helianto.core.creation.Notification;
import org.helianto.core.creation.UserCreatorImpl;

import junit.framework.TestCase;

public class UserCreatorTests extends TestCase {
    
    public void testPersonalDataFactory() {
        PersonalData personalData = 
            UserCreatorImpl.personalDataFactory();
        assertEquals("", personalData.getFirstName());
        assertEquals("", personalData.getLastName());
        assertEquals(Appellation.NOT_SUPPLIED.getValue(),
                personalData.getAppellation());
        assertEquals(Gender.NOT_SUPPLIED.getValue(),
                personalData.getGender());
    }

    public void testIdentityFactory() {
        Identity identity = UserCreatorImpl.identityFactory("PRINCIPAL", "OPTIONAL_ALIAS");
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
            UserCreatorImpl.credentialFactory(null); fail();
        } catch (IllegalArgumentException e) {}
    }

    public void testCredentialFactory() {
        Identity identity = UserCreatorImpl.identityFactory("PRINCIPAL", "OPTIONAL_ALIAS");
        Credential credential = UserCreatorImpl.credentialFactory(identity);
        assertSame(identity, credential.getIdentity());
        assertEquals(8, credential.getPassword().length());
        assertEquals("", credential.getVerifyPassword());
        assertTrue(credential.getLastModified().compareTo(new Date()) < 1000);
        assertNull(credential.getExpired());
        assertEquals(CredentialState.IDLE.getValue(), credential.getCredentialState());
    }
    
    public void testUserGroupFactoryError() {
        try {
            UserCreatorImpl.userGroupFactory(null, new Identity()); fail();
        } catch (IllegalArgumentException e) {}
        try {
            UserCreatorImpl.userGroupFactory(new Entity(), null); fail();
        } catch (IllegalArgumentException e) {}
    }

    public void testUserGroupFactory() {
        Identity identity = UserCreatorImpl.identityFactory("PRINCIPAL", "OPTIONAL_ALIAS");
        Entity entity = new Entity();
        UserGroup userGroup = UserCreatorImpl.userGroupFactory(entity, identity);
        assertSame(entity, userGroup.getEntity());
        assertSame(identity, userGroup.getIdentity());
        assertNull(userGroup.getParent());
        assertEquals(0, userGroup.getRoles().size());
    }
    
    public void testUserFactoryError() {
        try {
            UserCreatorImpl.userFactory(new Entity(), null); fail();
        } catch (IllegalArgumentException e) {}
        try {
            UserCreatorImpl.userFactory(new UserGroup(), null); fail();
        } catch (IllegalArgumentException e) {}
    }

    public void testUserFactory() {
        Identity identity = UserCreatorImpl.identityFactory("PRINCIPAL", "OPTIONAL_ALIAS");
        Entity entity = new Entity();
        User user = UserCreatorImpl.userFactory(entity, identity);
        assertSame(entity, user.getEntity());
        assertSame(identity, user.getIdentity());
        assertNull(user.getParent());
        assertEquals(0, user.getRoles().size());
        assertEquals(UserType.INTERNAL.getValue(), user.getUserType());
    }
    
    public void testDerivedUserFactory() {
        Identity identity1 = UserCreatorImpl.identityFactory("PRINCIPAL1", "OPTIONAL_ALIAS");
        Identity identity2 = UserCreatorImpl.identityFactory("PRINCIPAL2", "OPTIONAL_ALIAS");
        Entity entity = new Entity();
        UserGroup userGroup = UserCreatorImpl.userGroupFactory(entity, identity1);
        User user = UserCreatorImpl.userFactory(userGroup, identity2);
        assertSame(entity, user.getEntity());
        assertEquals(userGroup, user.getParent());
    }
    
    public void testGeneratePassword() {
        String password = UserCreatorImpl.generatePassword(8);
        assertEquals(8, password.length());
    }
    
    public void testUserLogFactoryError() {
        try {
            UserCreatorImpl.userLogFactory(null, null); fail();
        } catch (IllegalArgumentException e) {}
        try {
            UserCreatorImpl.userLogFactory(null, new Date()); fail();
        } catch (IllegalArgumentException e) {}
    }

    public void testUserLogFactory() {
        Identity identity = UserCreatorImpl.identityFactory("PRINCIPAL", "OPTIONAL_ALIAS");
        Entity entity = new Entity();
        User user = UserCreatorImpl.userFactory(entity, identity);
        Date date = new Date();
        UserLog userLog = UserCreatorImpl.userLogFactory(user, date);
        assertSame(user, userLog.getUser());
        assertSame(date, userLog.getLastLogin());
    }

    public void testUserLogFactoryNullDate() {
        Identity identity = UserCreatorImpl.identityFactory("PRINCIPAL", "OPTIONAL_ALIAS");
        Entity entity = new Entity();
        User user = UserCreatorImpl.userFactory(entity, identity);
        Date date = new Date();
        UserLog userLog = UserCreatorImpl.userLogFactory(user, null);
        assertSame(user, userLog.getUser());
        assertTrue(Math.abs(date.getTime() - userLog.getLastLogin().getTime()) < 1000);
    }

}
