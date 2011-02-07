package org.helianto.web.action;

import org.helianto.core.Entity;
import org.helianto.core.User;
import org.helianto.core.filter.Page;

/**
 * Simple model to aggregate a filter to a page. 
 * 
 * @author mauriciofernandesdecastro
 */
public class SimpleModel<F> extends Page {

	private static final long serialVersionUID = 1L;
	private User user;
	private F filter;
	
	/**
	 * Constructor.
	 * 
	 * @param filter
	 */
	public SimpleModel(F filter) {
		super();
		setFilter(filter);
	}
	
	/**
	 * User constructor.
	 * 
	 * @param filter
	 * @param user
	 */
	public SimpleModel(F filter, User user) {
		this(filter);
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
		
	/**
	 * Filter.
	 */
	public F getFilter() {
		return filter;
	}
	public void setFilter(F filter) {
		this.filter = filter;
	}
	
}
