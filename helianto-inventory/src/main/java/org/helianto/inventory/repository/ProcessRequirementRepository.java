package org.helianto.inventory.repository;

import java.io.Serializable;

import org.helianto.core.data.FilterRepository;
import org.helianto.core.domain.Entity;
import org.helianto.inventory.domain.ProcessRequirement;

/**
 * Process requirement repository interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface ProcessRequirementRepository 
	extends FilterRepository<ProcessRequirement, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param entity
	 * @param internalNumber
	 */
	ProcessRequirement findByEntityAndInternalNumber(Entity entity, long internalNumber);

}