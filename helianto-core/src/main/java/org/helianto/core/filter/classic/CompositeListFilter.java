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

package org.helianto.core.filter.classic;

import java.util.List;

import org.helianto.core.filter.ListFilter;

/**
 * Extends {@link ListFilter} to add composite list behavior.
 *  
 * @author Mauricio Fernandes de Castro
 * @deprecated
 */
public interface CompositeListFilter extends ListFilter {
	
	/**
	 * Parent filter getter.
	 */
	public ListFilter getParentFilter();
	
	/**
	 * Parent filter setter.
	 * 
	 * @param parentFilter
	 */
	public void setParentFilter(ListFilter parentFilter);
	
	/**
	 * Parent list getter.
	 */
	public List<?> getParentList();

	/**
	 * Parent list setter.
	 * 
	 * @param itemList
	 */
	public void setParentList(List<?> itemList);

	/**
	 * Resulting parent list size.
	 */
	public int getParentListSize();
	
	/**
	 * Parent list index getter.
	 */
	public int getParentIndex();
	
	/**
	 * Parent list index setter.
	 * 
	 * @param index
	 */
	public void setParentIndex(int index);
	
	/**
	 * Current parent.
	 */
	public Object getParentItem();
	
	/**
	 * Increase index by one, if has next, to return the next parent.
	 */
	public Object nextParent();
	
	/**
	 * Decrease index by one, if has previous, to return the previous parent.
	 */
	public Object previousParent();
	
	/**
	 * True if getParentItem() return null.
	 */
	public boolean isParentClear();
	
}
