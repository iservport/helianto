package org.helianto.document;

/**
 * Helper class to resolve <code>AbstractRecord</code> state.
 * 
 * @author mauriciofernandesdecastro
 */
public class RecordStateResolver implements StateResolver {
	
	private Object target;
	
	/**
	 * Record constructor.
	 * 
	 * @param record
	 */
	public RecordStateResolver(Object target) {
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
	private Recordable getRecord() {
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
    	if (getRecord().getResolution()==Resolution.PRELIMINARY.getValue()) return true;
    	if (getRecord().getResolution()==Resolution.TODO.getValue()) return true;
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
    	if (getRecord().getResolution()==Resolution.WAIT.getValue()) return true;
        return false;
    }

    /**
     * True if complete is 100%.
     */
    public boolean isComplete() {
    	if (getRecord().getComplete()==100) return true;
        return false;
    }

}
