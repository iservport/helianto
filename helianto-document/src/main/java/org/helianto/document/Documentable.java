package org.helianto.document;

import org.helianto.core.domain.type.TrunkEntity;

/**
 * Interface to represent documents in general
 * .
 * @author mauriciofernandesdecastro
 */
public interface Documentable extends TrunkEntity {
	
	/**
	 * Document code.
	 */
	String getDocCode();

}
