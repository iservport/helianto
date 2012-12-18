package org.helianto.core.domain.type;


/**
 * Interface to domain classes that represent a folder.
 * 
 * <p>
 * Folders are meant to contain other objects.
 * </p>
 * 
 * @author mauriciofernandesdecastro
 */
public interface FolderEntity extends TrunkEntity {
	
	/**
	 * Folder code.
	 */
	String getFolderCode();
	
	/**
	 * Folder name.
	 */
	String getFolderName();
	
	/**
	 * Folder decoration url, likely pointing to an image.
	 */
	String getFolderDecorationUrl();
	
}
