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

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Base class to create criteria using a criteria builder.
 * 
 * @author Mauricio Fernandes de Castro
 */
@SuppressWarnings("serial")
public abstract class AbstractFilter implements Serializable, Filter {

	/**
	 * Delegate criteria creation to a builder.
	 */
	public String createCriteriaAsString() {
        return createCriteriaAsString(new CriteriaBuilder(getObjectAlias()));
    }
	
	/**
	 * Delegate criteria creation to a chain of processors.
	 */
	protected String createCriteriaAsString(CriteriaBuilder mainCriteriaBuilder) {
        preProcessFilter(mainCriteriaBuilder);
        if (isSelection()) {
        	doSelect(mainCriteriaBuilder);
        	reset();
        }
        else {
        	doFilter(mainCriteriaBuilder);
        }
        postProcessFilter(mainCriteriaBuilder);
        if (logger.isDebugEnabled()) {
            logger.debug("Filter query: "+mainCriteriaBuilder.getCriteriaAsString());
        }
        return mainCriteriaBuilder.getCriteriaAsString();
    }
	
	/**
	 * By default, filters do not return an unique result.
	 */
	public boolean isSelection() {
		return false;
	}
	
	// processors
	
	/**
	 * Subclasses overriding this method should create query segments
	 * to be included for both selection and filter operations.
	 * 
	 * @param filter
	 * @param mainCriteriaBuilder
	 */
	protected void preProcessFilter(CriteriaBuilder mainCriteriaBuilder) {
	}
	
	/**
	 * Hook to the selection processor.
	 * 
	 * @param mainCriteriaBuilder
	 */
	protected abstract void doSelect(CriteriaBuilder mainCriteriaBuilder);
	
	/**
	 * Hook to the filter processor.
	 * 
	 * @param mainCriteriaBuilder
	 */
	protected abstract void doFilter(CriteriaBuilder mainCriteriaBuilder);
	
	/**
	 * Hook to the filter post-processor.
	 * 
	 * @param mainCriteriaBuilder
	 */
	protected void postProcessFilter(CriteriaBuilder mainCriteriaBuilder) {
	}
	
	// appenders
	
    /**
     * Equal appender.
     * 
     * @param fieldName
     * @param fieldContent
     * @param criteriaBuilder
     */
    protected void appendEqualFilter(String fieldName, String fieldContent, CriteriaBuilder criteriaBuilder) {
    	if (fieldContent!=null && fieldContent.length()>0) {
            criteriaBuilder.appendAnd().appendSegment(fieldName, "=")
            .appendString(fieldContent);
        }
    }
    
    /**
     * Equal appender.
     * 
     * @param fieldName
     * @param fieldContent
     * @param criteriaBuilder
     */
    protected void appendEqualFilter(String fieldName, int fieldContent, CriteriaBuilder criteriaBuilder) {
    	if (fieldContent>0) {
            criteriaBuilder.appendAnd().appendSegment(fieldName, "=")
            .append(fieldContent);
        }
    }
    
    /**
     * Equal appender.
     * 
     * @param fieldName
     * @param fieldContent
     * @param criteriaBuilder
     */
    protected void appendEqualFilter(String fieldName, long fieldContent, CriteriaBuilder criteriaBuilder) {
    	if (fieldContent>0) {
            criteriaBuilder.appendAnd().appendSegment(fieldName, "=")
            .append(fieldContent);
        }
    }
    
    /**
     * Equal appender.
     * 
     * @param fieldName
     * @param fieldContent
     * @param criteriaBuilder
     */
    protected void appendEqualFilter(String fieldName, char fieldContent, CriteriaBuilder criteriaBuilder) {
    	if (fieldContent!=' ') {
            criteriaBuilder.appendAnd().appendSegment(fieldName, "=")
            .append(fieldContent);
        }
    }
    
    /**
     * Case sensitive like appender.
     * 
     * @param fieldName
     * @param fieldContent
     * @param criteriaBuilder
     */
    protected void appendLikeCaseFilter(String fieldName, String fieldContent, CriteriaBuilder criteriaBuilder) {
    	if (fieldContent!=null && fieldContent.length()>0) {
    		criteriaBuilder.appendAnd().appendSegment(fieldName, "like")
            .appendLike(fieldContent);
        }
    }
    
    /**
     * Case unsensitive like appender.
     * 
     * @param fieldName
     * @param fieldContent
     * @param criteriaBuilder
     */
    protected void appendLikeFilter(String fieldName, String fieldContent, CriteriaBuilder criteriaBuilder) {
    	if (fieldContent!=null && fieldContent.length()>0) {
    		criteriaBuilder.appendAnd().appendSegment(fieldName, "like", "lower")
            .appendLike(fieldContent.toLowerCase());
        }
    }
    
    /**
     * Base order by segment.
     * 
     * @param fieldContent
     * @param criteriaBuilder
     */
    protected void appendOrderBy(String fieldContent, CriteriaBuilder criteriaBuilder) {
    	String[] fieldNames = fieldContent.split(",");
    	if (fieldNames.length>0) {
    		criteriaBuilder.appendOrderBy(fieldNames);
        }
    }
    
    private static Log logger = LogFactory.getLog(AbstractFilter.class);

}