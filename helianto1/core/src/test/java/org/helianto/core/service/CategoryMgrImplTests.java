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

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.helianto.core.Category;
import org.helianto.core.CategoryFilter;
import org.helianto.core.dao.CategoryDao;
import org.helianto.core.dao.CategorySelectionStrategy;

public class CategoryMgrImplTests extends TestCase {
    
    private CategoryMgrImpl categoryMgr;
    
    public void testFindCategories() {
    	CategoryFilter categoryFilter = new CategoryFilter();
    	List<Category> categoryList = new ArrayList<Category>();
    	String criteria = "TEST";
    	
    	expect(categorySelectionStrategy.createCriteriaAsString(categoryFilter, "user")).andReturn(criteria);
    	replay(categorySelectionStrategy);

    	expect(categoryDao.findCategories(criteria)).andReturn(categoryList);
    	replay(categoryDao);
    	
    	assertSame(categoryList, categoryMgr.findCategories(categoryFilter));
    	verify(categorySelectionStrategy);
    	verify(categoryDao);
    }
    
    public void testStoreCategory() {
    	Category category = new Category();
    	Category managedCategory = new Category();
    	
    	expect(categoryDao.mergeCategory(category)).andReturn(managedCategory);
    	replay(categoryDao);
    	
    	assertSame(managedCategory, categoryMgr.storeCategory(category));
    	verify(categoryDao);
    }
    
    private CategoryDao categoryDao;
    private CategorySelectionStrategy categorySelectionStrategy;
    
    @Override
    public void setUp() {
        categoryMgr = new CategoryMgrImpl();
        categoryDao = createMock(CategoryDao.class);
        categoryMgr.setCategoryDao(categoryDao);
        categorySelectionStrategy = createMock(CategorySelectionStrategy.class);
        categoryMgr.setCategorySelectionStrategy(categorySelectionStrategy);
    }
    
    @Override
    public void tearDown() {
        reset(categoryDao);
        reset(categorySelectionStrategy);
    }
    
}
