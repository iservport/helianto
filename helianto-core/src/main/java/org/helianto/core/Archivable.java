package org.helianto.core;

import java.util.Date;

/**
 * Archive interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface Archivable {
	
	/**
	 * Archive type.
	 */
	char getArchive();
	
	/**
	 * Archive date.
	 */
	Date getArchiveDate();

}
