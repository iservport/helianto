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

import javax.annotation.Resource;
import static org.junit.Assert.assertEquals;

import org.helianto.core.Entity;
import org.helianto.core.dao.BasicDao;
import org.helianto.core.test.EntityTestSupport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Mauricio Fernandes de Castro
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/META-INF/spring/dataSource.xml", "classpath:/META-INF/spring/data.xml", "classpath:/META-INF/spring/core-context.xml"})
@TestExecutionListeners(value = {TransactionalTestExecutionListener.class, DependencyInjectionTestExecutionListener.class})
public class DefaultEntityDaoIntegrationTests  {
	
	@Test
	@Transactional
	public void testFindUnique() {
		Entity entity = EntityTestSupport.createEntity();
		entityDao.persist(entity);
		assertEquals(entity, entityDao.findUnique(entity.getOperator(), entity.getAlias()));
	}

    //- collabs

    private BasicDao<Entity> entityDao;
    
    @Resource(name="entityDao")
    public void setEntityDao(BasicDao<Entity> entityDao) {
        this.entityDao = entityDao;
    }
    
}
