package org.helianto.core.naming;

public class DefaultNamingConventionStrategy 
	extends AbstractNamingConventionStrategy {

	private static final long serialVersionUID = 1L;

	@Override
	protected String getSuffix() {
		return null;
	}

	@Override
	protected boolean isValid(Class<?> clazz) {
		return true;
	}

}
