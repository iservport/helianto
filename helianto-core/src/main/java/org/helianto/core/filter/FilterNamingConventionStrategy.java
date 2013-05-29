package org.helianto.core.filter;

import org.helianto.core.naming.internal.AbstractNamingConventionStrategy;

/**
 * Implementation to extract alias from the filter class name.
 * 
 * @author mauriciofernandesdecastro
 */
public class FilterNamingConventionStrategy extends AbstractNamingConventionStrategy {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor.
	 */
	public FilterNamingConventionStrategy() {
		super();
	}

	@Override
	protected String getSuffix() { return "Filter"; }

	@Override
	protected boolean isValid(Class<?> clazz) {
		return Filter.class.isAssignableFrom(clazz);
	}

}
