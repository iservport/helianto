package org.helianto.web.action;

import org.helianto.core.naming.AbstractNamingConventionStrategy;

/**
 * Implementation to extract target name from the action class name.
 * 
 * @author mauriciofernandesdecastro
 */
public class ActionNamingConventionStrategy extends AbstractNamingConventionStrategy {

	@Override
	protected String getSuffix() { return "ActionImpl"; }

	@Override
	protected boolean isValid(Class<?> clazz) {
		return AbstractAction.class.isAssignableFrom(clazz);
	}

}
