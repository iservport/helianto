package org.helianto.core;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * 			
 * <p>
 * Persist services.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 * 				
 * 		
*/
public class Service implements Serializable {

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String serviceName;

    /** full constructor */
    public Service(String serviceName) {
        this.serviceName = serviceName;
    }

    /** default constructor */
    public Service() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
