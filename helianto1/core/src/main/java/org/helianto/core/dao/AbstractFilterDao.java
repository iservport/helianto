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

/**
 * Default implementation to <code>FilterDao</code> interface.
 * 
 * <p>
 * This implementation recognizes filter states to be:
 * </p>
 * <ul>
 * <li>selection: an unique result is expected, or </li>
 * <li>filter: any result set constrained by the filter.</li>
 * </ul> 
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractFilterDao<T, F extends AbstractUserBackedCriteriaFilter> extends AbstractBasicDao<T> implements FilterDao<T, F> {

	/* @see FilterDao interface */
	public Collection<T> find(F filter) {
		String whereClause = createCriteriaAsString(filter);
		return super.find(getSelectBuilder(), whereClause);
	}

	/**
	 * If true, raise exception when entity is null. 
	 * 
	 * <p>
	 * Subclasses overriding this method can control how the the result set 
	 * spans beyond the entity namespace boundaries. However, returning false
	 * here is recommended only for a few top-level domain classes.
	 * </p>
	 */
	protected boolean requireEntity() {
		return true;
	}
	
	/**
	 * Delegate criteria creation to a chain of processors.
	 */
	protected String createCriteriaAsString(F filter) {
		return createCriteriaAsString(filter, requireEntity());
	}

	/**
	 * Delegate criteria creation to a chain of processors.
	 */
	public final String createCriteriaAsString(F filter, boolean requireEntity) {
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
        	filter.reset();
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
	 * Create the the builder.
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
	 * Subclasses overriding this method should create here query segments
	 * to be included for both selection and filter operations.
	 * 
	 * @param filter
	 * @param mainCriteriaBuilder
	 */
	protected void preProcessFilter(F filter, CriteriaBuilder mainCriteriaBuilder) {
	}
	
	/**
	 * True when filter should return an unique result.
	 * 
	 * @param filter
	 */
	protected boolean isSelection(F filter) {
		return filter.isSelection();
	}
	
	/**
	 * Hook to the selection processor.
	 * 
	 * @param filter
	 * @param mainCriteriaBuilder
	 */
	protected abstract void doSelect(F filter, CriteriaBuilder mainCriteriaBuilder);
	
	/**
	 * Hook to the filter processor.
	 * 
	 * @param filter
	 * @param mainCriteriaBuilder
	 */
	protected abstract void doFilter(F filter, CriteriaBuilder mainCriteriaBuilder);
	
	/**
	 * Hook to the filter post-processor.
	 * 
	 * @param filter
	 * @param mainCriteriaBuilder
	 */
	protected void postProcessFilter(F filter, CriteriaBuilder mainCriteriaBuilder) {
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
    
    protected static Log logger = LogFactory.getLog(AbstractFilterDao.class);

}
