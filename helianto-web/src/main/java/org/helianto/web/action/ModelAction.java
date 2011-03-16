package org.helianto.web.action;

import org.springframework.webflow.core.collection.MutableAttributeMap;

/**
 * Interface to business delegated beans (actions) exposing a form selection model.
 * 
 * @author mauriciofernandesdecastro
 */
public interface ModelAction {
	
	/**
	 * Get the form model.
	 * 
	 * @param attributes
	 */
	public FormModel<?> getModel(MutableAttributeMap attributes);

}
