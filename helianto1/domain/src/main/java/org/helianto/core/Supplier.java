package org.helianto.core;
// Generated 14/11/2006 21:06:50 by Hibernate Tools 3.1.0.beta5



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


