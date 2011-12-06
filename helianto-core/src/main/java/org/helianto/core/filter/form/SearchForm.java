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
	 * Search string as R/W property.
	 * 
	 * @param searchString
	 */
	void setSearchString(String searchString);
	
	/**
	 * Search mode.
	 */
	char getSearchMode();

	/**
	 * Search mode as R/W property.
	 * 
	 * @param searchMode
	 */
	void setSearchMode(char searchMode);

	/**
	 * Search list.
	 */
	String getSearchList();

	/**
	 * Search list as R/W property.
	 * 
	 * @param searchList
	 */
	void setSearchList(String searchList);

}
