package org.helianto.document.repository;

import java.io.Serializable;

import org.helianto.core.data.FilterRepository;
import org.helianto.core.domain.Entity;
import org.helianto.document.domain.DocumentFolder;

/**
 * Document folder repository interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface DocumentFolderRepository extends FilterRepository<DocumentFolder, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param entity
	 * @param folderCode
	 */
	DocumentFolder findByEntityAndFolderCode(Entity entity, String folderCode);
	
	/**
	 * Find by entity alias and folder code.
	 * 
	 * @param entityAlias
	 * @param folderCode
	 */
	DocumentFolder findByEntityAliasAndFolderCode(String entityAlias, String folderCode);

}
