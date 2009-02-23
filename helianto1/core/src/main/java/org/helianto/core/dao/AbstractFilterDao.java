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


package org.helianto.core.dao;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.Entity;
import org.helianto.core.filter.AbstractUserBackedCriteriaFilter;
import org.helianto.core.filter.CriteriaBuilder;
import org.helianto.core.orm.DefaultCategorySelectionStrategy;

/**
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractFilterDao<T, F extends AbstractUserBackedCriteriaFilter> extends AbstractBasicDao<T> implements FilterDao<T, F> {

	public Collection<T> find(F filter) {
		String whereClause = createCriteriaAsString(filter);
		return super.find(getSelectBuilder(), whereClause);
	}

	/**
	 * Delegate criteria creation to a chain of processors.
	 */
	protected String createCriteriaAsString(F filter) {
		return createCriteriaAsString(filter, true);
	}

	/**
	 * Delegate criteria creation to a chain of processors.
	 */
	protected final String createCriteriaAsString(F filter, boolean requireEntity) {
        CriteriaBuilder mainCriteriaBuilder = createCriteriaBuilder();
        if (requireEntity && filter.getEntity()==null) {
            throw new IllegalArgumentException("User or entity required!");
        }
        else if (filter.getEntity()!=null){
            appendEntityFilter(filter.getEntity(), mainCriteriaBuilder);
        }
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
	 * Create the builder.
	 */
	protected CriteriaBuilder createCriteriaBuilder() {
        return new CriteriaBuilder(getObjectAlias());
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
		if (logger.isDebugEnabled()) {
			logger.debug("Filter entity constraint set to "+entity.getAlias());
		}
    }
    
	/**
	 * Filter pre-processor.
	 * 
	 * @param filter
	 * @param mainCriteriaBuilder
	 */
	protected void preProcessFilter(F filter, CriteriaBuilder mainCriteriaBuilder) {
	}
	
	/**
	 * A selection should create a criteria string from a unique key.
	 * 
	 * @param filter
	 */
	protected abstract boolean isSelection(F filter);
	
	/**
	 * Selection processor.
	 * 
	 * @param filter
	 * @param mainCriteriaBuilder
	 */
	protected abstract void doSelect(F filter, CriteriaBuilder mainCriteriaBuilder);
	
	/**
	 * Filter processor.
	 * 
	 * @param filter
	 * @param mainCriteriaBuilder
	 */
	protected abstract void doFilter(F filter, CriteriaBuilder mainCriteriaBuilder);
	
	/**
	 * Filter post-processor.
	 * 
	 * @param filter
	 * @param mainCriteriaBuilder
	 */
	protected void postProcessFilter(F filter, CriteriaBuilder mainCriteriaBuilder) {
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
    
    protected static Log logger = LogFactory.getLog(DefaultCategorySelectionStrategy.class);

}
