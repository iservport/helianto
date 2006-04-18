package org.helianto.core;
// Generated 18/04/2006 13:34:39 by Hibernate Tools 3.1.0.beta4



/**
 * 				
 * <p>
 * Domain object to indicate which <code>Entity</code>
 * may be taken by default when required by an association.
 * </p>
 * <p>
 * This is usefull for environments where all domain objects may be associated
 * to a single <code>Entity</code>.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id: helianto-core2.hbm.xml,v 1.6 2006/03/20 16:11:40 iserv Exp $
 * 				
 * 		
 */

public class DefaultEntity  implements java.io.Serializable {


    // Fields    

     private int id;
     private Entity entity;
     private int priority;


    // Constructors

    /** default constructor */
    public DefaultEntity() {
    }

	/** minimal constructor */
    public DefaultEntity(int priority) {
        this.priority = priority;
    }
    
    /** full constructor */
    public DefaultEntity(Entity entity, int priority) {
        this.entity = entity;
        this.priority = priority;
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

    public int getPriority() {
        return this.priority;
    }
    
    public void setPriority(int priority) {
        this.priority = priority;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof DefaultEntity) ) return false;
		 DefaultEntity castOther = ( DefaultEntity ) other; 
         
		 return ( (this.getEntity()==castOther.getEntity()) || ( this.getEntity()!=null && castOther.getEntity()!=null && this.getEntity().equals(castOther.getEntity()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getEntity() == null ? 0 : this.getEntity().hashCode() );
         
         return result;
   }   





}
