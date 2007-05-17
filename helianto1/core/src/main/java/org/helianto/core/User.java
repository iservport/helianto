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
import javax.persistence.Transient;
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
     * @param identity
     */
    public static User userFactory(Entity entity, Identity identity) {
        User user = new User();
        user.setEntity(entity);
        user.setIdentity(identity);
        return user;
    }

    /**
     * <code>User</code> natural id query.
     */
    @Transient
    public static String getUserNaturalIdQueryString() {
        return "select user from User user where user.entity = ? and user.identity = ? ";
    }

    /**
     * toString
     * @return String
     */
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("entity").append("='").append(getEntity()).append("' ");
        buffer.append("identity").append("='").append(getIdentity()).append("' ");
        buffer.append("]");
      
        return buffer.toString();
    }

   /**
    * equals
    */
    @Override
    public boolean equals(Object other) {
         if ( (this == other ) ) return true;
         if ( (other == null ) ) return false;
         if ( !(other instanceof User) ) return false;
         User castOther = (User) other; 
         
         return ((this.getEntity()==castOther.getEntity()) || ( this.getEntity()!=null && castOther.getEntity()!=null && this.getEntity().equals(castOther.getEntity()) ))
             && ((this.getIdentity()==castOther.getIdentity()) || ( this.getIdentity()!=null && castOther.getIdentity()!=null && this.getIdentity().equals(castOther.getIdentity()) ));
   }
   
   /**
    * hashCode
    */
   @Override
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getEntity() == null ? 0 : this.getEntity().hashCode() );
         result = 37 * result + ( getIdentity() == null ? 0 : this.getIdentity().hashCode() );
         return result;
   }   

}
