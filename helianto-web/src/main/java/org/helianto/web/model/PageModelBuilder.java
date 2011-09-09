package org.helianto.web.model;

import java.util.List;

import org.helianto.core.security.PublicUserDetails;
import org.helianto.web.action.PageModel;
import org.springframework.webflow.core.collection.MutableAttributeMap;

/**
 * Page model builder interface.
 * 
 * @author mauriciofernandesdecastro
 * 
 * @param <F>
 */
public interface PageModelBuilder<F> extends ModelBuilder<F> {
	
	/**
	 * Create a page form model.
	 * 
	 * @param attributes
	 * @param userDetails
	 */
	public PageModel<? extends F> createFormModel(MutableAttributeMap attributes, PublicUserDetails userDetails);
	
	/**
	 * Add a page.
	 * 
	 * @param attributes
	 * @param pageName
	 * @param list
	 */
	public void addPage(MutableAttributeMap attributes, String pageName, List<?> list);
	
}
