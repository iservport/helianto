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

package org.helianto.resource;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * <p>
 * A class to represent parameters values.
 * </p>
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="rsrc_resparamvalue",
    uniqueConstraints = {@UniqueConstraint(columnNames={"resourceId", "parameterId"})}
)
public class ResourceParameterValue  implements java.io.Serializable {

	/**
	 * Fatory method.
	 * 
	 * @param resourceGroup
	 * @param resourceParameter
	 */
    public static ResourceParameterValue resourceParameterValueFactory(ResourceGroup resourceGroup, ResourceParameter resourceParameter) {
        ResourceParameterValue resourceParameterValue = new ResourceParameterValue();
		resourceParameterValue.setResource(resourceGroup);
		resourceParameterValue.setParameter(resourceParameter);
    	return resourceParameterValue;
    }

	private static final long serialVersionUID = 1L;
	private int id;
    private ResourceGroup resource;
    private ResourceParameter parameter;
    private BigDecimal parameterNumericValue;
    private String parameterTextValue;
    private boolean suppressed;

    /** default constructor */
    public ResourceParameterValue() {
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
     * Resource group.
     */
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="resourceId", nullable=true)
    public ResourceGroup getResource() {
        return this.resource;
    }
    public void setResource(ResourceGroup resource) {
        this.resource = resource;
    }
    
    /**
     * Resource parameter.
     */
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="parameterId", nullable=true)
    public ResourceParameter getParameter() {
        return this.parameter;
    }
    public void setParameter(ResourceParameter parameter) {
        this.parameter = parameter;
    }
    
    public BigDecimal getParameterNumericValue() {
        return this.parameterNumericValue;
    }
    public void setParameterNumericValue(BigDecimal parameterNumericValue) {
        this.parameterNumericValue = parameterNumericValue;
    }
    
    public String getParameterTextValue() {
        return this.parameterTextValue;
    }
    public void setParameterTextValue(String parameterTextValue) {
        this.parameterTextValue = parameterTextValue;
    }
    
    public boolean isSuppressed() {
        return this.suppressed;
    }
    public void setSuppressed(boolean suppressed) {
        this.suppressed = suppressed;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("resource").append("='").append(getResource()).append("' ");			
      buffer.append("parameter").append("='").append(getParameter()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }

   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ResourceParameterValue) ) return false;
		 ResourceParameterValue castOther = ( ResourceParameterValue ) other; 
         
		 return ( (this.getResource()==castOther.getResource()) || ( this.getResource()!=null && castOther.getResource()!=null && this.getResource().equals(castOther.getResource()) ) )
 && ( (this.getParameter()==castOther.getParameter()) || ( this.getParameter()!=null && castOther.getParameter()!=null && this.getParameter().equals(castOther.getParameter()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getResource() == null ? 0 : this.getResource().hashCode() );
         result = 37 * result + ( getParameter() == null ? 0 : this.getParameter().hashCode() );
         return result;
   }   


}


