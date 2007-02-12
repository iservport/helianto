package org.helianto.core;
// Generated 12/02/2007 21:24:30 by Hibernate Tools 3.2.0.beta8


import java.util.Date;

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
     private PartnerData partnerData;
     private char shareMode;
     private Date sharedSince;

     // Constructors

    /** default constructor */
    public SharedEntity() {
    }

	/** minimal constructor */
    public SharedEntity(Entity sharedEntity, PartnerData partnerData, char shareMode) {
        this.sharedEntity = sharedEntity;
        this.partnerData = partnerData;
        this.shareMode = shareMode;
    }
    /** full constructor */
    public SharedEntity(Entity sharedEntity, PartnerData partnerData, char shareMode, Date sharedSince) {
       this.sharedEntity = sharedEntity;
       this.partnerData = partnerData;
       this.shareMode = shareMode;
       this.sharedSince = sharedSince;
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
    public PartnerData getPartnerData() {
        return this.partnerData;
    }
    
    public void setPartnerData(PartnerData partnerData) {
        this.partnerData = partnerData;
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
      buffer.append("partnerData").append("='").append(getPartnerData()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }

   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof SharedEntity) ) return false;
		 SharedEntity castOther = ( SharedEntity ) other; 
         
		 return ( (this.getSharedEntity()==castOther.getSharedEntity()) || ( this.getSharedEntity()!=null && castOther.getSharedEntity()!=null && this.getSharedEntity().equals(castOther.getSharedEntity()) ) )
 && ( (this.getPartnerData()==castOther.getPartnerData()) || ( this.getPartnerData()!=null && castOther.getPartnerData()!=null && this.getPartnerData().equals(castOther.getPartnerData()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getSharedEntity() == null ? 0 : this.getSharedEntity().hashCode() );
         result = 37 * result + ( getPartnerData() == null ? 0 : this.getPartnerData().hashCode() );
         
         
         return result;
   }   


}


