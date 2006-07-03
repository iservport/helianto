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
import java.util.HashSet;
import java.util.Random;

import org.helianto.core.Credential;
import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.PersonalData;
import org.helianto.core.Role;
import org.helianto.core.User;
import org.helianto.core.UserGroup;

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

    public Identity identityFactory(String principal, String optionalAlias) {
        Identity identity = new Identity();
        identity.setPrincipal(principal);
        identity.setOptionalAlias(optionalAlias);
        identity.setCreated(new Date());
        identity.setIdentityType(IdentityType.NOT_ADDRESSABLE.getValue());
        identity.setNotification(Notification.BY_REQUEST.getValue());
        PersonalData pd = personalDataFactory();
        identity.setPersonalData(pd);
        return identity;
    }

    public Credential credentialFactory(Identity identity) {
        Credential credential = new Credential();
        credential.setIdentity(identity);
        credential.setPassword(generatePassword(8));
        credential.setVerifyPassword("");
        credential.setPasswordDirty(false);
        credential.setLastModified(new Date());
        credential.setExpired(null);
        credential.setCredentialState(CredentialState.IDLE.getValue());
        return credential;
    }

    public User userFactory(Entity entity, Identity identity) throws NullEntityException {
        if (entity==null) {
            throw new NullEntityException("An User must belong to an Entity");
        }
        User user = new User();
        user.setEntity(entity);
        user.setIdentity(identity);
        user.setParent(null);
        user.setUserType(UserType.INTERNAL.getValue());
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setRoles(new HashSet<Role>());
        return user;
    }

    public User userFactory(UserGroup parent, Identity identity) {
        User user = userFactory(parent.getEntity(), identity);
        user.setParent(parent);
        return user;
    }

    public UserGroup userGroupFactory(Entity entity, Identity identity) throws NullEntityException {
        if (entity==null) {
            throw new NullEntityException("An UserGroup must belong to an Entity");
        }
        UserGroup userGroup = new UserGroup();
        userGroup.setEntity(entity);
        userGroup.setIdentity(identity);
        userGroup.setParent(null);
        userGroup.setRoles(new HashSet<Role>());
        return userGroup;
    }
        
}
