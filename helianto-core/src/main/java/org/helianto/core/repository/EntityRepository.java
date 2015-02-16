package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.data.FilterRepository;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Operator;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Entity repository.
 * 
 * @author mauriciofernandesdecastro
 */
@Transactional
public interface EntityRepository extends FilterRepository<Entity, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param operator
	 * @param alias
	 */
	Entity findByOperatorAndAlias(Operator operator, String alias);
	
	/**
	 * Find adapter.
	 * 
	 * @param entityId
	 */
	@Query("select new "
			+ "org.helianto.core.repository.EntityReadAdapter"
			+ "(entity_.id"
			+ ", entity_.operator.id"
			+ ", entity_.alias"
			+ ", entity_.installDate"
			+ ", entity_.summary"
			+ ", entity_.entityDomain"
			+ ", entity_.externalLogoUrl"
			+ ", entity_.customProperties"
			+ ", entity_.activityState"
			+ ", entity_.entityType"
			+ ") "
			+ "from Entity entity_ "
			+ "where entity_.id = ?1 ")
	EntityReadAdapter findAdapter(int entityId);
	
	/**
	 * Find by Operator name and alias.
	 * 
	 * @param operator
	 * @param alias
	 */
	@Query("select entity from Entity entity where entity.operator.operatorName = ?1 and entity.alias = ?2 ")
	Entity findByContextNameAndAlias(String contextName, String alias);
	
}
