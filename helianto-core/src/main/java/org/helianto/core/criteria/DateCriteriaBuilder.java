package org.helianto.core.criteria;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.helianto.core.filter.DateInterval;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Extend <code>CriteriaBuilder</code> to build an EJB-QL string criteria
 * with dates.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class DateCriteriaBuilder extends OrmCriteriaBuilder {
	
	private String dateFieldName;
	
    /**
     * Default constructor.
     */
    public DateCriteriaBuilder() {
        super();
    }
    
    /**
     * Default prefix constructor.
     * 
     * @param prefix
     * @param dateFieldName
     */
    public DateCriteriaBuilder(String prefix, String dateFieldName) {
        this(prefix, dateFieldName, DEFAULT_SQL_CONVERSION_DATE_FORMAT);
        setDateFieldName(dateFieldName);
    }
    
    /**
     * Constructor taking a date format string.
     * 
     * @param prefix
     * @param dateFieldName
     * @param dateFormat
     */
    public DateCriteriaBuilder(String prefix, String dateFieldName, String dateFormat) {
    	this();
        setDateFormat(dateFormat);
        createCriteria(prefix);
        logger.debug("New date field name is {} ({})", new StringBuilder(prefix).append(".").append(dateFieldName), dateFormat);
    }
    
    /**
     * Date field name.
     */
	public String getDateFieldName() {
		return dateFieldName;
	}
	public void setDateFieldName(String dateFieldName) {
		this.dateFieldName = dateFieldName;
	}

    /**
     * Append from date range.
     * 
     * @param dateInterval
     */
    public OrmCriteriaBuilder appendFromDateRange(DateInterval dateInterval) {
    	return appendFromDateRange(dateInterval.getFromDate(), dateInterval.getToDate(), dateInterval.getInterval());
	}

    /**
     * Append date range.
     * 
     * @param fromDate
     * @param toDate
     */
    public OrmCriteriaBuilder appendDateRange(Date fromDate, Date toDate) {
        if (fromDate!=null) {
        	appendSegment(getDateFieldName(), ">=").append(fromDate);
        }
    	if (toDate!=null) {
			appendAnd(fromDate!=null);
    		appendSegment(getDateFieldName(), "<").append(toDate);
    	}        	
        return this;
    }
    
    /**
     * Append from date range.
     * 
     * @param fromDate
     * @param toDate
     * @param interval
     */
    public OrmCriteriaBuilder appendFromDateRange(Date fromDate, Date toDate, int interval) {
        if (fromDate!=null) {
        	appendSegment(getDateFieldName(), ">=");
        	if (interval<0) {
        		append(newCalendar(fromDate, interval).getTime());
        	}
        	else {
            	append(fromDate);
        	}
        }
        else {
        	if (interval<0 && toDate!=null) {
        		appendSegment(getDateFieldName(), ">=");
        		append(newCalendar(toDate, interval).getTime()).appendAnd();
        	}        	
        }
        return this;
    }
    
    /**
     * Append to date range.
     * 
     * @param dateInterval
     */
    public OrmCriteriaBuilder appendToDateRange(DateInterval dateInterval) {
    	return appendToDateRange(dateInterval.getFromDate(), dateInterval.getToDate(), dateInterval.getInterval());
	}

    /**
     * Append to date range.
     * 
     * @param fromDate
     * @param toDate
     * @param interval
     */
    public OrmCriteriaBuilder appendToDateRange(Date fromDate, Date toDate, int interval) {
        if (toDate!=null) {
        	appendAnd(fromDate!=null).appendSegment(getDateFieldName(), "<");
        	if (interval>0) {
        		append(newCalendar(toDate, interval).getTime());
        	}
        	else {
        		append(toDate);
        	}
        }
        else {
        	if (interval>0 && fromDate!=null) {
        		appendAnd(fromDate!=null).appendSegment(getDateFieldName(), "<");
        		append(newCalendar(fromDate, interval).getTime());
        	}
        }
        return this;
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
    
    private static final Logger logger = LoggerFactory.getLogger(DateCriteriaBuilder.class);
    
}
