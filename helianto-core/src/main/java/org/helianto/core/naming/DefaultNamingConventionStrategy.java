package org.helianto.core.naming;

public class DefaultNamingConventionStrategy extends AbstractNamingConventionStrategy {

	@Override
	protected String getSuffix() {
		return null;
	}

	@Override
	protected boolean isValid(Class<?> clazz) {
		return true;
	}

}
