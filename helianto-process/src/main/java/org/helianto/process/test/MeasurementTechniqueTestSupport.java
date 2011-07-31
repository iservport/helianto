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

package org.helianto.process.test;

import org.helianto.core.Entity;
import org.helianto.core.test.DomainTestSupport;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.process.MeasurementTechnique;

/**
 * Measurement technique test support.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class MeasurementTechniqueTestSupport {

	private static int testKey = 0;
	
	/**
	 * Create sample.
	 */
	public static MeasurementTechnique createMeasurementTechnique() {
		Entity entity = EntityTestSupport.createEntity();
		return MeasurementTechniqueTestSupport.createMeasurementTechnique(entity);
	}

	/**
	 * Create sample.
	 * 
	 * @param entity
	 */
	public static MeasurementTechnique createMeasurementTechnique(Entity entity) {
		String measurementTechniqueCode = DomainTestSupport.getNonRepeatableStringValue(testKey++);
		return MeasurementTechniqueTestSupport.createMeasurementTechnique(entity, measurementTechniqueCode);
	}

	/**
	 * Create sample.
	 * 
	 * @param entity
	 * @param measurementTechniqueCode
	 */
	public static MeasurementTechnique createMeasurementTechnique(Entity entity, String measurementTechniqueCode) {
		MeasurementTechnique sample =  new MeasurementTechnique(entity, measurementTechniqueCode);
		return sample;
	}

}
