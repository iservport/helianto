package org.helianto.core.form.internal;

import java.util.Date;

import org.helianto.core.form.ControlForm;

/**
 * Base class to {@link ControlForm} form implementations.
 * 
 * @author mauriciofernandesdecastro
 */
public abstract class AbstractControlForm 
	extends AbstractInternalForm implements ControlForm {

	private static final long serialVersionUID = 1L;
    private int ownerId;
    private Date issueDate;
    private char resolution = ' ';
    private Date nextCheckDate;
    private int frequency;
    private int frequencyType;

    /**
     * Constructor.
     */
    public AbstractControlForm() {
    	super();
    }
    
    public int getOwnerId() {
		return ownerId;
	}
    public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
    
    public Date getIssueDate() {
		return issueDate;
	}
    public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
    
    public char getResolution() {
    	return resolution;
    }
    public void setResolution(char resolution) {
        this.resolution = resolution;
    }
    
    public Date getNextCheckDate() {
        return this.nextCheckDate;
    }
    public void setNextCheckDate(Date nextCheckDate) {
        this.nextCheckDate = nextCheckDate;
    }
    
    public int getFrequency() {
		return frequency;
	}
    public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
    
    public int getFrequencyType() {
		return frequencyType;
	}
    public void setFrequencyType(int frequencyType) {
		this.frequencyType = frequencyType;
	}
    
}
