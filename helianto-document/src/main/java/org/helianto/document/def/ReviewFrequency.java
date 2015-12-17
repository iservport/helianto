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

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Review frequency.
 * 
 * @author Mauricio Fernandes de Castro
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ReviewFrequency {
    
    /**
     * Not required.
     */
    NOT_REQUIRED(0),
    /**
     * Annual.
     */
    YEARLY(364),
    /**
     * Biannual.
     */
    HALF_YEARLY(182),
    /**
     * Quarterly.
     */
    QUARTERLY(92),
    /**
     * Bimonthly.
     */
    BIMONTHLY(60),
    /**
     * Monthly.
     */
    MONTHLY(30),
    /**
     * Biweekly.
     */
    BIWEEKLY(15),
    /**
     * Biweekly.
     */
    WEEKLY(7),
    /**
     * Daily.
     */
    DAILY(1);
    
    private int value;
    
    private ReviewFrequency(int value) {
        this.value = value;
    }
    
    public String getName() {
    	return name();
    }
    
    public int getValue() {
        return value;
    }
    
}
