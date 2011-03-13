package org.helianto.core.filter;

import org.helianto.core.criteria.CriteriaBuilder;

/**
 * Extends <code>AbstractAliasFilter</code> to provide search string functionality.
 * 
 * @author mauriciofernandesdecastro
 */
public class SearchFilter extends AbstractAliasFilter {
	
	private static final long serialVersionUID = 1L;
	private Filter filter;
	private String searchFieldName;
	private String searchString;
	
	/**
	 * Default constructor.
	 * 
	 * @param filter
	 */
	public SearchFilter(Filter filter) {
		this(filter, "");
	}
	
	/**
	 * Field name constructor.
	 * 
	 * @param filter
	 * @param searchFieldName
	 */
	public SearchFilter(Filter filter, String searchFieldName) {
		this(filter, searchFieldName, "");
	}
	
	/**
	 * Full constructor.
	 * 
	 * @param filter
	 * @param searchFieldName
	 * @param searchString
	 */
	public SearchFilter(Filter filter, String searchFieldName, String searchString) {
		this.filter = filter;
		setObjectAlias(filter.getObjectAlias());
		setSearchFieldName(searchFieldName);
		setSearchString(searchString);
	}
	
	/**
	 * The search field name.
	 */
	public String getSearchFieldName() {
		return this.searchFieldName;
	}
	public void setSearchFieldName(String searchFieldName) {
		this.searchFieldName = searchFieldName;
	}
	
	/**
	 * Search string.
	 */
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	
	public void reset() {
		setSearchString("");
	}

	@Override
	public String createSelectAsString() {
		return filter.createSelectAsString();
	}

	@Override
	protected void doSelect(CriteriaBuilder mainCriteriaBuilder) {
		throw new IllegalArgumentException("Not applicable to search filters!");
	}

	@Override
	public void doFilter(CriteriaBuilder mainCriteriaBuilder) {
		if (filter instanceof AbstractAliasFilter) {
			((AbstractAliasFilter) filter).preProcessFilter(mainCriteriaBuilder);
		}
		appendLikeFilter(getSearchFieldName(), getSearchString(), mainCriteriaBuilder);
	}

	
}
