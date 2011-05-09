package org.helianto.web.action;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.helianto.core.filter.Listable;

/**
 * Page model.
 * 
 * @author mauriciofernandesdecastro
 */
public class PageModel<F> implements FormModel<F> {

	private static final long serialVersionUID = 1L;
//	private User user;
	private F form;
	private String searchString;
	private Map<String, Listable> pages;
	private String pageName;
	
	/**
	 * Construtor.
	 */
	public PageModel() {
		setPages(new HashMap<String, Listable>());
	}
	
//	/**
//	 * Construtor de usuário.
//	 * 
//	 * @param user
//	 * @param pageNames
//	 */
//	public PageModel(User user, String... pageNames) {
//		this();
//		setUser(user);
//		for (String p : pageNames) {
//			getPages().put(p, new Page());
//			setPageName(pageNames[0]);
//		}
//	}
	
//	/**
//	 * User.
//	 */
//	public User getUser() {
//		return user;
//	}
//	public void setUser(User user) {
//		this.user = user;
//	}
//	
//	/**
//	 * Convenience to retrieve entity.
//	 */
//	public Entity getEntity() {
//		if (getUser()!=null) {
//			return getUser().getEntity();
//		}
//		return null;
//	}
	
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
