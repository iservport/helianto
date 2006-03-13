package org.helianto.core;
// Generated Mar 13, 2006 12:21:09 PM by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * 				
 * <p>
 * Persist a relationship to a division. 
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 * 				
 * 			
 */

public class Division extends org.helianto.core.Partner implements java.io.Serializable {


    // Fields    

     private char divisionType;


    // Constructors

    /** default constructor */
    public Division() {
    }

	/** minimal constructor */
    public Division(Entity entity, String alias, char state, boolean strong, char divisionType) {
        super(entity, alias, state, strong);        
        this.divisionType = divisionType;
    }
    
    /** full constructor */
    public Division(Entity entity, String alias, Entity related, char state, boolean strong, Date relatedSince, String numberAssignedRemotely, long importedKey, String profile, char divisionType) {
        super(entity, alias, related, state, strong, relatedSince, numberAssignedRemotely, importedKey, profile);        
        this.divisionType = divisionType;
    }
    

   
    // Property accessors

    public char getDivisionType() {
        return this.divisionType;
    }
    
    public void setDivisionType(char divisionType) {
        this.divisionType = divisionType;
    }
   








}
