package org.helianto.core;
// Generated 14/03/2006 22:23:56 by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 *  A 
 * 				class to persist Resources. 
 */

public class HumanResource extends org.helianto.core.Contact implements java.io.Serializable {


    // Fields    

     private int resourceState;
     private int resourceCategory;
     private Date born;
     private Date hired;
     private Date terminated;


    // Constructors

    /** default constructor */
    public HumanResource() {
    }

	/** minimal constructor */
    public HumanResource(Partner partner, Credential credential, int internalNumber, int priority, int resourceState, int resourceCategory) {
        super(partner, credential, internalNumber, priority);        
        this.resourceState = resourceState;
        this.resourceCategory = resourceCategory;
    }
    
    /** full constructor */
    public HumanResource(Partner partner, Credential credential, int internalNumber, String jobReference, String department, int priority, int resourceState, int resourceCategory, Date born, Date hired, Date terminated) {
        super(partner, credential, internalNumber, jobReference, department, priority);        
        this.resourceState = resourceState;
        this.resourceCategory = resourceCategory;
        this.born = born;
        this.hired = hired;
        this.terminated = terminated;
    }
    

   
    // Property accessors

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
   








}
