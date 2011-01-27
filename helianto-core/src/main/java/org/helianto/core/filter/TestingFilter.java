package org.helianto.core.filter;


/**
 * Convenience to be used in tests.
 * 
 * @author mauriciofernandesdecastro
 */
public class TestingFilter implements Filter {

	private static final long serialVersionUID = 1L;

	public String createCriteriaAsString() {
		return "TESTFILTER";
	}

	public String getObjectAlias() {
		return "alias";
	}

	public void setObjectAlias(String objectAlias) {}
	
	public String createSelectAsString() { return null; }

}
