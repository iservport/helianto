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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.helianto.core.Sequenceable;


/**
 * <p>
 * Represents an <code>Operation</code>.  
 * </p>
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="proc_operation")
public class Operation extends DerivedProcessDocument implements Sequenceable {

    private static final long serialVersionUID = 1L;
    protected long internalNumber;
    private int operationType;
    private long operationTime;
    private List<Setup> setups = new ArrayList<Setup>(0);

    /** default constructor */
    public Operation() {
    	super();
    	setOperationType(OperationType.OPERATION);
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
		return "OPER";
	}
 
    /**
     * Operation type.
     */
    public int getOperationType() {
        return this.operationType;
    }
    public void setOperationType(int operationType) {
        this.operationType = operationType;
    }
    public void setOperationType(OperationType operationType) {
        this.operationType = operationType.getValue();
    }

    /**
     * Operation time.
     */
    public long getOperationTime() {
        return this.operationTime;
    }
    public void setOperationTime(long operationTime) {
        this.operationTime = operationTime;
    }

    /**
     * Setups.
     */
    @OneToMany(mappedBy="operation", cascade = {CascadeType.ALL})
    public List<Setup> getSetups() {
        return this.setups;
    }
    public void setSetups(List<Setup> setups) {
        this.setups = setups;
    }

    //1.0
    /**
     * <code>Operation</code> subprocess factory.
     * 
     * @param processCode
     * @param internalNumber
     * @param sequence
     */
    public ProcessDocumentAssociation operationProcessFactory(String processCode, long internalNumber, int sequence) {
    	ProcessDocumentAssociation documentAssociation = documentAssociationFactory(Process.class, processCode, sequence);
        return documentAssociation;
    }

	//1.1
    /**
     * Return an association with a new <tt>Characteristic</tt>.
     */
	@Override
	public ProcessDocumentAssociation documentAssociationFactory(int sequence) {
		String characteristicCode = new StringBuilder("CH").append(sequence).toString();
		return operationCharacteristicFactory(characteristicCode, 0, sequence);
	}

    //1.2
    /**
     * <code>Operation</code> characteristic factory.
     * 
     * @param characteristicCode
     * @param internalNumber
     * @param sequence
     */
    public ProcessDocumentAssociation operationCharacteristicFactory(String characteristicCode, long internalNumber, int sequence) {
    	ProcessDocumentAssociation documentAssociation = documentAssociationFactory(Characteristic.class, characteristicCode, sequence);
        return documentAssociation;
    }

    /**
     * <code>Operation</code> setup factory.
     * 
     * @param resourceGroup
     */
    public Setup operationSetupFactory(ResourceGroup resourceGroup) {
    	Setup setup = Setup.setupFactory(this, resourceGroup);
        return setup;
    }

	public boolean equals(Object other) {
		 if ( !(other instanceof Operation) ) return false;
		 return super.equals(other);
	}

}