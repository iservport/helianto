package org.helianto.document.form;

import org.helianto.document.Serializer;

/**
 * Custom document form.
 * 
 * @author mauriciofernandesdecastro
 */
public interface CustomDocumentForm extends DocumentForm {
	
	/**
	 * Series.
	 */
	public Serializer getSeries();

	/**
	 * Content type.
	 */
	public char getContentType();
	
	/**
	 * Builder code.
	 */
	public String getBuilderCode();

}
