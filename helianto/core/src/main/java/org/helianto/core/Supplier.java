package org.helianto.core;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


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
public class Supplier extends Partner implements Serializable {

    /** nullable persistent field */
    private Float supplierAsessment;

    /** nullable persistent field */
    private org.helianto.core.User buyer;

    /** full constructor */
    public Supplier(String alias, char state, boolean strong, Date relatedSince, String numberAssignedRemotely, Long importedKey, String profile, org.helianto.core.Entity entity, org.helianto.core.Entity related, Float supplierAsessment, org.helianto.core.User buyer) {
        super(alias, state, strong, relatedSince, numberAssignedRemotely, importedKey, profile, entity, related);
        this.supplierAsessment = supplierAsessment;
        this.buyer = buyer;
    }

    /** default constructor */
    public Supplier() {
    }

    /** minimal constructor */
    public Supplier(String alias, char state, boolean strong) {
      super(alias, state, strong);
    }

    public Float getSupplierAsessment() {
        return this.supplierAsessment;
    }

    public void setSupplierAsessment(Float supplierAsessment) {
        this.supplierAsessment = supplierAsessment;
    }

    public org.helianto.core.User getBuyer() {
        return this.buyer;
    }

    public void setBuyer(org.helianto.core.User buyer) {
        this.buyer = buyer;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
