package org.helianto.core;
// Generated Mar 13, 2006 12:21:06 PM by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * 				
 * <p>
 * Persist a relationship to a bank 
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id$
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
    public Bank(Entity entity, String alias, char state, boolean strong) {
        super(entity, alias, state, strong);        
    }
    
    /** full constructor */
    public Bank(Entity entity, String alias, Entity related, char state, boolean strong, Date relatedSince, String numberAssignedRemotely, long importedKey, String profile, String checkFormFile) {
        super(entity, alias, related, state, strong, relatedSince, numberAssignedRemotely, importedKey, profile);        
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
