package org.helianto.resource.repository;

import java.io.Serializable;

import org.helianto.core.domain.Entity;
import org.helianto.resource.domain.ResourceGroup;
import org.springframework.data.jpa.repository.JpaRepository;

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
	
}
