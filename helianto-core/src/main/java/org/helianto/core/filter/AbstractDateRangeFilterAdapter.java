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


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.helianto.core.DateFilterMode;
import org.helianto.core.TrunkEntity;
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
	private DateFilterMode dateFilterMode;
    private int interval = -7;

	/** 
	 * Default constructor
	 * 
	 * @param filter
	 * @param dateFilterMode
	 */
    public AbstractDateRangeFilterAdapter(T filter, DateFilterMode dateFilterMode) {
    	super(filter);
    	setDateFilterMode(dateFilterMode);
    }
    
	/**
	 * Filter mode.
	 */
	public DateFilterMode getDateFilterMode() {
		return dateFilterMode;
	}
	public void setDateFilterMode(DateFilterMode dateFilterMode) {
		this.dateFilterMode = dateFilterMode;
	}
	
	/**
	 * The date field name to be used with this filter.
	 */
	public abstract String getDateFieldName();
	
    /**
     * Start date
     */
    @DateTimeFormat(style="S-")
    public abstract Date getFromDate();

    /**
     * End date
     */
    @DateTimeFormat(style="S-")
    public abstract Date getToDate();

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
		if (getDateFilterMode()!=DateFilterMode.DISABLE_DATE_RANGE 
				&& getDateFieldName()!=null 
				&& getDateFieldName().length()>0) {
			CriteriaBuilder dateCriteria = new CriteriaBuilder(getObjectAlias());
			appendFromDateRange(dateCriteria);	
			appendToDateRange(dateCriteria);	
			if (dateCriteria.getSegmentCount()>0) {
				mainCriteriaBuilder.appendAnd().append(dateCriteria);
			}
		}
		else {
			logger.debug("Date range filter disabled");
		}
	}

    /**
     * Append from date range.
     * 
     * @param criteriaBuilder
     */
    protected void appendFromDateRange(CriteriaBuilder criteriaBuilder) {
        if (getFromDate()!=null) {
        	criteriaBuilder.appendSegment(getDateFieldName(), ">=");
        	if (getDateFilterMode().equals(DateFilterMode.TO_DATE_MINUS_INTERVAL) && getToDate()!=null) {
        		criteriaBuilder.append(newCalendar(getToDate(), getInterval()).getTime());
        	}
        	else {
            	criteriaBuilder.append(getFromDate());
        	}
        }
    }
    
    /**
     * Append to date range.
     * 
     * @param criteriaBuilder
     */
    protected void appendToDateRange(CriteriaBuilder criteriaBuilder) {
        if (getToDate()!=null) {
        	criteriaBuilder.appendAnd(getFromDate()!=null).appendSegment(getDateFieldName(), "<");
        	if (getDateFilterMode().equals(DateFilterMode.FROM_DATE_PLUS_INTERVAL) && getFromDate()!=null) {
        		criteriaBuilder.append(newCalendar(getFromDate(), getInterval()).getTime());
        	}
        	else {
        		criteriaBuilder.append(getToDate());
        	}
        }
    }
    
    /**
     * New calendar instance for a date.
     * 
     * @param date
     */
    protected Calendar newCalendar(Date date) {
    	Calendar reference = GregorianCalendar.getInstance();
    	reference.setTime(date);
    	reference.set(Calendar.HOUR_OF_DAY, 23);
    	reference.set(Calendar.MINUTE, 59);
    	reference.set(Calendar.SECOND, 59);
    	return reference;
    }
    
    /**
     * New calendar instance for a day plus (or minus) interval.
     * 
     * @param date
     * @param interval
     */
    protected Calendar newCalendar(Date date, int interval) {
    	Calendar reference = newCalendar(date);
    	reference.add(Calendar.DATE, interval);
    	return reference;
    }
    
    private static final Logger logger = LoggerFactory.getLogger(AbstractDateRangeFilterAdapter.class);
    
}


