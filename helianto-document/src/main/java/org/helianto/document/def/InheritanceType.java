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


package org.helianto.document.def;

/**
 * Define types of inheritance.
 * 
 * @author Mauricio Fernandes de Castro
 */
public enum InheritanceType {
    
    /**
     * Descendants are allowed and required.
     */
    ABSTRACT('A', true),
    /**
     * Descendants are allowed.
     */
    CONCRETE('C', true), 
    /**
     * Descendants are allowed, shown as group.
     */
    GROUP('G', true), 
    /**
     * Descendants are not allowed.
     */
    FINAL('F', false);
    
    private char value;
    private boolean allowed;
    
    private InheritanceType(char value, boolean allowed) {
        this.value = value;
    }
    
    public char getValue() {
        return value;
    }

	/**
	 * True if inheritance is allowed.
	 */
	public boolean isAllowed() {
		return allowed;
	}

}

