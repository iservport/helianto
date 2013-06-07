package org.helianto.document.repository;

import java.io.Serializable;

import org.helianto.core.data.FilterRepository;
import org.helianto.core.domain.Entity;
import org.helianto.document.domain.Document;

/**
 * Document repository interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface DocumentRepository extends FilterRepository<Document, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param entity
	 * @param docCode
	 */
	Document findByEntityAndDocCode(Entity entity, String docCode);
	
}