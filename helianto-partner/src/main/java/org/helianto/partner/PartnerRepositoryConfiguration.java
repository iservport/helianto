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

package org.helianto.partner;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.helianto.core.RepositoryFactory;
import org.helianto.core.repository.BasicDao;
import org.helianto.core.repository.FilterDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Java config to the repository.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Configuration
@SuppressWarnings("unchecked")
public class PartnerRepositoryConfiguration {
	
	/**
	 * Account data access.
	 */
	@Bean
	public FilterDao<Account, AccountFilter> accountDao() {
		FilterDao<Account, AccountFilter> dao =  
			repositoryFactory.filterDaoFactory(Account.class, AccountFilter.class, "entity", "accountCode");
		logger.info("Created accountDao");
		return dao;
	}

	/**
	 * Address data access.
	 */
	@Bean
	public BasicDao<Address> addressDao() {
		BasicDao<Address> dao =  
			repositoryFactory.basicDaoFactory(Address.class, "partnerRegistry", "sequence");
		logger.info("Created addressDao");
		return dao;
	}

	/**
	 * Partner data access.
	 */
	@Bean
	public FilterDao<Partner, PartnerFilter> partnerDao() {
		FilterDao<Partner, PartnerFilter> dao =  
			repositoryFactory.filterDaoFactory(Partner.class, PartnerFilter.class, "partnerRegistry", "class");
		logger.info("Created partnerDao");
		return dao;
	}

	/**
	 * Partner key data access.
	 */
	@Bean
	public BasicDao<PartnerKey> partnerKeyDao() {
		BasicDao<PartnerKey> dao =  
			repositoryFactory.basicDaoFactory(PartnerKey.class, "partner", "keyType");
		logger.info("Created partnerKeyDao");
		return dao;
	}

	/**
	 * Partner registry data access.
	 */
	@Bean
	public FilterDao<PartnerRegistry, PartnerRegistryFilter> partnerRegistryDao() {
		FilterDao<PartnerRegistry, PartnerRegistryFilter> dao =  
			repositoryFactory.filterDaoFactory(PartnerRegistry.class, PartnerRegistryFilter.class, "entity", "partnerAlias");
		logger.info("Created partnerRegistryDao");
		return dao;
	}

	/**
	 * Partner registry key data access.
	 */
	@Bean
	public BasicDao<PartnerRegistryKey> partnerRegistryKeyDao() {
		BasicDao<PartnerRegistryKey> dao =  
			repositoryFactory.basicDaoFactory(PartnerRegistryKey.class, "partnerRegistry", "keyType");
		logger.info("Created partnerRegistryKeyDao");
		return dao;
	}

	/**
	 * Phone data access.
	 */
	@Bean
	public BasicDao<Phone> phoneDao() {
		BasicDao<Phone> dao =  
			repositoryFactory.basicDaoFactory(Phone.class, "address", "sequence");
		logger.info("Created phoneDao");
		return dao;
	}

	// collabs
    
    private RepositoryFactory repositoryFactory;
    
    @Resource
	public void setRepositoryFactory(RepositoryFactory repositoryFactory) {
		this.repositoryFactory = repositoryFactory;
	}

    private static final Logger logger = LoggerFactory.getLogger(PartnerRepositoryConfiguration.class);

}
