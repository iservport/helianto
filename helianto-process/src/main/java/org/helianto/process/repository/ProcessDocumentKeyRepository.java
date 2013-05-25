package org.helianto.process.repository;

import java.io.Serializable;

import org.helianto.core.data.FilterRepository;
import org.helianto.core.domain.KeyType;
import org.helianto.process.domain.ProcessDocument;
import org.helianto.process.domain.ProcessDocumentKey;

/**
 * Process document key repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface ProcessDocumentKeyRepository extends
		FilterRepository<ProcessDocumentKey, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param processDocument
	 * @param keyType
	 */
	ProcessDocumentKey findByProcessDocumentAndKeyType(ProcessDocument processDocument, KeyType keyType);

}
