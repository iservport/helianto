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
 */
public enum Resolution {
	
    /**
     * Not started.
     */
    PRELIMINARY('P', 1, false, false, false),
    /**
     * Work in progress.
     */
    TODO('T', 2, true, false, true),
    /**
     * No further control action required.
     */
    DONE('D', 3, true, true, false);
    
    private char value;
    private int order;
    private boolean started;
    private boolean ended;
    private boolean open;
    
    private Resolution(char value, int order, boolean started, boolean ended, boolean open) {
        this.value = value;
        this.order = order;
        this.started = started;
        this.ended = ended;
        this.open = open;
    }
    
    public final char getValue() {
        return this.value;
    }
    
    public int getOrder() {
		return order;
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
    public static Resolution getValue(char value) {
    	for (Resolution resolution: values()) {
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
    	for (Resolution resolution: values()) {
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
    	for (Resolution resolution: values()) {
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
