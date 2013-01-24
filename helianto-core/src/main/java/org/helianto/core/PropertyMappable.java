package org.helianto.core;

import java.util.Map;

/**
 * Implemented by classes that translate a csv string into
 * a map of properties.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PropertyMappable {
	
	/**
	 * A csv list of properties in the format key=value.
	 */
	String getProperties();
	
	/**
	 * A map of properties.
	 */
	Map<String, Object> getPropertiesAsMap();

}
