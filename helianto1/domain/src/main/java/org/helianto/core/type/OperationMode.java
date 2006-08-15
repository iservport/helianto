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

package org.helianto.core.type;


/**
 * Modes of operation.
 * 
 * @author Mauricio Fernandes de Castro
 */
public enum OperationMode {

    /**
     * Inherit properties of a parent <code>Operator</code>
     * and may have a more restricted range of operation.
     */
    DELEGATED('D'),
    /**
     * Appropriate for small installations where the notion
     * of operator and the system itself are interchangeable.
     */
    LOCAL('L'),
    /**
     * Use in systems where different operators control different
     * territories. Collaboration among entities should happen
     * only within enterprise operators.
     */
    ENTERPRISE('E');
    
    private char value;
    
    private OperationMode(char value) {
        this.value = value;
    }
    public char getValue() {
        return this.value;
    }

}
