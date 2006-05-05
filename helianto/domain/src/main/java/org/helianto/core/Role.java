package org.helianto.core;
// Generated 05/05/2006 07:19:20 by Hibernate Tools 3.1.0.beta4



/**
 * 			
 * <p>
 * Persist roles.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id: helianto-core6.hbm.xml 70 2006-04-16 21:21:57 -0300 (Dom, 16 Abr 2006) iserv $
 * 				
 * 		
 */

public class Role  implements java.io.Serializable {


    // Fields    

     private int id;
     private User user;
     private String roleName;
     private Service service;


    // Constructors

    /** default constructor */
    public Role() {
    }

	/** minimal constructor */
    public Role(User user, String roleName) {
        this.user = user;
        this.roleName = roleName;
    }
    
    /** full constructor */
    public Role(User user, String roleName, Service service) {
        this.user = user;
        this.roleName = roleName;
        this.service = service;
    }
    

   
    // Property accessors

    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
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

    public Service getService() {
        return this.service;
    }
    
    public void setService(Service service) {
        this.service = service;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Role) ) return false;
		 Role castOther = ( Role ) other; 
         
		 return ( (this.getUser()==castOther.getUser()) || ( this.getUser()!=null && castOther.getUser()!=null && this.getUser().equals(castOther.getUser()) ) )
 && ( (this.getRoleName()==castOther.getRoleName()) || ( this.getRoleName()!=null && castOther.getRoleName()!=null && this.getRoleName().equals(castOther.getRoleName()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getUser() == null ? 0 : this.getUser().hashCode() );
         result = 37 * result + ( getRoleName() == null ? 0 : this.getRoleName().hashCode() );
         
         return result;
   }   





}
