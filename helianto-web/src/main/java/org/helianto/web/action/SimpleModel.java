package org.helianto.web.action;

import org.helianto.core.Entity;
import org.helianto.core.User;
import org.helianto.core.filter.Page;

/**
 * Simple model to aggregate a filter to a page. 
 * 
 * @author mauriciofernandesdecastro
 */
public class SimpleModel<F> extends Page implements FormModel<F> {

	private static final long serialVersionUID = 1L;
	private User user;
	private F form;
	private String searchString;
	
	/**
	 * Constructor.
	 * 
	 * @param form
	 */
	public SimpleModel(F form) {
		super();
		setForm(form);
	}
	
	/**
	 * User constructor.
	 * 
	 * @param form
	 * @param user
	 */
	public SimpleModel(F form, User user) {
		this(form);
		setUser(user);
	}
	
	/**
	 * User.
	 */
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * Convenience to retrieve entity.
	 */
	public Entity getEntity() {
		if (getUser()!=null) {
			return getUser().getEntity();
		}
		return null;
	}
		
	public F getForm() {
		return form;
	}
	public void setForm(F filter) {
		this.form = filter;
	}
	
	/**
	 * @deprecated used getForm().
	 */
	public F getFilter() {
		return form;
	}
	
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	
}
