package org.helianto.core;
// Generated 23/05/2006 20:22:27 by Hibernate Tools 3.1.0.beta4



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
 * alias and a reference to its <code>Home</code>. That makes 
 * it flexible enough to be associated to virtually any real world 
 * entity, even individuals. 
 * </p>
 * <p>
 * A small footprint is also desirable for some serialization strategies
 * like Hibernate's (www.hibernate.org) non-lazy loading.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id: helianto-core1.hbm.xml 75 2006-04-17 21:45:55 -0300 (Seg, 17 Abr 2006) iserv $
 * 				
 * 		
 */

public class Entity  implements java.io.Serializable {


    // Fields    

     private long id;
     private Home home;
     private String alias;


    // Constructors

    /** default constructor */
    public Entity() {
    }

    
    /** full constructor */
    public Entity(Home home, String alias) {
        this.home = home;
        this.alias = alias;
    }
    

   
    // Property accessors

    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }

    public Home getHome() {
        return this.home;
    }
    
    public void setHome(Home home) {
        this.home = home;
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
         
		 return ( (this.getHome()==castOther.getHome()) || ( this.getHome()!=null && castOther.getHome()!=null && this.getHome().equals(castOther.getHome()) ) )
 && ( (this.getAlias()==castOther.getAlias()) || ( this.getAlias()!=null && castOther.getAlias()!=null && this.getAlias().equals(castOther.getAlias()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getHome() == null ? 0 : this.getHome().hashCode() );
         result = 37 * result + ( getAlias() == null ? 0 : this.getAlias().hashCode() );
         return result;
   }   





}
