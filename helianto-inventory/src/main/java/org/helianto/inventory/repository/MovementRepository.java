package org.helianto.inventory.repository;

import java.io.Serializable;

import org.helianto.core.data.FilterRepository;
import org.helianto.inventory.domain.Inventory;
import org.helianto.inventory.domain.InventoryTransaction;
import org.helianto.inventory.domain.Movement;

/**
 * Cost centre repository interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface MovementRepository 
	extends FilterRepository<Movement, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param entity
	 * @param internalNumber
	 */
	Movement findByInventoryTransactionAndInventory(InventoryTransaction inventoryTransaction, Inventory inventory);

}