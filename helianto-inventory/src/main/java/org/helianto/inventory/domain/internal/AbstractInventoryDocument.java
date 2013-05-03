/* Copyright 2005 I Serv Consultoria Empresarial Ltda.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.helianto.inventory.domain.internal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.helianto.document.base.AbstractNumberedDocument;
import org.helianto.inventory.BlockingState;
import org.helianto.inventory.domain.Inventory;
import org.helianto.inventory.domain.InventoryTransaction;
import org.helianto.inventory.domain.Movement;


/**
 * Extends the document hierarchy to wrap an InventoryTransaction.
 * 
 * @author Mauricio Fernandes de Castro
 */
@MappedSuperclass
public class AbstractInventoryDocument extends AbstractNumberedDocument {

    private static final long serialVersionUID = 1L;
    private InventoryTransaction inventoryTransaction;
 	private Date shipmentDate;
	private char blockingState;
	
	/**
	 * Default constructor.
	 */
	public AbstractInventoryDocument() {
		super();
		if (getInventoryTransaction()==null) {
			setInventoryTransaction(new InventoryTransaction());
		}
		setBlockingState(BlockingState.OPEN);
	}
    
    /**
	 * The wrapped inventory transaction
	 */
    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="inventoryTransactionId")
	public InventoryTransaction getInventoryTransaction() {
		return inventoryTransaction;
	}
	public void setInventoryTransaction(
			InventoryTransaction inventoryTransaction) {
		this.inventoryTransaction = inventoryTransaction;
	}
	
	/**
	 * List associated movements.
	 */
	@Transient
	public List<Movement> getMovementList() {
		List<Movement> movementList = new ArrayList<Movement>(getInventoryTransaction().getMovements());
		return movementList;
	}

	/**
	 * Add movement.
	 */
	@Transient
	public boolean addMovement(Movement movement) {
		movement.setInventoryTransaction(getInventoryTransaction());
		calculate();
		return getInventoryTransaction().getMovements().add(movement);
	}

	/**
	 * Create and add movement.
	 */
	@Transient
	protected <T extends Movement> T addMovement(Class<T> clazz, Inventory inventory, BigDecimal qty) {
		try {
			T movement = clazz.newInstance();
			movement.setInventory(inventory);
			movement.setQuantity(qty);
			if (addMovement(movement)) {
				return movement;
			}
		}
		catch (Exception e) {
			throw new IllegalArgumentException("Unable to create movement", e);
		}
		throw new IllegalArgumentException("Unable to add movement to transaction");
	}

	/**
	 * Shipment date.
	 */
	@Temporal(TemporalType.DATE)
	public Date getShipmentDate() {
		return shipmentDate;
	}
	public void setShipmentDate(Date shipmentDate) {
		this.shipmentDate = shipmentDate;
	}

	/**
	 * The blocking state.
	 * 
	 * <p>
	 * If the document is in calculate state, any change made
	 * to a calculation field should trigger the calculate() method.
	 * </p>
	 */
	public char getBlockingState() {
		return blockingState;
	}
	public void setBlockingState(char blockingState) {
		this.blockingState = blockingState;
	}
	public void setBlockingState(BlockingState blockingState) {
		this.blockingState = blockingState.getValue();
	}
	
	/**
	 * 
	 */
	protected void calculate() {
		
	}

}
