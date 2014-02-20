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

package org.helianto.inventory.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonManagedReference;


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
@Table(name="inv_transaction")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="type",
    discriminatorType=DiscriminatorType.CHAR
)
@DiscriminatorValue("T")
public class InventoryTransaction implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @Version
    private Integer version;
    
	@JsonManagedReference("inventoryTransaction")
	@OneToMany(mappedBy="inventoryTransaction")
	private Set<Movement> movements = new HashSet<Movement>();
	
	/**
	 * Default constructor.
	 */
	public InventoryTransaction() {
		super();
	}
	
	/**
	 * Move constructor.
	 * 
	 * @param moveFrom
	 * @param moveTo
	 * @param quantity
	 */
	public InventoryTransaction(Inventory moveFrom, Inventory moveTo, BigDecimal quantity) {
		this();
		move(moveFrom, moveTo, quantity);
	}

    /**
     * Primary key.
     */
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Version.
     */
    public Integer getVersion() {
        return this.version;
    }
    public void setVersion(Integer version) {
        this.version = version;
    }
    

	/**
	 * Set of movements.
	 */
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

}
