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
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.helianto.core.Entity;
import org.helianto.core.Sequenceable;
import org.helianto.core.TopLevelNumberedEntity;

/**
 * A simple picking report.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="inv_picking",
    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "internalNumber"})}
)
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="type",
    discriminatorType=DiscriminatorType.CHAR
)
@DiscriminatorValue("P")
public class Picking implements Serializable, Sequenceable, TopLevelNumberedEntity {

	private static final long serialVersionUID = 1L;
	private int id;
    private Entity entity;
    private long internalNumber;
	private Invoice invoice;
    private BigDecimal quantity;
    private String packaging;
    private BigDecimal netWeight;
    private BigDecimal grossWeight;
    
    /**
     * Constructor.
     */
    public Picking() {
    	setPackaging("");
    }

    /**
     * Primary key
     */
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
    /**
     * <<NaturalKey>> Owning entity.
     * @see {@link Entity}
     */
    @ManyToOne
    @JoinColumn(name="entityId", nullable=true)
    public Entity getEntity() {
        return this.entity;
    }
    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    /**
     * <<NaturalKey>> Picking number.
     */
    public long getInternalNumber() {
        return this.internalNumber;
    }
    public void setInternalNumber(long internalNumber) {
        this.internalNumber = internalNumber;
    }
    
    @Transient
	public String getInternalNumberKey() {
		return "PICKING";
	}
    
	/**
	 * Invoice.
	 */
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="invoiceId")
	public Invoice getInvoice() {
		return this.invoice;
	}
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	
    /**
     * Quantity of shipment.
     */
    public BigDecimal getQuantity() {
        return quantity;
    }
    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    /**
     * Packaging.
     */
    @Column(length=60)
    public String getPackaging() {
        return this.packaging;
    }
    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

    /**
     * Net weight (kg).
     */
    public BigDecimal getNetWeight() {
        return netWeight;
    }
    public void setNetWeight(BigDecimal netWeight) {
        this.netWeight = netWeight;
    }

    /**
     * Gross weight (kg).
     */
    public BigDecimal getGrossWeight() {
        return grossWeight;
    }
    public void setGrossWeight(BigDecimal grossWeight) {
        this.grossWeight = grossWeight;
    }

    /**
     * Required by <code>TopLevelNumberedEntity</code> interface.
     */
	public TopLevelNumberedEntity setKey(Entity entity, long internalNumber) {
		setEntity(entity);
		setInternalNumber(internalNumber);
		return this;
	}

    /**
     * toString
     * @return String
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();

    	buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
    	buffer.append("internalNumber").append("='").append(getInternalNumber()).append("' ");			
        buffer.append("]");
      
        return buffer.toString();
    }

   /**
	 * equals
	 */
	public boolean equals(Object other) {
         if ( (this == other ) ) return true;
         if ( (other == null ) ) return false;
         if ( !(other instanceof Picking) ) return false;
         Picking castOther = (Picking) other; 
         
         return ( (this.getEntity()==castOther.getEntity()) || ( this.getEntity()!=null && castOther.getEntity()!=null && this.getEntity().equals(castOther.getEntity()) ) )
                 && (this.getInternalNumber()==castOther.getInternalNumber());
        }

	@Override
	public int hashCode() {
		int result = 17;
		result = 37 * result + (int) this.getInternalNumber();
		return result;
	}

}
