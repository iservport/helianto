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
import org.helianto.core.dao.AbstractHibernateBasicDao;
import org.helianto.core.dao.AbstractHibernateFilterDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Java config to the repository.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Configuration
public class CoreHibernateRepository {
	
	/**
	 * Category data access
	 */
	@Bean
	public AbstractHibernateFilterDao<Category, CategoryFilter> categoryDao() {
		AbstractHibernateFilterDao<Category, CategoryFilter> filterDao =  
			new AbstractHibernateFilterDao<Category, CategoryFilter>() { };
		filterDao.setClazz(Category.class);
		filterDao.setParams("entity", "categoryGroup");
		filterDao.setSessionFactory(sessionFactory);
		logger.info("Created categoryDao");
		return filterDao;
	}

	/**
	 * Credential data access
	 */
	@Bean
	public AbstractHibernateBasicDao<Credential> credentialDao() {
		AbstractHibernateBasicDao<Credential> basicDao =  
			new AbstractHibernateBasicDao<Credential>() { };
		basicDao.setClazz(Credential.class);
		basicDao.setParams("identity");
		basicDao.setSessionFactory(sessionFactory);
		logger.info("Created credentialDao");
		return basicDao;
	}

	/**
	 * Entity data access
	 */
	@Bean
	public AbstractHibernateFilterDao<Entity, EntityFilter> entityDao() {
		AbstractHibernateFilterDao<Entity, EntityFilter> filterDao =  
			new AbstractHibernateFilterDao<Entity, EntityFilter>() { };
		filterDao.setClazz(Entity.class);
		filterDao.setParams("operator", "alias");
		filterDao.setSessionFactory(sessionFactory);
		logger.info("Created entityDao");
		return filterDao;
	}

	/**
	 * Identity data access
	 */
	@Bean
	public AbstractHibernateFilterDao<Identity, IdentityFilter> identityDao() {
		AbstractHibernateFilterDao<Identity, IdentityFilter> filterDao =  
			new AbstractHibernateFilterDao<Identity, IdentityFilter>() { };
		filterDao.setClazz(Identity.class);
		filterDao.setParams("principal");
		filterDao.setSessionFactory(sessionFactory);
		logger.info("Created identityDao");
		return filterDao;
	}

	// collabs
    
    private org.hibernate.SessionFactory sessionFactory;
    
    /**
     * Spring will inject a managed Hibernate Session into this field.
     */
    @Resource(name="sessionFactory")
	public void setSessionFactory(org.hibernate.SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

    private static final Log logger = LogFactory.getLog(AbstractHibernateBasicDao.class);

}
