package org.helianto.core;
// Generated 03/07/2006 15:47:02 by Hibernate Tools 3.1.0.beta4

import java.util.HashSet;
import java.util.Set;


/**
 * 			
 * <p>
 * The user group.
 * </p>
 * <p>
 * An user account represents the association between an <code>Identity</code>
 * and an <code>Entity</code>. If authorization fails at user level, the authorization 
 * service will look-up the user hierarchy roles as well.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id: $
 * 				
 * 		
 */

public class UserGroup  implements java.io.Serializable {


    // Fields    

     private long id;
     private Entity entity;
     private Identity identity;
     private UserGroup parent;
     private Set<Role> roles = new HashSet<Role>(0);


    // Constructors

    /** default constructor */
    public UserGroup() {
    }

	/** minimal constructor */
    public UserGroup(Entity entity, Identity identity) {
        this.entity = entity;
        this.identity = identity;
    }
    
    /** full constructor */
    public UserGroup(Entity entity, Identity identity, UserGroup parent, Set<Role> roles) {
        this.entity = entity;
        this.identity = identity;
        this.parent = parent;
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

    public Identity getIdentity() {
        return this.identity;
    }
    
    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    public UserGroup getParent() {
        return this.parent;
    }
    
    public void setParent(UserGroup parent) {
        this.parent = parent;
    }

    public Set<Role> getRoles() {
        return this.roles;
    }
    
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("entity").append("='").append(getEntity()).append("' ");			
      buffer.append("identity").append("='").append(getIdentity()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof UserGroup) ) return false;
		 UserGroup castOther = ( UserGroup ) other; 
         
		 return ( (this.getEntity()==castOther.getEntity()) || ( this.getEntity()!=null && castOther.getEntity()!=null && this.getEntity().equals(castOther.getEntity()) ) )
 && ( (this.getIdentity()==castOther.getIdentity()) || ( this.getIdentity()!=null && castOther.getIdentity()!=null && this.getIdentity().equals(castOther.getIdentity()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getEntity() == null ? 0 : this.getEntity().hashCode() );
         result = 37 * result + ( getIdentity() == null ? 0 : this.getIdentity().hashCode() );
         
         
         return result;
   }   





}
