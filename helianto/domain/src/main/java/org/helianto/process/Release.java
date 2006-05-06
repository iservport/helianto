package org.helianto.process;
// Generated 05/05/2006 22:23:08 by Hibernate Tools 3.1.0.beta4

import java.util.Date;
import org.helianto.core.Entity;


/**
 * 				
 * <p>
 * A class to represent process releases.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id: iservport-process0.hbm.xml,v 1.2 2006/03/13 15:29:13 iserv Exp $
 * 				
 * 		
 */

public class Release  implements java.io.Serializable {


    // Fields    

     private long id;
     private Entity entity;
     private long internalNumber;
     private Date releaseDate;
     private char releaseState;


    // Constructors

    /** default constructor */
    public Release() {
    }

    
    /** full constructor */
    public Release(Entity entity, long internalNumber, Date releaseDate, char releaseState) {
        this.entity = entity;
        this.internalNumber = internalNumber;
        this.releaseDate = releaseDate;
        this.releaseState = releaseState;
    }
    

   
    // Property accessors

    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }

    public Entity getEntity() {
        return this.entity;
    }
    
    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public long getInternalNumber() {
        return this.internalNumber;
    }
    
    public void setInternalNumber(long internalNumber) {
        this.internalNumber = internalNumber;
    }

    public Date getReleaseDate() {
        return this.releaseDate;
    }
    
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public char getReleaseState() {
        return this.releaseState;
    }
    
    public void setReleaseState(char releaseState) {
        this.releaseState = releaseState;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Release) ) return false;
		 Release castOther = ( Release ) other; 
         
		 return ( (this.getEntity()==castOther.getEntity()) || ( this.getEntity()!=null && castOther.getEntity()!=null && this.getEntity().equals(castOther.getEntity()) ) )
 && (this.getInternalNumber()==castOther.getInternalNumber());
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getEntity() == null ? 0 : this.getEntity().hashCode() );
         result = 37 * result + (int) this.getInternalNumber();
         
         
         return result;
   }   





}
