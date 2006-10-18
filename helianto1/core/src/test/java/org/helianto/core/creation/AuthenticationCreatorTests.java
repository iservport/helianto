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
import org.helianto.core.Identity;
import org.helianto.core.PersonalData;
import org.helianto.core.PrivateKey;
import org.helianto.core.type.ActivityState;
import org.helianto.core.type.Appellation;
import org.helianto.core.type.Encription;
import org.helianto.core.type.Gender;
import org.helianto.core.type.IdentityType;
import org.helianto.core.type.Notification;

import junit.framework.TestCase;

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
     * Test method for 'org.helianto.core.creation.AuthenticationCreator.credentialFactory(Identity, String)'
     */
    public void testCredentialFactory() {
        AuthenticationCreator.currentDateNormalizer = new Date();
        Identity identity = AuthenticationCreator.identityFactory("PRINCIPAL", "OPTIONAL_ALIAS");
        Credential credential = AuthenticationCreator.credentialFactory(identity, "PASSWORD");
        
        assertSame(identity, credential.getIdentity());
        assertEquals("PASSWORD", credential.getPassword());
        assertEquals("", credential.getVerifyPassword());
        assertFalse(credential.isPasswordDirty());
        assertSame(AuthenticationCreator.currentDateNormalizer, credential.getLastModified());
        assertNull(credential.getExpired());
        assertEquals(ActivityState.INITIAL.getValue(), credential.getCredentialState());
        assertEquals(Encription.PLAIN_PASSWORD.getValue(), credential.getEncription());
    }

    public void testCredentialFactoryDefaults() {
        Credential credential = AuthenticationCreator.credentialFactory(null, "PASSWORD");
        
        assertEquals("", credential.getIdentity().getPrincipal());
        assertEquals("", credential.getIdentity().getOptionalAlias());
    }

    /*
     * Test method for 'org.helianto.core.creation.AuthenticationCreator.privateKeyFactory(Credential, String)'
     */
    public void testPrivateKeyFactory() {
        Identity identity = AuthenticationCreator.identityFactory("PRINCIPAL", "OPTIONAL_ALIAS");
        Credential credential = AuthenticationCreator.credentialFactory(identity, "PASSWORD");
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