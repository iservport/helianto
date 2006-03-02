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

package org.helianto.core;

/**
 * An enumeration to supply int types for 
 * {@link PersonalData#appellation}.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 */
public enum Appellation {
    
    /**
     * Not supplied.
     */
    NOT_SUPPLIED(0),
    /**
     * Miss.
     */
    MISS(1),
    /**
     * Mister or Mistress. Further distinction depends on 
     * the gender.
     */
    MR_OR_MRS(2),
    /**
     * Miss or Mistress
     */
    MS(3);
    
    private int value;
    
    private Appellation(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }

}
