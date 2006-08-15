package org.helianto.core;
// Generated 15/08/2006 11:35:56 by Hibernate Tools 3.1.0.beta4



/**
 * 			
 * <p>
 * Represents a relationship to a supplier.
 * </p>
 * @author Mauricio Fernandes de Castro
 * 			
 * 		
 */

public class Supplier extends org.helianto.core.Partner implements java.io.Serializable {


    // Fields    



    // Constructors

    /** default constructor */
    public Supplier() {
    }

	/** minimal constructor */
    public Supplier(PartnerData partnerData, byte sequence, char priority, char partnerState) {
        super(partnerData, sequence, priority, partnerState);        
    }
    
    /** full constructor */
    public Supplier(PartnerData partnerData, byte sequence, char priority, char partnerState, String profile) {
        super(partnerData, sequence, priority, partnerState, profile);        
    }
    

   
    // Property accessors
   








}
