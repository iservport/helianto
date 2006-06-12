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

/**
 * Default implementation for the <code>UserCreator</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 */
public class UserCreatorImpl implements UserCreator {
    
    public PersonalData personalDataFactory() {
        PersonalData personalData = new PersonalData();
        personalData.setFirstName("");
        personalData.setLastName("");
        personalData.setAppellation(Appellation.NOT_SUPPLIED.getValue());
        personalData.setGender(Gender.NOT_SUPPLIED.getValue());
        return personalData;
    }

    public Credential credentialFactory() {
        return credentialFactory("");
    }

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
    
    public User userFactory(Entity entity, Credential credential) throws NullEntityException {
        if (entity==null) {
            throw new NullEntityException("An User must belong to an Entity");
        }
        User user = new User();
        user.setEntity(entity);
        user.setCredential(credential);
        user.setUserType(UserType.INTERNAL.getValue());
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        return user;
    }

    public User userFactory(User parent, Credential credential) {
        User user = userFactory(parent.getEntity(), credential);
        user.setParent(parent);
        return user;
    }

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
