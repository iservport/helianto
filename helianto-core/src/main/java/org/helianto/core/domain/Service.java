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

package org.helianto.core.domain;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.helianto.core.domain.type.RootEntity;
/**
 * A service made available in a name space (operator).
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="core_service",
    uniqueConstraints = {@UniqueConstraint(columnNames={"operatorId", "serviceName"})}
)
public class Service implements RootEntity {

    private static final long serialVersionUID = 1L;
    private int id;
    private Operator operator;
    private String serviceName;
    private String serviceExtensions;

    /** 
     * Empty constructor.
     */
    public Service() {
    	setServiceName("USER");
    }

    /** 
     * Key constructor.
     * 
     * @param operator
     * @param serviceName
     */
    public Service(Operator operator, String serviceName) {
    	this();
    	setOperator(operator);
    	setServiceName(serviceName);
    }

    /**
     * Primary key
     */
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Operator.
     */
    @ManyToOne
    @JoinColumn(name="operatorId", nullable=true)
    public Operator getOperator() {
        return this.operator;
    }
    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    /**
     * Service name.
     */
    @Column(length=12)
    public String getServiceName() {
        return this.serviceName;
    }
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    
    /**
     * Comma separated list of applicable service extension codes.
     */
    @Column(length=72)
    public String getServiceExtensions() {
		return serviceExtensions;
	}
    public void setServiceExtensions(String serviceExtensions) {
		this.serviceExtensions = serviceExtensions;
	}

    /**
     * Array of applicable service extension codes.
     */
	@Transient
	public String[] getServiceExtensionsAsArray() {
		if (getServiceExtensions()!=null) {
			return getServiceExtensions().replace(" ", "").split(",");
		}
		return new String[] {};
	}
	public void setServiceExtensionsAsArray(String[] natureArray) {
		setServiceExtensions(Arrays.deepToString(natureArray).replace("[", "").replace("]", "").replace(" ", ""));
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

   /**
    * equals
    */
   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
         if ( (other == null ) ) return false;
         if ( !(other instanceof Service) ) return false;
         Service castOther = (Service) other; 
         
         return ((this.getOperator()==castOther.getOperator()) || ( this.getOperator()!=null && castOther.getOperator()!=null && this.getOperator().equals(castOther.getOperator()) ))
             && ((this.getServiceName()==castOther.getServiceName()) || ( this.getServiceName()!=null && castOther.getServiceName()!=null && this.getServiceName().equals(castOther.getServiceName()) ));
   }
   
   /**
    * hashCode
    */
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getOperator() == null ? 0 : this.getOperator().hashCode() );
         result = 37 * result + ( getServiceName() == null ? 0 : this.getServiceName().hashCode() );
         return result;
   }   

}
