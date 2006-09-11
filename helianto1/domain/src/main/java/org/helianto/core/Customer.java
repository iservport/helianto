package org.helianto.core;
// Generated 09/09/2006 21:08:18 by Hibernate Tools 3.1.0.beta4



/**
 * 			
 * <p>
 * Represents a relationship to a customer 
 * </p>
 * @author Mauricio Fernandes de Castro
 * 			
 * 		
 */

public class Customer extends org.helianto.core.Partner implements java.io.Serializable {


    // Fields    



    // Constructors

    /** default constructor */
    public Customer() {
    }

	/** minimal constructor */
    public Customer(PartnerData partnerData, byte sequence, char priority, char partnerState) {
        super(partnerData, sequence, priority, partnerState);        
    }
    
    /** full constructor */
    public Customer(PartnerData partnerData, byte sequence, char priority, char partnerState, String profile) {
        super(partnerData, sequence, priority, partnerState, profile);        
    }
    

   
    // Property accessors
   








}
