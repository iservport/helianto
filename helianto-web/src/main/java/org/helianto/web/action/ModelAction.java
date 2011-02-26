package org.helianto.web.action;

import org.springframework.webflow.core.collection.MutableAttributeMap;

/**
 * Interface to business delegated beans (actions) exposing a page selection model.
 * 
 * @author mauriciofernandesdecastro
 */
public interface ModelAction {
	
	/**
	 * Get the page model.
	 * 
	 * @param attributes
	 */
	public PageModel<?> getModel(MutableAttributeMap attributes);

}
