package org.helianto.document.repository;

import java.io.Serializable;

import org.helianto.core.domain.Entity;
import org.helianto.document.domain.PrivateDocument;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Private document repository interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PrivateDocumentRepository extends JpaRepository<PrivateDocument, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param entity
	 * @param docCode
	 */
	PrivateDocument findByEntityAndDocCode(Entity entity, String docCode);
	
}
