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

package org.helianto.document.filter.classic;


import org.helianto.core.criteria.CriteriaBuilder;

/**
 * Base class to control filters.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractControlFilter extends AbstractRecordFilter {

	private static final long serialVersionUID = 1L;
	private char trackingMode;
    private int priority = 0;

	/** 
     * Default constructor.
     */
    public AbstractControlFilter() {
    	super();
    	setTrackingMode(' ');
    }
    
    /**
     * Tracking mode filter.
     */
    public char getTrackingMode() {
    	return this.trackingMode;
    }
    public void setTrackingMode(char trackingMode) {
    	this.trackingMode = trackingMode;
    }
    
    /**
     * Priority.
     */
    public int getPriority() {
        return this.priority;
    }
    public void setPriority(int priority) {
        this.priority = priority;
    }
    
	@Override
	public void doFilter(CriteriaBuilder mainCriteriaBuilder) {
		super.doFilter(mainCriteriaBuilder);
		appendTrackingMode(mainCriteriaBuilder);
		appendPriority(mainCriteriaBuilder);
	}
	
	/**
	 * Tracking mode appender.
	 * 
	 * @param mainCriteriaBuilder
	 */
	protected void appendTrackingMode(CriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("trackingMode", getTrackingMode(), mainCriteriaBuilder);
	}

	/**
	 * Priority appender.
	 * 
	 * @param mainCriteriaBuilder
	 */
	protected void appendPriority(CriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("priority", getPriority(), mainCriteriaBuilder);
	}

}


