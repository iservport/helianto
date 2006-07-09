package org.helianto.core;
// Generated 09/07/2006 19:17:11 by Hibernate Tools 3.1.0.beta4



/**
 * 			
 * <p>
 * Provides distinction among different services available to each <code>Entity</code>.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id: $
 * 				
 * 		
 */

public class Service  implements java.io.Serializable {


    // Fields    

     private int id;
     private String serviceName;


    // Constructors

    /** default constructor */
    public Service() {
    }

    
    /** full constructor */
    public Service(String serviceName) {
        this.serviceName = serviceName;
    }
    

   
    // Property accessors

    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public String getServiceName() {
        return this.serviceName;
    }
    
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("serviceName").append("='").append(getServiceName()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Service) ) return false;
		 Service castOther = ( Service ) other; 
         
		 return ( (this.getServiceName()==castOther.getServiceName()) || ( this.getServiceName()!=null && castOther.getServiceName()!=null && this.getServiceName().equals(castOther.getServiceName()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getServiceName() == null ? 0 : this.getServiceName().hashCode() );
         return result;
   }   





}
