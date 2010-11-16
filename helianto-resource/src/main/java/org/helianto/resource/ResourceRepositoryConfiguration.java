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

package org.helianto.resource;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class ResourceRepositoryConfiguration {
	
	/**
	 * Resource association data access.
	 */
	@Bean
	public BasicDao<ResourceAssociation> resourceAssociationDao() {
		BasicDao<ResourceAssociation> dao =  
			repositoryFactory.basicDaoFactory(ResourceAssociation.class, "parent", "child");
		logger.info("Created resourceAssociationDao");
		return dao;
	}

	/**
	 * Resource group data access.
	 */
	@Bean
	public FilterDao<ResourceGroup, ResourceGroupFilter> resourceGroupDao() {
		FilterDao<ResourceGroup, ResourceGroupFilter> dao =  
			repositoryFactory.filterDaoFactory(ResourceGroup.class, ResourceGroupFilter.class, "entity", "resourceCode");
		logger.info("Created resourceGroupDao");
		return dao;
	}

	/**
	 * Resource parameter data access.
	 */
	@Bean
	public FilterDao<ResourceParameter, ResourceParameterFilter> resourceParameterDao() {
		FilterDao<ResourceParameter, ResourceParameterFilter> dao =  
			repositoryFactory.filterDaoFactory(ResourceParameter.class, ResourceParameterFilter.class, "entity", "parameterCode");
		logger.info("Created resourceParameterDao");
		return dao;
	}

	/**
	 * Resource parameter value data access.
	 */
	@Bean
	public BasicDao<ResourceParameterValue> resourceParameterValueDao() {
		BasicDao<ResourceParameterValue> dao =  
			repositoryFactory.basicDaoFactory(ResourceParameterValue.class, "resource", "parameter");
		logger.info("Created resourceParameterValueDao");
		return dao;
	}

	// collabs
    
    private RepositoryFactory repositoryFactory;
    
    @Resource
	public void setRepositoryFactory(RepositoryFactory repositoryFactory) {
		this.repositoryFactory = repositoryFactory;
	}

    private static final Logger logger = LoggerFactory.getLogger(ResourceRepositoryConfiguration.class);

}
