package org.helianto.inventory.repository;

import java.io.Serializable;

import org.helianto.inventory.domain.Inventory;
import org.helianto.inventory.domain.InventoryTransaction;
import org.helianto.inventory.domain.Movement;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Cost centre repository interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface MovementRepository 
	extends JpaRepository<Movement, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param entity
	 * @param internalNumber
	 */
	Movement findByInventoryTransactionAndInventory(InventoryTransaction inventoryTransaction, Inventory inventory);

}