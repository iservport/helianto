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
import java.util.Random;

import org.helianto.core.Credential;
import org.helianto.core.Entity;
import org.helianto.core.PersonalData;
import org.helianto.core.User;
import org.helianto.core.service.AbstractGenericService;

/**
 * Default implementation for the <code>UserCreator</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 */
public class UserCreatorImpl extends AbstractGenericService implements UserCreator {
    
    /* (non-Javadoc)
     * @see org.helianto.core.UserCreator#personalDataFactory()
     */
    public PersonalData personalDataFactory() {
        PersonalData personalData = new PersonalData();
        personalData.setFirstName("");
        personalData.setLastName("");
        personalData.setAppellation(Appellation.NOT_SUPPLIED.getValue());
        personalData.setGender(Gender.NOT_SUPPLIED.getValue());
        return personalData;
    }

    /* (non-Javadoc)
     * @see org.helianto.core.UserCreator#credentialFactory()
     */
    public Credential credentialFactory() {
        return credentialFactory("");
    }

    /* (non-Javadoc)
     * @see org.helianto.core.UserCreator#credentialFactory(java.lang.String)
     */
    public Credential credentialFactory(String principal) {
        Credential credential = new Credential();
        credential.setPrincipal(principal);
        credential.setPassword(generatePassword(8));
        credential.setVerifyPassword("");
        credential.setPasswordDirty(false);
        credential.setCreated(new Date());
        credential.setLastModified(credential.getCreated());
        credential.setExpired(null);
        credential.setCredentialType(CredentialType.NOT_ADDRESSABLE.getValue());
        credential.setNotification(Notification.BY_REQUEST.getValue());
        credential.setCredentialState(CredentialState.IDLE.getValue());
        PersonalData pd = personalDataFactory();
        credential.setPersonalData(pd);
        return credential;
    }
    
    /* (non-Javadoc)
     * @see org.helianto.core.UserCreator#userFactory(org.helianto.core.Entity, org.helianto.core.Credential)
     */
    public User userFactory(Entity entity, Credential credential) {
        User user = new User();
        user.setEntity(entity);
        user.setCredential(credential);
        user.setUserType(UserType.INTERNAL.getValue());
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        return user;
    }

    /* (non-Javadoc)
     * @see org.helianto.core.UserCreator#userFactory(org.helianto.core.User, org.helianto.core.Credential)
     */
    public User userFactory(User parent, Credential credential) {
        User user = userFactory(parent.getEntity(), credential);
        user.setParent(parent);
        return user;
    }

    /* (non-Javadoc)
     * @see org.helianto.core.UserCreator#generatePassword(int)
     */
    public String generatePassword(int size) {
        Random generator = new Random();
        String source = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String password = ""; 
        for (int i=0; i<size; i++)  {
            int index = generator.nextInt(source.length());
            password += source.substring(index, index+1);
        }
        return password;
    }
        
}
