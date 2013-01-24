package org.helianto.core.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Common string list operations.
 * 
 * @author mauriciofernandesdecastro
 */
public class StringListUtils {
	
	/**
	 * Convert array to string of comma separated values.
	 * 
	 * @param array
	 */
	public static String arrayToString(String[] array) {
		if (array!=null && array.length>0 ) {
			return Arrays.deepToString(array).replace(" ", "").replace("[", "").replace("]", "");
		}
		return "";
	}
	
	/**
	 * Convert string of comma separated values to array.
	 * 
	 * @param csv
	 */
	public static String[] stringToArray(String csv) {
		if (csv!=null && csv.trim().length()>0 ) {
			return csv.replace(" ", "").split(",");
		}
		return new String[0];
	}
	
	/**
	 * Convert string of comma separated properties to a map.
	 * 
	 * @param properties
	 */
	public static Map<String, Object> propertiesToMap(String properties) {
    	Map<String, Object> propertyMap = new HashMap<String, Object>();
		for (String property: StringListUtils.stringToArray(properties)) {
			String[] pair = property.split("=");
			if (pair.length==1) {
				propertyMap.put(pair[0], null);
			}
			if (pair.length==2) {
				propertyMap.put(pair[0], pair[1]);
			}
		}
		return propertyMap;
	}

	/**
	 * Convert string of comma separated properties to a map.
	 * 
	 * <p>
	 * Any value will be converted by default to string using {@link #toString()}.
	 * </p>
	 * 
	 * @param properties
	 */
	public static String mapToProperties(Map<String, Object> propertyMap) {
		StringBuilder propertyBuilder = new StringBuilder();
		boolean hasAtLeastOneKey = false;
		for (String key: propertyMap.keySet()) {
			if (hasAtLeastOneKey) {
				propertyBuilder.append(", ");
			}
			if (!key.isEmpty()) {
				hasAtLeastOneKey = true;
				propertyBuilder.append(key);
				if (propertyMap.get(key)!=null) {
					propertyBuilder.append("=").append(propertyMap.get(key).toString().trim());
				}
			}
		}
		return propertyBuilder.toString();
	}
	
}
