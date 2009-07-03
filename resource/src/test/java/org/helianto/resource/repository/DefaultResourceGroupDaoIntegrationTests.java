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
import org.helianto.resource.ResourceGroup;
import org.helianto.resource.test.AbstractResourceDaoIntegrationTest;
import org.helianto.resource.test.ResourceGroupTestSupport;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultResourceGroupDaoIntegrationTests extends AbstractResourceDaoIntegrationTest {
	
	@Override
	public void testFindUnique() {
		ResourceGroup resourceGroup = ResourceGroupTestSupport.createResourceGroup();
		resourceGroupDao.persist(resourceGroup);
		assertEquals(resourceGroup, resourceGroupDao.findUnique(resourceGroup.getEntity(), resourceGroup.getResourceCode()));
	}

    //- collabs

    private BasicDao<ResourceGroup> resourceGroupDao;
    
    @Resource(name="resourceGroupDao")
    public void setResourceGroupDao(BasicDao<ResourceGroup> resourceGroupDao) {
        this.resourceGroupDao = resourceGroupDao;
    }
    
}
