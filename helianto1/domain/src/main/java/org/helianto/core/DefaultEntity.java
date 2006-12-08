package org.helianto.core;
// Generated 08/12/2006 09:46:04 by Hibernate Tools 3.2.0.beta8



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
 * <p>
 * Each <code>DefaultEntity</code> should have a corresponding {@link Partner}.
 * </p>
 * @author Mauricio Fernandes de Castro
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


