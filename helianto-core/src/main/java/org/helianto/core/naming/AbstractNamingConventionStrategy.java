package org.helianto.core.naming;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Template base class to naming convention strategy implementations.
 * 
 * @author mauriciofernandesdecastro
 */
public abstract class AbstractNamingConventionStrategy 
	implements Serializable, NamingConventionStrategy {
	
	private static final long serialVersionUID = 1L;
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
	 * Convenience to define a suffix based naming convention, ignored if null.
	 */
	protected abstract String getSuffix();
	
	/**
	 * Convenience method to validate the class.
	 * 
	 * @param object
	 */
	protected abstract boolean isValid(Class<?> clazz);
	
	public String getConventionalName(Class<?> clazz) {
		String conventionalName = "";
		if (isValid(clazz)) {
			String objectName = clazz.getSimpleName();
			if (getSuffix()!=null) {
				objectName = clazz.getSimpleName().replace(getSuffix(), "");
			}
			if (isConvertToLowerCase()) {
				conventionalName = objectName.toLowerCase();
			}
			else {
				conventionalName = new StringBuilder(objectName)
				.replace(0, 1, String.valueOf(objectName.charAt(0)).toLowerCase())
				.toString();
			}
			logger.debug("Conventional name is {}.", conventionalName);
			return conventionalName;
		}
		throw new IllegalArgumentException("Unable to resolve conventional name from class name.");
	}
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractNamingConventionStrategy.class);

}
