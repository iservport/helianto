package org.helianto.core;

import org.helianto.core.def.Resolution;
import org.joda.time.DateMidnight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Helper class to resolve states.
 * 
 * @author mauriciofernandesdecastro
 */
public class SimpleStateResolver implements StateResolver {

	private Object target;
	
	/**
	 * Control constructor.
	 * 
	 * @param control
	 */
	public SimpleStateResolver(Object target) {
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
	private Verifiable getControl() {
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
     * Default implementation returns true if Resolution.TODO.
     * </p>
     */
    public boolean isRunning() {
    	if (getControl().getResolution()==Resolution.TODO.getValue()) return true;
        return false;
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
     * True if the resolution is DONE.
     */
    public boolean isComplete() {
    	if (getControl().getResolution()==Resolution.DONE.getValue()) return true;
        return false;
    }

    /**
     * True if next check date is past tonight before midnight.
     */
    public boolean isLate() {
    	DateMidnight tonight = new DateMidnight().plusDays(1).minus(1000);
    	if (getControl().getNextCheckDate()==null) {
    		logger.warn("Control {} nextCheckDate is null.", getControl());
    	}
    	else if (tonight.isAfter(getControl().getNextCheckDate().getTime())) {
    		logger.warn("Control {} is late (after {}).", getControl(), tonight);
    		return true;
    	}
    	return false;
    }
    
    private static final Logger logger = LoggerFactory.getLogger(SimpleStateResolver.class);
    
}
