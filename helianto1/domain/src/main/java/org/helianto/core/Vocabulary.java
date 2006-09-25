package org.helianto.core;
// Generated 24/09/2006 12:54:26 by Hibernate Tools 3.1.0.beta4



/**
 * 				
 * <p>
 * A class to standardize vocabulary across domain objects.
 * </p>
 * @author Mauricio Fernandes de Castro
 * 				
 * 		
 */

public class Vocabulary  implements java.io.Serializable {


    // Fields    

     private int id;
     private Entity entity;
     private String code;
     private Vocabulary parent;
     private String name;


    // Constructors

    /** default constructor */
    public Vocabulary() {
    }

	/** minimal constructor */
    public Vocabulary(Entity entity, String code) {
        this.entity = entity;
        this.code = code;
    }
    
    /** full constructor */
    public Vocabulary(Entity entity, String code, Vocabulary parent, String name) {
        this.entity = entity;
        this.code = code;
        this.parent = parent;
        this.name = name;
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

    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    public Vocabulary getParent() {
        return this.parent;
    }
    
    public void setParent(Vocabulary parent) {
        this.parent = parent;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Vocabulary) ) return false;
		 Vocabulary castOther = ( Vocabulary ) other; 
         
		 return ( (this.getEntity()==castOther.getEntity()) || ( this.getEntity()!=null && castOther.getEntity()!=null && this.getEntity().equals(castOther.getEntity()) ) )
 && ( (this.getCode()==castOther.getCode()) || ( this.getCode()!=null && castOther.getCode()!=null && this.getCode().equals(castOther.getCode()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getEntity() == null ? 0 : this.getEntity().hashCode() );
         result = 37 * result + ( getCode() == null ? 0 : this.getCode().hashCode() );
         
         
         return result;
   }   





}
