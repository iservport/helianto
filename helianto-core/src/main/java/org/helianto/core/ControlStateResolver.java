package org.helianto.core;



/**
 * Helper class to resolve <code>AbstractRecord</code> state.
 * 
 * @author mauriciofernandesdecastro
 */
public class ControlStateResolver extends SimpleStateResolver implements StateResolver {

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
     * True if complete is 100%.
     */
    public boolean isComplete() {
    	if (getControl().getComplete()==100) return true;
        return false;
    }

}
