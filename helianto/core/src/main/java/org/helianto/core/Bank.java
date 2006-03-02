package org.helianto.core;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * 				
 * <p>
 * Persist a relationship to a bank 
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 * 				
 * 			
*/
public class Bank extends Partner implements Serializable {

    /** nullable persistent field */
    private String checkFormFile;

    /** full constructor */
    public Bank(String alias, char state, boolean strong, Date relatedSince, String numberAssignedRemotely, Long importedKey, String profile, org.helianto.core.Entity entity, org.helianto.core.Entity related, String checkFormFile) {
        super(alias, state, strong, relatedSince, numberAssignedRemotely, importedKey, profile, entity, related);
        this.checkFormFile = checkFormFile;
    }

    /** default constructor */
    public Bank() {
    }

    /** minimal constructor */
    public Bank(String alias, char state, boolean strong) {
      super(alias, state, strong);
    }

    public String getCheckFormFile() {
        return this.checkFormFile;
    }

    public void setCheckFormFile(String checkFormFile) {
        this.checkFormFile = checkFormFile;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
