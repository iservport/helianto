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


package org.helianto.process.orm;

import org.helianto.core.dao.BasicDao;
import org.helianto.process.ResourceParameterValue;
import org.helianto.process.test.ResourceParameterValueTestSupport;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultResourceParameterValueDaoIntegrationTests extends AbstractProcessIntegrationTest {
	
	public DefaultResourceParameterValueDaoIntegrationTests() {
		setAutowireMode(AUTOWIRE_BY_NAME);
	}
	
	public void testFindUnique() {
		ResourceParameterValue resourceParameterValue = ResourceParameterValueTestSupport.createResourceParameterValue();
		resourceParameterValueDao.persist(resourceParameterValue);
		assertEquals(resourceParameterValue, resourceParameterValueDao.findUnique(resourceParameterValue.getResource(), resourceParameterValue.getParameter()));
	}

    //- collabs

    private BasicDao<ResourceParameterValue> resourceParameterValueDao;
    
    public void setResourceParameterValueDao(BasicDao<ResourceParameterValue> resourceParameterValueDao) {
        this.resourceParameterValueDao = resourceParameterValueDao;
    }
    
}