package org.helianto.document.form;

import org.helianto.core.Resettable;
import org.helianto.core.TrunkEntity;

/**
 * Document form.
 * 
 * @author mauriciofernandesdecastro
 */
public interface DocumentForm extends TrunkEntity, Resettable {

	/**
	 * Document code.
	 */
	public String getDocCode();

	/**
	 * Document name.
	 */
	public String getDocName();

	/**
	 * Document priority.
	 */
	public char getPriority();

}
