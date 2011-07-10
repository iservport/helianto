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
 * Control resolution.
 * 
 * @author Mauricio Fernandes de Castro
 */
public enum Resolution {
    
    /**
     * Issued.
     */
    ISSUED('I', false, false, false),
    /**
     * Not yet published.
     */
    NOT_PUBLISHED('N', false, false, false),
    /**
     * Not started.
     */
    PRELIMINARY('P', false, false, true),
    /**
     * Work in progress.
     */
    TODO('T', true, false, true),
    /**
     * Suspended.
     */
    WAIT('W', true, false, true),
    /**
     * No further control action required.
     */
    DONE('D', true, true, false),
    /**
     * Cancellled (once started).
     */
    CANCELLED('C', true, false, false),
    /**
     * Cancelled (but never started).
     */
    UNFOUNDED('U', false, false, false);
    
    private char value;
    private boolean started;
    private boolean ended;
    private boolean open;
    
    private Resolution(char value, boolean started, boolean ended, boolean open) {
        this.value = value;
        this.started = started;
        this.ended = ended;
        this.open = open;
    }
    
    public final char getValue() {
        return this.value;
    }

    public boolean hasStarted() {
        return this.started;
    }

    public boolean hasEnded() {
        return this.ended;
    }
    
    public boolean isOpen() {
        return this.open;
    }
    
    public static Resolution getValue(char value) {
    	for (Resolution resolution: values()) {
    		if (resolution.getValue()==value) {
    			return resolution;
    		}
    	}
    	return null;
    }

}
