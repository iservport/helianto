package org.helianto.web.test;

import org.helianto.core.naming.AbstractNamingConventionStrategy;

/**
 * Implementation to extract alias from the flow test class name.
 * 
 * @author mauriciofernandesdecastro
 */
public class FlowTestNamingConventionStrategy extends AbstractNamingConventionStrategy {

	@Override
	protected String getSuffix() { return "FlowTests"; }

	@Override
	protected boolean isValid(Class<?> clazz) {
		return true;
	}

}
