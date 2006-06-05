package org.helianto.core;
// Generated 05/06/2006 11:48:00 by Hibernate Tools 3.1.0.beta4

import java.util.HashSet;
import java.util.Set;


/**
 * 			
 * <p>
 * Persist the user account.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id: helianto-core4.hbm.xml,v 1.4 2006/03/20 16:11:40 iserv Exp $
 * 				
 * 		
 */

public class User  implements java.io.Serializable {


    // Fields    

     private long id;
     private Entity entity;
     private Credential credential;
     private User parent;
     private boolean accountNonExpired;
     private boolean accountNonLocked;
     private char userType;
     private Set<Role> roles = new HashSet<Role>(0);


    // Constructors

    /** default constructor */
    public User() {
    }

	/** minimal constructor */
    public User(Entity entity, Credential credential, boolean accountNonExpired, boolean accountNonLocked, char userType) {
        this.entity = entity;
        this.credential = credential;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.userType = userType;
    }
    
    /** full constructor */
    public User(Entity entity, Credential credential, User parent, boolean accountNonExpired, boolean accountNonLocked, char userType, Set<Role> roles) {
        this.entity = entity;
        this.credential = credential;
        this.parent = parent;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.userType = userType;
        this.roles = roles;
    }
    

   
    // Property accessors

    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }

    public Entity getEntity() {
        return this.entity;
    }
    
    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public Credential getCredential() {
        return this.credential;
    }
    
    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    public User getParent() {
        return this.parent;
    }
    
    public void setParent(User parent) {
        this.parent = parent;
    }

    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }
    
    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }
    
    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public char getUserType() {
        return this.userType;
    }
    
    public void setUserType(char userType) {
        this.userType = userType;
    }

    public Set<Role> getRoles() {
        return this.roles;
    }
    
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof User) ) return false;
		 User castOther = ( User ) other; 
         
		 return ( (this.getEntity()==castOther.getEntity()) || ( this.getEntity()!=null && castOther.getEntity()!=null && this.getEntity().equals(castOther.getEntity()) ) )
 && ( (this.getCredential()==castOther.getCredential()) || ( this.getCredential()!=null && castOther.getCredential()!=null && this.getCredential().equals(castOther.getCredential()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getEntity() == null ? 0 : this.getEntity().hashCode() );
         result = 37 * result + ( getCredential() == null ? 0 : this.getCredential().hashCode() );
         
         
         
         
         
         return result;
   }   





}
