package org.helianto.core.internal;

import java.io.Serializable;

/**
 * Interface to classes having parsed content.
 * 
 * @author mauriciofernandesdecastro
 */
public interface Extensible 
	extends Serializable {

    /**
     * Parsed content.
     */
    String getParsedContent();
    
}
