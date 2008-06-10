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

import java.io.Serializable;

import junit.framework.TestCase;

import org.helianto.core.filter.UserBackedFilter;

/**
 * 
 * @author Maurício Fernandes de Castro
 */
public class UnitFilterTests extends TestCase {
	
	public void testConstructor() {
		UnitFilter unitFilter = new UnitFilter();
		assertTrue(unitFilter instanceof Serializable);
		assertTrue(unitFilter instanceof UserBackedFilter);
	}
	
	public void testFactory() {
		User user = new User();
		UnitFilter unitFilter = UnitFilter.unitFilterFactory(user);
		assertSame(unitFilter.getUser(), user);
	}
	
	public void testReset() {
		UnitFilter unitFilter = UnitFilter.unitFilterFactory(new User());
		assertNull(unitFilter.getUnitCode());
		assertNull(unitFilter.getUnitNameLike());
		unitFilter.reset();
		assertEquals("", unitFilter.getUnitCode());
		assertEquals("", unitFilter.getUnitNameLike());
	}

	public void testCategory() {
		Category category = new Category();
		UnitFilter unitFilter = UnitFilter.unitFilterFactory(new User());
		unitFilter.setCategory(category);
		assertSame(unitFilter.getCategory(), category);
	}

	public void testUnitCode() {
		String unitCode = "CODE";
		UnitFilter unitFilter = UnitFilter.unitFilterFactory(new User());
		unitFilter.setUnitCode(unitCode);
		assertSame(unitFilter.getUnitCode(), unitCode);
	}

	public void testUnitNameLike() {
		String unitNameLike = "NAME_LIKE";
		UnitFilter unitFilter = UnitFilter.unitFilterFactory(new User());
		unitFilter.setUnitNameLike(unitNameLike);
		assertSame(unitFilter.getUnitNameLike(), unitNameLike);
	}
	
}
