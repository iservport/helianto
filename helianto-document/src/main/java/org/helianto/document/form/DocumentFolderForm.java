package org.helianto.document.form;

import org.helianto.core.Resettable;
import org.helianto.core.filter.form.FolderForm;

/**
 * Serializer form.
 * 
 * @author mauriciofernandesdecastro
 */
public interface DocumentFolderForm extends FolderForm, Resettable {

	/**
	 * Content type.
	 */
	public char getContentType();
	
}
