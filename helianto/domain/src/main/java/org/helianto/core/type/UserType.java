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
 * Enumeration to represent a type for 
 * an <code>User</code>. 
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id: UserType.java,v 1.1 2006/03/02 22:44:27 iserv Exp $
 */
public enum UserType {
    
    EXTERNAL('E'),
    INTERNAL('I');
    
    private char value;
    private UserType(char type) {
        this.value = type;
    }
    public char getValue() {
        return value;
    }

}
