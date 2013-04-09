package org.helianto.core.form;

import java.util.Date;

import org.helianto.core.domain.Identity;

/**
 * Base class to {@link ControlForm} form implementations.
 * 
 * @author mauriciofernandesdecastro
 */
public class AbstractEventForm 
	extends AbstractTrunkForm 
	implements EventForm {

	private static final long serialVersionUID = 1L;
    private Identity owner;
    private Date issueDate;
    private char resolution;

    /**
     * Constructor.
     */
    public AbstractEventForm() {
    	super();
        setResolution(' ');
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
    
}
