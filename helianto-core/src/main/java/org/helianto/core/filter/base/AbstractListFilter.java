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

package org.helianto.core.filter.base;

import java.util.List;

import org.helianto.core.domain.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class implementing <code>ListFilter</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
@SuppressWarnings("serial")
public abstract class AbstractListFilter extends AbstractFilter {
	
	private List<?> list;
    private Entity entity;
	private int index = 0;

	/**
	 * Entity filter.
	 */
    public Entity getEntity() {
        return entity;
    }
    public void setEntity(Entity entity) {
        this.entity = entity;
    }
    
	/**
	 * Entity as string alias.
	 */
    public String getEntityAsStringAlias() {
    	if (getEntity()!=null) {
    		return getEntity().getAlias();
    	}
        return "";
    }

    /**
	 * The resulting list.
	 */
	public List<?> getList() {
		return list;
	}
	public void setList(List<?> list) {
		this.list = list;
	}
	
	/**
	 * Resulting list size.
	 */
	public int getListSize() {
		if (getList()!=null) {
			return getList().size();
		}
		return 0;
	}
	
	/**
	 * The list index.
	 */
	public int getIndex() {
		return index;
	}
	/**
	 * The list index setter, allowed in the range -1 to list size.
	 */
	public void setIndex(int index) {
		if (index >= -1 && index < getListSize()) {
			this.index = index;
		}
		logger.debug("Index is {}.", index);
	}
	
	/**
	 * True if resulting list size greater than zero.
	 */
	public boolean hasContents() {
		return getListSize() > 0;
	}
	
	/**
	 * True if resulting list has a previous item.
	 */
	public boolean hasPrevious() {
		return hasContents() && getIndex() > 0;
	}
	
	/**
	 * True if resulting list has a next item.
	 */
	public boolean hasNext() {
		return hasContents() && getIndex() < getListSize() - 1;
	}
	
	/**
	 * Current item.
	 */
	public Object getItem() {
		if (hasContents() && !isClear()) {
			return getList().get(getIndex());
		}
		return null;
	}
	
	/**
	 * Increase index by one, if has next, to return the next item.
	 */
	public Object next() {
		if (hasNext() && !isClear()) {
			setIndex(index+1);
		}
		return getItem();
	}
	
	/**
	 * Decrease index by one, if has previous, to return the previous item.
	 */
	public Object previous() {
		if (hasPrevious() && !isClear()) {
			setIndex(index-1);
		}
		return getItem();
	}
	
	/**
	 * True if getItem() return null.
	 */
	public boolean isClear() {
		return getIndex() < 0;
	}
	
    private static Logger logger = LoggerFactory.getLogger(AbstractListFilter.class);

}
