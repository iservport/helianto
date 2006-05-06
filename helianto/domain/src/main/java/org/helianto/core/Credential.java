package org.helianto.core;
// Generated 05/05/2006 22:23:06 by Hibernate Tools 3.1.0.beta4

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * 				
 * <p>
 * Persist a credential, unique by principal.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id: helianto-core3.hbm.xml,v 1.4 2006/03/28 10:03:12 iserv Exp $
 * 				
 * 		
 */

public class Credential extends AbstractCredential implements java.io.Serializable {


    // Fields    

     private long id;
     private String principal;
     private String password;
     /**
      * 				@see CredentialType
 * 			
     */
     private char credentialType;
     /**
      * 				@see NotificationType
 * 			
     */
     private char notification;
     private PersonalData personalData;
     private Date created;
     private Date lastModified;
     private Date expired;
     /**
      * 				@see CredentialState
 * 			
     */
     private char credentialState;
     private Set<User> users = new HashSet<User>(0);


    // Constructors

    /** default constructor */
    public Credential() {
    }

	/** minimal constructor */
    public Credential(String principal, char credentialType, char notification, PersonalData personalData, char credentialState) {
        this.principal = principal;
        this.credentialType = credentialType;
        this.notification = notification;
        this.personalData = personalData;
        this.credentialState = credentialState;
    }
    
    /** full constructor */
    public Credential(String principal, String password, char credentialType, char notification, PersonalData personalData, Date created, Date lastModified, Date expired, char credentialState, Set<User> users) {
        this.principal = principal;
        this.password = password;
        this.credentialType = credentialType;
        this.notification = notification;
        this.personalData = personalData;
        this.created = created;
        this.lastModified = lastModified;
        this.expired = expired;
        this.credentialState = credentialState;
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

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    /**       
     *      * 				@see CredentialType
     * 			
     */

    public char getCredentialType() {
        return this.credentialType;
    }
    
    public void setCredentialType(char credentialType) {
        this.credentialType = credentialType;
    }
    /**       
     *      * 				@see NotificationType
     * 			
     */

    public char getNotification() {
        return this.notification;
    }
    
    public void setNotification(char notification) {
        this.notification = notification;
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

    public Date getLastModified() {
        return this.lastModified;
    }
    
    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public Date getExpired() {
        return this.expired;
    }
    
    public void setExpired(Date expired) {
        this.expired = expired;
    }
    /**       
     *      * 				@see CredentialState
     * 			
     */

    public char getCredentialState() {
        return this.credentialState;
    }
    
    public void setCredentialState(char credentialState) {
        this.credentialState = credentialState;
    }

    public Set<User> getUsers() {
        return this.users;
    }
    
    public void setUsers(Set<User> users) {
        this.users = users;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Credential) ) return false;
		 Credential castOther = ( Credential ) other; 
         
		 return ( (this.getPrincipal()==castOther.getPrincipal()) || ( this.getPrincipal()!=null && castOther.getPrincipal()!=null && this.getPrincipal().equals(castOther.getPrincipal()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getPrincipal() == null ? 0 : this.getPrincipal().hashCode() );
         
         
         
         
         
         
         
         
         
         return result;
   }   





}
