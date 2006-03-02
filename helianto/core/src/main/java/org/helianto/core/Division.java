package org.helianto.core;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * 				
 * <p>
 * Persist a relationship to a division. 
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 * 				
 * 			
*/
public class Division extends Partner implements Serializable {

    /** persistent field */
    private char divisionType;

    /** full constructor */
    public Division(String alias, char state, boolean strong, Date relatedSince, String numberAssignedRemotely, Long importedKey, String profile, org.helianto.core.Entity entity, org.helianto.core.Entity related, char divisionType) {
        super(alias, state, strong, relatedSince, numberAssignedRemotely, importedKey, profile, entity, related);
        this.divisionType = divisionType;
    }

    /** default constructor */
    public Division() {
    }

    /** minimal constructor */
    public Division(String alias, char state, boolean strong, char divisionType) {
      super(alias, state, strong);
        this.divisionType = divisionType;
    }

    public char getDivisionType() {
        return this.divisionType;
    }

    public void setDivisionType(char divisionType) {
        this.divisionType = divisionType;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
