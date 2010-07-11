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

/**
 * Extends {@link Filter} to add list behavior.
 *  
 * @author Mauricio Fernandes de Castro
 */
public interface ListFilter extends Filter {
	
	/**
	 * Item list getter.
	 */
	public List<?> getList();

	/**
	 * Item list setter.
	 * 
	 * @param itemList
	 */
	public void setList(List<?> itemList);

	/**
	 * Resulting list size.
	 */
	public int getListSize();
	
	/**
	 * List index getter.
	 */
	public int getIndex();
	
	/**
	 * List index setter.
	 * 
	 * @param index
	 */
	public void setIndex(int index);
	
	/**
	 * Current item.
	 */
	public Object getItem();
	
	/**
	 * Increase index by one, if has next, to return the next item.
	 */
	public Object next();
	
	/**
	 * Decrease index by one, if has previous, to return the previous item.
	 */
	public Object previous();
	
	/**
	 * True if getItem() return null.
	 */
	public boolean isClear();
	
}
