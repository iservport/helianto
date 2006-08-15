package org.helianto.process;
// Generated 15/08/2006 11:35:55 by Hibernate Tools 3.1.0.beta4



/**
 * 				
 * <p>
 * A class to represent a setup.
 * </p>
 * @author Mauricio Fernandes de Castro
 * 				
 * 		
 */

public class Setup  implements java.io.Serializable {


    // Fields    

     private long id;
     private Resource resource;
     private Operation operation;
     private int priority;
     private long setupTime;
     private long transportTime;


    // Constructors

    /** default constructor */
    public Setup() {
    }

    
    /** full constructor */
    public Setup(Resource resource, Operation operation, int priority, long setupTime, long transportTime) {
        this.resource = resource;
        this.operation = operation;
        this.priority = priority;
        this.setupTime = setupTime;
        this.transportTime = transportTime;
    }
    

   
    // Property accessors

    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }

    public Resource getResource() {
        return this.resource;
    }
    
    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public Operation getOperation() {
        return this.operation;
    }
    
    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public int getPriority() {
        return this.priority;
    }
    
    public void setPriority(int priority) {
        this.priority = priority;
    }

    public long getSetupTime() {
        return this.setupTime;
    }
    
    public void setSetupTime(long setupTime) {
        this.setupTime = setupTime;
    }

    public long getTransportTime() {
        return this.transportTime;
    }
    
    public void setTransportTime(long transportTime) {
        this.transportTime = transportTime;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Setup) ) return false;
		 Setup castOther = ( Setup ) other; 
         
		 return ( (this.getResource()==castOther.getResource()) || ( this.getResource()!=null && castOther.getResource()!=null && this.getResource().equals(castOther.getResource()) ) )
 && ( (this.getOperation()==castOther.getOperation()) || ( this.getOperation()!=null && castOther.getOperation()!=null && this.getOperation().equals(castOther.getOperation()) ) )
 && (this.getPriority()==castOther.getPriority());
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getResource() == null ? 0 : this.getResource().hashCode() );
         result = 37 * result + ( getOperation() == null ? 0 : this.getOperation().hashCode() );
         result = 37 * result + this.getPriority();
         
         
         return result;
   }   





}
