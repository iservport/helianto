package org.helianto.core;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * 					
 * <p>
 * Domain object to extend <code>Entity</code> with address
 * information.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 * 				
 * 			
*/
public class AddressableEntity extends Entity implements Serializable {

    /** nullable persistent field */
    private String entityAddress1;

    /** nullable persistent field */
    private String entityAddress2;

    /** nullable persistent field */
    private String entityCityName;

    /** nullable persistent field */
    private String entityProvinceName;

    /** nullable persistent field */
    private String entityPostalCode;

    /** full constructor */
    public AddressableEntity(String alias, org.helianto.core.Supervisor supervisor, String entityAddress1, String entityAddress2, String entityCityName, String entityProvinceName, String entityPostalCode) {
        super(alias, supervisor);
        this.entityAddress1 = entityAddress1;
        this.entityAddress2 = entityAddress2;
        this.entityCityName = entityCityName;
        this.entityProvinceName = entityProvinceName;
        this.entityPostalCode = entityPostalCode;
    }

    /** default constructor */
    public AddressableEntity() {
    }

    /** minimal constructor */
    public AddressableEntity(String alias) {
      super(alias);
    }

    public String getEntityAddress1() {
        return this.entityAddress1;
    }

    public void setEntityAddress1(String entityAddress1) {
        this.entityAddress1 = entityAddress1;
    }

    public String getEntityAddress2() {
        return this.entityAddress2;
    }

    public void setEntityAddress2(String entityAddress2) {
        this.entityAddress2 = entityAddress2;
    }

    public String getEntityCityName() {
        return this.entityCityName;
    }

    public void setEntityCityName(String entityCityName) {
        this.entityCityName = entityCityName;
    }

    public String getEntityProvinceName() {
        return this.entityProvinceName;
    }

    public void setEntityProvinceName(String entityProvinceName) {
        this.entityProvinceName = entityProvinceName;
    }

    public String getEntityPostalCode() {
        return this.entityPostalCode;
    }

    public void setEntityPostalCode(String entityPostalCode) {
        this.entityPostalCode = entityPostalCode;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
