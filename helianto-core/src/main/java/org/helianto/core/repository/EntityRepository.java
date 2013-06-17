package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.data.FilterRepository;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Operator;
import org.springframework.data.jpa.repository.Query;

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
	
	/**
	 * Find by Operator name and alias.
	 * 
	 * @param operator
	 * @param alias
	 */
	@Query("select entity from Entity entity where entity.operator.operatorName = ?1 and entity.alias = ?2 ")
	Entity findByContextNameAndAlias(String contextName, String alias);
	
}
