package org.helianto.process.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.helianto.resource.domain.ResourceGroup;


/**
 * Represents a <code>Setup</code>.
 * 
 * <p>
 * The operation setup must be able to relate both <tt>Resource</tt>s
 * and <tt>ResourceGroup</tt>s. Besides the operation itself is not
 * likely runnable on a resource group, such flexibility allows the 
 * actual choice to be delegated to a subsequent planning phase.
 * </p>
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="proc_setup",
    uniqueConstraints = {@UniqueConstraint(columnNames={"operationId", "resourceId"})}
)
public class Setup  implements java.io.Serializable, Comparable<Setup> {

    private static final long serialVersionUID = 1L;
    private int id;
    private Operation operation;
    private ResourceGroup resource;
    private int priority;
    private long setupTime;
    private long transportTime;

    /** 
     * Default constructor.
     */
    public Setup() {
    	setPriority(0);
    }

    /** 
     * Key constructor.
     * 
     * @param entity
     * @param docCode
     */
    public Setup(Operation operation, ResourceGroup resourceGroup) {
    	this();
    	setOperation(operation);
    	setResource(resourceGroup);
    }

    /**
     * Primary key.
     */
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Parent operation.
     */
    @ManyToOne
    @JoinColumn(name="operationId", nullable=true)
    public Operation getOperation() {
		return operation;
	}
	public void setOperation(Operation operation) {
		this.operation = operation;
	}

    /**
     * Related resource or resource group.
     */
    @ManyToOne
    @JoinColumn(name="resourceId", nullable=true)
    public ResourceGroup getResource() {
        return this.resource;
    }
    public void setResource(ResourceGroup resource) {
        this.resource = resource;
    }
    @Transient
    public String getResourceCode() {
    	if(getResource()!=null) {
    		return getResource().getResourceCode();
    	}
    	return "";
    }

    /**
     * Priority.
     */
    public int getPriority() {
        return this.priority;
    }
    public void setPriority(int priority) {
        this.priority = priority;
    }
    
    /**
     * Setup time.
     */
    public long getSetupTime() {
        return this.setupTime;
    }
    public void setSetupTime(long setupTime) {
        this.setupTime = setupTime;
    }
    
    /**
     * Transport time.
     */
    public long getTransportTime() {
        return this.transportTime;
    }
    public void setTransportTime(long transportTime) {
        this.transportTime = transportTime;
    }
    
    public int compareTo(Setup next) {
    	if (this.priority==next.priority) {
    		return (int) (this.setupTime - next.setupTime);
    	}
    	return this.priority - next.priority;
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


