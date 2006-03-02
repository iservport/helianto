package org.helianto.process;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.helianto.core.Entity;


/** 
 * 				
 * <p>
 * A class to represent a material.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 * 				
 * 		
*/
public class Material implements Serializable {

    /** identifier field */
    private Long id;

    /** nullable persistent field */
    private Long internalNumber;

    /** nullable persistent field */
    private String materialName;

    /** nullable persistent field */
    private Entity entity;

    /** nullable persistent field */
    private org.helianto.process.Material parent;

    /** persistent field */
    private org.helianto.process.Unit materialUnit;

    /** full constructor */
    public Material(Long internalNumber, String materialName, Entity entity, org.helianto.process.Material parent, org.helianto.process.Unit materialUnit) {
        this.internalNumber = internalNumber;
        this.materialName = materialName;
        this.entity = entity;
        this.parent = parent;
        this.materialUnit = materialUnit;
    }

    /** default constructor */
    public Material() {
    }

    /** minimal constructor */
    public Material(org.helianto.process.Unit materialUnit) {
        this.materialUnit = materialUnit;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInternalNumber() {
        return this.internalNumber;
    }

    public void setInternalNumber(Long internalNumber) {
        this.internalNumber = internalNumber;
    }

    public String getMaterialName() {
        return this.materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public Entity getEntity() {
        return this.entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public org.helianto.process.Material getParent() {
        return this.parent;
    }

    public void setParent(org.helianto.process.Material parent) {
        this.parent = parent;
    }

    public org.helianto.process.Unit getMaterialUnit() {
        return this.materialUnit;
    }

    public void setMaterialUnit(org.helianto.process.Unit materialUnit) {
        this.materialUnit = materialUnit;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
