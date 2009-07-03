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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.helianto.core.Entity;
import org.helianto.core.Unit;

/**	
 * <p>
 * A class to represent resource parameters.
 * </p>
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="proc_params",
    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "parameterCode"})}
)
public class ResourceParameter implements java.io.Serializable {

    /**
     * <code>ResourceParameter</code> factory.
     * 
     * @param entity
     * @param parameterCode
     */
    public static ResourceParameter resourceParameterFactory(Entity entity, String parameterCode) {
    	ResourceParameter resourceParameter = new ResourceParameter();
		resourceParameter.setEntity(entity);
		resourceParameter.setParameterCode(parameterCode);
        return resourceParameter;
    }

    /**
     * <code>ResourceParameter</code> factory.
     * 
     * @param entity
     * @param parameterCode
     */
    public static ResourceParameter resourceParameterFactory(ResourceParameter parent, String parameterCode) {
    	if (parent==null) {
    		throw new IllegalArgumentException("Parent resource parameter should not be null!");
    	}
    	ResourceParameter resourceParameter = resourceParameterFactory(parent.getEntity(), parameterCode);
    	resourceParameter.setParent(parent);
    	resourceParameter.setUnit(parent.getUnit());
    	return resourceParameter;
    }

	private static final long serialVersionUID = 1L;
    private int id;
    private Entity entity;
    private String parameterCode;
    private ResourceParameter parent;
    private Unit unit;
    private String paramName;
    private String paramDesc;
    private boolean textOnly;

    /** default constructor */
    public ResourceParameter() {
    }

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Entity.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="entityId", nullable=true)
    public Entity getEntity() {
        return this.entity;
    }
    public void setEntity(Entity entity) {
        this.entity = entity;
    }
    
    /**
     * Parameter code.
     */
    @Column(length=20)
    public String getParameterCode() {
        return this.parameterCode;
    }
    public void setParameterCode(String parameterCode) {
        this.parameterCode = parameterCode;
    }
    
    /**
     * Parent.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="parentId", nullable=true)
    public ResourceParameter getParent() {
        return this.parent;
    }
    public void setParent(ResourceParameter parent) {
        this.parent = parent;
    }
    
    /**
     * Unit.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="unitId", nullable=true)
    public Unit getUnit() {
        return this.unit;
    }
    public void setUnit(Unit unit) {
        this.unit = unit;
    }
    
    /**
     * Parameter name.
     */
    @Column(length=64)
    public String getParamName() {
        return this.paramName;
    }
    public void setParamName(String paramName) {
        this.paramName = paramName;
    }
    
    /**
     * Parameter description.
     */
    @Column(length=256)
    public String getParamDesc() {
        return this.paramDesc;
    }
    public void setParamDesc(String paramDesc) {
        this.paramDesc = paramDesc;
    }
    
    /**
     * Text only flag.
     */
    public boolean isTextOnly() {
        return this.textOnly;
    }
    public void setTextOnly(boolean textOnly) {
        this.textOnly = textOnly;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("parameterCode").append("='").append(getParameterCode()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }

   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ResourceParameter) ) return false;
		 ResourceParameter castOther = ( ResourceParameter ) other; 
         
		 return ( (this.getEntity()==castOther.getEntity()) || ( this.getEntity()!=null && castOther.getEntity()!=null && this.getEntity().equals(castOther.getEntity()) ) )
             && ( (this.getParameterCode()==castOther.getParameterCode()) || ( this.getParameterCode()!=null && castOther.getParameterCode()!=null && this.getParameterCode().equals(castOther.getParameterCode()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getParameterCode() == null ? 0 : this.getParameterCode().hashCode() );
         return result;
   }   

}


