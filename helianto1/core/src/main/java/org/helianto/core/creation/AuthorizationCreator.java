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
import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.PrivacyLevel;
import org.helianto.core.User;
import org.helianto.core.UserAssociation;
import org.helianto.core.UserGroup;
import org.helianto.core.UserRole;
import org.helianto.core.UserType;

/**
 * Authorization required classes factory methods.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class AuthorizationCreator extends CreatorSupport {
    
    private static UserGroup internalUserGroupFactory(Class<? extends UserGroup> clazz, Entity entity, Identity identity) {
        assertNotNull(entity, "Entity must not be null");
        try {
            UserGroup userGroup = clazz.newInstance();
            userGroup.setEntity(entity);

            if (identity==null) {
                identity = AuthenticationCreator.identityFactory("", "");
            }
            userGroup.setIdentity(identity);
            identity.getUsers().add(userGroup);
            userGroup.setUserState(ActivityState.ACTIVE.getValue());
            userGroup.setParents(new HashSet<UserAssociation>());
            userGroup.setRoles(new HashSet<UserRole>());
            if (logger.isDebugEnabled()) {
                logger.debug("Created: "+userGroup);
            }
            return userGroup;
        } catch (Exception e) {
            throw new IllegalStateException("Unable to create class "+clazz, e);
        }
    }
    
    /**
     * Default <code>User</code> creator.
     * 
     * Set UserType as UserType.INTERNAL by default
     *  
     * @param requiredEntity 
     * @param identity if null, default is new empty identity
     * 
     * @see UserType
     */
    public static User userFactory(Entity requiredEntity, Identity identity) {
        User user = (User) internalUserGroupFactory(User.class, requiredEntity, identity);
        user.setUserType(UserType.INTERNAL.getValue());
        user.setAccountNonExpired(true);
        return user;
    }

    /**
     * Default member <code>User</code> creator.
     * 
     * @param requiredParent
     * @param identity if null, default is new empty identity

     * @see UserType
     */
    public static User userFactory(UserGroup requiredParent, Identity identity) {
        assertNotNull(requiredParent);
        User user = userFactory(requiredParent.getEntity(), identity);
        createUserAssociation(requiredParent, user);
        user.setPrivacyLevel(PrivacyLevel.PUBLIC.getValue());
        return user;
    }
    
    /**
     * Convenience method to create associations between users.
     * 
     * @param requiredParent
     * @param user
     */
    public static void createUserAssociation(UserGroup requiredParent, UserGroup user) {
        UserAssociation association = new UserAssociation();
        association.setParent(requiredParent);
        association.setChild(user);
        user.getParents().add(association);
        if (logger.isDebugEnabled()) {
            logger.debug("Created: "+association);
        }
    }

    /**
     * Default <code>UserGroup</code> creator.
     * 
     * @param requiredEntity
     * @param identity
     */
    public static UserGroup userGroupFactory(Entity requiredEntity, Identity identity) {
        return internalUserGroupFactory(UserGroup.class, requiredEntity, identity);
    }
    
    /**
     * Default member <code>UserGroup</code> creator.
     * 
     * @param requiredParent
     * @param identity if null, default is new empty identity

     * @see UserType
     */
    public static UserGroup userGroupFactory(UserGroup requiredParent, Identity identity) {
        assertNotNull(requiredParent);
        UserGroup userGroup = userFactory(requiredParent.getEntity(), identity);
        createUserAssociation(requiredParent, userGroup);
        return userGroup;
    }

}
