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
import java.util.HashSet;

import org.helianto.core.test.DomainTestSupport;
import org.helianto.core.type.Appellation;
import org.helianto.core.type.ActivityState;
import org.helianto.core.type.Encription;
import org.helianto.core.type.Gender;
import org.helianto.core.type.IdentityType;
import org.helianto.core.type.Notification;

import junit.framework.TestCase;

public class AuthenticationDomainTests extends TestCase {

    public void testPersonalData() {
        PersonalData personalData = new PersonalData();
        personalData.setFirstName("");
        personalData.setLastName("");
        personalData.setAppellation(Appellation.MISS.getValue());
        personalData.setAppellation(Appellation.MR_OR_MRS.getValue());
        personalData.setAppellation(Appellation.MS.getValue());
        personalData.setAppellation(Appellation.NOT_SUPPLIED.getValue());
        personalData.setGender(Gender.FEMALE.getValue());
        personalData.setGender(Gender.MALE.getValue());
        personalData.setGender(Gender.NOT_SUPPLIED.getValue());
    }

    public void testIdentity() {
        Identity identity = new Identity();
        identity.setId(Long.MAX_VALUE);
        identity.setId(Long.MIN_VALUE);
        identity.setCreated(new Date());
        identity.setIdentityType(IdentityType.NOT_ADDRESSABLE.getValue());
        identity.setIdentityType(IdentityType.ORGANIZATIONAL_EMAIL.getValue());
        identity.setIdentityType(IdentityType.PERSONAL_EMAIL.getValue());
        identity.setNotification(Notification.AUTOMATIC.getValue());
        identity.setNotification(Notification.BY_REQUEST.getValue());
        identity.setOptionalAlias("");
        identity.setPersonalData(new PersonalData());
        identity.setPrincipal("");
        identity.setUsers(new HashSet<UserGroup>());
    }
    
    public void testIdentityEquals() {
        Identity copy, identity = new Identity();
        identity.setPrincipal("TEST");
        copy = (Identity) DomainTestSupport.minimalEqualsTest(identity);
        
        copy.setPrincipal("TEST");
        assertTrue(identity.equals(copy));
    }

    public void testCredential() {
        Credential credential = new Credential();
        
        credential.setId(Long.MAX_VALUE);
        credential.setId(Long.MIN_VALUE);
        
        credential.setIdentity(new Identity());
        
        credential.setPassword("");
        
        credential.setCredentialState(ActivityState.ACTIVE.getValue());
        credential.setCredentialState(ActivityState.CANCELLED.getValue());
        credential.setCredentialState(ActivityState.INITIAL.getValue());
        credential.setCredentialState(ActivityState.SUSPENDED.getValue());
        
        credential.setLastModified(new Date());
        
        credential.setExpired(new Date());
        
        credential.setEncription(Encription.PLAIN_PASSWORD.getValue());

        credential.setPasswordDirty(true);
        credential.setPasswordDirty(false);
        
        credential.setVerifyPassword("");
    }

    public void testCredentialEquals() {
        Credential copy, credential = new Credential();
        credential.setIdentity(new Identity());
        copy = (Credential) DomainTestSupport.minimalEqualsTest(credential);

        copy.setIdentity(credential.getIdentity());
        assertTrue(credential.equals(copy));
    }
    
    public void testPrivateKey() {
        PrivateKey privateKey = new PrivateKey();
        
        privateKey.setId(Integer.MAX_VALUE);
        privateKey.setId(Integer.MIN_VALUE);
        
        privateKey.setCredential(new Credential());
        
        privateKey.setPrivateKey("");
    }

    public void testPrivateKeyEquals() {
        PrivateKey copy, privateKey = new PrivateKey();
        privateKey.setCredential(new Credential());
        copy = (PrivateKey) DomainTestSupport.minimalEqualsTest(privateKey);
        
        copy.setCredential(privateKey.getCredential());
        assertTrue(privateKey.equals(copy));
    }

}
