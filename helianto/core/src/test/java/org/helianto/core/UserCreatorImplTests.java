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

import junit.framework.TestCase;

public class UserCreatorImplTests extends TestCase {
    
    UserCreatorImpl factory;
    
    public void setUp() {
        factory = new UserCreatorImpl();
    }

    public void testPersonalDataFactory() {
        PersonalData personalData = 
            factory.personalDataFactory();
        assertEquals("", personalData.getFirstName());
        assertEquals("", personalData.getLastName());
        assertEquals(Appellation.NOT_SUPPLIED.getValue(),
                personalData.getAppellation());
        assertEquals(Gender.NOT_SUPPLIED.getValue(),
                personalData.getGender());
    }

    public void testCredentialFactory() {
        Credential credential = factory.credentialFactory();
        assertEquals("", credential.getPrincipal());
    }

    public void testCredentialFactoryString() {
        Credential credential = 
            factory.credentialFactory("UNIQUE");
        assertEquals("UNIQUE", credential.getPrincipal());
        assertEquals(8, credential.getPassword().length());
        assertTrue(credential.getCreated().compareTo(new Date()) < 1000);
        assertSame(credential.getCreated(), credential.getLastModified());
        assertNull(credential.getExpired());
        assertEquals(CredentialType.NOT_ADDRESSABLE.getValue(), credential.getCredentialType());
        assertEquals(Notification.BY_REQUEST.getValue(), credential.getNotification());
        assertEquals(CredentialState.IDLE.getValue(), credential.getCredentialState());
        assertEquals(Appellation.NOT_SUPPLIED.getValue(),
                credential.getPersonalData().getAppellation());
        assertEquals(Gender.NOT_SUPPLIED.getValue(),
                credential.getPersonalData().getGender());
        assertEquals(0, credential.getUsers().size());
    }

}
