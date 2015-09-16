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
 * Tracking modes.
 * 
 * @author Mauricio Fernandes de Castro
 */
public enum TrackingMode {
    
    /**
     * Not tracked.
     */
    NOT_TRACKED('N'),
    /**
     * Continuous.
     */
    CONTINUOUS('C'),
    /**
     * End only.
     */
    END_ONLY('E');
    
    private char value;
    
    private TrackingMode(char value) {
        this.value = value;
    }
    
    public char getValue() {
        return this.value;
    }
    public static TrackingMode getValue(char value) {
    	for (TrackingMode delayTracker: values()) {
    		if (delayTracker.getValue()==value) {
    			return delayTracker;
    		}
    	}
    	return null;
    }

}
