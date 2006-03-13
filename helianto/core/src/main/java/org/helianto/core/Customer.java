package org.helianto.core;
// Generated Mar 13, 2006 12:20:58 PM by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * 				
 * <p>
 * Persist a relationship to a customer 
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id$
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
