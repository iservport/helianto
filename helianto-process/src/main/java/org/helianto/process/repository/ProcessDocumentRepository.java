package org.helianto.process.repository;

import java.io.Serializable;

import org.helianto.core.data.FilterRepository;
import org.helianto.core.domain.Entity;
import org.helianto.process.domain.ProcessDocument;

/**
 * Process document repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface ProcessDocumentRepository 
	extends FilterRepository<ProcessDocument, Serializable> {

	/**
	 * Find by natural key.
	 * 
	 * @param parent
	 * @param child
	 */
	ProcessDocument findByEntityAndDocCode(Entity entity, String docCode);

}
