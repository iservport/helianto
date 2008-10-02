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


/**
 * <p>
 * Represents an <code>Operation</code>.  
 * </p>
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="proc_operation")
public class Operation extends Process implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private int operationType;
    private long operationTime;
    private List<Setup> setups = new ArrayList<Setup>(0);

    /** default constructor */
    public Operation() {
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
    @OneToMany(mappedBy="operation", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    public List<Setup> getSetups() {
        return this.setups;
    }
    public void setSetups(List<Setup> setups) {
        this.setups = setups;
    }

    /**
     * <code>Operation</code> query <code>StringBuilder</code>.
     */
    @Transient
    public static StringBuilder getOperationQueryStringBuilder() {
        return new StringBuilder("select operation from Operation operation ");
    }

    /**
     * <code>Operation</code> natural id query.
     */
    @Transient
    public static String getOperationNaturalIdQueryString() {
        return getOperationQueryStringBuilder().append("where process.entity = ? and operation.internalNumber = ? ").toString();
    }
    
    //1.1
    /**
     * <code>Operation</code> component factory.
     * 
     * @param componentCode
     * @param internalNumber
     * @param sequence
     */
    public DocumentAssociation operationRequirementFactory(String componentCode, long internalNumber, int sequence) {
    	DocumentAssociation documentAssociation = documentAssociationFactory(Part.class, componentCode, sequence);
        return documentAssociation;
    }

    //1.2
    /**
     * <code>Operation</code> subprocess factory.
     * 
     * @param processCode
     * @param internalNumber
     * @param sequence
     */
    public DocumentAssociation operationProcessFactory(String processCode, long internalNumber, int sequence) {
    	DocumentAssociation documentAssociation = documentAssociationFactory(Process.class, processCode, sequence);
        return documentAssociation;
    }

    //1.3
    /**
     * <code>Operation</code> characteristic factory.
     * 
     * @param characteristicCode
     * @param internalNumber
     * @param sequence
     */
    public DocumentAssociation operationCharacteristicFactory(String characteristicCode, long internalNumber, int sequence) {
    	DocumentAssociation documentAssociation = documentAssociationFactory(Characteristic.class, characteristicCode, sequence);
        return documentAssociation;
    }

    public boolean equals(Object other) {
		 if ( !(other instanceof Operation) ) return false;
		 return super.equals(other);
	}

}
