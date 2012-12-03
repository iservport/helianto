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

package org.helianto.resource.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.helianto.core.Controllable;
import org.helianto.core.Navigable;
import org.helianto.core.TrunkEntity;
import org.helianto.core.domain.Entity;
import org.helianto.resource.def.ResourceType;

/**
 * <p>
 * A class to represent a process resource group.
 * </p>
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="rsrc_resources",
    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "resourceCode"})}
)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="type",
    discriminatorType=DiscriminatorType.CHAR
)
@DiscriminatorValue("G")
public class ResourceGroup 

	implements TrunkEntity
	, Navigable
	, Comparable<ResourceGroup> 

{

	private static final long serialVersionUID = 1L;
	private int id;
    private Entity entity;
    private String resourceCode;
    private String resourceName;
    private String parentPath;
    private char resourceType;

    /** 
     * Default constructor.
     */
    public ResourceGroup() {
    	super();
    	setResourceTypeAsEnum(ResourceType.EQUIPMENT);
    	setResourceCode("");
    	setResourceName("");
    }

    /** 
     * Key constructor.
     * 
     * @param entity
     * @param resourceCode
     */
    public ResourceGroup(Entity entity, String resourceCode) {
    	this();
    	setEntity(entity);
    	setResourceCode(resourceCode);
    }

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Entity getter.
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
     * Resource code.
     */
    @Column(length=20)
    public String getResourceCode() {
        return this.resourceCode;
    }
    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }
    
    /**
     * <<Transient>> Make discriminator value available.
     */
    @Transient
    public char getDiscriminatorValue() {
    	return 'G';
    }
    
    /**
     * Resource name.
     */
    @Column(length=128)
    public String getResourceName() {
        return this.resourceName;
    }
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
    
    @Column(length=64)
    public String getParentPath() {
		return getInternalParentPath(parentPath);
	}
    public void setParentPath(String parentPath) {
		this.parentPath = parentPath;
	}
    
    /**
     * Subclasses overriding this method may have a parent path other than "/".
     * 
     * @param parentPath
     */
    @Transient
    protected String getInternalParentPath(String parentPath) {
    	return "/";
    }
    
    @Transient
    public String getCurrentPath() {
    	if (getParentPath()!=null) {
    		return new StringBuilder(getParentPath()).append(getResourceCode()).append("/").toString();
    	}
    	return "/";
    }

    /**
     * Resource type.
     */
    public char getResourceType() {
        return this.resourceType;
    }
    public void setResourceType(char resourceType) {
        this.resourceType = resourceType;
    }
    public void setResourceTypeAsEnum(ResourceType resourceType) {
        this.resourceType = resourceType.getValue();
    }
    
    //transient
    private Controllable controlReference;
    
    /**
     * <<Transient>> Control reference.
     */
    @Transient
    public Controllable getControlReference() {
		return controlReference;
	}
	public void setControlReference(Controllable controlReference) {
		this.controlReference = controlReference;
	}

    public int compareTo(ResourceGroup other) {
    	if (getResourceCode()!=null && other.getResourceCode()!=null) {
    		return getResourceCode().compareTo(other.getResourceCode());
    	}
    	return 0;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("resourceCode").append("='").append(getResourceCode()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }

   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ResourceGroup) ) return false;
		 ResourceGroup castOther = ( ResourceGroup ) other; 
         
		 return ( (this.getEntity()==castOther.getEntity()) || ( this.getEntity()!=null && castOther.getEntity()!=null && this.getEntity().equals(castOther.getEntity()) ) )
             && ( (this.getResourceCode()==castOther.getResourceCode()) || ( this.getResourceCode()!=null && castOther.getResourceCode()!=null && this.getResourceCode().equals(castOther.getResourceCode()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getResourceCode() == null ? 0 : this.getResourceCode().hashCode() );
         return result;
   }

}


