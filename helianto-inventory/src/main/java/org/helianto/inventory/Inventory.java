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

import java.util.Date;
import java.util.Set;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.helianto.core.domain.Entity;
import org.helianto.process.ProcessDocument;


/**
 * A base class for "on-hand" quantities.
 * 
 * <p>
 * Inventory and InventoryTransaction capture the key abstraction to represent
 * material flow. A subclass of inventory, like agreement, holds the
 * input quantity to be conveyed into an order, then to manufacturing
 * operations and shipment. Fractions of this quantity may also be
 * forwarded to rework or scrap as well. The polymorphic nature of 
 * the two classes allow for many other combinations.
 * </p> 
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="inv_inventory",
    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "internalNumber"})}
)
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="type",
    discriminatorType=DiscriminatorType.CHAR
)
@DiscriminatorValue("I")
public class Inventory extends AbstractRequirement {
	
	private static final long serialVersionUID = 1L;
	private Set<Movement> movements;
	
	@Transient
	public String getInternalNumberKey() {
		return "INVENT";
	}

    @Transient
    public int getStartNumber() {
    	return 1;
    }

	/**
	 * Default constructor.
	 */
	public Inventory() {
		super();
		setRequirementSign(RequirementSign.INCREMENT);
		setRequirementDate(new Date());
		setResolution(RequirementState.FORECAST.getValue());
	}

	/**
	 * Key constructor.
	 * 
	 * @param entity
	 * @param internalNumber
	 */
	public Inventory(Entity entity, long internalNumber) {
		this();
		setEntity(entity);
		setInternalNumber(internalNumber);
	}

	/**
	 * Item constructor.
	 * 
	 * @param item
	 * @param internalNumber
	 */
	public Inventory(ProcessDocument item, long internalNumber) {
		this(item.getEntity(), internalNumber);
		setProcessDocument(item);
	}

	/**
	 * Set of movements.
	 */
	@OneToMany(mappedBy="inventory")
	public Set<Movement> getMovements() {
		return movements;
	}
	public void setMovements(Set<Movement> movements) {
		this.movements = movements;
	}

    /**
     * equals
     */
    @Override
    public boolean equals(Object other) {
          if ( !(other instanceof Inventory) ) return false;
          return super.equals(other);
    }

}
