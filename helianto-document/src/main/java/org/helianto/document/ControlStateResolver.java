package org.helianto.document;

import java.util.Date;

/**
 * Helper class to resolve <code>AbstractRecord</code> state.
 * 
 * @author mauriciofernandesdecastro
 */
public class ControlStateResolver extends RepeatableStateResolver {

	/**
	 * Control constructor.
	 * 
	 * @param control
	 */
	public ControlStateResolver(Object target) {
		super(target);
	}

	/**
	 * Local resolver target.
	 */
	private Controllable getControl() {
		return getResolverTarget();
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
