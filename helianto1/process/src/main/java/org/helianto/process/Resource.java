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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.helianto.core.Entity;
import org.helianto.partner.Partner;

/**			
 * <p>
 * A class to represent a process resource.
 * </p>
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@DiscriminatorValue("R")
public class Resource extends ResourceGroup implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private String serialNumber;
    private char resourceState;
    private Partner manufacturer;
    private Partner owner;
    private boolean keyResource;

     // Constructors

    /** default constructor */
    public Resource() {
    }

    /**
     * Resource serial number.
     */
    @Column(length=20)
    public String getSerialNumber() {
        return this.serialNumber;
    }
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
    
    /**
     * Resource state.
     */
    public char getResourceState() {
        return this.resourceState;
    }
    public void setResourceState(char resourceState) {
        this.resourceState = resourceState;
    }
    public void setResourceState(ResourceState resourceState) {
        this.resourceState = resourceState.getValue();
    }

    /**
     * Resource manufacturer.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="manufaturerId", nullable=true)
    public Partner getManufacturer() {
        return this.manufacturer;
    }
    public void setManufacturer(Partner manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * Resource owner.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="ownerId", nullable=true)
    public Partner getOwner() {
        return this.owner;
    }
    public void setOwner(Partner owner) {
        this.owner = owner;
    }
    
    /**
     * Resource classified as key resource.
     */
    public boolean isKeyResource() {
        return this.keyResource;
    }
    public void setKeyResource(boolean keyResource) {
        this.keyResource = keyResource;
    }
    
    /**
     * <code>Resource</code> factory.
     * 
     * @param entity
     * @param resourceCode
     */
    public static Resource resourceGroupFactory(Entity entity, String resourceCode) {
        return resourceGroupFactory(Resource.class, entity, resourceCode);
    }

    /**
     * <code>Resource</code> factory.
     * 
     * @param parent
     * @param resourceCode
     */
    public static Resource resourceGroupFactory(ResourceGroup parent, String resourceCode) {
    	Resource resource =  resourceGroupFactory(Resource.class, parent.getEntity(), resourceCode);
    	resource.setParent(parent);
    	resource.setResourceType(parent.getResourceType());
    	return resource;
    }

    /**
     * <code>Resource</code> query <code>StringBuilder</code>.
     */
    @Transient
    public static StringBuilder getResourceQueryStringBuilder() {
        return new StringBuilder("select resource from Resource resource ");
    }

    /**
     * <code>Resource</code> natural id query.
     */
    @Transient
    public static String getResourceNaturalIdQueryString() {
        return getResourceQueryStringBuilder().append("where resource.entity = ? and resource.resourceCode = ? ").toString();
    }
    
    public boolean equals(Object other) {
		 if ( !(other instanceof Resource) ) return false;
		 return super.equals(other);
  }
  
}


