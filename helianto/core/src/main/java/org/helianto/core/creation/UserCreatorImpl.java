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
import org.helianto.core.UserLog;
import org.springframework.util.Assert;

/**
 * <code>User</code> and associated classes factory methods.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id: $
 */
public class UserCreatorImpl {
    
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
        return personalData;
    }

    /**
     * Generate a password with a known size from digts and plain UTF-8 alpha
     * characters (upper or lower case).

     * @param size
     */
    public static String generatePassword(int size) {
        Random generator = new Random();
        String source = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String password = ""; 
        for (int i=0; i<size; i++)  {
            int index = generator.nextInt(source.length());
            password += source.substring(index, index+1);
        }
        return password;
    }

    /**
     * <code>Identity</code> created with current system date, <code>NOT_ADDRESSABLE</code>
     * and flagged to be notified only <code>BY_REQUEST</code>.
     *
     * @param principal
     * @param optionalAlias
     * @see IdentityType
     * @see Notification
     */
    public static Identity identityFactory(String principal, String optionalAlias) {
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

    /**
     * <code>Credential</code> created with a generated 8 char
     * password and current system date, initially set as <code>IDLE</code>.
     * 
     * @param identity
     * @see CredentialState
     */
    public static Credential credentialFactory(Identity identity) {
        Assert.notNull(identity);
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

    /**
     * Root <code>User</code> (not member of any <code>UserGroup</code>)
     * created with type <code>INTERNAL</code>.
     * 
     * @param entity
     * @param identity
     * @see UserType
     */
    public static User userFactory(Entity entity, Identity identity) {
        Assert.notNull(entity);
        Assert.notNull(identity);
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

    /**
     * <code>User</code>, member of <code>UserGroup</code>, created with 
     * type <code>INTERNAL</code>.
     * 
     * @param parent
     * @param identity
     * @see UserType
     */
    public static User userFactory(UserGroup parent, Identity identity) {
        Assert.notNull(parent);
        Assert.notNull(identity);
        User user = userFactory(parent.getEntity(), identity);
        user.setParent(parent);
        return user;
    }

    /**
     * <code>UserGroup</code>.
     * 
     * @param entity
     * @param identity
     */
    public static UserGroup userGroupFactory(Entity entity, Identity identity) {
        Assert.notNull(entity);
        Assert.notNull(identity);
        UserGroup userGroup = new UserGroup();
        userGroup.setEntity(entity);
        userGroup.setIdentity(identity);
        userGroup.setParent(null);
        userGroup.setRoles(new HashSet<Role>());
        return userGroup;
    }
    
    /**
     * <code>UserLog</code> created with current system date. Note that
     * <code>Identity</code> last login date is also set.
     * 
     * @param user
     * @param date
     */
    public static UserLog userLogFactory(User user, Date date) {
        Assert.notNull(user);
        UserLog userLog = new UserLog();
        userLog.setUser(user);
        if (date==null) {
            date = new Date();
        }
        userLog.setLastLogin(date);
        user.getIdentity().setLastLogin(date);
        return userLog;
    }
        
}
