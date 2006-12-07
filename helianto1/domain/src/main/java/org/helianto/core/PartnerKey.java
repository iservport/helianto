package org.helianto.core;
// Generated 07/12/2006 11:35:10 by Hibernate Tools 3.2.0.beta8



/**
 * 			
 * <p>
 * Represents different key values for each <code>PartnerRole</code>.
 * </p>
 * @author Mauricio Fernandes de Castro
 * 				
 * 		
 */
public class PartnerKey  implements java.io.Serializable {

    // Fields    

     private long id;
     private Partner partner;
     private KeyType keyType;
     private String keyValue;

     // Constructors

    /** default constructor */
    public PartnerKey() {
    }

	/** minimal constructor */
    public PartnerKey(Partner partner, KeyType keyType) {
        this.partner = partner;
        this.keyType = keyType;
    }
    /** full constructor */
    public PartnerKey(Partner partner, KeyType keyType, String keyValue) {
       this.partner = partner;
       this.keyType = keyType;
       this.keyValue = keyValue;
    }
   
    // Property accessors
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    public Partner getPartner() {
        return this.partner;
    }
    
    public void setPartner(Partner partner) {
        this.partner = partner;
    }
    public KeyType getKeyType() {
        return this.keyType;
    }
    
    public void setKeyType(KeyType keyType) {
        this.keyType = keyType;
    }
    public String getKeyValue() {
        return this.keyValue;
    }
    
    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("partner").append("='").append(getPartner()).append("' ");			
      buffer.append("keyType").append("='").append(getKeyType()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }

   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof PartnerKey) ) return false;
		 PartnerKey castOther = ( PartnerKey ) other; 
         
		 return ( (this.getPartner()==castOther.getPartner()) || ( this.getPartner()!=null && castOther.getPartner()!=null && this.getPartner().equals(castOther.getPartner()) ) )
 && ( (this.getKeyType()==castOther.getKeyType()) || ( this.getKeyType()!=null && castOther.getKeyType()!=null && this.getKeyType().equals(castOther.getKeyType()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getPartner() == null ? 0 : this.getPartner().hashCode() );
         result = 37 * result + ( getKeyType() == null ? 0 : this.getKeyType().hashCode() );
         
         return result;
   }   


}


