package org.helianto.resource.repository;

import java.io.Serializable;

import org.helianto.core.domain.Entity;
import org.helianto.resource.domain.ResourceGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Resource group repository interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface ResourceGroupRepository 
	extends JpaRepository<ResourceGroup, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param entity
	 * @param resourceCode
	 */
	ResourceGroup findByEntityAndResourceCode(Entity entity, String resourceCode);
	
	/**
	 * Count by natural key.
	 * 
	 * @param entity
	 * @param resourceCode
	 */
	@Query("select count(resource.id) from ResourceGroup resource "
			+ "where resource.entity.id = ?1 and resource.resourceCode = ?2 ")
	Long countByEntityIdAndResourceCode(int entityId, String resourceCode);
	
}
