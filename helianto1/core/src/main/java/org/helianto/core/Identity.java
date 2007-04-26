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
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

/**
 *              
 * <p>
 * An uniquely identified actor.
 * </p>
 * @author Mauricio Fernandes de Castro
 *              
 *      
 */
@javax.persistence.Entity
@Table(name="core_identity",
    uniqueConstraints = {@UniqueConstraint(columnNames={"principal"})}
)
public class Identity implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private long id;
    private String principal;
    private String optionalAlias;
    private PersonalData personalData;
    private Date created;
    private Date lastLogin;
    private char identityType;
    private char notification;
    private Set<UserGroup> users = new HashSet<UserGroup>();

    /** default constructor */
    public Identity() {
    }

    // Property accessors
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Principal getter.
     */
    @Column(length=64)
    public String getPrincipal() {
        return this.principal;
    }
    /**
     * Principal setter.
     */
    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    /**
     * OptionalAlias getter.
     */
    @Column(length=20)
    public String getOptionalAlias() {
        return this.optionalAlias;
    }
    /**
     * OptionalAlias setter.
     */
    public void setOptionalAlias(String optionalAlias) {
        this.optionalAlias = optionalAlias;
    }

    /**
     * PersonalData getter.
     */
    @Embedded
    public PersonalData getPersonalData() {
        return this.personalData;
    }
    /**
     * PersonalData setter.
     */
    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }

    /**
     * Created getter.
     */
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreated() {
        return this.created;
    }
    /**
     * Created setter.
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * LastLogin getter.
     */
    @Temporal(TemporalType.TIMESTAMP)
    public Date getLastLogin() {
        return this.lastLogin;
    }
    /**
     * LastLogin setter.
     */
    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    /**
     * IdentityType getter.
     */
    public char getIdentityType() {
        return this.identityType;
    }
    /**
     * IdentityType setter.
     */
    public void setIdentityType(char identityType) {
        this.identityType = identityType;
    }

    /**
     * Notification getter.
     */
    public char getNotification() {
        return this.notification;
    }
    /**
     * Notification setter.
     */
    public void setNotification(char notification) {
        this.notification = notification;
    }

    /**
     * Users getter.
     */
    @OneToMany(mappedBy = "identity", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    public Set<UserGroup> getUsers() {
        return this.users;
    }
    /**
     * Users setter.
     */
    public void setUsers(Set<UserGroup> users) {
        this.users = users;
    }

    /**
     * <code>Identity</code> factory.
     * 
     * @param principal
     */
    public static Identity identityFactory(String principal) {
        Identity identity = new Identity();
        identity.setPrincipal(principal);
        return identity;
    }

    /**
     * <code>Identity</code> factory with current system date, <code>NOT_ADDRESSABLE</code>
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
        identity.setCreated(new Date());
        identity.setIdentityType(IdentityType.NOT_ADDRESSABLE.getValue());
        identity.setNotification(Notification.BY_REQUEST.getValue());
        identity.setPersonalData(PersonalData.personalDataFactory("", ""));
        identity.setLastLogin(identity.getCreated());
        identity.setUsers(new HashSet<UserGroup>());
        return identity;
    }

    /**
     * <code>Identity</code> natural id query.
     */
    @Transient
    public static String getIdentityNaturalIdQueryString() {
        return "select identity from Identity identity where identity.principal = ? ";
    }

    /**
     * toString
     * @return String
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("principal").append("='").append(getPrincipal()).append("' ");
        buffer.append("]");
      
        return buffer.toString();
    }

   /**
    * equals
    */
   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
         if ( (other == null ) ) return false;
         if ( !(other instanceof Identity) ) return false;
         Identity castOther = (Identity) other; 
         
         return ((this.getPrincipal()==castOther.getPrincipal()) || ( this.getPrincipal()!=null && castOther.getPrincipal()!=null && this.getPrincipal().equals(castOther.getPrincipal()) ));
   }
   
   /**
    * hashCode
    */
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getPrincipal() == null ? 0 : this.getPrincipal().hashCode() );
         return result;
   }   

}
