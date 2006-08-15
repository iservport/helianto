package org.helianto.core;
// Generated 15/08/2006 11:35:55 by Hibernate Tools 3.1.0.beta4



/**
 * 			
 * <p>
 * Represents a relationship to an Agent, like a sales representative. 
 * </p>
 * @author Mauricio Fernandes de Castro
 * 			
 * 		
 */

public class Agent extends org.helianto.core.Partner implements java.io.Serializable {


    // Fields    



    // Constructors

    /** default constructor */
    public Agent() {
    }

	/** minimal constructor */
    public Agent(PartnerData partnerData, byte sequence, char priority, char partnerState) {
        super(partnerData, sequence, priority, partnerState);        
    }
    
    /** full constructor */
    public Agent(PartnerData partnerData, byte sequence, char priority, char partnerState, String profile) {
        super(partnerData, sequence, priority, partnerState, profile);        
    }
    

   
    // Property accessors
   








}
