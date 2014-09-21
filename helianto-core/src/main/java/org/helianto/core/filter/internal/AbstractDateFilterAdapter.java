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

package org.helianto.core.filter.internal;


import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.DateForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Filter base class to be used with date fields.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractDateFilterAdapter<T extends DateForm> 
	extends AbstractEntityIdFilterAdapter<T> 
{

	private static final long serialVersionUID = 1L;

	/** 
	 * Form constructor
	 * 
	 * @param form
	 */
    public AbstractDateFilterAdapter(T form) {
    	super(form);
    }
    
	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		if (isDateFilterEnabled()) {
			appendDateRange(mainCriteriaBuilder); 
		}
	}
	
	/**
	 * True if date filter is enabled.
	 */
	protected boolean isDateFilterEnabled() {
		return !getDateFieldName().isEmpty() && (isDateFilterFrom() | isDateFilterTo());
	}
	
	/**
	 * True if date filter from date is not empty.
	 */
	public boolean isDateFilterFrom() {
		return getForm().getFromDate()!=null;
	}
	
	/**
	 * True if date filter to date is not empty.
	 */
	public boolean isDateFilterTo() {
		return getForm().getToDate()!=null;
	}
	
	/**
	 * Field name.
	 */
	protected String getDateFieldName() {
		if (getForm().getDateFieldName()!=null) {
			return getForm().getDateFieldName();
		}
		return "";
	}
	
	@Override
	public String getOrderByString() {
		if (getForm().getDateFieldName()!=null) {
			return getForm().getDateFieldName();
		}
		return "";
	}
	
    /**
     * Append date range.
     * 
     * @param mainCriteriaBuilder
     */
    public OrmCriteriaBuilder appendDateRange(OrmCriteriaBuilder mainCriteriaBuilder) {
		mainCriteriaBuilder.appendAnd();
        if (isDateFilterFrom()) {
        	mainCriteriaBuilder.appendSegment(getDateFieldName(), ">=").append(getForm().getFromDate());
        	logger.debug("Filter {} from date {}.", getDateFieldName(), getForm().getFromDate());
        }
    	if (isDateFilterTo()) {
    		mainCriteriaBuilder.appendAnd(isDateFilterFrom());
    		mainCriteriaBuilder.appendSegment(getDateFieldName(), "<").append(getForm().getToDate());
        	logger.debug("Filter {} to date {}.", getDateFieldName(), getForm().getToDate());
    	}        	
        return mainCriteriaBuilder;
    }
    
    private static final Logger logger = LoggerFactory.getLogger(AbstractDateFilterAdapter.class);
    
}


