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

import javax.persistence.DiscriminatorValue;
/**
 * <p>
 * The user account.
 * </p>
 * <p>
 * An user account represents the association between an <code>Identity</code>
 * and an <code>Entity</code>. Such association allows for a singly identified 
 * actor, i.e. a person or any other organizational <code>Identity</code>, to keep
 * a single authentication scheme and have multiple authorization schemes, one per
 * <code>Entity</code>.
 * </p>
 * @author Mauricio Fernandes de Castro
 * 			
 */
@javax.persistence.Entity
@DiscriminatorValue("U")
public class User extends UserGroup implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private char userType;
    private char privacyLevel;
    private boolean accountNonExpired;

    /** default constructor */
    public User() {
    }

    // Property accessors
    /**
     * UserType getter.
     */
    public char getUserType() {
        return this.userType;
    }
    /**
     * UserType setter.
     */
    public void setUserType(char userType) {
        this.userType = userType;
    }

    /**
     * PrivacyLevel getter.
     */
    public char getPrivacyLevel() {
        return this.privacyLevel;
    }
    /**
     * PrivacyLevel setter.
     */
    public void setPrivacyLevel(char privacyLevel) {
        this.privacyLevel = privacyLevel;
    }

    /**
     * AccountNonExpired getter.
     */
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }
    /**
     * AccountNonExpired setter.
     */
    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    /**
     * <code>User</code> factory.
     * 
     * @param entity
     */
    public static User userFactory(Entity entity) {
        return userFactory(entity, null);
    }

    /**
     * <code>User</code> factory.
     * 
     * @param entity
     * @param identity
     */
    public static User userFactory(Entity entity, Identity identity) {
        User user = internalUserGroupFactory(User.class, entity, identity);
        user.setUserType(UserType.INTERNAL.getValue());
        user.setAccountNonExpired(true);
        return user;
    }

    /**
     * <code>User</code> factory.
     * 
     * @param entity
     */
    public static User userFactory(UserGroup parent) {
        return userFactory(parent, null);
    }

    /**
     * <code>User</code> factory.
     * 
     * @param entity
     */
    public static User userFactory(UserGroup parent, Identity identity) {
        User user = userFactory(parent.getEntity(), identity);
        UserAssociation.userAssociationFactory(parent, user);
        return user;
    }

    /**
     * <code>User</code> natural id query.
     */
    public static StringBuilder getUserQueryStringBuilder() {
        return new StringBuilder("select user from User user ");
    }

    /**
     * <code>User</code> natural id query.
     */
    public static String getUserNaturalIdQueryString() {
        return getUserQueryStringBuilder().append("where user.entity = ? and user.identity = ? ").toString();
    }

}
