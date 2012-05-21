package org.helianto.document.form;

import org.helianto.document.domain.DocumentFolder;

/**
 * Custom document form.
 * 
 * @author mauriciofernandesdecastro
 */
public interface CustomDocumentForm extends DocumentForm, DocumentFolderForm {
	
	/**
	 * Series.
	 */
	public DocumentFolder getSeries();

}
