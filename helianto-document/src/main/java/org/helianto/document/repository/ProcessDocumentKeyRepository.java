package org.helianto.document.repository;

import java.io.Serializable;

import org.helianto.core.domain.KeyType;
import org.helianto.document.domain.ProcessDocument;
import org.helianto.document.domain.ProcessDocumentKey;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Process document key repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface ProcessDocumentKeyRepository extends JpaRepository<ProcessDocumentKey, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param processDocument
	 * @param keyType
	 */
	ProcessDocumentKey findByProcessDocumentAndKeyType(ProcessDocument processDocument, KeyType keyType);

}
