package org.helianto.core.filter.classic;

import org.helianto.core.filter.Filter;


/**
 * Convenience to be used in tests.
 * 
 * @author mauriciofernandesdecastro
 */
public class TestingFilter implements Filter {

	public String createCriteriaAsString() {
		return "TESTFILTER";
	}

	public String getObjectAlias() {
		return "alias";
	}

	public void setObjectAlias(String objectAlias) {}
	
	public String createSelectAsString() { return null; }

}
