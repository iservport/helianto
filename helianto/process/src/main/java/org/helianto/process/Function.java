package org.helianto.process;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.helianto.core.Entity;


/** 
 * 				
 * <p>
 * A class to represent a function.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 * 				
 * 		
*/
public class Function implements Serializable {

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String functionName;

    /** nullable persistent field */
    private Entity entity;

    /** nullable persistent field */
    private org.helianto.process.Function parent;

    /** full constructor */
    public Function(String functionName, Entity entity, org.helianto.process.Function parent) {
        this.functionName = functionName;
        this.entity = entity;
        this.parent = parent;
    }

    /** default constructor */
    public Function() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFunctionName() {
        return this.functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public Entity getEntity() {
        return this.entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public org.helianto.process.Function getParent() {
        return this.parent;
    }

    public void setParent(org.helianto.process.Function parent) {
        this.parent = parent;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
