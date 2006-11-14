package org.helianto.core;
// Generated 14/11/2006 21:06:50 by Hibernate Tools 3.1.0.beta5



/**
 * 			
 * <p>
 * Provides distinction among different services available to 
 * each <code>Entity</code>.
 * </p>
 * @author Mauricio Fernandes de Castro
 * 				
 * 		
 */
public class Service  implements java.io.Serializable {

    // Fields    

     private int id;
     private Operator operator;
     private String serviceName;

     // Constructors

    /** default constructor */
    public Service() {
    }

    /** full constructor */
    public Service(Operator operator, String serviceName) {
       this.operator = operator;
       this.serviceName = serviceName;
    }
    
   
    // Property accessors
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Operator getOperator() {
        return this.operator;
    }
    
    public void setOperator(Operator operator) {
        this.operator = operator;
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
      buffer.append("operator").append("='").append(getOperator()).append("' ");			
      buffer.append("serviceName").append("='").append(getServiceName()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }

   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Service) ) return false;
		 Service castOther = ( Service ) other; 
         
		 return ( (this.getOperator()==castOther.getOperator()) || ( this.getOperator()!=null && castOther.getOperator()!=null && this.getOperator().equals(castOther.getOperator()) ) )
 && ( (this.getServiceName()==castOther.getServiceName()) || ( this.getServiceName()!=null && castOther.getServiceName()!=null && this.getServiceName().equals(castOther.getServiceName()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getOperator() == null ? 0 : this.getOperator().hashCode() );
         result = 37 * result + ( getServiceName() == null ? 0 : this.getServiceName().hashCode() );
         return result;
   }   


}


