package org.helianto.core;
// Generated 07/12/2006 11:35:10 by Hibernate Tools 3.2.0.beta8


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 				
 * <p>
 * An uniquely identified actor.
 * </p>
 * @author Mauricio Fernandes de Castro
 * 				
 * 		
 */
public class Identity  implements java.io.Serializable {

    // Fields    

     private long id;
     private String principal;
     private String optionalAlias;
     private PersonalData personalData;
     private Date created;
     private Date lastLogin;
     private char identityType;
     private char notification;
     private Set<UserGroup> users = new HashSet<UserGroup>(0);

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
    public Identity(String principal, String optionalAlias, PersonalData personalData, Date created, Date lastLogin, char identityType, char notification, Set<UserGroup> users) {
       this.principal = principal;
       this.optionalAlias = optionalAlias;
       this.personalData = personalData;
       this.created = created;
       this.lastLogin = lastLogin;
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
    public Date getLastLogin() {
        return this.lastLogin;
    }
    
    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
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
    public Set<UserGroup> getUsers() {
        return this.users;
    }
    
    public void setUsers(Set<UserGroup> users) {
        this.users = users;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("principal").append("='").append(getPrincipal()).append("' ");			
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


