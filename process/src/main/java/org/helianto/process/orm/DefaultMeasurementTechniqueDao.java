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

import org.helianto.core.dao.AbstractHibernateFilterDao;
import org.helianto.process.MeasurementTechnique;
import org.helianto.process.MeasurementTechniqueFilter;
import org.springframework.stereotype.Repository;


/**
 * Measurement technique data access.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Repository("measurementTechniqueDao")
public class DefaultMeasurementTechniqueDao extends AbstractHibernateFilterDao<MeasurementTechnique, MeasurementTechniqueFilter> {

	@Override
	protected String[] getParams() {
		return new String[] { "entity", "measurementTechniqueCode" };
	}

	@Override
	public Class<? extends MeasurementTechnique> getClazz() {
		return MeasurementTechnique.class;
	}

}
