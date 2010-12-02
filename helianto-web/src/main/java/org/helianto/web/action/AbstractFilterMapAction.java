package org.helianto.web.action;

import java.util.HashMap;
import java.util.Map;

import org.helianto.core.filter.Listable;
import org.helianto.core.filter.Page;
import org.helianto.core.security.PublicUserDetails;
import org.springframework.webflow.core.collection.MutableAttributeMap;

/**
 * Subclass AbstractFilter to keep a map of lists as model.
 * 
 * @author mauriciofernandesdecastro
 *
 * @param <T>
 */
public abstract class AbstractFilterMapAction<T> extends org.helianto.web.action.AbstractFilterAction<T> {

	private static final long serialVersionUID = 1L;
	
	/**
	 * The model name.
	 */
	public String getModelName() {
		return "mapModel";
	}
	
	@Override
	public String filter(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		if(super.filter(attributes, userDetails).equals("success")) {
			getPage(attributes, getTargetName()).setList(getFilter(attributes).getList());
		}
		return "success";
	}

	/**
	 * Get a list, or create a new.
	 * 
	 * @param attributes
	 * @param listName
	 */
	public Listable getPage(MutableAttributeMap attributes, String listName) {
		Listable list = (Listable) attributes.get("listName");
		if (list==null) {
			list = new Page();
			getModelMap(attributes).put(listName, list);
		}
		return list;
	}
	
	/**
	 * Get the model, or create it.
	 * 
	 * @param attributes
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Listable> getModelMap(MutableAttributeMap attributes) {
		Map<String, Listable> model = (Map<String, Listable>) attributes.get(getModelName());
		if (model==null) {
			model = new HashMap<String, Listable>();
			attributes.put(getModelName(), model);
		}
		return model;
	}

}
