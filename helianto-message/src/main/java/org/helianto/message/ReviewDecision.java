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

package org.helianto.message;

import org.helianto.core.Resolution;

/**
 * Define <code>Review</code> decisions.
 * 
 * @author Mauricio Fernandes de Castro
 */
public enum ReviewDecision {
	
    /**
     * Justify.
     */
	JUSTIFY('J', Resolution.UNFOUNDED),
    /**
     * Start.
     */
	START('S', Resolution.TODO),
    /**
     * Stage (before actually start).
     */
	STAGE('A', Resolution.PRELIMINARY),
    /**
     * Suspend if running.
     */
	SUSPEND('W', Resolution.WAIT),
    /**
     * Continue.
     */
	CONTINUE('G', Resolution.TODO),
    /**
     * RESCHEDULE if waiting.
     */
	RESCHEDULE('E', Resolution.TODO),
    /**
     * CLOSE and keep the progress as it is, if active.
     */
	CLOSE('C', Resolution.DONE),
    /**
     * FINISH to force to 100% and close if active.
     */
	FINISH('F', Resolution.DONE),
    /**
     * CANCEL .
     */
	CANCEL('X', Resolution.CANCELLED),
    /**
     * REOPEN if not active.
     */
	REOPEN('R', Resolution.TODO),
    /**
     * Reassess if unfounded.
     */
	REASSESS('B', Resolution.ISSUED),
    /**
     * Revert if cancelled.
     */
	REVERT('V', Resolution.TODO);
    
    private char value;
    private Resolution nextResolution;
    
    private ReviewDecision(char value, Resolution nextResolution) {
        this.value = value;
        this.nextResolution = nextResolution;
    }
    
    public char getValue() {
        return this.value;
    }
    
    public Resolution getNextResolution() {
		return nextResolution;
	}

}
