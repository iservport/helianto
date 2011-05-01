package org.helianto.core.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.base.AbstractFilter;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class SerarchFilterTests {
	
	@SuppressWarnings("serial")
	@Test
	public void filter() {
		AbstractFilter sourceFilter = new AbstractFilter() {
			@Override public String createSelectAsString() { return "SELECT STRING"; }
			public void reset() { }
			@Override protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {}
			@Override public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) { }
			@Override public void preProcessFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
				mainCriteriaBuilder.appendSegment("uniqueField", "=").appendString("key");
			}
		};
		SearchFilter filter = new SearchFilter(sourceFilter) {
			public void reset() {}
			@Override public String getSearchFieldName() {
				return "searchField";
			}
		};
		filter.setSearchString("SEARCHSTRING");
		assertEquals("alias.uniqueField = 'key' AND lower(alias.searchField) like '%searchstring%' ", filter.createCriteriaAsString());
		assertEquals(sourceFilter.createSelectAsString(), filter.createSelectAsString());
	}
	
}
