package org.helianto.document.repository;

import java.io.Serializable;

import org.helianto.core.data.FilterRepository;
import org.helianto.core.domain.Entity;
import org.helianto.document.domain.Document;
import org.springframework.data.jpa.repository.Query;

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
	
	/**
	 * Find by number key.
	 * 
	 * @param entity
	 * @param internalNumberKey
	 */
	@Query("select max(document.internalNumber) from Document document " +
			"where document.entity = ?1 " +
			"and document.internalNumberKey = ?2 ")
	Long findLastNumber(Entity entity, String internalNumberKey);
	
}
