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

package org.helianto.process.repository;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.helianto.core.repository.FilterDao;
import org.helianto.process.domain.MeasurementTechnique;
import org.helianto.process.domain.ProcessDocument;
import org.helianto.process.domain.ProcessDocumentAssociation;
import org.helianto.process.domain.Setup;
import org.helianto.process.domain.classic.Cause;
import org.helianto.process.test.AbstractProcessDaoIntegrationTest;
import org.helianto.resource.domain.ResourceGroup;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
@Transactional
public class ProcessRepositoryConfigurationTests extends AbstractProcessDaoIntegrationTest {

	@Resource FilterDao<MeasurementTechnique> measurementTechniqueDao;
	@Resource FilterDao<ProcessDocumentAssociation> processDocumentAssociationDao;
	@Resource FilterDao<ProcessDocument> processDocumentDao;
	@Resource FilterDao<ResourceGroup> resourceGroupDao;
	@Resource FilterDao<Setup> setupDao;

	@Test
	public void process() {
		MeasurementTechnique measurementTechnique = new MeasurementTechnique(entity, "CODE");
		assertEquals(measurementTechniqueDao.merge(measurementTechnique), measurementTechniqueDao.findUnique(entity, "CODE"));

		ProcessDocument processDocument = new ProcessDocument(entity, "DOCCODE");
		processDocumentDao.saveOrUpdate(processDocument);
		assertEquals(processDocument, processDocumentDao.findUnique(processDocument.getEntity(), processDocument.getDocCode()));

//		ProcessDocumentAssociation processDocumentAssociation = ProcessDocumentAssociationTestSupport.createDocumentAssociation(processDocument);
//		assertEquals(processDocumentAssociationDao.merge(processDocumentAssociation), processDocumentAssociationDao.findUnique(processDocumentAssociation.getParent(), processDocumentAssociation.getChild()));
//
//		Characteristic characteristic = CharacteristicTestSupport.createCharacteristic(entity);
//		assertEquals(processDocumentDao.merge(characteristic), processDocumentDao.findUnique(characteristic.getEntity(), characteristic.getDocCode()));
//
//		Operation operation = (Operation) processDocumentDao.merge(OperationTestSupport.createOperation(entity));
//		assertEquals(operation, processDocumentDao.findUnique(operation.getEntity(), operation.getDocCode()));
//
//		ResourceGroup resource = resourceGroupDao.merge(ResourceGroupTestSupport.createResourceGroup(entity));
//		Setup setup = setupDao.merge(SetupTestSupport.createSetup(operation, resource));
//		assertEquals(setup, setupDao.findUnique(setup.getOperation(), setup.getResource()));
	}
	
}
