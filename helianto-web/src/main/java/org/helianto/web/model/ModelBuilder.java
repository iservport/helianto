package org.helianto.web.model;

import org.helianto.core.security.PublicUserDetails;
import org.helianto.web.action.FormModel;
import org.springframework.webflow.core.collection.MutableAttributeMap;

/**
 * Model builder interface.
 * 
 * @author mauriciofernandesdecastro
 * 
 * @param <F>
 */
public interface ModelBuilder<F> {
	
	/**
	 * The model name.
	 */
	public String getModelName();
	
	/**
	 * Create a form model.
	 * 
	 * @param attributes
	 * @param userDetails
	 */
	public FormModel<? extends F> createFormModel(MutableAttributeMap attributes, PublicUserDetails userDetails);
	
	/**
	 * Get the form.
	 * 
	 * @param attributes
	 */
	public F getForm(MutableAttributeMap attributes);

}
