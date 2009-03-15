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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.Entity;

/**
 * Base implementation for <code>SelectionStrategy</code>
 * interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractSelectionStrategy<T extends AbstractUserBackedCriteriaFilter> implements SelectionStrategy<T> {

	/**
	 * Delegate criteria creation to a chain of processors.
	 */
	public final String createCriteriaAsString(T filter, String prefix) {
        CriteriaBuilder mainCriteriaBuilder = new CriteriaBuilder(prefix);
        if (filter.getEntity()==null) {
            throw new IllegalArgumentException("User or entity required!");
        }
        appendEntityFilter(filter.getEntity(), mainCriteriaBuilder);
        preProcessFilter(filter, mainCriteriaBuilder);
        
        if (isSelection(filter)) {
        	doSelect(filter, mainCriteriaBuilder);
        }
        else {
        	doFilter(filter, mainCriteriaBuilder);
        }
        
        postProcessFilter(filter, mainCriteriaBuilder);
        
        if (logger.isDebugEnabled()) {
            logger.debug("Filter query: "+mainCriteriaBuilder.getCriteriaAsString());
        }
        return mainCriteriaBuilder.getCriteriaAsString();
    }
	
    /**
     * Append an <code>Entity</code> filter.
     * 
     * @param entity
	 * @param mainCriteriaBuilder
     */
	protected void appendEntityFilter(Entity entity, CriteriaBuilder mainCriteriaBuilder) {
		mainCriteriaBuilder.appendSegment("entity.id", "=")
        .append(entity.getId());
    }
    
	/**
	 * Filter pre-processor.
	 * 
	 * @param filter
	 * @param mainCriteriaBuilder
	 */
	protected void preProcessFilter(T filter, CriteriaBuilder mainCriteriaBuilder) {
	}
	
	/**
	 * A selection should create a criteria string from a unique key.
	 * 
	 * @param filter
	 */
	protected boolean isSelection(T filter) {
		return false;
	}
	
	/**
	 * Selection processor.
	 * 
	 * @param filter
	 * @param mainCriteriaBuilder
	 */
	protected abstract void doSelect(T filter, CriteriaBuilder mainCriteriaBuilder);
	
	/**
	 * Filter processor.
	 * 
	 * @param filter
	 * @param mainCriteriaBuilder
	 */
	protected abstract void doFilter(T filter, CriteriaBuilder mainCriteriaBuilder);
	
	/**
	 * Filter post-processor.
	 * 
	 * @param filter
	 * @param mainCriteriaBuilder
	 */
	protected void postProcessFilter(T filter, CriteriaBuilder mainCriteriaBuilder) {
	}
	
    /**
     * Base String filter segment.
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
     * Base String filter segment.
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
     * Base String filter segment.
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
     * Base String filter segment.
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
     * Base String filter segment.
     * 
     * @param fieldName
     * @param fieldContent
     * @param criteriaBuilder
     */
    protected void appendLikeFilter(String fieldName, String fieldContent, CriteriaBuilder criteriaBuilder) {
    	if (fieldContent!=null && fieldContent.length()>0) {
    		criteriaBuilder.appendAnd().appendSegment(fieldName, "like")
            .appendLike(fieldContent);
        }
    }
    
    protected static Log logger = LogFactory.getLog(AbstractSelectionStrategy.class);

}
