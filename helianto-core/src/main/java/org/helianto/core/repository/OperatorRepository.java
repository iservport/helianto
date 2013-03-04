package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.data.FilterRepository;
import org.helianto.core.domain.Operator;

/**
 * Operator repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface OperatorRepository extends FilterRepository<Operator, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param operatorName
	 */
	Operator findByOperatorName(String operatorName);
	
}
