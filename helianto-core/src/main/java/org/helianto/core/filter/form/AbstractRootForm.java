package org.helianto.core.filter.form;

import org.helianto.core.Operator;
import org.helianto.core.RootEntity;

/**
 * Base class to root (aggregated to Operator) forms.
 * 
 * @author mauriciofernandesdecastro
 */
public abstract class AbstractRootForm implements RootEntity {

	private static final long serialVersionUID = 1L;
	private Operator operator;
	private String searchString;
	private char searchMode;
	private String searchList;
	
	public Operator getOperator() {
		return operator;
	}
	public void setOperator(Operator operator) {
		this.operator = operator;
	}

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
