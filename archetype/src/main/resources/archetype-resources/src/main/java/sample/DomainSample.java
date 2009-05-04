package org.helianto.sample;

import org.helianto.core.Entity;

/**
 *              
 * <p>
 * Class description.
 * </p>
 * @author 
 *              
 *      
 */
public class DomainSample  implements java.io.Serializable {

    // Fields    

     private int id;
     private Entity entity;
     private String aKey;
     private int aProperty;

     // Constructors

    /** default constructor */
    public DomainSample() {
    }

    /** minimal constructor */
    public DomainSample(Entity entity, String aKey) {
        this.entity = entity;
        this.aKey = aKey;
    }
    /** full constructor */
    public DomainSample(Entity entity, String aKey, int aProperty) {
       this.entity = entity;
       this.aKey = aKey;
       this.aProperty = aProperty;
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
    public String getaKey() {
        return this.aKey;
    }
    
    public void setaKey(String aKey) {
        this.aKey = aKey;
    }
    public int getaProperty() {
        return this.aProperty;
    }
    
    public void setaProperty(int aProperty) {
        this.aProperty = aProperty;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
      StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("entity").append("='").append(getEntity()).append("' ");            
      buffer.append("aKey").append("='").append(getaKey()).append("' ");            
      buffer.append("]");
      
      return buffer.toString();
     }

   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
         if ( (other == null ) ) return false;
         if ( !(other instanceof DomainSample) ) return false;
         DomainSample castOther = ( DomainSample ) other; 
         
         return ( (this.getEntity()==castOther.getEntity()) || ( this.getEntity()!=null && castOther.getEntity()!=null && this.getEntity().equals(castOther.getEntity()) ) )
 && ( (this.getaKey()==castOther.getaKey()) || ( this.getaKey()!=null && castOther.getaKey()!=null && this.getaKey().equals(castOther.getaKey()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getEntity() == null ? 0 : this.getEntity().hashCode() );
         result = 37 * result + ( getaKey() == null ? 0 : this.getaKey().hashCode() );
         
         return result;
   }   


}


