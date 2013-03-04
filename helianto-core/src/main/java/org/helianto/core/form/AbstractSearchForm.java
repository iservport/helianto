package org.helianto.core.form;

import java.io.Serializable;

/**
 * Base class to search forms.
 * 
 * @author mauriciofernandesdecastro
 */
public abstract class AbstractSearchForm implements Serializable {

	private static final long serialVersionUID = 1L;
	private String searchString;
	private char searchMode;
	private String searchList;
	
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	
	public char getSearchMode() {
		return searchMode;
	}
	public void setSearchMode(char searchMode) {
		this.searchMode = searchMode;
	}
	
	public String getSearchList() {
		return searchList;
	}
	public void setSearchList(String searchList) {
		this.searchList = searchList;
	}

}
