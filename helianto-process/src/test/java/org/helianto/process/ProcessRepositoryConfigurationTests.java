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

package org.helianto.process;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.helianto.core.Entity;
import org.helianto.core.repository.BasicDao;
import org.helianto.core.repository.FilterDao;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.core.test.TopLevelNumberedEntityTestSupport;
import org.helianto.process.test.AbstractProcessDaoIntegrationTest;
import org.helianto.process.test.CharacteristicTestSupport;
import org.helianto.process.test.MeasurementTechniqueTestSupport;
import org.helianto.process.test.OperationTestSupport;
import org.helianto.process.test.ProcessDocumentAssociationTestSupport;
import org.helianto.process.test.ProcessDocumentTestSupport;
import org.helianto.process.test.SetupTestSupport;
import org.helianto.resource.ResourceGroup;
import org.helianto.resource.test.ResourceGroupTestSupport;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
@Transactional
public class ProcessRepositoryConfigurationTests extends AbstractProcessDaoIntegrationTest {

	@Resource FilterDao<Cause, CauseFilter> causeDao;
	@Test
	public void cause() {
		Entity entity = entityDao.merge(EntityTestSupport.createEntity());
		Cause target = TopLevelNumberedEntityTestSupport.create(Cause.class, entity);
		assertEquals(causeDao.merge(target), causeDao.findUnique(target.getEntity(), target.getInternalNumber()));
	}

	@Resource FilterDao<MeasurementTechnique, MeasurementTechniqueFilter> measurementTechniqueDao;
	@Test
	public void measurementTechnique() {
		Entity entity = entityDao.merge(EntityTestSupport.createEntity());
		MeasurementTechnique target = MeasurementTechniqueTestSupport.createMeasurementTechnique(entity);
		assertEquals(measurementTechniqueDao.merge(target), measurementTechniqueDao.findUnique(target.getEntity(), target.getMeasurementTechniqueCode()));
	}

	@Resource BasicDao<ProcessDocumentAssociation> processDocumentAssociationDao;
	@Test
	public void processDocumentAssociation() {
		Entity entity = entityDao.merge(EntityTestSupport.createEntity());
		ProcessDocument processDocument = ProcessDocumentTestSupport.createProcessDocument(entity);
		processDocumentDao.persist(processDocument);
		ProcessDocumentAssociation target = ProcessDocumentAssociationTestSupport.createDocumentAssociation(processDocument);
		assertEquals(processDocumentAssociationDao.merge(target), processDocumentAssociationDao.findUnique(target.getParent(), target.getChild()));
	}
	
	@Resource FilterDao<ProcessDocument, ProcessDocumentFilter> processDocumentDao;
	@Resource BasicDao<ResourceGroup> resourceGroupDao;
	@Resource BasicDao<Setup> setupDao;
	@Test
	public void processDocument() {
		Entity entity = entityDao.merge(EntityTestSupport.createEntity());
		ProcessDocument processDocument = ProcessDocumentTestSupport.createProcessDocument(entity);
		assertEquals(processDocumentDao.merge(processDocument), processDocumentDao.findUnique(processDocument.getEntity(), processDocument.getDocCode()));

		Characteristic characteristic = CharacteristicTestSupport.createCharacteristic(entity);
		assertEquals(processDocumentDao.merge(characteristic), processDocumentDao.findUnique(characteristic.getEntity(), characteristic.getDocCode()));

		Operation operation = (Operation) processDocumentDao.merge(OperationTestSupport.createOperation(entity));
		assertEquals(operation, processDocumentDao.findUnique(operation.getEntity(), operation.getDocCode()));

		ResourceGroup resource = resourceGroupDao.merge(ResourceGroupTestSupport.createResourceGroup(entity));
		Setup setup = setupDao.merge(SetupTestSupport.createSetup(operation, resource));
		assertEquals(setup, setupDao.findUnique(setup.getOperation(), setup.getResource()));
	}
	
}
