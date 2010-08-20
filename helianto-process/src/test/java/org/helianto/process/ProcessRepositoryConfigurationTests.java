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

import org.helianto.core.repository.BasicDao;
import org.helianto.core.repository.FilterDao;
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
	@Resource FilterDao<MeasurementTechnique, MeasurementTechniqueFilter> measurementTechniqueDao;
	@Resource BasicDao<ProcessDocumentAssociation> processDocumentAssociationDao;
	@Resource FilterDao<ProcessDocument, ProcessDocumentFilter> processDocumentDao;
	@Resource BasicDao<ResourceGroup> resourceGroupDao;
	@Resource BasicDao<Setup> setupDao;

	@Test
	public void process() {
		Cause cause = TopLevelNumberedEntityTestSupport.create(Cause.class, entity);
		assertEquals(causeDao.merge(cause), causeDao.findUnique(cause.getEntity(), cause.getInternalNumber()));

		MeasurementTechnique measurementTechnique = MeasurementTechniqueTestSupport.createMeasurementTechnique(entity);
		assertEquals(measurementTechniqueDao.merge(measurementTechnique), measurementTechniqueDao.findUnique(measurementTechnique.getEntity(), measurementTechnique.getMeasurementTechniqueCode()));

		ProcessDocument processDocument = ProcessDocumentTestSupport.createProcessDocument(entity);
		assertEquals(processDocumentDao.merge(processDocument), processDocumentDao.findUnique(processDocument.getEntity(), processDocument.getDocCode()));

		ProcessDocumentAssociation processDocumentAssociation = ProcessDocumentAssociationTestSupport.createDocumentAssociation(processDocument);
		assertEquals(processDocumentAssociationDao.merge(processDocumentAssociation), processDocumentAssociationDao.findUnique(processDocumentAssociation.getParent(), processDocumentAssociation.getChild()));

		Characteristic characteristic = CharacteristicTestSupport.createCharacteristic(entity);
		assertEquals(processDocumentDao.merge(characteristic), processDocumentDao.findUnique(characteristic.getEntity(), characteristic.getDocCode()));

		Operation operation = (Operation) processDocumentDao.merge(OperationTestSupport.createOperation(entity));
		assertEquals(operation, processDocumentDao.findUnique(operation.getEntity(), operation.getDocCode()));

		ResourceGroup resource = resourceGroupDao.merge(ResourceGroupTestSupport.createResourceGroup(entity));
		Setup setup = setupDao.merge(SetupTestSupport.createSetup(operation, resource));
		assertEquals(setup, setupDao.findUnique(setup.getOperation(), setup.getResource()));
	}
	
}
