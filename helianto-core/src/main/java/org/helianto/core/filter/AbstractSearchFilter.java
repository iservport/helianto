package org.helianto.core.filter;

import org.helianto.core.criteria.CriteriaBuilder;

/**
 * Extends <code>AbstractAliasFilter</code> to provide search string functionality.
 * 
 * @author mauriciofernandesdecastro
 */
public abstract class AbstractSearchFilter extends AbstractAliasFilter {
	
	private static final long serialVersionUID = 1L;
	private AbstractAliasFilter filter;
	private String searchString;
	
	/**
	 * Constructor.
	 */
	public AbstractSearchFilter(AbstractAliasFilter filter) {
		this.filter = filter;
		setObjectAlias(filter.getObjectAlias());
	}
	
	/**
	 * Hook to provide the search field name.
	 */
	public abstract String getSearchFieldName();
	
	/**
	 * Search string.
	 */
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	
	@Override
	public String createSelectAsString() {
		return filter.createSelectAsString();
	}

	@Override
	protected void doSelect(CriteriaBuilder mainCriteriaBuilder) {
		// not required by search filter
	}

	@Override
	public void doFilter(CriteriaBuilder mainCriteriaBuilder) {
		filter.preProcessFilter(mainCriteriaBuilder);
		appendLikeFilter(getSearchFieldName(), getSearchString(), mainCriteriaBuilder);
	}

	
}