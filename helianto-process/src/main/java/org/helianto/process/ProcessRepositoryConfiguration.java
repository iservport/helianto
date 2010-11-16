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

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.helianto.core.filter.AbstractFilter;
import org.helianto.core.repository.BasicDao;
import org.helianto.core.repository.FilterDao;
import org.helianto.core.repository.RepositoryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Java config to the repository.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Configuration
@SuppressWarnings("unchecked")
public class ProcessRepositoryConfiguration {

	/**
	 * Cause data access.
	 */
	@Bean
	public FilterDao<Cause, CauseFilter> causeDao() {
		FilterDao<Cause, CauseFilter> dao =  
			repositoryFactory.filterDaoFactory(Cause.class, CauseFilter.class, "entity", "internalNumber");
		logger.info("Created causeDao");
		return dao;
	}

	/**
	 * Measurement technique data access.
	 */
	@Bean
	public FilterDao<MeasurementTechnique, MeasurementTechniqueFilter> measurementTechniqueDao() {
		FilterDao<MeasurementTechnique, MeasurementTechniqueFilter> dao =  
			repositoryFactory.filterDaoFactory(MeasurementTechnique.class, MeasurementTechniqueFilter.class, "entity", "measurementTechniqueCode");
		logger.info("Created measurementTechniqueDao");
		return dao;
	}

	/**
	 * Process document association data access.
	 */
	@Bean
	public BasicDao<ProcessDocumentAssociation> processDocumentAssociationDao() {
		BasicDao<ProcessDocumentAssociation> dao =  
			repositoryFactory.basicDaoFactory(ProcessDocumentAssociation.class, "parent", "child");
		logger.info("Created processDocumentAssociationDao");
		return dao;
	}

	/**
	 * Process document data access.
	 */
	@Bean
	public FilterDao<ProcessDocument, ProcessDocumentFilter> processDocumentDao() {
		FilterDao<ProcessDocument, ProcessDocumentFilter> dao =  
			repositoryFactory.filterDaoFactory(ProcessDocument.class, ProcessDocumentFilter.class, "entity", "docCode");
		logger.info("Created processDocumentDao");
		return dao;
	}

	/**
	 * Setup data access.
	 */
	@Bean
	public BasicDao<Setup> setupDao() {
		BasicDao<Setup> dao =  
			repositoryFactory.basicDaoFactory(Setup.class, "operation", "resource");
		logger.info("Created setupDao");
		return dao;
	}

	// collabs
    
    private RepositoryFactory repositoryFactory;
    
    @Resource
	public void setRepositoryFactory(RepositoryFactory repositoryFactory) {
		this.repositoryFactory = repositoryFactory;
	}

    private static final Logger logger = LoggerFactory.getLogger(ProcessRepositoryConfiguration.class);

}
