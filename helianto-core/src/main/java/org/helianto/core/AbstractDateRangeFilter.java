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
import org.helianto.core.filter.DateRange;


/**
 * Date range filter superclass.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractDateRangeFilter extends AbstractUserBackedCriteriaFilter implements DateRange {

	private static final long serialVersionUID = 1L;
    private Date fromDate;
    private Date toDate;
    private boolean toCurrentDate;
	private int daysIncluded = -7;
    private int daysToInclude = 7;
    private int interval = -7;

	/** 
	 * Default constructor
	 */
    public AbstractDateRangeFilter() {
    	super();
    }
    
    /**
     * Set the default date range.
     */
    public void selectDateRange() {
        if (toDate==null) {
        	setToDate(new Date());
        }
        if (fromDate==null) {
        	Calendar from = GregorianCalendar.getInstance();
        	from.setTime(toDate);
            from.add(Calendar.DATE, -daysIncluded);
            setFromDate(from.getTime());
        }
    }
    
    public void reset() {
    	setToDate(new Date());
    }
    

    /**
     * Start date
     */
    public Date getFromDate() {
        return this.fromDate;
    }
    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * End date
     */
    public Date getToDate() {
        return this.toDate;
    }
    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    /**
     * True if <code>toDate</code> is auto update to now.
     */
    public boolean isToCurrentDate() {
        return this.toCurrentDate;
    }
    public void setToCurrentDate(boolean toCurrentDate) {
        this.toCurrentDate = toCurrentDate;
    }
    
    /**
     * Set up range start date.
     * @deprecated see interval and updateRange()
     */
    public int getDaysIncluded() {
    	return daysIncluded;
    }
    public void setDaysIncluded(int daysIncluded) {
    	this.daysIncluded = daysIncluded;
    	if (daysIncluded!=0) {
    		setFromDate(getReferenceCalendar(new Date(), daysIncluded).getTime());
    	}
    }

    /**
     * Set up range end date.
     * @deprecated see interval and updateRange()
     */
    public int getDaysToInclude() {
    	return daysToInclude;
    }
    public void setDaysToInclude(int daysToInclude) {
    	this.daysToInclude = daysToInclude;
    	if (daysToInclude!=0) {
    		setToDate(getReferenceCalendar(new Date(), daysToInclude).getTime());
    	}
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
    
    /**
     * Adds the interval to the end date (toDate) to update the 
     * start date (fromDate).
     * 
     * @param force true will require negative interval to force consistency.
     */
    public void updateRangePast(boolean force) {
    	if (getToDate()==null) {
    		setToDate(new Date());
    	}
    	if (force && getInterval()>0) {
    		throw new IllegalArgumentException("Interval must be negative to have start date before end date");
    	}
    	setFromDate(getReferenceDate(getToDate(), getInterval()));
    	
    }

    /**
     * Adds the interval to the start date (fromDate) to update the 
     * end date (toDate).
     * 
     * @param force true will require positive interval to force consistency.
     */
    public void updateRangeFuture(boolean force) {
    	if (getFromDate()==null) {
    		setFromDate(new Date());
    	}
    	if (force && getInterval()<0) {
    		throw new IllegalArgumentException("Interval must be positive to have start date before end date");
    	}
    	setToDate(getReferenceDate(getFromDate(), getInterval()));
    	
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
    
}


