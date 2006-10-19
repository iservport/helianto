package org.helianto.core;
// Generated 18/10/2006 20:52:47 by Hibernate Tools 3.1.0.beta5



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
     private User user;
     private Service service;
     private String serviceExtension;

     // Constructors

    /** default constructor */
    public UserRole() {
    }

    /** full constructor */
    public UserRole(User user, Service service, String serviceExtension) {
       this.user = user;
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
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
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
      buffer.append("user").append("='").append(getUser()).append("' ");			
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
         
		 return ( (this.getUser()==castOther.getUser()) || ( this.getUser()!=null && castOther.getUser()!=null && this.getUser().equals(castOther.getUser()) ) )
 && ( (this.getService()==castOther.getService()) || ( this.getService()!=null && castOther.getService()!=null && this.getService().equals(castOther.getService()) ) )
 && ( (this.getServiceExtension()==castOther.getServiceExtension()) || ( this.getServiceExtension()!=null && castOther.getServiceExtension()!=null && this.getServiceExtension().equals(castOther.getServiceExtension()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getUser() == null ? 0 : this.getUser().hashCode() );
         result = 37 * result + ( getService() == null ? 0 : this.getService().hashCode() );
         result = 37 * result + ( getServiceExtension() == null ? 0 : this.getServiceExtension().hashCode() );
         return result;
   }   


}


