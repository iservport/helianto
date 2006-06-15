package org.helianto.process;
// Generated 15/06/2006 16:32:04 by Hibernate Tools 3.1.0.beta4

import org.helianto.core.Entity;


/**
 * 			
 * <p>
 * A class to represent a process resource group.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id: iservport-process0.hbm.xml,v 1.2 2006/03/13 15:29:13 iserv Exp $
 * 				
 * 		
 */

public class ResourceGroup  implements java.io.Serializable {


    // Fields    

     private int id;
     private Entity entity;
     private String resourceCode;
     private ResourceGroup parent;
     private String resourceName;
     private char resourceType;


    // Constructors

    /** default constructor */
    public ResourceGroup() {
    }

	/** minimal constructor */
    public ResourceGroup(Entity entity, String resourceCode, char resourceType) {
        this.entity = entity;
        this.resourceCode = resourceCode;
        this.resourceType = resourceType;
    }
    
    /** full constructor */
    public ResourceGroup(Entity entity, String resourceCode, ResourceGroup parent, String resourceName, char resourceType) {
        this.entity = entity;
        this.resourceCode = resourceCode;
        this.parent = parent;
        this.resourceName = resourceName;
        this.resourceType = resourceType;
    }
    

   
    // Property accessors

    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public Entity getEntity() {
        return this.entity;
    }
    
    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public String getResourceCode() {
        return this.resourceCode;
    }
    
    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public ResourceGroup getParent() {
        return this.parent;
    }
    
    public void setParent(ResourceGroup parent) {
        this.parent = parent;
    }

    public String getResourceName() {
        return this.resourceName;
    }
    
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public char getResourceType() {
        return this.resourceType;
    }
    
    public void setResourceType(char resourceType) {
        this.resourceType = resourceType;
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
