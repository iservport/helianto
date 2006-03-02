package org.helianto.process;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * 				
 * <p>
 * A class to define relationships between different document types.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 * 				
 * 		
*/
public class Tree implements Serializable {

    /** identifier field */
    private Long id;

    /** persistent field */
    private int sequence;

    /** nullable persistent field */
    private Double coefficient;

    /** persistent field */
    private int associationType;

    /** nullable persistent field */
    private org.helianto.process.Document parent;

    /** nullable persistent field */
    private org.helianto.process.Document child;

    /** nullable persistent field */
    private org.helianto.process.Function function;

    /** full constructor */
    public Tree(int sequence, Double coefficient, int associationType, org.helianto.process.Document parent, org.helianto.process.Document child, org.helianto.process.Function function) {
        this.sequence = sequence;
        this.coefficient = coefficient;
        this.associationType = associationType;
        this.parent = parent;
        this.child = child;
        this.function = function;
    }

    /** default constructor */
    public Tree() {
    }

    /** minimal constructor */
    public Tree(int sequence, int associationType) {
        this.sequence = sequence;
        this.associationType = associationType;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSequence() {
        return this.sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public Double getCoefficient() {
        return this.coefficient;
    }

    public void setCoefficient(Double coefficient) {
        this.coefficient = coefficient;
    }

    public int getAssociationType() {
        return this.associationType;
    }

    public void setAssociationType(int associationType) {
        this.associationType = associationType;
    }

    public org.helianto.process.Document getParent() {
        return this.parent;
    }

    public void setParent(org.helianto.process.Document parent) {
        this.parent = parent;
    }

    public org.helianto.process.Document getChild() {
        return this.child;
    }

    public void setChild(org.helianto.process.Document child) {
        this.child = child;
    }

    public org.helianto.process.Function getFunction() {
        return this.function;
    }

    public void setFunction(org.helianto.process.Function function) {
        this.function = function;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
