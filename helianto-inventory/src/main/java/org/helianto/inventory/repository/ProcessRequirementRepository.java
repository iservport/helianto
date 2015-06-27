package org.helianto.inventory.repository;

import java.io.Serializable;

import org.helianto.core.domain.Entity;
import org.helianto.inventory.domain.ProcessRequirement;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Process requirement repository interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface ProcessRequirementRepository 
	extends JpaRepository<ProcessRequirement, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param entity
	 * @param internalNumber
	 */
	ProcessRequirement findByEntityAndInternalNumber(Entity entity, long internalNumber);

}