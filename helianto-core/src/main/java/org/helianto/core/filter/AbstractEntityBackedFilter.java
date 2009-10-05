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
 * Base class to filters that requires an <code>Entity</code>.
 * 
 * @author Mauricio Fernandes de Castro
 */
@SuppressWarnings("serial")
public abstract class AbstractEntityBackedFilter extends AbstractFilter implements EntityBackedFilter {
	
    private Entity entity;
    
	/**
	 * Default constructor
	 */
	protected AbstractEntityBackedFilter() {
		super();
	}
    
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
	    
	/**
	 * Delegate criteria creation to a chain of processors.
	 */
	@Override
	public String createCriteriaAsString() {
		return createCriteriaAsString(requireEntity());
	}

	/**
	 * Delegate criteria creation to a chain of processors.
	 */
	public final String createCriteriaAsString(boolean requireEntity) {
        CriteriaBuilder mainCriteriaBuilder = new CriteriaBuilder(getObjectAlias());
        if (requireEntity && getEntity()==null) {
            throw new IllegalArgumentException("User or entity required!");
        }
        else if (getEntity()!=null){
            appendEntityFilter(getEntity(), mainCriteriaBuilder);
        }
        return super.createCriteriaAsString(mainCriteriaBuilder);
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
    
    private static Log logger = LogFactory.getLog(AbstractEntityBackedFilter.class);

}
