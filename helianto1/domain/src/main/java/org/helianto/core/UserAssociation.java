package org.helianto.core;
// Generated 18/02/2007 09:37:41 by Hibernate Tools 3.2.0.beta8



/**
 * 				
 * <p>
 * A class to hold user and user groups associations.
 * </p>
 * @author Mauricio Fernandes de Castro
 * 				
 * 		
 */
public class UserAssociation  implements java.io.Serializable {

    // Fields    

     private long id;
     private UserGroup parent;
     private UserGroup child;

     // Constructors

    /** default constructor */
    public UserAssociation() {
    }

    /** full constructor */
    public UserAssociation(UserGroup parent, UserGroup child) {
       this.parent = parent;
       this.child = child;
    }
   
    // Property accessors
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    public UserGroup getParent() {
        return this.parent;
    }
    
    public void setParent(UserGroup parent) {
        this.parent = parent;
    }
    public UserGroup getChild() {
        return this.child;
    }
    
    public void setChild(UserGroup child) {
        this.child = child;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof UserAssociation) ) return false;
		 UserAssociation castOther = ( UserAssociation ) other; 
         
		 return ( (this.getParent()==castOther.getParent()) || ( this.getParent()!=null && castOther.getParent()!=null && this.getParent().equals(castOther.getParent()) ) )
 && ( (this.getChild()==castOther.getChild()) || ( this.getChild()!=null && castOther.getChild()!=null && this.getChild().equals(castOther.getChild()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getParent() == null ? 0 : this.getParent().hashCode() );
         result = 37 * result + ( getChild() == null ? 0 : this.getChild().hashCode() );
         return result;
   }   


}


