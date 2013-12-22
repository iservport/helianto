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
import static org.junit.Assert.assertSame;

import org.helianto.core.domain.Category;
import org.helianto.core.repository.CategoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class CategoryMgrImplTests {
    
    private CategoryMgrImpl categoryMgr;
    
    @Test
    public void storeCategory() {
    	Category category = new Category();
    	
    	expect(categoryRepository.saveAndFlush(category)).andReturn(category);
    	replay(categoryRepository);
    	
    	assertSame(category, categoryMgr.storeCategory(category));
    	verify(categoryRepository);
    }
    
    private CategoryRepository categoryRepository;
    
	@Before
    public void setUp() {
        categoryMgr = new CategoryMgrImpl();
        categoryRepository = createMock(CategoryRepository.class);
        categoryMgr.setCategoryRepository(categoryRepository);
    }
    
    @After
    public void tearDown() {
        reset(categoryRepository);
    }
    
}
