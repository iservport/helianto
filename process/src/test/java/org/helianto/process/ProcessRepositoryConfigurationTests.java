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
import org.helianto.core.dao.BasicDao;
import org.helianto.core.dao.FilterDao;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.process.test.AbstractProcessDaoIntegrationTest;
import org.helianto.process.test.CharacteristicTestSupport;
import org.helianto.process.test.MeasurementTechniqueTestSupport;
import org.helianto.process.test.OperationTestSupport;
import org.helianto.process.test.ProcessDocumentAssociationTestSupport;
import org.helianto.process.test.ProcessDocumentTestSupport;
import org.helianto.process.test.SetupTestSupport;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
@Transactional
public class ProcessRepositoryConfigurationTests extends AbstractProcessDaoIntegrationTest {

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
		ProcessDocument processDocument = ProcessDocumentTestSupport.createProcessDocument();
		processDocumentDao.persist(processDocument);
		ProcessDocumentAssociation target = ProcessDocumentAssociationTestSupport.createDocumentAssociation(processDocument);
		assertEquals(processDocumentAssociationDao.merge(target), processDocumentAssociationDao.findUnique(target.getParent(), target.getChild()));
	}
	
	@Resource FilterDao<ProcessDocument, ProcessDocumentFilter> processDocumentDao;
	@Test
	public void processDocument() {
		ProcessDocument target = ProcessDocumentTestSupport.createProcessDocument();
		assertEquals(processDocumentDao.merge(target), processDocumentDao.findUnique(target.getEntity(), target.getDocCode()));
	}
	// subclasses
	@Test
	public void characteristic() {
		Characteristic target = CharacteristicTestSupport.createCharacteristic();
		assertEquals(processDocumentDao.merge(target), processDocumentDao.findUnique(target.getEntity(), target.getDocCode()));
	}
	@Test
	public void operation() {
		Operation target = OperationTestSupport.createOperation();
		assertEquals(processDocumentDao.merge(target), processDocumentDao.findUnique(target.getEntity(), target.getDocCode()));
	}

	@Resource BasicDao<Setup> setupDao;
	@Test
	public void setup() {
		Setup target = SetupTestSupport.createSetup();
		assertEquals(setupDao.merge(target), setupDao.findUnique(target.getOperation(), target.getResource()));
	}
	
}
