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
 * @author Mauricio Fernandes de Castro
 */
public class CategoryFilterTests extends TestCase {
	
	public void testConstructor() {
		CategoryFilter categoryFilter = new CategoryFilter();
		assertTrue(categoryFilter instanceof Serializable);
		assertTrue(categoryFilter instanceof UserBackedFilter);
	}
	
	public void testFactory() {
		User user = new User();
		CategoryGroup categoryGroup = CategoryGroup.NOT_DEFINED;
		CategoryFilter categoryFilter = CategoryFilter.categoryFilterFactory(user);
		assertSame(categoryFilter.getUser(), user);
		assertSame(categoryFilter.getCategoryGroup(), categoryGroup);
	}
	
	public void testReset() {
		CategoryFilter categoryFilter = CategoryFilter.categoryFilterFactory(new User());
		categoryFilter.reset();
		assertEquals("", categoryFilter.getCategoryCode());
		assertEquals("", categoryFilter.getCategoryNameLike());
	}

	public void testCategoryCode() {
		CategoryFilter categoryFilter = CategoryFilter.categoryFilterFactory(new User());
		String categoryCode = "CODE";
		categoryFilter.setCategoryCode(categoryCode);
		assertSame(categoryFilter.getCategoryCode(), categoryCode);
	}

	public void testCategoryNameLike() {
		String categoryNameLike = "NAME_LIKE";
		CategoryFilter categoryFilter = CategoryFilter.categoryFilterFactory(new User());
		categoryFilter.setCategoryNameLike(categoryNameLike);
		assertSame(categoryFilter.getCategoryNameLike(), categoryNameLike);
	}
	
}
