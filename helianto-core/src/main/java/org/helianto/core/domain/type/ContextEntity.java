package org.helianto.core.domain.type;

import java.io.Serializable;

/**
 * To be implemented by any entity directly related to an <code>Context</code>.
 * 
 * @author mauriciofernandesdecastro
 */
public interface ContextEntity extends Serializable {
	
	/**
	 * The owning context id.
	 */
	public int getContextId();

}
