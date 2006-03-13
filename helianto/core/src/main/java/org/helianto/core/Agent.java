package org.helianto.core;
// Generated Mar 13, 2006 12:20:54 PM by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * 				
 * <p>
 * Persist a relationship to an Agent, like a sales representative. 
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id$
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
    public Agent(Entity entity, String alias, char state, boolean strong) {
        super(entity, alias, state, strong);        
    }
    
    /** full constructor */
    public Agent(Entity entity, String alias, Entity related, char state, boolean strong, Date relatedSince, String numberAssignedRemotely, long importedKey, String profile) {
        super(entity, alias, related, state, strong, relatedSince, numberAssignedRemotely, importedKey, profile);        
    }
    

   
    // Property accessors
   








}
