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


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.helianto.core.filter.AbstractUserBackedCriteriaFilter;
import org.helianto.core.filter.CriteriaBuilder;
import org.helianto.core.filter.DateRange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * Filter base class to be used with date fields.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractDateRangeFilter extends AbstractUserBackedCriteriaFilter implements DateRange {

	private static final long serialVersionUID = 1L;
	private String dateFieldName;
	private DateFilterMode dateFilterMode;
	private boolean intervalIntegrityEnforced;
    private Date fromDate;
    private Date toDate;
    private int interval = -7;

	/** 
	 * Default constructor
	 */
    public AbstractDateRangeFilter() {
    	super();
    	setDateFilterMode(DateFilterMode.TO_DATE_MINUS_INTERVAL);
    	setIntervalIntegrityEnforced(false);
    }
    
    public void reset() {
    	setToDate(new Date());
    }
    

	/**
	 * If not empty, expose the date field name to be used with this filter.
	 */
	public String getDateFieldName() {
		return dateFieldName;
	}
	public void setDateFieldName(String dateFieldName) {
		this.dateFieldName = dateFieldName;
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
	 * True if interval must be positive for future and negative for past ranges.
	 */
	public boolean isIntervalIntegrityEnforced() {
		return intervalIntegrityEnforced;
	}
	public void setIntervalIntegrityEnforced(boolean intervalIntegrityEnforced) {
		this.intervalIntegrityEnforced = intervalIntegrityEnforced;
	}

    /**
     * Start date
     */
    @DateTimeFormat(style="S-")
    public Date getFromDate() {
        return this.fromDate;
    }
    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * End date
     */
    @DateTimeFormat(style="S-")
    public Date getToDate() {
        return this.toDate;
    }
    public void setToDate(Date toDate) {
        this.toDate = toDate;
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
		if (getDateFilterMode()!=DateFilterMode.DISABLE_DATE_RANGE 
				&& getDateFieldName()!=null 
				&& getDateFieldName().length()>0) {
			switch (getDateFilterMode()) {
			case FROM_DATE_PLUS_INTERVAL : 
				setRangeFromDatePlusInterval(isIntervalIntegrityEnforced());
				break;
			case TO_DATE_MINUS_INTERVAL :
				setRangeToDateMinusInterval(isIntervalIntegrityEnforced());
				break;
			case FROM_DATE_TO_DATE :
				setRangeFromDateToDate();
				break;
			}
			mainCriteriaBuilder.appendDateRange(getDateFieldName(), this);	
		}
		else {
			logger.debug("Date range filter disabled");
		}
	}

    /**
     * Adds the interval to the end date (toDate) to update the 
     * start date (fromDate).
     * 
     * @param force true will require negative interval to force consistency.
     */
	protected void setRangeToDateMinusInterval(boolean force) {
    	if (getToDate()!=null) {
        	if (force && getInterval()>0) {
        		throw new IllegalArgumentException("Interval must be negative to have start date before end date");
        	}
        	setFromDate(getReferenceDate(getToDate(), getInterval()));
    		logger.debug("Filter {} from {} (or {} days before) to {}", new Object[] { getDateFieldName(), getFromDate(), getInterval(), getToDate() });
    	}
    }

    /**
     * Adds the interval to the start date (fromDate) to update the 
     * end date (toDate).
     * 
     * @param force true will require positive interval to force consistency.
     */
    protected void setRangeFromDatePlusInterval(boolean force) {
    	if (getFromDate()!=null) {
        	if (force && getInterval()<0) {
        		throw new IllegalArgumentException("Interval must be positive to have start date before end date");
        	}
        	setToDate(getReferenceDate(getFromDate(), getInterval()));
    		logger.debug("Filter {} from {} to {} (or {} days after)", new Object[] { getDateFieldName(), getFromDate(), getToDate(), getInterval() });
    	}
    }

    /**
     * Require both dates (to and from) to be not null.
     */
    protected void setRangeFromDateToDate() {
    	if (getFromDate()==null || getToDate()==null) {
    		throw new IllegalArgumentException("From date and to date are both required");
    	}
		logger.debug("Filter {} from {} to {}", new Object[] { getDateFieldName(), getFromDate(), getToDate() });
    }

    protected Calendar getReferenceCalendar(Date date) {
    	Calendar reference = GregorianCalendar.getInstance();
    	reference.setTime(date);
    	reference.set(Calendar.HOUR_OF_DAY, 23);
    	reference.set(Calendar.MINUTE, 59);
    	reference.set(Calendar.SECOND, 59);
    	return reference;
    }
    
    protected Calendar getReferenceCalendar(Date date, int days) {
    	Calendar reference = getReferenceCalendar(date);
    	reference.add(Calendar.DATE, days);
    	return reference;
    }
    
    protected Date getReferenceDate(Date date, int days) {
     	return getReferenceCalendar(date, days).getTime();
    }
    
    private static final Logger logger = LoggerFactory.getLogger(AbstractDateRangeFilter.class);
    
}


