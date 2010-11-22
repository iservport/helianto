package org.helianto.document;

/**
 * Helper class to resolve <code>AbstractRecord</code> state.
 * 
 * @author mauriciofernandesdecastro
 */
public class RecordStateResolver {
	
	private AbstractRecord record;
	
	/**
	 * Record constructor.
	 * 
	 * @param record
	 */
	public RecordStateResolver(AbstractRecord record) {
		this.record = record;
	}
	
	/**
	 * The  resolver target.
	 * 
	 * @param <T>
	 */
	@SuppressWarnings("unchecked")
	protected <T extends AbstractRecord> T getResolverTarget() {
		return (T) this.record;
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
    	if (record.getResolution()==Resolution.PRELIMINARY.getValue()) return true;
    	if (record.getResolution()==Resolution.TODO.getValue()) return true;
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
    	if (record.getResolution()==Resolution.WAIT.getValue()) return true;
        return false;
    }

}
