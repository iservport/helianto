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
 * <p>
 * Represents a <code>Process</code>.  
 * </p>
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="proc_process")
public class Process extends DerivedProcessDocument implements Sequenceable {

    private static final long serialVersionUID = 1L;
    private long internalNumber;
    private Partner partner;

	/** default constructor */
    public Process() {
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
    public List<DocumentAssociation> getOperationAssociations() {
    	List<DocumentAssociation> childAssociationList = new ArrayList<DocumentAssociation>();
    	for (DocumentAssociation documentAssociation: this.getChildAssociationList()) {
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
    	List<DocumentAssociation> operationAssociations = getOperationAssociations();
    	List<Operation> operations = new ArrayList<Operation>();
    	for (DocumentAssociation documentAssociation: operationAssociations) {
    		operations.add((Operation) documentAssociation.getChild());
    	}
    	return operations;
    }
    
    //1.0
    /**
     * <code>Process</code> factory.
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

	//1.1
    /**
     * Return an association with a new <tt>Operation</tt>.
     */
	@Override
	public DocumentAssociation documentAssociationFactory(int sequence) {
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
    public DocumentAssociation processOperationFactory(String operationCode, long internalNumber, int sequence) {
    	DocumentAssociation documentAssociation = documentAssociationFactory(Operation.class, operationCode, sequence);
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
    public DocumentAssociation processCharacteristicFactory(String characteristicCode, long internalNumber, int sequence) {
    	DocumentAssociation documentAssociation = documentAssociationFactory(Characteristic.class, characteristicCode, sequence);
        return documentAssociation;
    }

    /**
     * <code>Process</code> query <code>StringBuilder</code>.
     */
    @Transient
    public static StringBuilder getProcessQueryStringBuilder() {
        return new StringBuilder("select process from Process process ");
    }

    /**
     * <code>Process</code> natural id query.
     */
    @Transient
    public static String getProcessNaturalIdQueryString() {
        return getProcessQueryStringBuilder().append("where process.entity = ? and process.internalNumber = ? ").toString();
    }
    
    public boolean equals(Object other) {
		 if ( !(other instanceof Process) ) return false;
		 return super.equals(other);
 }

}
