package org.helianto.core;
// Generated 05/12/2006 07:28:32 by Hibernate Tools 3.2.0.beta8


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
 * 				
 * 		
 */
public class UserGroup  implements java.io.Serializable {

    // Fields    

     private long id;
     private Entity entity;
     private Identity identity;
     private char userState;
     private UserGroup parent;
     private Set<UserGroup> children = new HashSet<UserGroup>(0);
     private Set<UserRole> roles = new HashSet<UserRole>(0);

     // Constructors

    /** default constructor */
    public UserGroup() {
    }

	/** minimal constructor */
    public UserGroup(Entity entity, Identity identity, char userState) {
        this.entity = entity;
        this.identity = identity;
        this.userState = userState;
    }
    /** full constructor */
    public UserGroup(Entity entity, Identity identity, char userState, UserGroup parent, Set<UserGroup> children, Set<UserRole> roles) {
       this.entity = entity;
       this.identity = identity;
       this.userState = userState;
       this.parent = parent;
       this.children = children;
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
    public char getUserState() {
        return this.userState;
    }
    
    public void setUserState(char userState) {
        this.userState = userState;
    }
    public UserGroup getParent() {
        return this.parent;
    }
    
    public void setParent(UserGroup parent) {
        this.parent = parent;
    }
    public Set<UserGroup> getChildren() {
        return this.children;
    }
    
    public void setChildren(Set<UserGroup> children) {
        this.children = children;
    }
    public Set<UserRole> getRoles() {
        return this.roles;
    }
    
    public void setRoles(Set<UserRole> roles) {
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


