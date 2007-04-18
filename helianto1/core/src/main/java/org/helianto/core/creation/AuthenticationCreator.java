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

import java.util.HashSet;

import org.helianto.core.ActivityState;
import org.helianto.core.Appellation;
import org.helianto.core.Credential;
import org.helianto.core.Encription;
import org.helianto.core.Gender;
import org.helianto.core.Identity;
import org.helianto.core.IdentityType;
import org.helianto.core.Notification;
import org.helianto.core.PersonalData;
import org.helianto.core.PrivateKey;
import org.helianto.core.UserGroup;

/**
 * Authentication required classes factory methods.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class AuthenticationCreator extends CreatorSupport {

    /**
     * <code>PersonalData</code> is created by default with
     * appellation and gender as <code>NOT_SUPPLIED</code>.
     * 
     * @see Appellation
     * @see Gender
     */
    public static PersonalData personalDataFactory() {
        PersonalData personalData = new PersonalData();
        
        personalData.setFirstName("");
        personalData.setLastName("");
        personalData.setAppellation(Appellation.NOT_SUPPLIED.getValue());
        personalData.setGender(Gender.NOT_SUPPLIED.getValue());
        if (logger.isDebugEnabled()) {
            logger.debug("Created: "+personalData);
        }
        return personalData;
    }

    /**
     * <code>Identity</code> created with current system date, <code>NOT_ADDRESSABLE</code>
     * and flagged to be notified only <code>BY_REQUEST</code>.
     *
     * @param principal
     * @param optionalAlias
     * 
     * @see IdentityType
     * @see Notification
     */
    public static Identity identityFactory(String principal, String optionalAlias) {
        Identity identity = new Identity();
        
        identity.setPrincipal(principal);
        identity.setOptionalAlias(optionalAlias);
        identity.setCreated(currentDate());
        identity.setIdentityType(IdentityType.NOT_ADDRESSABLE.getValue());
        identity.setNotification(Notification.BY_REQUEST.getValue());
        identity.setPersonalData(personalDataFactory());
        identity.setLastLogin(identity.getCreated());
        identity.setUsers(new HashSet<UserGroup>());
        if (logger.isDebugEnabled()) {
            logger.debug("Created: "+identity);
        }
        return identity;
    }

    /**
     * <code>Credential</code> created with current system 
     * date, initially set as <code>INITIAL</code>.
     * 
     * @param requiredIdentity 
     * @param password
     * 
     * @see CredentialState
     */
    public static Credential credentialFactory(Identity requiredIdentity, String password) {
        Credential credential = new Credential();
        assertNotNull(requiredIdentity);
        credential.setIdentity(requiredIdentity);
        credential.setPassword(password);
        credential.setVerifyPassword("");
        credential.setPasswordDirty(false);
        credential.setLastModified(currentDate());
        credential.setExpired(null);
        credential.setCredentialState(ActivityState.INITIAL.getValue());
        credential.setEncription(Encription.PLAIN_PASSWORD.getValue());
        if (logger.isDebugEnabled()) {
            logger.debug("Created: "+credential);
        }
        return credential;
    }
    
    /**
     * <code>PrivateKey</code> created with minumum requirements.
     * 
     * @param credential
     * @param privateKeyValue
     */
    public static PrivateKey privateKeyFactory(Credential requiredCredential, String privateKeyValue) {
        assertNotNull(requiredCredential);
        PrivateKey privateKey = new PrivateKey();
        privateKey.setCredential(requiredCredential);
        privateKey.setPrivateKey(privateKeyValue);
        if (logger.isDebugEnabled()) {
            logger.debug("Created: "+privateKey);
        }
        return privateKey;
    }
    
}
