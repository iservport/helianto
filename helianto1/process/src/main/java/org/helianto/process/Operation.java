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
     * OperationType getter.
     */
    public int getOperationType() {
        return this.operationType;
    }
    /**
     * OperationType setter.
     */
    public void setOperationType(int operationType) {
        this.operationType = operationType;
    }

    /**
     * OperationTime getter.
     */
    public long getOperationTime() {
        return this.operationTime;
    }
    /**
     * OperationTime setter.
     */
    public void setOperationTime(long operationTime) {
        this.operationTime = operationTime;
    }

    /**
     * Setups getter.
     */
    @OneToMany(mappedBy="operation", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    public List<Setup> getSetups() {
        return this.setups;
    }
    /**
     * Setups setter.
     */
    public void setSetups(List<Setup> setups) {
        this.setups = setups;
    }

}
