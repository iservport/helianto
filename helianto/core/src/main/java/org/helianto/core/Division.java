package org.helianto.core;
// Generated 23/04/2006 22:14:27 by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * 				
 * <p>
 * Persist relationships between the organization and its 
 * partners, like customers, suppliers, banks, etc. 
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id: helianto-entity1.hbm.xml,v 1.3 2006/03/20 16:11:40 iserv Exp $
 * 				
 *         
 * 				
 * <p>
 * Persist a relationship to a division. 
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id: helianto-entity1.hbm.xml,v 1.3 2006/03/20 16:11:40 iserv Exp $
 * 				
 * 			
 * 				
 * <p>
 * Persist a relationship to a customer 
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id: helianto-entity1.hbm.xml,v 1.3 2006/03/20 16:11:40 iserv Exp $
 * 				
 * 			
 * 				
 * <p>
 * Persist a relationship to a supplier 
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id: helianto-entity1.hbm.xml,v 1.3 2006/03/20 16:11:40 iserv Exp $
 * 				
 * 			
 * 				
 * <p>
 * Persist a relationship to a bank 
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id: helianto-entity1.hbm.xml,v 1.3 2006/03/20 16:11:40 iserv Exp $
 * 				
 * 			
 * 				
 * <p>
 * Persist a relationship to an Agent, like a sales representative. 
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id: helianto-entity1.hbm.xml,v 1.3 2006/03/20 16:11:40 iserv Exp $
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
