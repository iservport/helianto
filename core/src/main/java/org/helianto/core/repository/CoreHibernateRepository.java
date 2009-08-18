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

package org.helianto.core.repository;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.Category;
import org.helianto.core.CategoryFilter;
import org.helianto.core.Credential;
import org.helianto.core.Entity;
import org.helianto.core.EntityFilter;
import org.helianto.core.Identity;
import org.helianto.core.IdentityFilter;
import org.helianto.core.dao.AbstractBasicDao;
import org.helianto.core.dao.AbstractFilterDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Java config to the repository.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Configuration
@SuppressWarnings("unchecked")
public class CoreHibernateRepository {
	
	/**
	 * Category data access
	 */
	@Bean
	public AbstractFilterDao<Category, CategoryFilter> categoryDao() {
		AbstractFilterDao<Category, CategoryFilter> filterDao =  
			repositoryFactory.filterDaoFactory(Category.class, CategoryFilter.class, "entity", "categoryGroup");
		logger.info("Created categoryDao");
		return filterDao;
	}

	/**
	 * Credential data access
	 */
	@Bean
	public AbstractBasicDao<Credential> credentialDao() {
		AbstractBasicDao<Credential> basicDao =  
			repositoryFactory.basicDaoFactory(Credential.class, "identity");
		logger.info("Created credentialDao");
		return basicDao;
	}

	/**
	 * Entity data access
	 */
	@Bean
	public AbstractFilterDao<Entity, EntityFilter> entityDao() {
		AbstractFilterDao<Entity, EntityFilter> filterDao =  
			repositoryFactory.filterDaoFactory(Entity.class, EntityFilter.class, "operator", "alias");
		logger.info("Created entityDao");
		return filterDao;
	}

	/**
	 * Identity data access
	 */
	@Bean
	public AbstractFilterDao<Identity, IdentityFilter> identityDao() {
		AbstractFilterDao<Identity, IdentityFilter> filterDao =  
			repositoryFactory.filterDaoFactory(Identity.class, IdentityFilter.class, "principal");
		logger.info("Created identityDao");
		return filterDao;
	}

	// collabs
    
    private RepositoryFactory repositoryFactory;
    
    @Resource
	public void setRepositoryFactory(RepositoryFactory repositoryFactory) {
		this.repositoryFactory = repositoryFactory;
	}

    private static final Log logger = LogFactory.getLog(CoreHibernateRepository.class);

}
