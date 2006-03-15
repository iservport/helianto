package org.helianto.core;
// Generated 14/03/2006 22:23:39 by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * 				
 * <p>
 * Persist a relationship to a supplier 
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 * 				
 * 			
 */

public class Supplier extends org.helianto.core.Partner implements java.io.Serializable {


    // Fields    

     private float supplierAsessment;
     private User buyer;


    // Constructors

    /** default constructor */
    public Supplier() {
    }

	/** minimal constructor */
    public Supplier(Entity entity, String alias, char state, boolean strong) {
        super(entity, alias, state, strong);        
    }
    
    /** full constructor */
    public Supplier(Entity entity, String alias, Entity related, char state, boolean strong, Date relatedSince, String numberAssignedRemotely, long importedKey, String profile, float supplierAsessment, User buyer) {
        super(entity, alias, related, state, strong, relatedSince, numberAssignedRemotely, importedKey, profile);        
        this.supplierAsessment = supplierAsessment;
        this.buyer = buyer;
    }
    

   
    // Property accessors

    public float getSupplierAsessment() {
        return this.supplierAsessment;
    }
    
    public void setSupplierAsessment(float supplierAsessment) {
        this.supplierAsessment = supplierAsessment;
    }

    public User getBuyer() {
        return this.buyer;
    }
    
    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }
   








}
