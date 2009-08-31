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

package org.helianto.inventory;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.dao.BasicDao;
import org.helianto.core.dao.FilterDao;
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
public class InventoryRepositoryConfiguration {

	/**
	 * Card data access.
	 */
	@Bean
	public BasicDao<Card> cardDao() {
		BasicDao<Card> dao =  
			repositoryFactory.basicDaoFactory(Card.class, "cardSet", "cardLabel");
		logger.info("Created cardDao");
		return dao;
	}

	/**
	 * Card set data access.
	 */
	@Bean
	public FilterDao<CardSet, CardSetFilter> cardSetDao() {
		FilterDao<CardSet, CardSetFilter> dao =  
			repositoryFactory.filterDaoFactory(CardSet.class, CardSetFilter.class, "entity", "internalNumber");
		logger.info("Created cardSetDao");
		return dao;
	}

	/**
	 * Picking data access.
	 */
	@Bean
	public FilterDao<Picking, PickingFilter> pickingDao() {
		FilterDao<Picking, PickingFilter> dao =  
			repositoryFactory.filterDaoFactory(Picking.class, PickingFilter.class, "entity", "internalNumber");
		logger.info("Created pickingDao");
		return dao;
	}

	/**
	 * Process agreement data access.
	 */
	@Bean
	public FilterDao<ProcessAgreement, ProcessAgreementFilter> agreementDao() {
		FilterDao<ProcessAgreement, ProcessAgreementFilter> dao =  
			repositoryFactory.filterDaoFactory(ProcessAgreement.class, ProcessAgreementFilter.class, "entity", "internalNumber");
		logger.info("Created agreementDao");
		return dao;
	}

	// collabs
    
    private RepositoryFactory repositoryFactory;
    
    @Resource
	public void setRepositoryFactory(RepositoryFactory repositoryFactory) {
		this.repositoryFactory = repositoryFactory;
	}

    private static final Log logger = LogFactory.getLog(InventoryRepositoryConfiguration.class);

}
