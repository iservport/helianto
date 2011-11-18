package org.helianto.core;

import java.util.Date;

import org.helianto.core.def.Resolution;


/**
 * Helper class to resolve <code>AbstractRecord</code> state.
 * 
 * @author mauriciofernandesdecastro
 */
public class ControlStateResolver implements StateResolver {

	private Object target;
	
	/**
	 * Control constructor.
	 * 
	 * @param control
	 */
	public ControlStateResolver(Object target) {
		this.target = target;
	}
	
	/**
	 * The  resolver target.
	 * 
	 * @param <T>
	 */
	@SuppressWarnings("unchecked")
	protected <T> T getResolverTarget() {
		return (T) this.target;
	}
	
	/**
	 * Local resolver target.
	 */
	private Controllable getControl() {
		return getResolverTarget();
	}

    /**
     * True if the resolution indicates state ACTIVE.
     * 
     * <p>
     * Default implementation returns true if Resolution.PRELIMINARY or {@link #isRunning()}.
     * </p>
     */
    public boolean isActive() {
        return isRunning() || isSuspended();
    }
    
    /**
     * True if the resolution indicates state RUNNIG.
     * 
     * <p>
     * Default implementation returns true if Resolution.TODO or {@link #isSuspended()}.
     * </p>
     */
    public boolean isRunning() {
    	if (getControl().getResolution()==Resolution.PRELIMINARY.getValue()) return true;
    	if (getControl().getResolution()==Resolution.TODO.getValue()) return true;
        return isSuspended();
    }

    /**
     * True if the resolution indicates state SUSPENDED.
     * 
     * <p>
     * Default implementation returns true if Resolution.WAIT.
     * </p>
     */
    public boolean isSuspended() {
    	if (getControl().getResolution()==Resolution.WAIT.getValue()) return true;
        return false;
    }

    /**
     * True if complete is 100%.
     */
    public boolean isComplete() {
    	if (getControl().getComplete()==100) return true;
        return false;
    }

    /**
     * True if next check date is past.
     */
    public boolean isLate() {
    	if (getControl().getNextCheckDate()!=null && (new Date()).after(getControl().getNextCheckDate())) {
    		return true;
    	}
    	return false;
    }
    
}
