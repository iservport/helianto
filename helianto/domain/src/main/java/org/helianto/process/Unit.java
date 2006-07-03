package org.helianto.process;
// Generated 03/07/2006 15:46:45 by Hibernate Tools 3.1.0.beta4

import org.helianto.core.Entity;


/**
 * 				
 * <p>
 * A class to represent a unit.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id: iservport-process1.hbm.xml,v 1.2 2006/03/13 15:29:13 iserv Exp $
 * 				
 * 		
 */

public class Unit  implements java.io.Serializable {


    // Fields    

     private int id;
     private Entity entity;
     private Unit parent;
     private String unitCode;
     private String unitName;


    // Constructors

    /** default constructor */
    public Unit() {
    }

	/** minimal constructor */
    public Unit(Entity entity, String unitCode) {
        this.entity = entity;
        this.unitCode = unitCode;
    }
    
    /** full constructor */
    public Unit(Entity entity, Unit parent, String unitCode, String unitName) {
        this.entity = entity;
        this.parent = parent;
        this.unitCode = unitCode;
        this.unitName = unitName;
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

    public Unit getParent() {
        return this.parent;
    }
    
    public void setParent(Unit parent) {
        this.parent = parent;
    }

    public String getUnitCode() {
        return this.unitCode;
    }
    
    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getUnitName() {
        return this.unitName;
    }
    
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("unitCode").append("='").append(getUnitCode()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Unit) ) return false;
		 Unit castOther = ( Unit ) other; 
         
		 return ( (this.getEntity()==castOther.getEntity()) || ( this.getEntity()!=null && castOther.getEntity()!=null && this.getEntity().equals(castOther.getEntity()) ) )
 && ( (this.getUnitCode()==castOther.getUnitCode()) || ( this.getUnitCode()!=null && castOther.getUnitCode()!=null && this.getUnitCode().equals(castOther.getUnitCode()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getEntity() == null ? 0 : this.getEntity().hashCode() );
         
         result = 37 * result + ( getUnitCode() == null ? 0 : this.getUnitCode().hashCode() );
         
         return result;
   }   





}
