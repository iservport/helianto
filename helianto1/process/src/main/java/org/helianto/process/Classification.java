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

/**
 * Classification applicable to process characteristics.
 * 
 * @author Mauricio Fernandes de Castro
 */
public enum Classification {
    
    /**
     * Affects product fit or function but is critical, i.e.
     * requires statistical control.
     */
    CRITICAL_FIT_FUNCTION(2),
    /**
     * Customer or operator risk, requires statistical control.
     */
    CRITICAL_SAFETY_REGULATORY(3),
    /**
     * Affects product fit or function.
     */
    FIT_FUNCTION(1),
    /**
     * Standard characteristic.
     */
    STANDARD(0);
    
    private int value;
    
    private Classification(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
    
    public boolean isStatisticalControlRequired() {
        if (value > 1) return true;
        return false;
    }

}
