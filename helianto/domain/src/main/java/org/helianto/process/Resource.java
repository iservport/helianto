package org.helianto.process;
// Generated 24/04/2006 20:17:54 by Hibernate Tools 3.1.0.beta4

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

public class Resource  implements java.io.Serializable {


    // Fields    

     private int id;
     private Entity entity;
     private Resource parent;
     private String resourceCode;
     private String resourceName;
     private int resourceType;
     private int resourceState;


    // Constructors

    /** default constructor */
    public Resource() {
    }

	/** minimal constructor */
    public Resource(int resourceType, int resourceState) {
        this.resourceType = resourceType;
        this.resourceState = resourceState;
    }
    
    /** full constructor */
    public Resource(Entity entity, Resource parent, String resourceCode, String resourceName, int resourceType, int resourceState) {
        this.entity = entity;
        this.parent = parent;
        this.resourceCode = resourceCode;
        this.resourceName = resourceName;
        this.resourceType = resourceType;
        this.resourceState = resourceState;
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

    public Resource getParent() {
        return this.parent;
    }
    
    public void setParent(Resource parent) {
        this.parent = parent;
    }

    public String getResourceCode() {
        return this.resourceCode;
    }
    
    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public String getResourceName() {
        return this.resourceName;
    }
    
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public int getResourceType() {
        return this.resourceType;
    }
    
    public void setResourceType(int resourceType) {
        this.resourceType = resourceType;
    }

    public int getResourceState() {
        return this.resourceState;
    }
    
    public void setResourceState(int resourceState) {
        this.resourceState = resourceState;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Resource) ) return false;
		 Resource castOther = ( Resource ) other; 
         
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
