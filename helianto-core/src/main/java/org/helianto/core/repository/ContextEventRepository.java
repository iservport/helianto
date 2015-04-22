package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.domain.ContextEvent;
import org.helianto.core.domain.Operator;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Context event repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface ContextEventRepository extends JpaRepository<ContextEvent, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param operator
	 * @param publicNumber
	 */
	ContextEvent findByOperatorAndPublicNumber(Operator operator, long publicNumber);
	

}
