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


package org.helianto.core.orm;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.helianto.core.Category;
import org.helianto.core.dao.BasicDao;
import org.helianto.core.test.CategoryTestSupport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Mauricio Fernandes de Castro
 */
@RunWith(BlockJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/META-INF/spring/dataSource.xml", "classpath:/META-INF/spring/data.xml", "classpath:/META-INF/spring/core-context.xml"})
public class DefaultCategoryDaoIntegrationTests  {
	
	@Test
	public void testFindUnique() {
		Category category = CategoryTestSupport.createCategory();
		categoryDao.persist(category);
		assertEquals(category, categoryDao.findUnique(category.getEntity(), category.getCategoryGroup()));
	}

    //- collabs

    private BasicDao<Category> categoryDao;
    
    @Resource(name="categoryDao")
    public void setCategoryDao(BasicDao<Category> categoryDao) {
        this.categoryDao = categoryDao;
    }
    
}
