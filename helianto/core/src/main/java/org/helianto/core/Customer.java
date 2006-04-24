package org.helianto.core;
// Generated 23/04/2006 22:14:10 by Hibernate Tools 3.1.0.beta4

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

public class Customer extends org.helianto.core.Partner implements java.io.Serializable {


    // Fields    

     private Partner deliverTo;


    // Constructors

    /** default constructor */
    public Customer() {
    }

	/** minimal constructor */
    public Customer(Entity entity, String alias, char state, boolean strong) {
        super(entity, alias, state, strong);        
    }
    
    /** full constructor */
    public Customer(Entity entity, String alias, Entity related, char state, boolean strong, Date relatedSince, String numberAssignedRemotely, long importedKey, String profile, Partner deliverTo) {
        super(entity, alias, related, state, strong, relatedSince, numberAssignedRemotely, importedKey, profile);        
        this.deliverTo = deliverTo;
    }
    

   
    // Property accessors

    public Partner getDeliverTo() {
        return this.deliverTo;
    }
    
    public void setDeliverTo(Partner deliverTo) {
        this.deliverTo = deliverTo;
    }
   








}
