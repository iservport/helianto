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

import org.helianto.core.Unit;
import org.helianto.core.dao.BasicDao;
import org.helianto.core.test.AbstractDaoIntegrationTest;
import org.helianto.core.test.UnitTestSupport;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultUnitDaoIntegrationTests extends AbstractDaoIntegrationTest {
	
	@Override
	public void testFindUnique() {
		Unit unit = UnitTestSupport.createUnit();
		unitDao.persist(unit);
		assertEquals(unit, unitDao.findUnique(unit.getEntity(), unit.getUnitCode()));
	}

    //- collabs

    private BasicDao<Unit> unitDao;
    
    @Resource(name="unitDao")
    public void setUnitDao(BasicDao<Unit> entityDao) {
        this.unitDao = entityDao;
    }
    
}
