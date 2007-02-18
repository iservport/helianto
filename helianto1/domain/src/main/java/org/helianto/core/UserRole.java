package org.helianto.core;
// Generated 18/02/2007 09:37:41 by Hibernate Tools 3.2.0.beta8



/**
 * 			
 * <p>
 * Represent roles an <code>User</code> can play.
 * </p>
 * @author Mauricio Fernandes de Castro
 * 				
 * 		
 */
public class UserRole  implements java.io.Serializable {

    // Fields    

     private long id;
     private UserGroup userGroup;
     private Service service;
     private String serviceExtension;

     // Constructors

    /** default constructor */
    public UserRole() {
    }

    /** full constructor */
    public UserRole(UserGroup userGroup, Service service, String serviceExtension) {
       this.userGroup = userGroup;
       this.service = service;
       this.serviceExtension = serviceExtension;
    }
   
    // Property accessors
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    public UserGroup getUserGroup() {
        return this.userGroup;
    }
    
    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }
    public Service getService() {
        return this.service;
    }
    
    public void setService(Service service) {
        this.service = service;
    }
    public String getServiceExtension() {
        return this.serviceExtension;
    }
    
    public void setServiceExtension(String serviceExtension) {
        this.serviceExtension = serviceExtension;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("userGroup").append("='").append(getUserGroup()).append("' ");			
      buffer.append("service").append("='").append(getService()).append("' ");			
      buffer.append("serviceExtension").append("='").append(getServiceExtension()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }

   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof UserRole) ) return false;
		 UserRole castOther = ( UserRole ) other; 
         
		 return ( (this.getUserGroup()==castOther.getUserGroup()) || ( this.getUserGroup()!=null && castOther.getUserGroup()!=null && this.getUserGroup().equals(castOther.getUserGroup()) ) )
 && ( (this.getService()==castOther.getService()) || ( this.getService()!=null && castOther.getService()!=null && this.getService().equals(castOther.getService()) ) )
 && ( (this.getServiceExtension()==castOther.getServiceExtension()) || ( this.getServiceExtension()!=null && castOther.getServiceExtension()!=null && this.getServiceExtension().equals(castOther.getServiceExtension()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getUserGroup() == null ? 0 : this.getUserGroup().hashCode() );
         result = 37 * result + ( getService() == null ? 0 : this.getService().hashCode() );
         result = 37 * result + ( getServiceExtension() == null ? 0 : this.getServiceExtension().hashCode() );
         return result;
   }   


}


