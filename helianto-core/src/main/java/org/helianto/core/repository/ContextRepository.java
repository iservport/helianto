package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.data.FilterRepository;
import org.helianto.core.domain.Operator;

/**
 * Context repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface ContextRepository extends FilterRepository<Operator, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param operatorName
	 */
	Operator findByOperatorName(String operatorName);
	
}
