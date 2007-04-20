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

import org.helianto.core.Appellation;
import org.helianto.core.Credential;
import org.helianto.core.Gender;
import org.helianto.core.Identity;
import org.helianto.core.IdentityType;
import org.helianto.core.Notification;
import org.helianto.core.PersonalData;
import org.helianto.core.PrivateKey;

public class AuthenticationCreatorTests extends TestCase {

    /*
     * Test method for 'org.helianto.core.creation.AuthenticationCreator.personalDataFactory()'
     */
    public void testPersonalDataFactory() {
        PersonalData personalData = 
            AuthenticationCreator.personalDataFactory();
        
        assertEquals("", personalData.getFirstName());
        assertEquals("", personalData.getLastName());
        assertEquals(Appellation.NOT_SUPPLIED.getValue(),
                personalData.getAppellation());
        assertEquals(Gender.NOT_SUPPLIED.getValue(),
                personalData.getGender());
    }

    /*
     * Test method for 'org.helianto.core.creation.AuthenticationCreator.identityFactory(String, String)'
     */
    public void testIdentityFactory() {
        AuthenticationCreator.currentDateNormalizer = new Date();
        Identity identity = AuthenticationCreator.identityFactory("PRINCIPAL", "OPTIONAL_ALIAS");
        
        assertEquals("PRINCIPAL", identity.getPrincipal());
        assertEquals("OPTIONAL_ALIAS", identity.getOptionalAlias());
        assertSame(AuthenticationCreator.currentDateNormalizer, identity.getCreated());
        assertEquals(IdentityType.NOT_ADDRESSABLE.getValue(), identity.getIdentityType());
        assertEquals(Notification.BY_REQUEST.getValue(), identity.getNotification());
        assertEquals(Appellation.NOT_SUPPLIED.getValue(),
                identity.getPersonalData().getAppellation());
        assertEquals(Gender.NOT_SUPPLIED.getValue(),
                identity.getPersonalData().getGender());
        assertSame(AuthenticationCreator.currentDateNormalizer, identity.getLastLogin());
        assertEquals(0, identity.getUsers().size());
    }

    /*
     * Test method for 'org.helianto.core.creation.AuthenticationCreator.privateKeyFactory(Credential, String)'
     */
    public void testPrivateKeyFactory() {
        Identity identity = AuthenticationCreator.identityFactory("PRINCIPAL", "OPTIONAL_ALIAS");
        Credential credential = Credential.credentialFactory(identity, "PASSWORD");
        PrivateKey privateKey =  AuthenticationCreator.privateKeyFactory(credential, "KEY");
        
        assertSame(credential, privateKey.getCredential());
        assertEquals("KEY", privateKey.getPrivateKey());
    }
    
    /*
     * Assertion errors
     */
    public void testErrors() {
        try {
            AuthenticationCreator.privateKeyFactory(null, "KEY"); fail();
        } catch (IllegalArgumentException e) {
        } catch (Exception e) { fail(); }
    }

}
