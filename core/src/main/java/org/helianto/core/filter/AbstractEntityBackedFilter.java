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
import org.helianto.core.Entity;

/**
 * Base class to filters that requires an <code>Entity</code>.
 * 
 * @author Mauricio Fernandes de Castro
 */
@SuppressWarnings("serial")
public abstract class AbstractEntityBackedFilter implements Serializable {
	
    private Entity entity;
    
	/**
	 * Default constructor
	 */
	protected AbstractEntityBackedFilter() {}
    
	/**
	 * Entity based constructor.
	 */
	protected AbstractEntityBackedFilter(Entity entity) {
		this();
		setEntity(entity);
	}
    
    public Entity getEntity() {
        return entity;
    }
    public void setEntity(Entity entity) {
        this.entity = entity;
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
	
    public abstract void reset();
    
    /**
     * Allow filters to be either in selection state (true) 
     * or in filter state (false).
     * 
     */
    public abstract boolean isSelection();

	/**
	 * Hook to customize persistent object alias.
	 */
	public abstract String getObjectAlias();

	/**
	 * Delegate criteria creation to a chain of processors.
	 */
	public String createCriteriaAsString() {
		return createCriteriaAsString(requireEntity());
	}

	/**
	 * Delegate criteria creation to a chain of processors.
	 */
	public final String createCriteriaAsString(boolean requireEntity) {
        CriteriaBuilder mainCriteriaBuilder = createCriteriaBuilder(getObjectAlias());
        if (requireEntity && getEntity()==null) {
            throw new IllegalArgumentException("User or entity required!");
        }
        else if (getEntity()!=null){
            appendEntityFilter(getEntity(), mainCriteriaBuilder);
        }
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
	 * Create the the builder.
	 */
	protected CriteriaBuilder createCriteriaBuilder(String objectAlias) {
        return new CriteriaBuilder(objectAlias);
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
    
    private static Log logger = LogFactory.getLog(AbstractEntityBackedFilter.class);

}
