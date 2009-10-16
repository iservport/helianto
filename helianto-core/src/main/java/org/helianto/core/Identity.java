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
 * An uniquely identified actor.
 * 
 * @author Mauricio Fernandes de Castro
 *              
 *      
 */
@javax.persistence.Entity
@Table(name="core_identity",
    uniqueConstraints = {@UniqueConstraint(columnNames={"principal"})}
)
public class Identity implements java.io.Serializable {

    /**
     * <code>Identity</code> factory.
     * 
     * @param principal
     */
    public static Identity identityFactory(String principal) {
        return identityFactory(principal, "");
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
        return identity;
    }

    private static final long serialVersionUID = 1L;
    private long id;
    private String principal;
    private String optionalAlias;
    private PersonalData personalData;
    private Date created;
    private char identityType;
    private char notification;
    private Set<Credential> credentials = new HashSet<Credential>();

    /** 
     * Default constructor
     */
    public Identity() {
        setPersonalData(PersonalData.personalDataFactory("", ""));
        setCreated(new Date());
        setIdentityType(IdentityType.EMAIL.getValue());
        setNotification(Notification.AUTOMATIC.getValue());
    }

    /**
     * Primary key.
     */
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
    @Column(length=40)
    public String getPrincipal() {
        return this.principal;
    }
    public void setPrincipal(String principal) {
        if (principal!=null) {
            this.principal = principal.toLowerCase();
        }
        else {
            this.principal = null;
        }
    }

    /**
     * OptionalAlias getter.
     */
    @Column(length=20)
    public String getOptionalAlias() {
        return this.optionalAlias;
    }
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
    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }
    @Transient
    public String getIdentityName() {
    	if (getPersonalData()==null) {
    		return "";
    	}
    	return new StringBuilder(getPersonalData().getFirstName())
    	    .append(" ")
    	    .append(getPersonalData().getLastName()).toString();
    }

    /**
     * Created getter.
     */
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreated() {
        return this.created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * IdentityType getter.
     */
    public char getIdentityType() {
        return this.identityType;
    }
    public void setIdentityType(char identityType) {
        this.identityType = identityType;
    }
    public void setIdentityType(IdentityType identityType) {
        this.identityType = identityType.getValue();
    }

    /**
     * Notification getter.
     */
    public char getNotification() {
        return this.notification;
    }
    public void setNotification(char notification) {
        this.notification = notification;
    }
    public void setNotification(Notification notification) {
        this.notification = notification.getValue();
    }
    
    /**
     * A set of credentials.
     */
    @OneToMany(mappedBy="identity", cascade=CascadeType.ALL)
    public Set<Credential> getCredentials() {
		return credentials;
	}
    public void setCredentials(Set<Credential> credentials) {
		this.credentials = credentials;
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
