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

package org.helianto.core;

import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Unit;

/**
 * Service interface to units.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface UnitMgr {
	
	/**
	 * Find unit by unique key.
	 * 
	 * @param entity
	 * @param unitCode
	 */
	Unit findUnit(Entity entity, String unitCode);

	/**
	 * Store unit.
	 * 
	 * @param unit
	 */
	Unit storeUnit(Unit unit);

	/**
	 * Remove unit.
	 * 
	 * @param unit
	 */
	void removeUnit(Unit unit);
	
	/**
	 * Install a unit if does not exist.
	 * 
	 * @param entity
	 * @param unitCode
	 * @param unitSymbol
	 * @param unitName
	 */
	Unit installUnit(Entity entity, String unitCode, String unitSymbol, String unitName);

}
