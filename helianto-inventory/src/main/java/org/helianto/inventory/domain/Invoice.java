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

import java.util.HashSet;
import java.util.Set;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.helianto.core.domain.Entity;
import org.helianto.inventory.InvoiceType;
import org.helianto.inventory.internal.AbstractInventoryDocument;
import org.helianto.partner.domain.Partner;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * Represents invoices.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="inv_invoice",
    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "docCode"})}
)
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="type",
    discriminatorType=DiscriminatorType.CHAR
)
@DiscriminatorValue("I")
public class Invoice 
	extends AbstractInventoryDocument 
{

    private static final long serialVersionUID = 1L;
    
    private char invoiceType = InvoiceType.OUTPUT.getValue();
    
    @ManyToOne
    @JoinColumn(name="partnerId")
    private Partner partner;
    
    @JsonManagedReference 
    @OneToMany(mappedBy="invoice")
    private Set<Picking> pickingSet = new HashSet<Picking>();
    
	/**
     * Default constructor.
     */
    public Invoice() {
    	super();
    }
    
	/**
     * Key constructor.
     * 
     * @param entity
     * @param docCode
     */
    public Invoice(Entity entity, String docCode) {
    	this();
    	setEntity(entity);
    	setDocCode(docCode);
    }
    
	/**
	 * Add the prefix "INV" to differentiate invoice keys.
	 */
//	@Override
//	@Transient
	public String getInternalDocCodeKey() {
		return "INV";
	}

    /**
	 * The invoice type.
	 */
	public char getInvoiceType() {
		return invoiceType;
	}
	public void setInvoiceType(char invoiceType) {
		this.invoiceType = invoiceType;
	}
	public void setInvoiceTypeAsEnum(InvoiceType invoiceType) {
		this.invoiceType = invoiceType.getValue();
	}

    /**
	 * Partner sending or receiving the invoice.
	 */
    public Partner getPartner() {
		return partner;
	}
	public void setPartner(Partner partner) {
		this.partner = partner;
	}

    /**
	 * Picking set.
	 */
	public Set<Picking> getPickingSet() {
		return pickingSet;
	}
	public void setPickingSet(Set<Picking> pickingSet) {
		this.pickingSet = pickingSet;
	}

    @Override
    public boolean equals(Object other) {
		 if (other instanceof Invoice) {
			 return super.equals(other);
		 }
		 return false;
    }
   
}
