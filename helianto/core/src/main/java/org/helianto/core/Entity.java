package org.helianto.core;
// Generated Mar 13, 2006 12:21:08 PM by Hibernate Tools 3.1.0.beta4



/**
 * 				
 * <p>
 * Domain object to represent different business units.
 * </p>
 * <p>
 * For example, if two equipment sets must be kept in different 
 * logical spaces to avoid identity collision, they
 * must be tied to different entities. This is also applicable for many
 * ohter domain classes, like accounts, statements, parts, processes, etc.
 * The <code>Entity</code> is the root for many of such objects and allow
 * for the desirable isolation between two or more organizations, or even
 * smaller units within one organization. In other words, an <code>Entity</code>
 * 'controls' a whole group of domain object instances, or if there is only one,
 * it controls the whole application.
 * </p>
 * <p>
 * A real world entity usually has many related properties, like 
 * address or trade mark. An <code>Entity</code> here, though, is 
 * designed not to hold much information, namely only an unique 
 * alias and a reference to its <code>Supervisor</code>. That makes 
 * it flexible enough to be associated to virtually any real world 
 * entity, even individuals. 
 * </p>
 * <p>
 * A small footprint is also desirable for some serialization strategies
 * like Hibernate's (www.hibernate.org) non-lazy loading.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 * 				
 * 		
 */

public class Entity  implements java.io.Serializable {


    // Fields    

     private long id;
     private Supervisor supervisor;
     private String alias;


    // Constructors

    /** default constructor */
    public Entity() {
    }

    
    /** full constructor */
    public Entity(Supervisor supervisor, String alias) {
        this.supervisor = supervisor;
        this.alias = alias;
    }
    

   
    // Property accessors

    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }

    public Supervisor getSupervisor() {
        return this.supervisor;
    }
    
    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }

    public String getAlias() {
        return this.alias;
    }
    
    public void setAlias(String alias) {
        this.alias = alias;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Entity) ) return false;
		 Entity castOther = ( Entity ) other; 
         
		 return ( (this.getSupervisor()==castOther.getSupervisor()) || ( this.getSupervisor()!=null && castOther.getSupervisor()!=null && this.getSupervisor().equals(castOther.getSupervisor()) ) )
 && ( (this.getAlias()==castOther.getAlias()) || ( this.getAlias()!=null && castOther.getAlias()!=null && this.getAlias().equals(castOther.getAlias()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getSupervisor() == null ? 0 : this.getSupervisor().hashCode() );
         result = 37 * result + ( getAlias() == null ? 0 : this.getAlias().hashCode() );
         return result;
   }   





}
