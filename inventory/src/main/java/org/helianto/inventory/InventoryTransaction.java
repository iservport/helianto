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

package org.helianto.inventory;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.helianto.core.Entity;
import org.helianto.document.AbstractEvent;

/**
 * Represents inventory movement.
 * 
 * <p>
 * Collaborate in use cases like:</p>
 * <ol>
 * <li>receipt, parts move from supplier to warehouse,</li>
 * <li>inspection, parts move from order (wip) to finished or rework,</li>
 * <li>invoicing, from finished to customer.
 * </ol>
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="inv_transaction",
    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "internalNumber"})}
)
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="type",
    discriminatorType=DiscriminatorType.CHAR
)
@DiscriminatorValue("T")
public class InventoryTransaction extends AbstractEvent {
	
	public static <T extends InventoryTransaction> T inventoryTransactionFactory(Class<T> clazz, Inventory moveFrom, Inventory moveTo, BigDecimal quantity) {
		T inventoryTransaction = null;
		try {
			inventoryTransaction = clazz.newInstance();
			inventoryTransaction.move(moveFrom, moveTo, quantity);
			return inventoryTransaction;
		} catch (Exception e) {
			throw new IllegalArgumentException("Unable to intantiate class"+clazz, e);
		}
	}

	private static final long serialVersionUID = 1L;
	private Entity entity;
	private BigDecimal movementQty;
	private Set<Movement> movements = new HashSet<Movement>();
	
	@Transient
	@Override
	public String getInternalNumberKey() {
		return "INVTX";
	}
	
	/**
	 * Entity.
	 */
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="entityId")
	public Entity getEntity() {
		return entity;
	}
	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	/**
	 * Default constructor.
	 */
	public InventoryTransaction() {
		super();
	}

	/**
	 * Quantity moved.
	 */
	public BigDecimal getMovementQty() {
		return movementQty;
	}
	public InventoryTransaction setMovementQty(BigDecimal movementQty) {
		this.movementQty = movementQty;
		return this;
	}
	
	/**
	 * Set of movements.
	 */
	@OneToMany(mappedBy="inventoryTransaction", cascade={CascadeType.ALL})
	public Set<Movement> getMovements() {
		return movements;
	}
	public void setMovements(Set<Movement> movements) {
		this.movements = movements;
	}
	
	/**
	 * Move a quantity into an inventory.
	 * 
	 * @param inventory
	 * @param quantity
	 */
	public InventoryTransaction move(Inventory inventory, BigDecimal quantity) {
		Movement movement = new Movement();
		movement.setInventory(inventory);
		movement.setInventoryTransaction(this);
		if (getMovements().add(movement)) {
			movement.setQuantity(quantity);
			return this;
		}
		throw new IllegalArgumentException("Duplicate movement in transaction "+this+" affecting "+inventory);
	}

	/**
	 * Move a quantity from one inventory to other.
	 * 
	 * @param from
	 * @param to
	 * @param quantity
	 */
	public InventoryTransaction move(Inventory from, Inventory to, BigDecimal quantity) {
		return move(from, quantity).move(to, quantity.negate());
	}

	/**
	 * equals
	 */
	public boolean equals(Object other) {
		if (other instanceof InventoryTransaction) {
			return super.equals(other);
		}
		return false;
	}
	
}
