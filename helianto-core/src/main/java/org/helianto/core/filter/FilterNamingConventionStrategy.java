package org.helianto.core.filter;

import org.helianto.core.naming.AbstractNamingConventionStrategy;

/**
 * Implementation to extract alias from the filter class name.
 * 
 * @author mauriciofernandesdecastro
 */
public class FilterNamingConventionStrategy extends AbstractNamingConventionStrategy {

	@Override
	protected String getSuffix() { return "Filter"; }

	@Override
	protected boolean isValid(Object object) {
		return object instanceof Filter;
	}

}
