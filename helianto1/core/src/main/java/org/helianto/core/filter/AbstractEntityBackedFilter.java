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

import org.helianto.core.Entity;

/**
 * Base class to filters that requires an <code>Entity</code>.
 * 
 * @author Mauricio Fernandes de Castro
 */
@SuppressWarnings("serial")
public abstract class AbstractEntityBackedFilter implements Serializable {
	
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
    
    private Entity entity;
    
    public Entity getEntity() {
        return entity;
    }
    public void setEntity(Entity entity) {
        this.entity = entity;
    }
    
    /**
     * Allow filters to be either in selection state (true) 
     * or in filter state (false).
     * 
     * <p>
     * By default, filter is in filter state (does not return 
     * an unique result).
     * </p>
     */
    public boolean isSelection() {
    	return false;
    }

}
