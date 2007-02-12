package org.helianto.core;
// Generated 12/02/2007 21:24:30 by Hibernate Tools 3.2.0.beta8



/**
 * 				
 * <p>
 * Top of the <code>Partner</code> hierarchy that represents,
 * customers, suppliers, banks, etc. 
 * </p>
 * @author Mauricio Fernandes de Castro
 * 				
 *         
 */
public class Partner  implements java.io.Serializable {

    // Fields    

     private long id;
     private PartnerData partnerData;
     private byte sequence;
     private char priority;
     private char partnerState;
     private String profile;

     // Constructors

    /** default constructor */
    public Partner() {
    }

	/** minimal constructor */
    public Partner(PartnerData partnerData, byte sequence, char priority, char partnerState) {
        this.partnerData = partnerData;
        this.sequence = sequence;
        this.priority = priority;
        this.partnerState = partnerState;
    }
    /** full constructor */
    public Partner(PartnerData partnerData, byte sequence, char priority, char partnerState, String profile) {
       this.partnerData = partnerData;
       this.sequence = sequence;
       this.priority = priority;
       this.partnerState = partnerState;
       this.profile = profile;
    }
   
    // Property accessors
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    public PartnerData getPartnerData() {
        return this.partnerData;
    }
    
    public void setPartnerData(PartnerData partnerData) {
        this.partnerData = partnerData;
    }
    public byte getSequence() {
        return this.sequence;
    }
    
    public void setSequence(byte sequence) {
        this.sequence = sequence;
    }
    public char getPriority() {
        return this.priority;
    }
    
    public void setPriority(char priority) {
        this.priority = priority;
    }
    public char getPartnerState() {
        return this.partnerState;
    }
    
    public void setPartnerState(char partnerState) {
        this.partnerState = partnerState;
    }
    public String getProfile() {
        return this.profile;
    }
    
    public void setProfile(String profile) {
        this.profile = profile;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("partnerData").append("='").append(getPartnerData()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }

   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Partner) ) return false;
		 Partner castOther = ( Partner ) other; 
         
		 return ( (this.getPartnerData()==castOther.getPartnerData()) || ( this.getPartnerData()!=null && castOther.getPartnerData()!=null && this.getPartnerData().equals(castOther.getPartnerData()) ) )
 && (this.getSequence()==castOther.getSequence());
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getPartnerData() == null ? 0 : this.getPartnerData().hashCode() );
         
         
         
         
         return result;
   }   


}


