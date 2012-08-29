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

import org.helianto.core.repository.BasicDao;
import org.helianto.core.repository.FilterDao;
import org.helianto.resource.domain.ResourceGroup;
import org.helianto.resource.domain.classic.ResourceAssociation;
import org.helianto.resource.domain.classic.ResourceParameter;
import org.helianto.resource.domain.classic.ResourceParameterValue;
import org.helianto.resource.test.AbstractResourceDaoIntegrationTest;
import org.helianto.resource.test.ResourceGroupTestSupport;
import org.helianto.resource.test.ResourceParameterTestSupport;
import org.helianto.resource.test.ResourceParameterValueTestSupport;
import org.helianto.resource.test.ResourceTestSupport;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;


/**
 * 
 * @author Mauricio Fernandes de Castro
 */
@Transactional
public class ResourceRepositoryIntegrationTests extends AbstractResourceDaoIntegrationTest {

	@javax.annotation.Resource BasicDao<ResourceAssociation> resourceAssociationDao;
	@javax.annotation.Resource FilterDao<ResourceGroup> resourceGroupDao;
	@javax.annotation.Resource FilterDao<ResourceParameter> resourceParameterDao;
	@javax.annotation.Resource BasicDao<ResourceParameterValue> resourceParameterValueDao;
	
	@Test
	public void resource() {
		
		ResourceGroup parent = resourceGroupDao.merge(ResourceGroupTestSupport.createResourceGroup(entity));
		assertEquals(parent, resourceGroupDao.findUnique(parent.getEntity(), parent.getResourceCode()));

		ResourceAssociation resourceAssociation = new ResourceAssociation();
		resourceAssociation.setParent(parent);
		resourceAssociation.setChild(ResourceTestSupport.createResource(entity));
		assertEquals(resourceAssociationDao.merge(resourceAssociation), resourceAssociationDao.findUnique(resourceAssociation.getParent(), resourceAssociation.getChild()));

		ResourceParameter resourceParameter = ResourceParameterTestSupport.createResourceParameter(entity);
		assertEquals(resourceParameterDao.merge(resourceParameter), resourceParameterDao.findUnique(resourceParameter.getEntity(), resourceParameter.getParameterCode()));

		ResourceParameterValue resourceParameterValue = ResourceParameterValueTestSupport.createResourceParameterValue(parent, resourceParameter);
		assertEquals(resourceParameterValueDao.merge(resourceParameterValue), resourceParameterValueDao.findUnique(resourceParameterValue.getResource(), resourceParameterValue.getParameter()));
	}

}
