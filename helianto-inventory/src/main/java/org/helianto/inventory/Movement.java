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

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;


/**
 * Join transactions and inventory.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="inv_movement",
    uniqueConstraints = {@UniqueConstraint(columnNames={"inventoryTransactionId", "inventoryId"})}
)
@DiscriminatorColumn(
    name="type",
    discriminatorType=DiscriminatorType.CHAR
)
@DiscriminatorValue("M")
public class Movement implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int id;
	private int version;
	private InventoryTransaction inventoryTransaction;
	private Inventory inventory;
	private BigDecimal quantity = BigDecimal.ZERO;

	/**
	 * Default constructor.
	 */
	public Movement() {
	}

	/**
	 * Auto generated primary key.
	 */
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

    /**
     * Version.
     */
    @Version
    public int getVersion() {
        return this.version;
    }
    public void setVersion(int version) {
        this.version = version;
    }

	/**
	 * Inventory transaction.
	 */
    @ManyToOne
    @JoinColumn(name="inventoryTransactionId")
	public InventoryTransaction getInventoryTransaction() {
		return inventoryTransaction;
	}
	public void setInventoryTransaction(InventoryTransaction inventoryTransaction) {
		this.inventoryTransaction = inventoryTransaction;
	}
	
	/**
	 * Source or destination inventory.
	 */
    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="inventoryId")
	public Inventory getInventory() {
		return inventory;
	}
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
	/**
	 * Quantity moved.
	 */
	public BigDecimal getQuantity() {
		return quantity;
	}
	public Movement setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
		return this;
	}
	
	/**
	 * Movement direction.
	 */
	@Transient
	public MovementDirection getDirection() {
		return MovementDirection.fromValue(getQuantity());
	}
	
    /**
     * toString
     * @return String
     */
    @Override
    public String toString() {
    	StringBuffer buffer = new StringBuffer();
    	buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
    	buffer.append("inventoryTransaction").append("='").append(getInventoryTransaction()).append("' ");			
    	buffer.append("inventory").append("='").append(getInventory()).append("' ");			
    	buffer.append("quantity").append("='").append(getQuantity()).append("' ");			
    	buffer.append("]");
    	return buffer.toString();
     }

    @Override
    public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Movement) ) return false;
		 Movement castOther = ( Movement ) other; 
         
		 return ( (this.getInventoryTransaction()==castOther.getInventoryTransaction()) || ( this.getInventoryTransaction()!=null && castOther.getInventoryTransaction()!=null && this.getInventoryTransaction().equals(castOther.getInventoryTransaction()) ) )
             && ( (this.getInventory()==castOther.getInventory()) || ( this.getInventory()!=null && castOther.getInventory()!=null && this.getInventory().equals(castOther.getInventory()) ) );
    }
   
    @Override
    public int hashCode() {
         int result = 17;
         result = 37 * result + ( getInventoryTransaction() == null ? 0 : this.getInventoryTransaction().hashCode() );
         result = 37 * result + ( getInventory() == null ? 0 : this.getInventory().hashCode() );
         return result;
   }

}