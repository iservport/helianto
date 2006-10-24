package org.helianto.core;
// Generated 24/10/2006 13:50:48 by Hibernate Tools 3.1.0.beta5



/**
 * 				
 * <p>
 * A class to hold last value for internal number lists.
 * </p>
 * @author Mauricio Fernandes de Castro
 * 				
 * 		
 */
public class InternalEnumerator  implements java.io.Serializable {

    // Fields    

     private int id;
     private Entity entity;
     private String typeName;
     private long lastNumber;

     // Constructors

    /** default constructor */
    public InternalEnumerator() {
    }

    /** full constructor */
    public InternalEnumerator(Entity entity, String typeName, long lastNumber) {
       this.entity = entity;
       this.typeName = typeName;
       this.lastNumber = lastNumber;
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
    public String getTypeName() {
        return this.typeName;
    }
    
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    public long getLastNumber() {
        return this.lastNumber;
    }
    
    public void setLastNumber(long lastNumber) {
        this.lastNumber = lastNumber;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("entity").append("='").append(getEntity()).append("' ");			
      buffer.append("typeName").append("='").append(getTypeName()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }

   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof InternalEnumerator) ) return false;
		 InternalEnumerator castOther = ( InternalEnumerator ) other; 
         
		 return ( (this.getEntity()==castOther.getEntity()) || ( this.getEntity()!=null && castOther.getEntity()!=null && this.getEntity().equals(castOther.getEntity()) ) )
 && ( (this.getTypeName()==castOther.getTypeName()) || ( this.getTypeName()!=null && castOther.getTypeName()!=null && this.getTypeName().equals(castOther.getTypeName()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getEntity() == null ? 0 : this.getEntity().hashCode() );
         result = 37 * result + ( getTypeName() == null ? 0 : this.getTypeName().hashCode() );
         
         return result;
   }   


}


