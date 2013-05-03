package org.helianto.core.filter;


/**
 * Convenience to be used in tests.
 * 
 * @author mauriciofernandesdecastro
 */
public class TestingFilter implements Filter {
	
	private String whereClause;
	
	/**
	 * Default constructor.
	 */
	public TestingFilter() {
		this("TESTFILTER");
	}

	/**
	 * Where clause constructor.
	 * 
	 * @param whereClause
	 */
	public TestingFilter(String whereClause) {
		this.whereClause = whereClause;
	}

	public String createCriteriaAsString() {
		return whereClause;
	}

	public String getObjectAlias() {
		return "alias";
	}

	public void setObjectAlias(String objectAlias) {}
	
	public String createSelectAsString() { return null; }

}
