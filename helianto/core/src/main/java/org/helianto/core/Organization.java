package org.helianto.core;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * 					
 * <p>
 * Domain object to extend <code>AddressableEntity</code> as an
 * organization.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 * 				
 * 				
*/
public class Organization extends AddressableEntity implements Serializable {

    /** nullable persistent field */
    private String businessName;

    /** full constructor */
    public Organization(String alias, org.helianto.core.Supervisor supervisor, String entityAddress1, String entityAddress2, String entityCityName, String entityProvinceName, String entityPostalCode, String businessName) {
        super(alias, supervisor, entityAddress1, entityAddress2, entityCityName, entityProvinceName, entityPostalCode);
        this.businessName = businessName;
    }

    /** default constructor */
    public Organization() {
    }

    /** minimal constructor */
    public Organization(String alias) {
      super(alias);
    }

    public String getBusinessName() {
        return this.businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
