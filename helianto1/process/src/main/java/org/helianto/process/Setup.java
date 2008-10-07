package org.helianto.process;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
/* Copyright 2005 I Serv Consultoria Empresarial Ltda.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

import javax.persistence.UniqueConstraint;

/**
 * <p>
 * Represents a <code>Setup</code> in a <code>Resource</code>.  
 * </p>
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="proc_setup",
    uniqueConstraints = {@UniqueConstraint(columnNames={"operationId", "resourceId"})}
)
public class Setup  implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private Operation operation;
    private Resource resource;
    private int priority;
    private long setupTime;
    private long transportTime;

     // Constructors

    /** default constructor */
    public Setup() {
    }

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
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="operationId", nullable=true)
    public Operation getOperation() {
		return operation;
	}
	public void setOperation(Operation operation) {
		this.operation = operation;
	}

    /**
     * Related resource.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="resourceId", nullable=true)
    public Resource getResource() {
        return this.resource;
    }
    public void setResource(Resource resource) {
        this.resource = resource;
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


    /**
     * <code>Setup</code> query <code>StringBuilder</code>.
     */
    @Transient
    public static StringBuilder getSetupQueryStringBuilder() {
        return new StringBuilder("select setup from Setup setup ");
    }

    /**
     * <code>Setup</code> natural id query.
     */
    @Transient
    public static String getSetupNaturalIdQueryString() {
        return getSetupQueryStringBuilder().append("where setup.operation = ? and setup.resource = ? ").toString();
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


