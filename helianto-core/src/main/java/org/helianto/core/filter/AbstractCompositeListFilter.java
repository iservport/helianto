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

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class implementing <code>CompositeListFilter</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
@SuppressWarnings("serial")
public abstract class AbstractCompositeListFilter extends AbstractListFilter implements CompositeListFilter {
	
	private ListFilter parentFilter;
	
	public ListFilter getParentFilter() {
		return parentFilter;
	}
	public void setParentFilter(ListFilter parentFilter) {
		logger.debug("Parent filter set to {}.", parentFilter);
		this.parentFilter = parentFilter;
	}

	public List<?> getParentList() {
		return getParentFilter().getList();
	}
	public void setParentList(List<?> itemList) {
		getParentFilter().setList(itemList);
	}
	
	public int getParentIndex() {
		return getParentFilter().getIndex();
	}
	public void setParentIndex(int index) {
		getParentFilter().setIndex(index);
	}
	
	public Object getParentItem() {
		return getParentFilter().getItem();
	}
	
	public int getParentListSize() {
		return getParentFilter().getListSize();
	}
	
	public boolean isParentClear() {
		return getParentFilter().isClear();
	}
	
	public Object nextParent() {
		return getParentFilter().next();
	}
	
	public Object previousParent() {
		return getParentFilter().previous();
	}

    private static Logger logger = LoggerFactory.getLogger(AbstractCompositeListFilter.class);

}
