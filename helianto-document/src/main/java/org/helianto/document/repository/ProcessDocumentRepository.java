package org.helianto.document.repository;

import java.io.Serializable;

import org.helianto.core.domain.Entity;
import org.helianto.document.domain.ProcessDocument;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Process document repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface ProcessDocumentRepository 
	extends JpaRepository<ProcessDocument, Serializable> {

	/**
	 * Find by natural key.
	 * 
	 * @param parent
	 * @param child
	 */
	ProcessDocument findByEntityAndDocCode(Entity entity, String docCode);

}
