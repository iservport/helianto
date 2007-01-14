package org.helianto.core;
// Generated 14/01/2007 10:50:26 by Hibernate Tools 3.2.0.beta8



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


