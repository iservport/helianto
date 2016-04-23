package org.helianto.security.repository;

import java.util.List;

import org.helianto.core.domain.Entity;
import org.helianto.core.internal.SimpleCounter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


/**
 * Entity statistics.
 * 
 * @author mauriciofernandesdecastro
 */
public interface EntityStatsRepository
	extends JpaRepository<Entity, Integer>
{
	
	/**
	 * Lista contagem de entidades ativas  por tipo.
	 * 
	 * @param contextId
	 */
	@Query("select new " +
			"org.helianto.core.internal.SimpleCounter"
			+ "(entity.entityType, count(entity)) "
			+ "from Entity entity "
			+ "where entity.operator.id = ?1 "
			+ "and entity.activityState = 'A' "
			+ "group by entity.entityType ")
	List<SimpleCounter> countActiveEntitiesGroupByType(int contextId);

}
