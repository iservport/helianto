package org.helianto.core;
// Generated 31/05/2006 20:59:28 by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * 				
 * <p>
 * Persist relationships between the organization and its 
 * partners, like customers, suppliers, banks, etc. 
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id: helianto-entity1.hbm.xml,v 1.3 2006/03/20 16:11:40 iserv Exp $
 * 				
 *         
 */

public class Partner  implements java.io.Serializable {


    // Fields    

     private long id;
     private Entity entity;
     private String alias;
     private Entity related;
     private char state;
     private boolean strong;
     private Date relatedSince;
     private String numberAssignedRemotely;
     private long importedKey;
     private String profile;


    // Constructors

    /** default constructor */
    public Partner() {
    }

	/** minimal constructor */
    public Partner(Entity entity, String alias, char state, boolean strong) {
        this.entity = entity;
        this.alias = alias;
        this.state = state;
        this.strong = strong;
    }
    
    /** full constructor */
    public Partner(Entity entity, String alias, Entity related, char state, boolean strong, Date relatedSince, String numberAssignedRemotely, long importedKey, String profile) {
        this.entity = entity;
        this.alias = alias;
        this.related = related;
        this.state = state;
        this.strong = strong;
        this.relatedSince = relatedSince;
        this.numberAssignedRemotely = numberAssignedRemotely;
        this.importedKey = importedKey;
        this.profile = profile;
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

    public String getAlias() {
        return this.alias;
    }
    
    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Entity getRelated() {
        return this.related;
    }
    
    public void setRelated(Entity related) {
        this.related = related;
    }

    public char getState() {
        return this.state;
    }
    
    public void setState(char state) {
        this.state = state;
    }

    public boolean isStrong() {
        return this.strong;
    }
    
    public void setStrong(boolean strong) {
        this.strong = strong;
    }

    public Date getRelatedSince() {
        return this.relatedSince;
    }
    
    public void setRelatedSince(Date relatedSince) {
        this.relatedSince = relatedSince;
    }

    public String getNumberAssignedRemotely() {
        return this.numberAssignedRemotely;
    }
    
    public void setNumberAssignedRemotely(String numberAssignedRemotely) {
        this.numberAssignedRemotely = numberAssignedRemotely;
    }

    public long getImportedKey() {
        return this.importedKey;
    }
    
    public void setImportedKey(long importedKey) {
        this.importedKey = importedKey;
    }

    public String getProfile() {
        return this.profile;
    }
    
    public void setProfile(String profile) {
        this.profile = profile;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Partner) ) return false;
		 Partner castOther = ( Partner ) other; 
         
		 return ( (this.getEntity()==castOther.getEntity()) || ( this.getEntity()!=null && castOther.getEntity()!=null && this.getEntity().equals(castOther.getEntity()) ) )
 && ( (this.getAlias()==castOther.getAlias()) || ( this.getAlias()!=null && castOther.getAlias()!=null && this.getAlias().equals(castOther.getAlias()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getEntity() == null ? 0 : this.getEntity().hashCode() );
         result = 37 * result + ( getAlias() == null ? 0 : this.getAlias().hashCode() );
         
         
         
         
         
         
         
         return result;
   }   





}
