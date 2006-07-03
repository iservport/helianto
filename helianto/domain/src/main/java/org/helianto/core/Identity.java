package org.helianto.core;
// Generated 03/07/2006 15:47:01 by Hibernate Tools 3.1.0.beta4

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * 				
 * <p>
 * An uniquely identified actor.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id: helianto-core3.hbm.xml,v 1.4 2006/03/28 10:03:12 iserv Exp $
 * 				
 * 		
 */

public class Identity  implements org.helianto.core.creation.ImmutableIdentity,java.io.Serializable {


    // Fields    

     private long id;
     private String principal;
     private String optionalAlias;
     private PersonalData personalData;
     private Date created;
     private char identityType;
     private char notification;
     private Set<User> users = new HashSet<User>(0);


    // Constructors

    /** default constructor */
    public Identity() {
    }

	/** minimal constructor */
    public Identity(String principal, PersonalData personalData, char identityType, char notification) {
        this.principal = principal;
        this.personalData = personalData;
        this.identityType = identityType;
        this.notification = notification;
    }
    
    /** full constructor */
    public Identity(String principal, String optionalAlias, PersonalData personalData, Date created, char identityType, char notification, Set<User> users) {
        this.principal = principal;
        this.optionalAlias = optionalAlias;
        this.personalData = personalData;
        this.created = created;
        this.identityType = identityType;
        this.notification = notification;
        this.users = users;
    }
    

   
    // Property accessors

    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }

    public String getPrincipal() {
        return this.principal;
    }
    
    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getOptionalAlias() {
        return this.optionalAlias;
    }
    
    public void setOptionalAlias(String optionalAlias) {
        this.optionalAlias = optionalAlias;
    }

    public PersonalData getPersonalData() {
        return this.personalData;
    }
    
    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }

    public Date getCreated() {
        return this.created;
    }
    
    public void setCreated(Date created) {
        this.created = created;
    }

    public char getIdentityType() {
        return this.identityType;
    }
    
    public void setIdentityType(char identityType) {
        this.identityType = identityType;
    }

    public char getNotification() {
        return this.notification;
    }
    
    public void setNotification(char notification) {
        this.notification = notification;
    }

    public Set<User> getUsers() {
        return this.users;
    }
    
    public void setUsers(Set<User> users) {
        this.users = users;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("optionalAlias").append("='").append(getOptionalAlias()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Identity) ) return false;
		 Identity castOther = ( Identity ) other; 
         
		 return ( (this.getPrincipal()==castOther.getPrincipal()) || ( this.getPrincipal()!=null && castOther.getPrincipal()!=null && this.getPrincipal().equals(castOther.getPrincipal()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getPrincipal() == null ? 0 : this.getPrincipal().hashCode() );
         
         
         
         
         
         
         return result;
   }   





}
