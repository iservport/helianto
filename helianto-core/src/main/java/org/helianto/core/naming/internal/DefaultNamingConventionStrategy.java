package org.helianto.core.naming.internal;

/**
 * Default naming convention strategy.
 * 
 * @author mauriciofernandesdecastro
 */
public class DefaultNamingConventionStrategy 
	extends AbstractNamingConventionStrategy {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor.
	 */
	public DefaultNamingConventionStrategy() {
		super();
	}

	@Override
	protected String getSuffix() {
		return null;
	}

	@Override
	protected boolean isValid(Class<?> clazz) {
		return true;
	}

}
