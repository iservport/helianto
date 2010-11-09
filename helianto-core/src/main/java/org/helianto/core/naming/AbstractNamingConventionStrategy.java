package org.helianto.core.naming;


/**
 * Template base class to naming convention strategy implementations.
 * 
 * @author mauriciofernandesdecastro
 */
public abstract class AbstractNamingConventionStrategy implements NamingConventionStrategy {
	
	private boolean isConvertToLowerCase = false;
	
	/**
	 * True to force lower case conversion, otherwise it will be converted to camel case.
	 */
	public boolean isConvertToLowerCase() {
		return isConvertToLowerCase;
	}
	public void setConvertToLowerCase(boolean isConvertToLowerCase) {
		this.isConvertToLowerCase = isConvertToLowerCase;
	}
	
	/**
	 * Convenience to define a suffix based naming convention.
	 */
	protected abstract String getSuffix();
	
	/**
	 * Convenience method to validate the instance.
	 * 
	 * @param object
	 */
	protected abstract boolean isValid(Object object);
	
	/** 
	 * The class name
	 * 
	 * @param object
	 */
	protected String getClassName(Object object) {
		return object.getClass().getSimpleName();
	}
	
	public String getObjectName(Object object) {
		if (isValid(object)) {
			String objectName = getClassName(object).replace(getSuffix(), "");
			if (isConvertToLowerCase()) {
				return objectName.toLowerCase();
			}
			return new StringBuilder(objectName)
				.replace(0, 1, String.valueOf(objectName.charAt(0)).toLowerCase())
				.toString();
		}
		throw new IllegalArgumentException("Invalid object.");
	}

}
