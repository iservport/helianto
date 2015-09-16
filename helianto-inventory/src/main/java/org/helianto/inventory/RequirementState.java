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
 * Define requirement state.
 * 
 * @author Mauricio Fernandes de Castro
 */
public enum RequirementState {
    
	/** 
	 * Forecast.
	 */
	FORECAST('F', true, RequirementPhase.ENTRY),
    /**
     * Pending.
     */
    PENDING('P', false, RequirementPhase.ENTRY),
    /** 
	 * Open.
	 */
    OPEN('O', false, RequirementPhase.MANAGEMENT),
    /**
     * Actual.
     */
    ACTUAL('A', false, RequirementPhase.MANAGEMENT),
    /**
     * Suspended.
     */
    SUSPENDED('W', false, RequirementPhase.MANAGEMENT),
    /**
     * Scheduled.
     */
    SCHEDULED('S', false, RequirementPhase.CONTROL),
	/**
	 * Running.
	 */
    RUNNING('R', true, RequirementPhase.CONTROL),
    /**
     * Cancelled.
     */
    CANCELLED('X', false, RequirementPhase.ARCHIVE),
    /**
     * Closed.
     */
    CLOSED('C', true, RequirementPhase.ARCHIVE);
    
    private RequirementState(char value, boolean subtract, RequirementPhase phase) {
        this.value = value;
        this.subtract = subtract;
        this.phase = phase;
    }
    
    private char value;
    private boolean subtract;
    private RequirementPhase phase;
    
    public final char getValue() {
        return this.value;
    }
    
    public final boolean isSubtract() {
        return this.subtract;
    }

    public final RequirementPhase getPhase() {
        return this.phase;
    }
    
    public static RequirementState getRequirementState(char value) {
    	for (RequirementState requirementState: RequirementState.values()) {
    		if (requirementState.getValue()==value) {
    			return requirementState;
    		}
    	}
    	return null;
    }
    
}
