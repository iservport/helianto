package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.data.FilterRepository;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Operator;

/**
 * Entity repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface EntityRepository extends FilterRepository<Entity, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param operator
	 * @param alias
	 */
	Entity findByOperatorAndAlias(Operator operator, String alias);
	
}
