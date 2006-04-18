package org.helianto.core;
// Generated 18/04/2006 13:34:37 by Hibernate Tools 3.1.0.beta4



/**
 * 				
 * <p>
 * Persist a contact.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id: helianto-entity2.hbm.xml,v 1.3 2006/03/20 16:11:40 iserv Exp $
 * 				
 *         
 */

public class Contact  implements java.io.Serializable {


    // Fields    

     private long id;
     private Partner partner;
     private Credential credential;
     private int internalNumber;
     private String jobReference;
     private String department;
     private int priority;


    // Constructors

    /** default constructor */
    public Contact() {
    }

	/** minimal constructor */
    public Contact(Partner partner, Credential credential, int internalNumber, int priority) {
        this.partner = partner;
        this.credential = credential;
        this.internalNumber = internalNumber;
        this.priority = priority;
    }
    
    /** full constructor */
    public Contact(Partner partner, Credential credential, int internalNumber, String jobReference, String department, int priority) {
        this.partner = partner;
        this.credential = credential;
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

    public Credential getCredential() {
        return this.credential;
    }
    
    public void setCredential(Credential credential) {
        this.credential = credential;
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
 && ( (this.getCredential()==castOther.getCredential()) || ( this.getCredential()!=null && castOther.getCredential()!=null && this.getCredential().equals(castOther.getCredential()) ) )
 && (this.getInternalNumber()==castOther.getInternalNumber());
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getPartner() == null ? 0 : this.getPartner().hashCode() );
         result = 37 * result + ( getCredential() == null ? 0 : this.getCredential().hashCode() );
         result = 37 * result + this.getInternalNumber();
         
         
         
         return result;
   }   





}
