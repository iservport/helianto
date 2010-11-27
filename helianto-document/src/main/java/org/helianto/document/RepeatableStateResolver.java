package org.helianto.document;


/**
 * Helper class to resolve <code>AbstractRecord</code> state.
 * 
 * @author mauriciofernandesdecastro
 */
public class RepeatableStateResolver extends RecordStateResolver {

	/**
	 * Control constructor.
	 * 
	 * @param repeatable
	 */
	public RepeatableStateResolver(Object target) {
		super(target);
	}

	/**
	 * Local resolver target.
	 */
	private Repeatable getControl() {
		return getResolverTarget();
	}

    /**
     * True if a next check is required if active.
     */
    public boolean isRepeatable() {
    	if (getControl().getTrackingMode()==TrackingMode.CONTINUOUS.getValue()) {
    		return true;
    	}
    	return false;
    }
    
}
