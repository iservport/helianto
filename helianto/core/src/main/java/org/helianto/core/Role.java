package org.helianto.core;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * 			
 * <p>
 * Persist roles.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 * 				
 * 		
*/
public class Role implements Serializable {

    /** identifier field */
    private Integer id;

    /** persistent field */
    private String roleName;

    /** nullable persistent field */
    private org.helianto.core.Service service;

    /** nullable persistent field */
    private org.helianto.core.User user;

    /** full constructor */
    public Role(String roleName, org.helianto.core.Service service, org.helianto.core.User user) {
        this.roleName = roleName;
        this.service = service;
        this.user = user;
    }

    /** default constructor */
    public Role() {
    }

    /** minimal constructor */
    public Role(String roleName) {
        this.roleName = roleName;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public org.helianto.core.Service getService() {
        return this.service;
    }

    public void setService(org.helianto.core.Service service) {
        this.service = service;
    }

    public org.helianto.core.User getUser() {
        return this.user;
    }

    public void setUser(org.helianto.core.User user) {
        this.user = user;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
