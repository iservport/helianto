package org.helianto.core;
// Generated 21/04/2006 16:47:38 by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * 				
 * <p>
 * Persist relationships between the organization and its 
 * partners, like customers, suppliers, banks, etc. 
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id: helianto-entity1.hbm.xml 14 2006-03-20 13:11:47 -0300 (Seg, 20 Mar 2006) iserv $
 * 				
 *         
 * 				
 * <p>
 * Persist a relationship to a division. 
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id: helianto-entity1.hbm.xml 14 2006-03-20 13:11:47 -0300 (Seg, 20 Mar 2006) iserv $
 * 				
 * 			
 * 				
 * <p>
 * Persist a relationship to a customer 
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id: helianto-entity1.hbm.xml 14 2006-03-20 13:11:47 -0300 (Seg, 20 Mar 2006) iserv $
 * 				
 * 			
 * 				
 * <p>
 * Persist a relationship to a supplier 
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id: helianto-entity1.hbm.xml 14 2006-03-20 13:11:47 -0300 (Seg, 20 Mar 2006) iserv $
 * 				
 * 			
 * 				
 * <p>
 * Persist a relationship to a bank 
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id: helianto-entity1.hbm.xml 14 2006-03-20 13:11:47 -0300 (Seg, 20 Mar 2006) iserv $
 * 				
 * 			
 * 				
 * <p>
 * Persist a relationship to an Agent, like a sales representative. 
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id: helianto-entity1.hbm.xml 14 2006-03-20 13:11:47 -0300 (Seg, 20 Mar 2006) iserv $
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
