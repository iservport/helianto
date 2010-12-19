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

package org.helianto.core.filter;


import java.util.Date;

import org.helianto.core.TrunkEntity;
import org.helianto.core.criteria.CriteriaBuilder;
import org.helianto.core.criteria.DateCriteriaBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * Filter base class to be used with date fields.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractDateRangeFilterAdapter<T extends TrunkEntity> extends AbstractTrunkFilterAdapter<T> {

	private static final long serialVersionUID = 1L;
    private int interval = -7;

	/** 
	 * Default constructor
	 * 
	 * @param filter
	 */
    public AbstractDateRangeFilterAdapter(T filter) {
    	super(filter);
    }
    
	/**
	 * The date field name to be used with this filter.
	 */
	public String getDateFieldName() {
		return "";
	}
	
    /**
     * Start date
     */
    @DateTimeFormat(style="S-")
    public Date getFromDate() {
    	return null;
    }

    /**
     * End date
     */
    @DateTimeFormat(style="S-")
    public Date getToDate() {
    	return new Date();
    }

    /**
     * Interval to be added.
     */
    public int getInterval() {
		return interval;
	}
    public void setInterval(int interval) {
		this.interval = interval;
	}
    
	@Override
	protected void doFilter(CriteriaBuilder mainCriteriaBuilder) {
		if (getDateFieldName().length()>0) {
			DateCriteriaBuilder dateCriteria = new DateCriteriaBuilder(mainCriteriaBuilder.getPrefix(), getDateFieldName());
			dateCriteria.appendFromDateRange(getFromDate(), getToDate(), getInterval());	
			dateCriteria.appendToDateRange(getFromDate(), getToDate(), getInterval());
			if (dateCriteria.getSegmentCount()>0) {
				mainCriteriaBuilder.appendAnd().append(dateCriteria);
			}
		}
		else {
			logger.debug("Date range filter disabled");
		}
	}

    private static final Logger logger = LoggerFactory.getLogger(AbstractDateRangeFilterAdapter.class);
    
}


