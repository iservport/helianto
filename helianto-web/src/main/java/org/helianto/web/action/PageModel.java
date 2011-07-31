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
	private F form;
	private String searchString;
	private Map<String, Listable> pages;
	
	/**
	 * Default construtor.
	 */
	public PageModel() {
		setPages(new HashMap<String, Listable>());
	}
	
	/**
	 * Form construtor.
	 * 
	 * @param form
	 */
	public PageModel(F form) {
		this();
		setForm(form);
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
	
	public F getForm() {
		return form;
	}
	public void setForm(F form) {
		this.form = form;
	}
	
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	
	/**
	 * Read page.
	 * 
	 * @param pageName
	 */
	public Listable getPage(String pageName) {
		return getPages().get(pageName);
	}
	
	/**
	 * Write page.
	 * 
	 * @param pageName
	 * @param page
	 */
	public void addPage(String pageName, Listable page) {
		getPages().put(pageName, page);
	}

}
