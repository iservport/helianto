package org.helianto.partner;


import java.util.Date;

import org.helianto.core.Entity;

/**
 * 				
 * <p>
 * Represents the relationship between the organization and other entities.  
 * </p>
 * @author Mauricio Fernandes de Castro
 * 				
 *         
 */
public class SharedEntity  implements java.io.Serializable {

    // Fields    

     private long id;
     private Entity sharedEntity;
     private PartnerAssociation partnerAssociation;
     private char shareMode;
     private Date sharedSince;

     // Constructors

    /** default constructor */
    public SharedEntity() {
    }

    // Property accessors
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    public Entity getSharedEntity() {
        return this.sharedEntity;
    }
    
    public void setSharedEntity(Entity sharedEntity) {
        this.sharedEntity = sharedEntity;
    }
    public PartnerAssociation getPartnerAssociation() {
        return this.partnerAssociation;
    }
    
    public void setPartnerAssociation(PartnerAssociation partnerAssociation) {
        this.partnerAssociation = partnerAssociation;
    }
    public char getShareMode() {
        return this.shareMode;
    }
    
    public void setShareMode(char shareMode) {
        this.shareMode = shareMode;
    }
    public Date getSharedSince() {
        return this.sharedSince;
    }
    
    public void setSharedSince(Date sharedSince) {
        this.sharedSince = sharedSince;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("sharedEntity").append("='").append(getSharedEntity()).append("' ");			
      buffer.append("partnerAssociation").append("='").append(getPartnerAssociation()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }

   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof SharedEntity) ) return false;
		 SharedEntity castOther = ( SharedEntity ) other; 
         
		 return ( (this.getSharedEntity()==castOther.getSharedEntity()) || ( this.getSharedEntity()!=null && castOther.getSharedEntity()!=null && this.getSharedEntity().equals(castOther.getSharedEntity()) ) )
 && ( (this.getPartnerAssociation()==castOther.getPartnerAssociation()) || ( this.getPartnerAssociation()!=null && castOther.getPartnerAssociation()!=null && this.getPartnerAssociation().equals(castOther.getPartnerAssociation()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getSharedEntity() == null ? 0 : this.getSharedEntity().hashCode() );
         result = 37 * result + ( getPartnerAssociation() == null ? 0 : this.getPartnerAssociation().hashCode() );
         
         
         return result;
   }   


}


