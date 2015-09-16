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

package org.helianto.inventory;

/**
 * Define if the amount is added, subtracted or ignored
 * to compute the cummulative amount.
 *  
 * @author Mauricio Fernandes de Castro
 */
public enum RequirementSign {
    
    /** 
     * The associated amount increments the requirement.
     */
    INCREMENT(1),
    /** 
     * The associated amount decrements the requirement.
     */
    DECREMENT(-1),
    /** 
     * The associated amount does not change the requirement.
     */
    IGNORE(0);
    
    private int value;

    private RequirementSign(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
