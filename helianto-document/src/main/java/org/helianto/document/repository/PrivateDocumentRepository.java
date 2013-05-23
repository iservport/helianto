package org.helianto.document.repository;

import java.io.Serializable;

import org.helianto.core.data.FilterRepository;
import org.helianto.core.domain.Entity;
import org.helianto.document.domain.PrivateDocument;

/**
 * Private document repository interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PrivateDocumentRepository extends FilterRepository<PrivateDocument, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param entity
	 * @param docCode
	 */
	PrivateDocument findByEntityAndDocCode(Entity entity, String docCode);
	
}
