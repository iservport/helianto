package org.helianto.core;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * 					
 * <p>
 * Domain object to extend <code>AddressableEntity</code> as an
 * individual
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 * 				
 * 				
*/
public class Individual extends AddressableEntity implements Serializable {

    /** nullable persistent field */
    private String credentialName;

    /** full constructor */
    public Individual(String alias, org.helianto.core.Supervisor supervisor, String entityAddress1, String entityAddress2, String entityCityName, String entityProvinceName, String entityPostalCode, String credentialName) {
        super(alias, supervisor, entityAddress1, entityAddress2, entityCityName, entityProvinceName, entityPostalCode);
        this.credentialName = credentialName;
    }

    /** default constructor */
    public Individual() {
    }

    /** minimal constructor */
    public Individual(String alias) {
      super(alias);
    }

    public String getCredentialName() {
        return this.credentialName;
    }

    public void setCredentialName(String credentialName) {
        this.credentialName = credentialName;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
