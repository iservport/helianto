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


package org.helianto.resource.repository;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.helianto.core.dao.BasicDao;
import org.helianto.resource.ResourceParameterValue;
import org.helianto.resource.test.AbstractResourceDaoIntegrationTest;
import org.helianto.resource.test.ResourceParameterValueTestSupport;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultResourceParameterValueDaoIntegrationTests extends AbstractResourceDaoIntegrationTest {
	
	@Override
	public void findUnique() {
		ResourceParameterValue resourceParameterValue = ResourceParameterValueTestSupport.createResourceParameterValue();
		resourceParameterValueDao.persist(resourceParameterValue);
		assertEquals(resourceParameterValue, resourceParameterValueDao.findUnique(resourceParameterValue.getResource(), resourceParameterValue.getParameter()));
	}

    //- collabs

    private BasicDao<ResourceParameterValue> resourceParameterValueDao;
    
    @Resource(name="resourceParameterValueDao")
    public void setResourceParameterValueDao(BasicDao<ResourceParameterValue> resourceParameterValueDao) {
        this.resourceParameterValueDao = resourceParameterValueDao;
    }
    
}
