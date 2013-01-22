package org.helianto.core.utils;

import java.util.Arrays;

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

}
