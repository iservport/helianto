package org.helianto.core.repository;

import java.io.Serializable;
import java.util.List;

import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Operator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Entity repository.
 * 
 * @author mauriciofernandesdecastro
 */
@Transactional
public interface EntityRepository extends JpaRepository<Entity, Serializable> {
	
	/**
	 * Find by alias and city.
	 * 
	 * @param alias
	 * @param cityId
	 */
	Entity findByAliasAndCityId(String alias, int cityId);
	
	/**
	 * Count by natural key.
	 * 
	 * @param contextName
	 * @param alias
	 */
	Long countByContextNameAndAliasIgnoreCase(String contextName, String alias);
	
	/**
	 * Find by Operator name and alias.
	 * 
	 * @param contextName
	 * @param alias
	 */
	@Query("select entity from Entity entity where entity.contextName = ?1 and entity.alias = ?2 ")
	Entity findByContextNameAndAlias(String contextName, String alias);
	
}
