package org.helianto.core;
// Generated 08/12/2006 09:46:04 by Hibernate Tools 3.2.0.beta8



/**
 * 			
 * <p>
 * Represents a relationship to a resource manufaturer. 
 * </p>
 * @author Mauricio Fernandes de Castro
 * 			
 * 		
 */
public class Manufacturer extends org.helianto.core.Partner implements java.io.Serializable {

    // Fields    


     // Constructors

    /** default constructor */
    public Manufacturer() {
    }

	/** minimal constructor */
    public Manufacturer(PartnerData partnerData, byte sequence, char priority, char partnerState) {
        super(partnerData, sequence, priority, partnerState);        
    }
    /** full constructor */
    public Manufacturer(PartnerData partnerData, byte sequence, char priority, char partnerState, String profile) {
        super(partnerData, sequence, priority, partnerState, profile);        
    }
   
    // Property accessors




}


