package org.helianto.process;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.helianto.core.Entity;


/** 
 * 			
 * <p>
 * A class to represent a process resource
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 * 				
 * 		
*/
public class Resource implements Serializable {

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String resourceCode;

    /** nullable persistent field */
    private String resourceName;

    /** persistent field */
    private int resourceType;

    /** nullable persistent field */
    private Entity entity;

    /** nullable persistent field */
    private org.helianto.process.Resource parent;

    /** full constructor */
    public Resource(String resourceCode, String resourceName, int resourceType, Entity entity, org.helianto.process.Resource parent) {
        this.resourceCode = resourceCode;
        this.resourceName = resourceName;
        this.resourceType = resourceType;
        this.entity = entity;
        this.parent = parent;
    }

    /** default constructor */
    public Resource() {
    }

    /** minimal constructor */
    public Resource(int resourceType) {
        this.resourceType = resourceType;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResourceCode() {
        return this.resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public String getResourceName() {
        return this.resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public int getResourceType() {
        return this.resourceType;
    }

    public void setResourceType(int resourceType) {
        this.resourceType = resourceType;
    }

    public Entity getEntity() {
        return this.entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public org.helianto.process.Resource getParent() {
        return this.parent;
    }

    public void setParent(org.helianto.process.Resource parent) {
        this.parent = parent;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
