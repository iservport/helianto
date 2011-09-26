package org.helianto.document.form;

import org.helianto.document.Serializer;

/**
 * Custom document form.
 * 
 * @author mauriciofernandesdecastro
 */
public interface CustomDocumentForm extends DocumentForm, SerializerForm {
	
	/**
	 * Series.
	 */
	public Serializer getSeries();

}
