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

import javax.persistence.Table;
import javax.persistence.Transient;

import org.helianto.core.Entity;

/**
 * <p>
 * Represents a <code>Process</code>.  
 * </p>
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="proc_process")
public class Process extends Document implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    protected long internalNumber;

    /** default constructor */
    public Process() {
    }

    /**
     * Internal number.
     * <p>Process <code>DocCode</code> is immediately re-generated as
     * "PRC#" followed by the internal number.
     * </p>
     */
    public long getInternalNumber() {
        return this.internalNumber;
    }
    public void setInternalNumber(long internalNumber) {
        this.internalNumber = internalNumber;
        StringBuilder prefix = new StringBuilder("PRC#");
        setDocCode(prefix.append(internalNumber).toString());
    }
    
    /**
     * List of child <code>Operation</code>.
     */
    @Transient
    public List<Operation> getOperations() {
    	List<Operation> operations = new ArrayList<Operation>();
    	for (DocumentAssociation documentAssociation: this.getChildAssociations()) {
    		if (documentAssociation.getAssociationType()==AssociationType.PROCESS_OPERATION.getValue()) {
    			operations.add((Operation) documentAssociation.getChild());
    		}
    	}
    	return operations;
    }
    
    /**
     * <code>Process</code> factory.
     * 
     * @param entity
     * @param internalNumber
     */
    public static Process processFactory(Entity entity, long internalNumber) {
    	Process process = documentFactory(Process.class, entity, "");
    	process.setInternalNumber(internalNumber);
    	return process;
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
