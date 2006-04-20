package org.helianto.core;
// Generated 20/04/2006 07:25:59 by Hibernate Tools 3.1.0.beta4



/**
 * 			
 * <p>
 * A domain class to accept different key numbers for each <code>Entity</code>.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id: helianto-entity1.hbm.xml,v 1.3 2006/03/20 16:11:40 iserv Exp $
 * 				
 * 		
 */

public class EntityKey  implements java.io.Serializable {


    // Fields    

     private int id;
     private Entity entity;
     private int keyType;
     private String keyNumber;


    // Constructors

    /** default constructor */
    public EntityKey() {
    }

    
    /** full constructor */
    public EntityKey(Entity entity, int keyType, String keyNumber) {
        this.entity = entity;
        this.keyType = keyType;
        this.keyNumber = keyNumber;
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

    public int getKeyType() {
        return this.keyType;
    }
    
    public void setKeyType(int keyType) {
        this.keyType = keyType;
    }

    public String getKeyNumber() {
        return this.keyNumber;
    }
    
    public void setKeyNumber(String keyNumber) {
        this.keyNumber = keyNumber;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof EntityKey) ) return false;
		 EntityKey castOther = ( EntityKey ) other; 
         
		 return ( (this.getEntity()==castOther.getEntity()) || ( this.getEntity()!=null && castOther.getEntity()!=null && this.getEntity().equals(castOther.getEntity()) ) )
 && (this.getKeyType()==castOther.getKeyType())
 && ( (this.getKeyNumber()==castOther.getKeyNumber()) || ( this.getKeyNumber()!=null && castOther.getKeyNumber()!=null && this.getKeyNumber().equals(castOther.getKeyNumber()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getEntity() == null ? 0 : this.getEntity().hashCode() );
         result = 37 * result + this.getKeyType();
         result = 37 * result + ( getKeyNumber() == null ? 0 : this.getKeyNumber().hashCode() );
         return result;
   }   





}
