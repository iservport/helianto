package org.helianto.process;
// Generated 23/05/2006 20:22:11 by Hibernate Tools 3.1.0.beta4

import org.helianto.core.Entity;
import org.helianto.core.Partner;


/**
 * 				
 * <p>
 * A class to represent a process resource.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id: iservport-process0.hbm.xml,v 1.2 2006/03/13 15:29:13 iserv Exp $
 * 				
 * 			
 */

public class Resource extends org.helianto.process.ResourceGroup implements java.io.Serializable {


    // Fields    

     private String serialNumber;
     private int resourceState;
     private Partner manufacturer;


    // Constructors

    /** default constructor */
    public Resource() {
    }

	/** minimal constructor */
    public Resource(int resourceType, int resourceState) {
        super(resourceType);        
        this.resourceState = resourceState;
    }
    
    /** full constructor */
    public Resource(Entity entity, ResourceGroup parent, String resourceCode, String resourceName, int resourceType, String serialNumber, int resourceState, Partner manufacturer) {
        super(entity, parent, resourceCode, resourceName, resourceType);        
        this.serialNumber = serialNumber;
        this.resourceState = resourceState;
        this.manufacturer = manufacturer;
    }
    

   
    // Property accessors

    public String getSerialNumber() {
        return this.serialNumber;
    }
    
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getResourceState() {
        return this.resourceState;
    }
    
    public void setResourceState(int resourceState) {
        this.resourceState = resourceState;
    }

    public Partner getManufacturer() {
        return this.manufacturer;
    }
    
    public void setManufacturer(Partner manufacturer) {
        this.manufacturer = manufacturer;
    }
   








}
