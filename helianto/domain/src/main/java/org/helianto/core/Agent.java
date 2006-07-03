package org.helianto.core;
// Generated 03/07/2006 15:46:53 by Hibernate Tools 3.1.0.beta4

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
 * Persist a relationship to a resource manufaturer. 
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
