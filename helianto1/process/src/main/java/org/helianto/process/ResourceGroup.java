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

package org.helianto.process;

import java.util.HashSet;
import java.util.Set;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.helianto.core.Entity;

/**
 * <p>
 * A class to represent a process resource group.
 * </p>
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="proc_resources",
    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "resourceCode"})}
)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="type",
    discriminatorType=DiscriminatorType.CHAR
)
@DiscriminatorValue("G")
public class ResourceGroup implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
    private Entity entity;
    private String resourceCode;
    private ResourceGroup parent;
    private String resourceName;
    private char resourceType;
    private Set<ResourceGroup> resources = new HashSet<ResourceGroup>(0);

    /** default constructor */
    public ResourceGroup() {
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
     * Parent.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="parentId", nullable=true)
    public ResourceGroup getParent() {
        return this.parent;
    }
    public void setParent(ResourceGroup parent) {
        this.parent = parent;
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
    
    /**
     * Resource type.
     */
    public char getResourceType() {
        return this.resourceType;
    }
    public void setResourceType(char resourceType) {
        this.resourceType = resourceType;
    }
    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType.getValue();
    }
    
    /**
     * Set of child <code>ResourceGroup</code>.
     */
    @OneToMany(mappedBy="parent", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
     public Set<ResourceGroup> getResources() {
        return this.resources;
    }
    public void setResources(Set<ResourceGroup> resources) {
        this.resources = resources;
    }

    /**
     * <code>ResourceGroup</code> factory.
     * 
     * @param clazz
     * @param entity
     * @param resourceCode
     */
    public static <T extends ResourceGroup> T resourceGroupFactory(Class<T> clazz, Entity entity, String resourceCode) {
        T resourceGroup = null;
        try {
        	resourceGroup = clazz.newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to create document of class "+clazz);
        }
        resourceGroup.setEntity(entity);
        resourceGroup.setResourceCode(resourceCode);
        resourceGroup.setResourceType(ResourceType.EQUIPMENT);
        return resourceGroup;
    }

    /**
     * <code>ResourceGroup</code> factory.
     * 
     * @param entity
     * @param resourceCode
     */
    public static ResourceGroup resourceGroupFactory(Entity entity, String resourceCode) {
        return resourceGroupFactory(ResourceGroup.class, entity, resourceCode);
    }

    /**
     * <code>ResourceGroup</code> factory.
     * 
     * @param parent
     * @param resourceCode
     */
    public static ResourceGroup resourceGroupFactory(ResourceGroup parent, String resourceCode) {
    	ResourceGroup resourceGroup =  resourceGroupFactory(ResourceGroup.class, parent.getEntity(), resourceCode);
    	resourceGroup.setParent(parent);
    	resourceGroup.setResourceType(parent.getResourceType());
    	return resourceGroup;
    }

    /**
     * <code>ResourceGroup</code> query <code>StringBuilder</code>.
     */
    @Transient
    public static StringBuilder getResourceGroupQueryStringBuilder() {
        return new StringBuilder("select resourceGroup from ResourceGroup resourceGroup ");
    }

    /**
     * <code>ResourceGroup</code> natural id query.
     */
    @Transient
    public static String getResourceGroupNaturalIdQueryString() {
        return getResourceGroupQueryStringBuilder().append("where resourceGroup.entity = ? and resourceGroup.resourceCode = ? ").toString();
    }
    
    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("entity").append("='").append(getEntity()).append("' ");			
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
         result = 37 * result + ( getEntity() == null ? 0 : this.getEntity().hashCode() );
         result = 37 * result + ( getResourceCode() == null ? 0 : this.getResourceCode().hashCode() );
         return result;
   }   

}


