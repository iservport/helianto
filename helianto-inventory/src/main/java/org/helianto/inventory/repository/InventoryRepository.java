package org.helianto.inventory.repository;

import java.io.Serializable;

import org.helianto.core.data.FilterRepository;
import org.helianto.core.domain.Entity;
import org.helianto.inventory.domain.Inventory;

/**
 * Cost centre repository interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface InventoryRepository 
	extends FilterRepository<Inventory, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param entity
	 * @param internalNumber
	 */
	Inventory findByEntityAndInternalNumber(Entity entity, long internalNumber);

}