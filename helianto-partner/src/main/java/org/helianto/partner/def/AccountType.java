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

package org.helianto.partner.def;

/**
 * <code>AccountType</code> enumeration.
 * 
 * @author Mauricio Fernandes de Castro
 */
public enum AccountType {

    /**
     * 
     */
    ASSET('A', 'B'),
    LIABILITY('L', 'B'),
    OWNER_EQUITY('O', 'B'),
    EXPENSE('E', 'I'),
    REVENUE('R', 'I');
    
    private char value;
    private char type;
    
    private AccountType(char value, char type) {
        this.value = value;
        this.type = type;
    }
    
    public char getValue() {
        return value;
    }

    public char getType() {
        return type;
    }

}
