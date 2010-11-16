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

import org.helianto.core.repository.BasicDao;
import org.helianto.core.repository.FilterDao;
import org.helianto.core.repository.RepositoryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	 * Public entity data access.
	 */
	@Bean
	public FilterDao<PublicEntity, PublicEntityFilter> publicEntityDao() {
		FilterDao<PublicEntity, PublicEntityFilter> dao =  
			repositoryFactory.filterDaoFactory(PublicEntity.class, PublicEntityFilter.class, "operator", "entity", "class");
		logger.info("Created publicEntityDao");
		return dao;
	}

	/**
	 * Public entity key data access.
	 */
	@Bean
	public BasicDao<PublicEntityKey> publicEntityKeyDao() {
		BasicDao<PublicEntityKey> dao =  
			repositoryFactory.basicDaoFactory(PublicEntityKey.class, "publicEntity", "keyType");
		logger.info("Created publicEntityKeyDao");
		return dao;
	}

	
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
	 * Address database data access.
	 */
	@Bean
	public FilterDao<PublicAddress, PublicAddressFilter> publicAddressDao() {
		FilterDao<PublicAddress, PublicAddressFilter> dao =  
			repositoryFactory.filterDaoFactory(PublicAddress.class, PublicAddressFilter.class, "operator", "postalCode");
		logger.info("Created publicAddressDao");
		return dao;
	}

	/**
	 * Partner data access.
	 */
	@Bean
	public FilterDao<Partner, PartnerFilter> partnerDao() {
		FilterDao<Partner, PartnerFilter> dao =  
			repositoryFactory.filterDaoFactory(Partner.class, PartnerFilter.class, "privateEntity", "class");
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
	 * Private entity data access.
	 */
	@Bean
	public FilterDao<PrivateEntity, PrivateEntityFilter> privateEntityDao() {
		FilterDao<PrivateEntity, PrivateEntityFilter> dao =  
			repositoryFactory.filterDaoFactory(PrivateEntity.class, PrivateEntityFilter.class, "entity", "entityAlias");
		logger.info("Created privateEntityDao");
		return dao;
	}

	/**
	 * Private entity key data access.
	 */
	@Bean
	public BasicDao<PrivateEntityKey> privateEntityKeyDao() {
		BasicDao<PrivateEntityKey> dao =  
			repositoryFactory.basicDaoFactory(PrivateEntityKey.class, "privateEntity", "keyType");
		logger.info("Created privateEntityKeyDao");
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
