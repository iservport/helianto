package org.helianto.process;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * 				
 * <p>
 * An operation characteristic.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 * 				
 * 		
*/
public class Characteristic implements Serializable {

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Integer sequence;

    /** nullable persistent field */
    private String shortDesc;

    /** nullable persistent field */
    private Integer classification;

    /** nullable persistent field */
    private org.helianto.process.Operation operation;

    /** full constructor */
    public Characteristic(Integer sequence, String shortDesc, Integer classification, org.helianto.process.Operation operation) {
        this.sequence = sequence;
        this.shortDesc = shortDesc;
        this.classification = classification;
        this.operation = operation;
    }

    /** default constructor */
    public Characteristic() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSequence() {
        return this.sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getShortDesc() {
        return this.shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public Integer getClassification() {
        return this.classification;
    }

    public void setClassification(Integer classification) {
        this.classification = classification;
    }

    public org.helianto.process.Operation getOperation() {
        return this.operation;
    }

    public void setOperation(org.helianto.process.Operation operation) {
        this.operation = operation;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
