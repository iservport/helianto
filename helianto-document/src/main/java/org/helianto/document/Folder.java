package org.helianto.document;

import org.helianto.core.TrunkEntity;

/**
 * Interface to domain classes that represent a folder.
 * 
 * @author mauriciofernandesdecastro
 */
public interface Folder extends TrunkEntity {
	
	/**
	 * Folder code.
	 */
	String getFolderCode();
	
	/**
	 * Folder name.
	 */
	String getFolderName();

}
