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

import org.helianto.core.Entity;
import org.helianto.document.AbstractDocument;
import org.helianto.document.AbstractNumberedDocument;
import org.helianto.document.Document;


/**
 * Extends the document hierarchy to wrap an InventoryTransaction.
 * 
 * @author Mauricio Fernandes de Castro
 */
@MappedSuperclass
public class AbstractInventoryDocument extends AbstractNumberedDocument {

    /**
     * Factory method.
     * 
     * @param entity
     * @param docCode
     */
    public static Document documentFactory(Entity entity, String docCode) {
        Document document = AbstractDocument.documentFactory(Document.class, entity, docCode);
        return document;
    }
    
    private static final long serialVersionUID = 1L;
    private InventoryTransaction inventoryTransaction;
    private Date issueDate;
	private Date shipmentDate;
	
	/**
	 * Default constructor.
	 */
	public AbstractInventoryDocument() {
		super();
		if (getInventoryTransaction()==null) {
			setInventoryTransaction(new InventoryTransaction());
		}
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
	public List<Movement> movementList() {
		List<Movement> movementList = new ArrayList<Movement>(inventoryTransaction.getMovements());
		return movementList;
	}

	/**
	 * Issue date.
	 */
	@Temporal(TemporalType.DATE)
    public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
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

}
