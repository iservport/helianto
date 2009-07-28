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

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.helianto.core.dao.BasicDao;
import org.helianto.process.MeasurementTechnique;
import org.helianto.process.test.AbstractProcessDaoIntegrationTest;
import org.helianto.process.test.MeasurementTechniqueTestSupport;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultMeasurementTechniqueIntegrationTests extends AbstractProcessDaoIntegrationTest {
	
	@Override
	public void findUnique() {
		MeasurementTechnique measurementTechnique = MeasurementTechniqueTestSupport.createMeasurementTechnique();
		measurementTechniqueDao.persist(measurementTechnique);
		assertEquals(measurementTechnique, measurementTechniqueDao.findUnique(measurementTechnique.getEntity(), measurementTechnique.getMeasurementTechniqueCode()));
	}

    //- collabs

    private BasicDao<MeasurementTechnique> measurementTechniqueDao;
    
    @Resource(name="measurementTechniqueDao")
    public void setMeasurementTechniqueDao(BasicDao<MeasurementTechnique> measurementTechniqueDao) {
        this.measurementTechniqueDao = measurementTechniqueDao;
    }
    
}
