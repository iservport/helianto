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

import org.helianto.core.repository.AbstractRepositoryConfiguration;
import org.helianto.core.repository.FilterDao;
import org.helianto.process.Cause;
import org.helianto.process.MeasurementTechnique;
import org.helianto.process.ProcessDocument;
import org.helianto.process.ProcessDocumentAssociation;
import org.helianto.process.Setup;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Java config to the repository.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Configuration
public class ProcessRepositoryConfiguration extends AbstractRepositoryConfiguration {

	/**
	 * Cause data access.
	 */
	@Bean
	public FilterDao<Cause> causeDao() {
		return getFilterDao(Cause.class);
	}

	/**
	 * Measurement technique data access.
	 */
	@Bean
	public FilterDao<MeasurementTechnique> measurementTechniqueDao() {
		return getFilterDao(MeasurementTechnique.class, "entity", "measurementTechniqueCode");
	}

	/**
	 * Process document association data access.
	 */
	@Bean
	public FilterDao<ProcessDocumentAssociation> processDocumentAssociationDao() {
		return getFilterDao(ProcessDocumentAssociation.class, "parent", "child");
	}

	/**
	 * Process document data access.
	 */
	@Bean
	public FilterDao<ProcessDocument> processDocumentDao() {
		return getFilterDao(ProcessDocument.class, "entity", "docCode");
	}

	/**
	 * Setup data access.
	 */
	@Bean
	public FilterDao<Setup> setupDao() {
		return getFilterDao(Setup.class, "operation", "resource");
	}

}
