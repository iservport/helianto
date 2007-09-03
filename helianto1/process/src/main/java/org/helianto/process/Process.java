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

import javax.persistence.Table;

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
    private long internalNumber;

    /** default constructor */
    public Process() {
    }

    // Property accessors
    public long getInternalNumber() {
        return this.internalNumber;
    }
    
    public void setInternalNumber(long internalNumber) {
        this.internalNumber = internalNumber;
    }




}


