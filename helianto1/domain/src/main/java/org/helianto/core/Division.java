package org.helianto.core;
// Generated 18/10/2006 20:52:47 by Hibernate Tools 3.1.0.beta5



/**
 * 			
 * <p>
 * Represents a relationship to a division. 
 * </p>
 * @author Mauricio Fernandes de Castro
 * 			
 * 		
 */
public class Division extends org.helianto.core.Partner implements java.io.Serializable {

    // Fields    


     // Constructors

    /** default constructor */
    public Division() {
    }

	/** minimal constructor */
    public Division(PartnerData partnerData, byte sequence, char priority, char partnerState) {
        super(partnerData, sequence, priority, partnerState);        
    }
    /** full constructor */
    public Division(PartnerData partnerData, byte sequence, char priority, char partnerState, String profile) {
        super(partnerData, sequence, priority, partnerState, profile);        
    }
    
   
    // Property accessors




}


