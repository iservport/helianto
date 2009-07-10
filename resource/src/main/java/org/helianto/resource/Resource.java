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
import javax.persistence.DiscriminatorValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.helianto.core.Entity;
import org.helianto.partner.Partner;

/**			
 * A process resource.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@DiscriminatorValue("R")
public class Resource extends ResourceGroup implements java.io.Serializable {

    /**
     * Factory method.
     * 
     * @param entity
     * @param resourceCode
     */
    public static Resource resourceFactory(Entity entity, String resourceCode) {
        return resourceGroupFactory(Resource.class, entity, resourceCode);
    }

    private static final long serialVersionUID = 1L;
    private String serialNumber;
    private char resourceState;
    private Partner manufacturer;
    private Partner owner;
    private boolean keyResource;

    /** default constructor */
    protected Resource() {
    	super();
    }

    /**
     * <<Transient>> Make discriminator value available.
     */
    @Transient
    @Override
    public char getDiscriminatorValue() {
    	return 'R';
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
    @Transient
    public String getManufacturerAlias() {
    	if (getManufacturer()!=null) {
    		return getManufacturer().getPartnerAlias();
    	}
    	return "";
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
    @Transient
    public String getOwnerAlias() {
    	if (getOwner()!=null) {
    		return getOwner().getPartnerAlias();
    	}
    	return "";
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
    
    public boolean equals(Object other) {
		 if ( !(other instanceof Resource) ) return false;
		 return super.equals(other);
  }
  
}


