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

package org.helianto.process;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.helianto.core.Entity;
import org.helianto.core.Sequenceable;
import org.helianto.partner.Partner;

/**
 * Represents a <code>Process</code>.
 * 
 * <p>
 * A process is an ordered list of operations.
 * </p>
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="proc_process")
public class Process extends DerivedProcessDocument implements Sequenceable {

    /**
     * Factory method.
     * 
     * @param entity
     * @param processCode
     * @param internalNumber
     */
    public static Process processFactory(Entity entity, String processCode, long internalNumber) {
    	Process process = documentFactory(Process.class, entity, processCode);
    	process.setInternalNumber(internalNumber);
    	return process;
    }

    private static final long serialVersionUID = 1L;
    private long internalNumber;
    private Partner partner;

	/** default constructor */
    public Process() {
    	super();
    }

    /**
     * Internal number.
     */
    public long getInternalNumber() {
        return this.internalNumber;
    }
    public void setInternalNumber(long internalNumber) {
        this.internalNumber = internalNumber;
    }
    @Transient
	public String getInternalNumberKey() {
		return "PROC";
	}
 
    /**
     * Partner, if exists.
     */
    @ManyToOne(cascade={CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name="partnerId", nullable=true)
    public Partner getPartner() {
		return partner;
	}
	public void setPartner(Partner partner) {
		this.partner = partner;
	}

    @Transient
    public List<ProcessDocumentAssociation> getOperationAssociations() {
    	List<ProcessDocumentAssociation> childAssociationList = new ArrayList<ProcessDocumentAssociation>();
    	for (ProcessDocumentAssociation documentAssociation: this.getChildAssociationList()) {
    		if (documentAssociation.getChild() instanceof Operation) {
    			childAssociationList.add(documentAssociation);
    		}
    	}
    	return childAssociationList;
    }
    
	/**
     * List of child <code>Operation</code>.
     */
    @Transient
    public List<Operation> getOperations() {
    	List<ProcessDocumentAssociation> operationAssociations = getOperationAssociations();
    	List<Operation> operations = new ArrayList<Operation>();
    	for (ProcessDocumentAssociation documentAssociation: operationAssociations) {
    		operations.add((Operation) documentAssociation.getChild());
    	}
    	return operations;
    }
    
	//1.1
    /**
     * Return an association with a new <tt>Operation</tt>.
     */
	public ProcessDocumentAssociation documentAssociationFactory(int sequence) {
		String operationCode = new StringBuilder("OP").append(sequence).toString();
		return processOperationFactory(operationCode, 0, sequence);
	}

    //1.2
    /**
     * <code>Process</code> operation factory.
     * 
     * @param operationCode
     * @param internalNumber
     * @param sequence
     */
    public ProcessDocumentAssociation processOperationFactory(String operationCode, long internalNumber, int sequence) {
    	ProcessDocumentAssociation documentAssociation = documentAssociationFactory(Operation.class, operationCode, sequence);
        return documentAssociation;
    }

    //1.3
    /**
     * <code>Process</code> characteristic factory.
     * 
     * @param characteristicCode
     * @param internalNumber
     * @param sequence
     */
    public ProcessDocumentAssociation processCharacteristicFactory(String characteristicCode, long internalNumber, int sequence) {
    	ProcessDocumentAssociation documentAssociation = documentAssociationFactory(Characteristic.class, characteristicCode, sequence);
        return documentAssociation;
    }

    public boolean equals(Object other) {
		 if ( !(other instanceof Process) ) return false;
		 return super.equals(other);
 }

}
