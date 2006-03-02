package org.helianto.core;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * 				
 * <p>
 * Persist a contact.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 * 				
 *         
*/
public class Contact implements Serializable {

    /** identifier field */
    private Long id;

    /** persistent field */
    private int internalNumber;

    /** nullable persistent field */
    private String jobReference;

    /** nullable persistent field */
    private String department;

    /** persistent field */
    private int priority;

    /** nullable persistent field */
    private org.helianto.core.Partner partner;

    /** nullable persistent field */
    private org.helianto.core.Credential credential;

    /** full constructor */
    public Contact(int internalNumber, String jobReference, String department, int priority, org.helianto.core.Partner partner, org.helianto.core.Credential credential) {
        this.internalNumber = internalNumber;
        this.jobReference = jobReference;
        this.department = department;
        this.priority = priority;
        this.partner = partner;
        this.credential = credential;
    }

    /** default constructor */
    public Contact() {
    }

    /** minimal constructor */
    public Contact(int internalNumber, int priority) {
        this.internalNumber = internalNumber;
        this.priority = priority;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getInternalNumber() {
        return this.internalNumber;
    }

    public void setInternalNumber(int internalNumber) {
        this.internalNumber = internalNumber;
    }

    public String getJobReference() {
        return this.jobReference;
    }

    public void setJobReference(String jobReference) {
        this.jobReference = jobReference;
    }

    public String getDepartment() {
        return this.department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public org.helianto.core.Partner getPartner() {
        return this.partner;
    }

    public void setPartner(org.helianto.core.Partner partner) {
        this.partner = partner;
    }

    public org.helianto.core.Credential getCredential() {
        return this.credential;
    }

    public void setCredential(org.helianto.core.Credential credential) {
        this.credential = credential;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
