package org.helianto.process;
// Generated 08/03/2007 19:38:51 by Hibernate Tools 3.2.0.beta8



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
     private int priority;
     private long setupTime;
     private long transportTime;

     // Constructors

    /** default constructor */
    public Setup() {
    }

    /** full constructor */
    public Setup(Resource resource, int priority, long setupTime, long transportTime) {
       this.resource = resource;
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
 && (this.getPriority()==castOther.getPriority());
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getResource() == null ? 0 : this.getResource().hashCode() );
         result = 37 * result + this.getPriority();
         
         
         return result;
   }   


}


