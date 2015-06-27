package org.helianto.inventory.repository;

import java.io.Serializable;

import org.helianto.core.domain.Entity;
import org.helianto.inventory.domain.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Cost centre repository interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface InventoryRepository 
	extends JpaRepository<Inventory, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param entity
	 * @param internalNumber
	 */
	Inventory findByEntityAndInternalNumber(Entity entity, long internalNumber);

}