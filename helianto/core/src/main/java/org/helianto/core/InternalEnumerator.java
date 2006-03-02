package org.helianto.core;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * 				
 * <p>
 * A class to hold last value for internal number lists.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 * 				
 * 		
*/
public class InternalEnumerator implements Serializable {

    /** identifier field */
    private Integer id;

    /** persistent field */
    private String typeName;

    /** persistent field */
    private int lastNumber;

    /** nullable persistent field */
    private org.helianto.core.Entity entity;

    /** full constructor */
    public InternalEnumerator(String typeName, int lastNumber, org.helianto.core.Entity entity) {
        this.typeName = typeName;
        this.lastNumber = lastNumber;
        this.entity = entity;
    }

    /** default constructor */
    public InternalEnumerator() {
    }

    /** minimal constructor */
    public InternalEnumerator(String typeName, int lastNumber) {
        this.typeName = typeName;
        this.lastNumber = lastNumber;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getLastNumber() {
        return this.lastNumber;
    }

    public void setLastNumber(int lastNumber) {
        this.lastNumber = lastNumber;
    }

    public org.helianto.core.Entity getEntity() {
        return this.entity;
    }

    public void setEntity(org.helianto.core.Entity entity) {
        this.entity = entity;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
