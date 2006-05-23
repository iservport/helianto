package org.helianto.process;
// Generated 23/05/2006 17:52:22 by Hibernate Tools 3.1.0.beta4

import org.helianto.core.Entity;


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
    public Resource(Entity entity, ResourceGroup parent, String resourceCode, String resourceName, int resourceType, String serialNumber, int resourceState) {
        super(entity, parent, resourceCode, resourceName, resourceType);        
        this.serialNumber = serialNumber;
        this.resourceState = resourceState;
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
   








}
