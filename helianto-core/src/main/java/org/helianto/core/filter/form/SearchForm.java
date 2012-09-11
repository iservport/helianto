package org.helianto.core.filter.form;

/**
 * Search form interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface SearchForm {
	
	/**
	 * Search string.
	 */
	String getSearchString();
	
	/**
	 * Search mode.
	 */
	char getSearchMode();

}
