package org.helianto.process;
// Generated 08/03/2007 19:38:51 by Hibernate Tools 3.2.0.beta8


import org.helianto.core.Entity;
import org.helianto.core.Unit;

/**
 * 				
 * <p>
 * A class to represent a material type.
 * </p>
 * @author Mauricio Fernandes de Castro
 * 				
 * 		
 */
public class MaterialType  implements java.io.Serializable {

    // Fields    

     private long id;
     private Entity entity;
     private long internalNumber;
     private MaterialType parent;
     private Unit materialUnit;
     private String materialName;

     // Constructors

    /** default constructor */
    public MaterialType() {
    }

	/** minimal constructor */
    public MaterialType(Unit materialUnit) {
        this.materialUnit = materialUnit;
    }
    /** full constructor */
    public MaterialType(Entity entity, long internalNumber, MaterialType parent, Unit materialUnit, String materialName) {
       this.entity = entity;
       this.internalNumber = internalNumber;
       this.parent = parent;
       this.materialUnit = materialUnit;
       this.materialName = materialName;
    }
   
    // Property accessors
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    public Entity getEntity() {
        return this.entity;
    }
    
    public void setEntity(Entity entity) {
        this.entity = entity;
    }
    public long getInternalNumber() {
        return this.internalNumber;
    }
    
    public void setInternalNumber(long internalNumber) {
        this.internalNumber = internalNumber;
    }
    public MaterialType getParent() {
        return this.parent;
    }
    
    public void setParent(MaterialType parent) {
        this.parent = parent;
    }
    public Unit getMaterialUnit() {
        return this.materialUnit;
    }
    
    public void setMaterialUnit(Unit materialUnit) {
        this.materialUnit = materialUnit;
    }
    public String getMaterialName() {
        return this.materialName;
    }
    
    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof MaterialType) ) return false;
		 MaterialType castOther = ( MaterialType ) other; 
         
		 return ( (this.getEntity()==castOther.getEntity()) || ( this.getEntity()!=null && castOther.getEntity()!=null && this.getEntity().equals(castOther.getEntity()) ) )
 && (this.getInternalNumber()==castOther.getInternalNumber());
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getEntity() == null ? 0 : this.getEntity().hashCode() );
         result = 37 * result + (int) this.getInternalNumber();
         
         
         
         return result;
   }   


}


