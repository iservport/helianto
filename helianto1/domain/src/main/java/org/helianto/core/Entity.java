package org.helianto.core;
// Generated 15/08/2006 12:04:34 by Hibernate Tools 3.1.0.beta4



/**
 * 				
 * <p>
 * Domain object to represent the logical namespace of a business
 * organization or individual.
 * </p>
 * <p>
 * For example, if two equipment sets must be kept in different 
 * logical spaces to avoid identity collision, they
 * must be tied to different entities. This is also applicable for many
 * ohter domain classes, like accounts, statements, parts, processes, etc.
 * The <code>Entity</code> is the root for many of such objects and allow
 * for the desirable isolation between two or more organizations, or even
 * smaller units within one organization. In other words, an <code>Entity</code>
 * 'controls' a whole group of domain object instances.
 * </p>
 * <p>
 * A real world entity usually has many related properties, like 
 * address or trade mark. An <code>Entity</code> here, though, is 
 * designed not to hold much information, namely only an unique. That makes 
 * it flexible enough to be associated to virtually any real world 
 * entity, even individuals. 
 * </p>
 * <p>
 * A small footprint is also desirable for some serialization strategies
 * like Hibernate's (www.hibernate.org) non-lazy loading.
 * </p>
 * @author Mauricio Fernandes de Castro
 * 				
 * 		
 */

public class Entity  implements java.io.Serializable {


    // Fields    

     private long id;
     private Operator operator;
     private String alias;


    // Constructors

    /** default constructor */
    public Entity() {
    }

    
    /** full constructor */
    public Entity(Operator operator, String alias) {
        this.operator = operator;
        this.alias = alias;
    }
    

   
    // Property accessors

    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }

    public Operator getOperator() {
        return this.operator;
    }
    
    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public String getAlias() {
        return this.alias;
    }
    
    public void setAlias(String alias) {
        this.alias = alias;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("operator").append("='").append(getOperator()).append("' ");			
      buffer.append("alias").append("='").append(getAlias()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Entity) ) return false;
		 Entity castOther = ( Entity ) other; 
         
		 return ( (this.getOperator()==castOther.getOperator()) || ( this.getOperator()!=null && castOther.getOperator()!=null && this.getOperator().equals(castOther.getOperator()) ) )
 && ( (this.getAlias()==castOther.getAlias()) || ( this.getAlias()!=null && castOther.getAlias()!=null && this.getAlias().equals(castOther.getAlias()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getOperator() == null ? 0 : this.getOperator().hashCode() );
         result = 37 * result + ( getAlias() == null ? 0 : this.getAlias().hashCode() );
         return result;
   }   





}
