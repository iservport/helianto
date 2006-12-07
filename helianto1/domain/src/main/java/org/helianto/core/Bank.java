package org.helianto.core;
// Generated 07/12/2006 11:35:10 by Hibernate Tools 3.2.0.beta8



/**
 * 			
 * <p>
 * Represents a relationship to a bank 
 * </p>
 * @author Mauricio Fernandes de Castro
 * 			
 * 		
 */
public class Bank extends org.helianto.core.Partner implements java.io.Serializable {

    // Fields    

     private String checkFormFile;

     // Constructors

    /** default constructor */
    public Bank() {
    }

	/** minimal constructor */
    public Bank(PartnerData partnerData, byte sequence, char priority, char partnerState) {
        super(partnerData, sequence, priority, partnerState);        
    }
    /** full constructor */
    public Bank(PartnerData partnerData, byte sequence, char priority, char partnerState, String profile, String checkFormFile) {
        super(partnerData, sequence, priority, partnerState, profile);        
       this.checkFormFile = checkFormFile;
    }
   
    // Property accessors
    public String getCheckFormFile() {
        return this.checkFormFile;
    }
    
    public void setCheckFormFile(String checkFormFile) {
        this.checkFormFile = checkFormFile;
    }




}


