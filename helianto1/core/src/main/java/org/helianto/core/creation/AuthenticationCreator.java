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

import org.helianto.core.Credential;
import org.helianto.core.PrivateKey;

/**
 * Authentication required classes factory methods.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class AuthenticationCreator extends CreatorSupport {
    
    private static int testKey = 0;

//    /**
//     * <code>PersonalData</code> is created by default with
//     * appellation and gender as <code>NOT_SUPPLIED</code>.
//     * 
//     * @see Appellation
//     * @see Gender
//     */
//    public static PersonalData personalDataFactory() {
//        PersonalData personalData = new PersonalData();
//        
//        personalData.setFirstName("");
//        personalData.setLastName("");
//        personalData.setAppellation(Appellation.NOT_SUPPLIED.getValue());
//        personalData.setGender(Gender.NOT_SUPPLIED.getValue());
//        if (logger.isDebugEnabled()) {
//            logger.debug("Created: "+personalData);
//        }
//        return personalData;
//    }
//
//    /**
//     * <code>Identity</code> created with current system date, <code>NOT_ADDRESSABLE</code>
//     * and flagged to be notified only <code>BY_REQUEST</code>.
//     *
//     * @param principal
//     * @param optionalAlias
//     * 
//     * @see IdentityType
//     * @see Notification
//     */
//    public static Identity identityFactory(String principal, String optionalAlias) {
//        if (principal == null) {
//            principal = DomainTestSupport.getNonRepeatableStringValue(testKey++, 20);
//        }
//        if (optionalAlias == null) {
//            optionalAlias = DomainTestSupport.getNonRepeatableStringValue(testKey++, 20);
//        }
//        Identity identity = new Identity();
//        
//        identity.setPrincipal(principal);
//        identity.setOptionalAlias(optionalAlias);
//        identity.setCreated(currentDate());
//        identity.setIdentityType(IdentityType.NOT_ADDRESSABLE.getValue());
//        identity.setNotification(Notification.BY_REQUEST.getValue());
//        identity.setPersonalData(personalDataFactory());
//        identity.setLastLogin(identity.getCreated());
//        identity.setUsers(new HashSet<UserGroup>());
//        if (logger.isDebugEnabled()) {
//            logger.debug("Created: "+identity);
//        }
//        return identity;
//    }
//
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
