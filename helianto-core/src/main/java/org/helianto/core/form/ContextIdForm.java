package org.helianto.core.form;

import java.io.Serializable;

/**
 * ContextId form interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface ContextIdForm 
	extends Serializable
{

	/**
	 * The owning context id.
	 */
	int getContextId();

}
