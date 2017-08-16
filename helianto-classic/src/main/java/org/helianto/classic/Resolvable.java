package org.helianto.classic;

import java.io.Serializable;

/**
 * Resolvable interface.
 * 
 * <p>
 * To be implemented by classes exposing a resolution, e.g. "DONE", or 
 * "PENDING".
 * </p>
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface Resolvable 
	extends Serializable {

    /**
     * Resolution.
     */
    char getResolution();

}
