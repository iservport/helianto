package org.helianto.core.domain.type;

import org.helianto.core.domain.Service;

/**
 * To be implemented by any entity directly related to an <code>Service</code>.
 * 
 * @author mauriciofernandesdecastro
 */
public interface ServiceEntity extends java.io.Serializable {

	/**
	 * The owning service.
	 */
	public Service getService();

}
