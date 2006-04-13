package org.helianto.core;
// Generated 13/04/2006 07:01:48 by Hibernate Tools 3.1.0.beta4



/**
 * 			
 * <p>
 * Persist roles.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id: helianto-core6.hbm.xml,v 1.3 2006/03/20 16:11:40 iserv Exp $
 * 				
 * 		
 */

public class Role  implements java.io.Serializable {


    // Fields    

     private int id;
     private Service service;
     private User user;
     private String roleName;


    // Constructors

    /** default constructor */
    public Role() {
    }

    
    /** full constructor */
    public Role(Service service, User user, String roleName) {
        this.service = service;
        this.user = user;
        this.roleName = roleName;
    }
    

   
    // Property accessors

    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public Service getService() {
        return this.service;
    }
    
    public void setService(Service service) {
        this.service = service;
    }

    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    public String getRoleName() {
        return this.roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Role) ) return false;
		 Role castOther = ( Role ) other; 
         
		 return ( (this.getService()==castOther.getService()) || ( this.getService()!=null && castOther.getService()!=null && this.getService().equals(castOther.getService()) ) )
 && ( (this.getUser()==castOther.getUser()) || ( this.getUser()!=null && castOther.getUser()!=null && this.getUser().equals(castOther.getUser()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getService() == null ? 0 : this.getService().hashCode() );
         result = 37 * result + ( getUser() == null ? 0 : this.getUser().hashCode() );
         
         return result;
   }   





}
