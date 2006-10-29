package org.helianto.core;
// Generated 29/10/2006 20:02:34 by Hibernate Tools 3.1.0.beta5



/**
 * 				
 * <p>
 * The contact domain class.
 * </p>
 * @author Mauricio Fernandes de Castro
 * 				
 *         
 */
public class Contact  implements java.io.Serializable {

    // Fields    

     private long id;
     private PartnerData partnerData;
     private Identity identity;
     private String jobReference;
     private String department;
     private char priority;

     // Constructors

    /** default constructor */
    public Contact() {
    }

	/** minimal constructor */
    public Contact(PartnerData partnerData, Identity identity, char priority) {
        this.partnerData = partnerData;
        this.identity = identity;
        this.priority = priority;
    }
    /** full constructor */
    public Contact(PartnerData partnerData, Identity identity, String jobReference, String department, char priority) {
       this.partnerData = partnerData;
       this.identity = identity;
       this.jobReference = jobReference;
       this.department = department;
       this.priority = priority;
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
    public Identity getIdentity() {
        return this.identity;
    }
    
    public void setIdentity(Identity identity) {
        this.identity = identity;
    }
    public String getJobReference() {
        return this.jobReference;
    }
    
    public void setJobReference(String jobReference) {
        this.jobReference = jobReference;
    }
    public String getDepartment() {
        return this.department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    public char getPriority() {
        return this.priority;
    }
    
    public void setPriority(char priority) {
        this.priority = priority;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("partnerData").append("='").append(getPartnerData()).append("' ");			
      buffer.append("identity").append("='").append(getIdentity()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }

   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Contact) ) return false;
		 Contact castOther = ( Contact ) other; 
         
		 return ( (this.getPartnerData()==castOther.getPartnerData()) || ( this.getPartnerData()!=null && castOther.getPartnerData()!=null && this.getPartnerData().equals(castOther.getPartnerData()) ) )
 && ( (this.getIdentity()==castOther.getIdentity()) || ( this.getIdentity()!=null && castOther.getIdentity()!=null && this.getIdentity().equals(castOther.getIdentity()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getPartnerData() == null ? 0 : this.getPartnerData().hashCode() );
         result = 37 * result + ( getIdentity() == null ? 0 : this.getIdentity().hashCode() );
         
         
         
         return result;
   }   


}


