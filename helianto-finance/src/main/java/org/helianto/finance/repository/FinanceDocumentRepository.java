package org.helianto.finance.repository;

import java.io.Serializable;

import org.helianto.core.data.FilterRepository;
import org.helianto.core.domain.Entity;
import org.helianto.finance.domain.FinanceDocument;

/**
 * Cash document repository interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface FinanceDocumentRepository extends FilterRepository<FinanceDocument, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param entity
	 * @param docCode
	 */
	FinanceDocument findByEntityAndInternalNumber(Entity entity, long internalNumber);
	
}
