package org.helianto.core;
// Generated 03/07/2006 17:04:43 by Hibernate Tools 3.1.0.beta4



/**
 * 				
 * <p>
 * The contact domain class.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id: helianto-entity2.hbm.xml 80 2006-04-19 21:56:03 -0300 (Qua, 19 Abr 2006) iserv $
 * 				
 *         
 */

public class Contact  implements java.io.Serializable {


    // Fields    

     private long id;
     private Partner partner;
     private Identity identity;
     private int internalNumber;
     private String jobReference;
     private String department;
     private int priority;


    // Constructors

    /** default constructor */
    public Contact() {
    }

	/** minimal constructor */
    public Contact(Partner partner, Identity identity, int internalNumber, int priority) {
        this.partner = partner;
        this.identity = identity;
        this.internalNumber = internalNumber;
        this.priority = priority;
    }
    
    /** full constructor */
    public Contact(Partner partner, Identity identity, int internalNumber, String jobReference, String department, int priority) {
        this.partner = partner;
        this.identity = identity;
        this.internalNumber = internalNumber;
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

    public Partner getPartner() {
        return this.partner;
    }
    
    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    public Identity getIdentity() {
        return this.identity;
    }
    
    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    public int getInternalNumber() {
        return this.internalNumber;
    }
    
    public void setInternalNumber(int internalNumber) {
        this.internalNumber = internalNumber;
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

    public int getPriority() {
        return this.priority;
    }
    
    public void setPriority(int priority) {
        this.priority = priority;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Contact) ) return false;
		 Contact castOther = ( Contact ) other; 
         
		 return ( (this.getPartner()==castOther.getPartner()) || ( this.getPartner()!=null && castOther.getPartner()!=null && this.getPartner().equals(castOther.getPartner()) ) )
 && ( (this.getIdentity()==castOther.getIdentity()) || ( this.getIdentity()!=null && castOther.getIdentity()!=null && this.getIdentity().equals(castOther.getIdentity()) ) )
 && (this.getInternalNumber()==castOther.getInternalNumber());
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getPartner() == null ? 0 : this.getPartner().hashCode() );
         result = 37 * result + ( getIdentity() == null ? 0 : this.getIdentity().hashCode() );
         result = 37 * result + this.getInternalNumber();
         
         
         
         return result;
   }   





}
