package org.helianto.core.form;

import java.util.Date;

import org.helianto.core.domain.Identity;

/**
 * Base class to {@link ControlForm} form implementations.
 * 
 * @author mauriciofernandesdecastro
 */
public class AbstractControllable extends AbstractInternalForm implements ControlForm {

	private static final long serialVersionUID = 1L;
    private Identity owner;
    private Date issueDate;
    private int complete;
    private char resolution;
    private Date nextCheckDate;
    private int frequency;
    private int frequencyType;

    /**
     * Constructor.
     */
    public AbstractControllable() {
    	super();
        setResolution(' ');
        setComplete(-1);
        setNextCheckDate(null);
    }
    
    public Identity getOwner() {
		return owner;
	}
    public void setOwner(Identity owner) {
		this.owner = owner;
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
    
    public int getComplete() {
    	return complete;
    }
    public void setComplete(int complete) {
        this.complete = complete;
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
