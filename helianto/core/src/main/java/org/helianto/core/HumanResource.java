package org.helianto.core;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 *  A 
 * 				class to persist Resources. 
*/
public class HumanResource extends Contact implements Serializable {

    /** persistent field */
    private int resourceState;

    /** persistent field */
    private int resourceCategory;

    /** nullable persistent field */
    private Date born;

    /** nullable persistent field */
    private Date hired;

    /** nullable persistent field */
    private Date terminated;

    /** full constructor */
    public HumanResource(int internalNumber, String jobReference, String department, int priority, org.helianto.core.Partner partner, org.helianto.core.Credential credential, int resourceState, int resourceCategory, Date born, Date hired, Date terminated) {
        super(internalNumber, jobReference, department, priority, partner, credential);
        this.resourceState = resourceState;
        this.resourceCategory = resourceCategory;
        this.born = born;
        this.hired = hired;
        this.terminated = terminated;
    }

    /** default constructor */
    public HumanResource() {
    }

    /** minimal constructor */
    public HumanResource(int internalNumber, int priority, int resourceState, int resourceCategory) {
      super(internalNumber, priority);
        this.resourceState = resourceState;
        this.resourceCategory = resourceCategory;
    }

    public int getResourceState() {
        return this.resourceState;
    }

    public void setResourceState(int resourceState) {
        this.resourceState = resourceState;
    }

    public int getResourceCategory() {
        return this.resourceCategory;
    }

    public void setResourceCategory(int resourceCategory) {
        this.resourceCategory = resourceCategory;
    }

    public Date getBorn() {
        return this.born;
    }

    public void setBorn(Date born) {
        this.born = born;
    }

    public Date getHired() {
        return this.hired;
    }

    public void setHired(Date hired) {
        this.hired = hired;
    }

    public Date getTerminated() {
        return this.terminated;
    }

    public void setTerminated(Date terminated) {
        this.terminated = terminated;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
