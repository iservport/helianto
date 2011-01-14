package org.helianto.web.action;

import org.helianto.core.naming.AbstractNamingConventionStrategy;

/**
 * Implementation to extract target name from the model action class name.
 * 
 * @author mauriciofernandesdecastro
 */
public class ModelActionNamingConventionStrategy extends AbstractNamingConventionStrategy {

	@Override
	protected String getSuffix() { return "ActionImpl"; }

	@Override
	protected boolean isValid(Class<?> clazz) {
		return AbstractModelAction.class.isAssignableFrom(clazz);
	}

}
