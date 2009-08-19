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
import org.helianto.core.InternalEnumerator;
import org.helianto.core.dao.AbstractBasicDao;
import org.helianto.core.dao.AbstractFilterDao;
import org.helianto.core.dao.BasicDao;
import org.helianto.core.dao.FilterDao;
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
	 * Category data access.
	 */
	@Bean
	public FilterDao<Category, CategoryFilter> categoryDao() {
		FilterDao<Category, CategoryFilter> dao =  
			repositoryFactory.filterDaoFactory(Category.class, CategoryFilter.class, "entity", "categoryGroup", "categoryCode");
		logger.info("Created categoryDao");
		return dao;
	}

	/**
	 * Credential data access.
	 */
	@Bean
	public BasicDao<Credential> credentialDao() {
		BasicDao<Credential> dao =  
			repositoryFactory.basicDaoFactory(Credential.class, "identity");
		logger.info("Created credentialDao");
		return dao;
	}

	/**
	 * Entity data access.
	 */
	@Bean
	public FilterDao<Entity, EntityFilter> entityDao() {
		FilterDao<Entity, EntityFilter> dao =  
			repositoryFactory.filterDaoFactory(Entity.class, EntityFilter.class, "operator", "alias");
		logger.info("Created entityDao");
		return dao;
	}

	/**
	 * Identity data access.
	 */
	@Bean
	public FilterDao<Identity, IdentityFilter> identityDao() {
		FilterDao<Identity, IdentityFilter> dao =  
			repositoryFactory.filterDaoFactory(Identity.class, IdentityFilter.class, "principal");
		logger.info("Created identityDao");
		return dao;
	}

	/**
	 * Internal enumerator data access.
	 */
	@Bean
	public BasicDao<InternalEnumerator> internalEnumeratorDao() {
		BasicDao<InternalEnumerator> dao =  
			repositoryFactory.basicDaoFactory(InternalEnumerator.class, "entity", "typeName");
		logger.info("Created internalEnumeratorDao");
		return dao;
	}

	// collabs
    
    private RepositoryFactory repositoryFactory;
    
    @Resource
	public void setRepositoryFactory(RepositoryFactory repositoryFactory) {
		this.repositoryFactory = repositoryFactory;
	}

    private static final Log logger = LogFactory.getLog(CoreHibernateRepository.class);

}
