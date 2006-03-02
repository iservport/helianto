package org.helianto.process;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.helianto.core.Entity;


/** 
 * 				
 * <p>
 * A class to represent a unit.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 * 				
 * 		
*/
public class Unit implements Serializable {

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String unitCode;

    /** nullable persistent field */
    private String unitName;

    /** nullable persistent field */
    private Entity entity;

    /** nullable persistent field */
    private org.helianto.process.Unit parent;

    /** full constructor */
    public Unit(String unitCode, String unitName, Entity entity, org.helianto.process.Unit parent) {
        this.unitCode = unitCode;
        this.unitName = unitName;
        this.entity = entity;
        this.parent = parent;
    }

    /** default constructor */
    public Unit() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUnitCode() {
        return this.unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getUnitName() {
        return this.unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Entity getEntity() {
        return this.entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public org.helianto.process.Unit getParent() {
        return this.parent;
    }

    public void setParent(org.helianto.process.Unit parent) {
        this.parent = parent;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
