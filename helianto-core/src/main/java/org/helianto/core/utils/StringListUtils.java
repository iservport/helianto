package org.helianto.core.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	 * Convert string of comma separated properties or JSON to a map.
	 * 
	 * <p>
	 * if the first character in properties is "{", this method 
	 * attempts to map the remaining content as JSON.
	 * </p>
	 * 
	 * @param properties
	 */
	public static Map<String, Object> propertiesToMap(String properties) {
    	Map<String, Object> propertyMap = new HashMap<String, Object>();
    	if (properties!=null) {
    		if (properties.startsWith("{")) {
    			logger.debug("Mapping properties as JSON.");
    			ObjectMapper mapper = new ObjectMapper();
    			try {
    				propertyMap = mapper.readValue(properties, 
    						new TypeReference<Map<String, Object>>() {});
    			} catch (Exception e) {
    				logger.error("Unable to map properties, {}.", e.getMessage());
    				propertyMap.put("error", e.getMessage());
    			}
    		}
    		else {
    			logger.debug("Mapping properties as key/value pairs.");
    			for (String property: StringListUtils.stringToArray(properties)) {
    				String[] pair = property.split("=");
    				if (pair.length==1) {
    					propertyMap.put(pair[0], null);
    				}
    				if (pair.length==2) {
    					propertyMap.put(pair[0], pair[1]);
    				}
    			}
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
	
	private static final Logger logger = LoggerFactory.getLogger(StringListUtils.class);
	
}
