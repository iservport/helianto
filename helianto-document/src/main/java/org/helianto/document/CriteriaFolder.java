package org.helianto.document;

import org.helianto.core.domain.type.FolderEntity;



/**
 * Interface to domain classes that represent criteria folders.
 * 
 * <p>
 * Criteria folders may contain other objects not by direct association but
 * using criteria selections.
 * </p>
 * 
 * @author mauriciofernandesdecastro
 */
public interface CriteriaFolder extends FolderEntity {
	
	/**
	 * Criteria used to select contained objects.
	 */
	String getCriteria();
	
}
