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
	public String getSearchString();
	
	/**
	 * Search string as R/W property.
	 * 
	 * @param searchString
	 */
	public void setSearchString(String searchString);

	/**
	 * Search list.
	 */
	public String getSearchList();

	/**
	 * Search list as R/W property.
	 * 
	 * @param searchList
	 */
	public void setSearchList(String searchList);

}
