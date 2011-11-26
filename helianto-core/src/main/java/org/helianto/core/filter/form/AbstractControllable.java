package org.helianto.core.filter.form;

import java.util.Date;

import org.helianto.core.Controllable;

/**
 * Base class to {@link Controllable} form implementations.
 * 
 * @author mauriciofernandesdecastro
 */
public class AbstractControllable extends AbstractInternalForm implements Controllable {

	private static final long serialVersionUID = 1L;
    private int complete;
    private char resolution;
    private Date nextCheckDate;

    public void reset() {
        setResolution(' ');
        setComplete(-1);
        setNextCheckDate(null);
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
    
}
