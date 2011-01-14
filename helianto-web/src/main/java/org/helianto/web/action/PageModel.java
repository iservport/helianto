package org.helianto.web.action;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.helianto.core.Entity;
import org.helianto.core.User;
import org.helianto.core.filter.Listable;
import org.helianto.core.filter.Page;

/**
 * Page model.
 * 
 * @author mauriciofernandesdecastro
 */
public class PageModel<F> implements Serializable {

	private static final long serialVersionUID = 1L;
	private User user;
	private F filter;
	private Map<String, Listable> pages;
	private String pageName;
	
	/**
	 * Construtor.
	 */
	public PageModel() {
		setPages(new HashMap<String, Listable>());
	}
	
	/**
	 * Construtor de usuário.
	 * 
	 * @param user
	 * @param pageNames
	 */
	public PageModel(User user, String... pageNames) {
		this();
		setUser(user);
		for (String p : pageNames) {
			getPages().put(p, new Page());
			setPageName(pageNames[0]);
		}
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
	 * Page names.
	 */
	public Set<String> getPageNames() {
		return getPages().keySet();
	}
	
	/**
	 * Page map.
	 */
	public Map<String, Listable> getPages() {
		return pages;
	}
	public void setPages(Map<String, Listable> pages) {
		this.pages = pages;
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
	
	/**
	 * Current page name.
	 */
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	
	/**
	 * Current page.
	 */
	public Listable getPage() {
		return getPages().get(getPageName());
	}
	public void setPage(Listable page) {
		getPages().put(getPageName(), page);
	}
	
}
