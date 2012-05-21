package org.helianto.document.form;

import org.helianto.core.Resettable;
import org.helianto.core.TrunkEntity;

/**
 * Serializer form.
 * 
 * @author mauriciofernandesdecastro
 */
public interface DocumentFolderForm extends TrunkEntity, Resettable {

	/**
	 * Content type.
	 */
	public char getContentType();
	
	/**
	 * Builder code.
	 */
	public String getFolderCode();

}
