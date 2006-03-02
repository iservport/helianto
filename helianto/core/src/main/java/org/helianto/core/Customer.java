package org.helianto.core;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


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
public class Customer extends Partner implements Serializable {

    /** nullable persistent field */
    private org.helianto.core.Partner deliverTo;

    /** full constructor */
    public Customer(String alias, char state, boolean strong, Date relatedSince, String numberAssignedRemotely, Long importedKey, String profile, org.helianto.core.Entity entity, org.helianto.core.Entity related, org.helianto.core.Partner deliverTo) {
        super(alias, state, strong, relatedSince, numberAssignedRemotely, importedKey, profile, entity, related);
        this.deliverTo = deliverTo;
    }

    /** default constructor */
    public Customer() {
    }

    /** minimal constructor */
    public Customer(String alias, char state, boolean strong) {
      super(alias, state, strong);
    }

    public org.helianto.core.Partner getDeliverTo() {
        return this.deliverTo;
    }

    public void setDeliverTo(org.helianto.core.Partner deliverTo) {
        this.deliverTo = deliverTo;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
