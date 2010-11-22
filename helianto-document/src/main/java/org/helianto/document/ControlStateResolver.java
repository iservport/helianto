package org.helianto.document;

import java.util.Date;

/**
 * Helper class to resolve <code>AbstractRecord</code> state.
 * 
 * @author mauriciofernandesdecastro
 */
public class ControlStateResolver extends RecordStateResolver {

	/**
	 * Control constructor.
	 * 
	 * @param control
	 */
	public ControlStateResolver(AbstractControl control) {
		super(control);
	}

    /**
     * True if next check date is past.
     */
    public boolean isLate() {
    	if ((new Date()).after(((AbstractControl) getResolverTarget()).getNextCheckDate())) {
    		return true;
    	}
    	return false;
    }
    
}
