package org.helianto.document;

import org.helianto.core.domain.type.TrunkEntity;



/**
 * Interface to help build Customizable keys.
 * 
 * @author mauriciofernandesdecastro
 */
public interface Customizer extends TrunkEntity {

    /**
     * Pattern to be used in association with <code>SequenceMgr</code>.
     * 
     * <p>
     * Patterns like "P0000" will produce codes
     * like P0001, P0002, etc., while 0000/'09' builds
     * 0001/09, 0002/09, etc.
     * </p>
     */
	public String getNumberPattern();
	
	/**
     * Build the code.
     */
	public String buildCode(long internalNumber);
    
}
