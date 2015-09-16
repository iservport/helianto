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

package org.helianto.core.def;


/**
 * Control resolution.
 * 
 * @author Mauricio Fernandes de Castro
 * @deprecated
 * @see Resolution
 */
public enum ResolutionExtended {
	
	/* C D I N P R T U W */
    
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
     * Repeat.
     */
    REPEAT('R', true, false, false),
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
    
    private ResolutionExtended(char value, boolean started, boolean ended, boolean open) {
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
    
    /**
     * Resolution assigned char value.
     * 
     * @param value
     */
    public static ResolutionExtended getValue(char value) {
    	for (ResolutionExtended resolution: values()) {
    		if (resolution.getValue()==value) {
    			return resolution;
    		}
    	}
    	return null;
    }

    /**
     * Array of started resolution values.
     */
    public static char[] getStartedResolutions() {
    	String result = "";
    	for (ResolutionExtended resolution: values()) {
    		if (resolution.hasStarted()) {
    			result += resolution.getValue();
    		}
    	}
    	return result.toCharArray();
    }

    /**
     * Array of open resolution values.
     */
    public static char[] getOpenResolutions() {
    	String result = "";
    	for (ResolutionExtended resolution: values()) {
    		if (resolution.isOpen()) {
    			result += resolution.getValue();
    		}
    	}
    	return result.toCharArray();
    }
    
    /**
     * Array of open resolution values listed as a comma separated value string.
     */
    public static String getOpenResolutionsAsCsv() {
    	char[] open = ResolutionExtended.getOpenResolutions();
    	StringBuilder sb = new StringBuilder();
    	for (int i = 0; i < open.length; i++) {
    		if (i == 0) {
    			sb.append("'");
    		}
    		if (i > 0) {
    			sb.append(",'");
    		}
    		sb.append(open[i]);
    		sb.append("'");
    	}
    	return sb.toString();
    }
    
}
