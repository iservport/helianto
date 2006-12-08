package org.helianto.core;
// Generated 07/12/2006 16:03:04 by Hibernate Tools 3.2.0.beta8



/**
 * 				
 * <p>
 * The phone domain class.
 * </p>
 * @author Mauricio Fernandes de Castro
 * 				
 *         
 */
public class Phone  implements java.io.Serializable {

    // Fields    

     private long id;
     private Contact contact;
     private String phoneNumber;
     private String areaCode;
     private char phoneType;
     private String comment;

     // Constructors

    /** default constructor */
    public Phone() {
    }

	/** minimal constructor */
    public Phone(Contact contact, String phoneNumber, String areaCode, char phoneType) {
        this.contact = contact;
        this.phoneNumber = phoneNumber;
        this.areaCode = areaCode;
        this.phoneType = phoneType;
    }
    /** full constructor */
    public Phone(Contact contact, String phoneNumber, String areaCode, char phoneType, String comment) {
       this.contact = contact;
       this.phoneNumber = phoneNumber;
       this.areaCode = areaCode;
       this.phoneType = phoneType;
       this.comment = comment;
    }
   
    // Property accessors
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    public Contact getContact() {
        return this.contact;
    }
    
    public void setContact(Contact contact) {
        this.contact = contact;
    }
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getAreaCode() {
        return this.areaCode;
    }
    
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
    public char getPhoneType() {
        return this.phoneType;
    }
    
    public void setPhoneType(char phoneType) {
        this.phoneType = phoneType;
    }
    public String getComment() {
        return this.comment;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("contact").append("='").append(getContact()).append("' ");			
      buffer.append("phoneNumber").append("='").append(getPhoneNumber()).append("' ");			
      buffer.append("areaCode").append("='").append(getAreaCode()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }

   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Phone) ) return false;
		 Phone castOther = ( Phone ) other; 
         
		 return ( (this.getContact()==castOther.getContact()) || ( this.getContact()!=null && castOther.getContact()!=null && this.getContact().equals(castOther.getContact()) ) )
 && ( (this.getPhoneNumber()==castOther.getPhoneNumber()) || ( this.getPhoneNumber()!=null && castOther.getPhoneNumber()!=null && this.getPhoneNumber().equals(castOther.getPhoneNumber()) ) )
 && ( (this.getAreaCode()==castOther.getAreaCode()) || ( this.getAreaCode()!=null && castOther.getAreaCode()!=null && this.getAreaCode().equals(castOther.getAreaCode()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getContact() == null ? 0 : this.getContact().hashCode() );
         result = 37 * result + ( getPhoneNumber() == null ? 0 : this.getPhoneNumber().hashCode() );
         result = 37 * result + ( getAreaCode() == null ? 0 : this.getAreaCode().hashCode() );
         
         
         return result;
   }   


}


