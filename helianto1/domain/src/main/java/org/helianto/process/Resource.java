package org.helianto.process;
// Generated 09/09/2006 21:08:17 by Hibernate Tools 3.1.0.beta4

import java.util.Set;
import org.helianto.core.Entity;
import org.helianto.core.Partner;


/**
 * 				
 * <p>
 * A class to represent a process resource.
 * </p>
 * @author Mauricio Fernandes de Castro
 * 				
 * 			
 */

public class Resource extends org.helianto.process.ResourceGroup implements java.io.Serializable {


    // Fields    

     private String serialNumber;
     private char resourceState;
     private Partner manufacturer;
     private Partner owner;
     private boolean keyResource;


    // Constructors

    /** default constructor */
    public Resource() {
    }

	/** minimal constructor */
    public Resource(Entity entity, String resourceCode, char resourceType, char resourceState, boolean keyResource) {
        super(entity, resourceCode, resourceType);        
        this.resourceState = resourceState;
        this.keyResource = keyResource;
    }
    
    /** full constructor */
    public Resource(Entity entity, String resourceCode, ResourceGroup parent, String resourceName, char resourceType, Set<ResourceGroup> resources, String serialNumber, char resourceState, Partner manufacturer, Partner owner, boolean keyResource) {
        super(entity, resourceCode, parent, resourceName, resourceType, resources);        
        this.serialNumber = serialNumber;
        this.resourceState = resourceState;
        this.manufacturer = manufacturer;
        this.owner = owner;
        this.keyResource = keyResource;
    }
    

   
    // Property accessors

    public String getSerialNumber() {
        return this.serialNumber;
    }
    
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public char getResourceState() {
        return this.resourceState;
    }
    
    public void setResourceState(char resourceState) {
        this.resourceState = resourceState;
    }

    public Partner getManufacturer() {
        return this.manufacturer;
    }
    
    public void setManufacturer(Partner manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Partner getOwner() {
        return this.owner;
    }
    
    public void setOwner(Partner owner) {
        this.owner = owner;
    }

    public boolean isKeyResource() {
        return this.keyResource;
    }
    
    public void setKeyResource(boolean keyResource) {
        this.keyResource = keyResource;
    }
   








}
