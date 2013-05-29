package org.helianto.document.form;

import org.helianto.core.form.FolderForm;

/**
 * Serializer form.
 * 
 * @author mauriciofernandesdecastro
 */
public interface DocumentFolderForm 
	extends FolderForm {

	/**
	 * Content type.
	 */
	public char getContentType();

}
