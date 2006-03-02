package org.helianto.core;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * 				
 * <p>
 * Persist a relationship to an Agent, like a sales representative. 
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 * 				
 * 			
*/
public class Agent extends Partner implements Serializable {

    /** full constructor */
    public Agent(String alias, char state, boolean strong, Date relatedSince, String numberAssignedRemotely, Long importedKey, String profile, org.helianto.core.Entity entity, org.helianto.core.Entity related) {
        super(alias, state, strong, relatedSince, numberAssignedRemotely, importedKey, profile, entity, related);
    }

    /** default constructor */
    public Agent() {
    }

    /** minimal constructor */
    public Agent(String alias, char state, boolean strong) {
      super(alias, state, strong);
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
