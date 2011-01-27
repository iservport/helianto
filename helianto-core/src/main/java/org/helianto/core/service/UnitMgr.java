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

package org.helianto.core.service;

import java.util.List;

import org.helianto.core.Category;
import org.helianto.core.Unit;
import org.helianto.core.filter.Filter;

/**
 * Service interface to units.
 * 
 * @author Maurício Fernandes de Castro
 */
public interface UnitMgr {
	
	/**
	 * List units.
	 * 
	 * @param unitFilter
	 */
	public List<Unit> findUnits(Filter unitFilter);

	/**
	 * Store unit.
	 * 
	 * @param unit
	 */
	public Unit storeUnit(Unit unit);

	/**
	 * Remove unit.
	 * 
	 * @param unit
	 */
	public void removeUnit(Unit unit);
	
	/**
	 * Install a unit if does not exist.
	 * 
	 * @param category
	 * @param unitCode
	 * @param unitName
	 */
	public Unit installUnit(Category category, String unitCode, String unitName);

}
