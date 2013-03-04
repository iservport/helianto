package org.helianto.core;

import java.util.Map;

/**
 * Implemented by classes that translate a csv string into
 * a map of custom properties.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PropertyMappable {
	
	/**
	 * A csv list of properties in the format key=value.
	 */
	String getCustomProperties();
	
	/**
	 * A map of properties.
	 */
	Map<String, Object> getCustomPropertiesAsMap();

}
