package org.helianto.process;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * 				
 * <p>
 * A class to represent a specification.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 * 				
 * 		
*/
public class Specification implements Serializable {

    /** identifier field */
    private Long id;

    /** nullable persistent field */
    private String speficicationDesc;

    /** persistent field */
    private int phase;

    /** nullable persistent field */
    private org.helianto.process.Specification parent;

    /** nullable persistent field */
    private org.helianto.process.Unit unit;

    /** nullable persistent field */
    private org.helianto.process.Characteristic characteristic;

    /** full constructor */
    public Specification(String speficicationDesc, int phase, org.helianto.process.Specification parent, org.helianto.process.Unit unit, org.helianto.process.Characteristic characteristic) {
        this.speficicationDesc = speficicationDesc;
        this.phase = phase;
        this.parent = parent;
        this.unit = unit;
        this.characteristic = characteristic;
    }

    /** default constructor */
    public Specification() {
    }

    /** minimal constructor */
    public Specification(int phase) {
        this.phase = phase;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpeficicationDesc() {
        return this.speficicationDesc;
    }

    public void setSpeficicationDesc(String speficicationDesc) {
        this.speficicationDesc = speficicationDesc;
    }

    public int getPhase() {
        return this.phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

    public org.helianto.process.Specification getParent() {
        return this.parent;
    }

    public void setParent(org.helianto.process.Specification parent) {
        this.parent = parent;
    }

    public org.helianto.process.Unit getUnit() {
        return this.unit;
    }

    public void setUnit(org.helianto.process.Unit unit) {
        this.unit = unit;
    }

    public org.helianto.process.Characteristic getCharacteristic() {
        return this.characteristic;
    }

    public void setCharacteristic(org.helianto.process.Characteristic characteristic) {
        this.characteristic = characteristic;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
