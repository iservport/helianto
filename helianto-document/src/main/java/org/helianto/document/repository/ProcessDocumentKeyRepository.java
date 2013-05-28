package org.helianto.document.repository;

import java.io.Serializable;

import org.helianto.core.data.FilterRepository;
import org.helianto.core.domain.KeyType;
import org.helianto.document.domain.ProcessDocument;
import org.helianto.document.domain.ProcessDocumentKey;

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
