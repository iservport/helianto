package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.domain.Operator;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Operator repository.
 * 
 * @author mauriciofernandesdecastro
 * @deprecated
 */
public interface OperatorRepository extends JpaRepository<Operator, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param operatorName
	 */
	Operator findByOperatorName(String operatorName);
	
}
