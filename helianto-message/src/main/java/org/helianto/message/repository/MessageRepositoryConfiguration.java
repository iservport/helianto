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

package org.helianto.message.repository;

import javax.annotation.Resource;

import org.helianto.core.RepositoryFactory;
import org.helianto.core.repository.BasicDao;
import org.helianto.message.ServiceEvent;
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
public class MessageRepositoryConfiguration {

	/**
	 * Card data access.
	 */
	@Bean
	public BasicDao<ServiceEvent> serviceEventDao() {
		BasicDao<ServiceEvent> dao =  
			repositoryFactory.basicDaoFactory(ServiceEvent.class, "service", "eventCode");
		logger.info("Created serviceEventDao");
		return dao;
	}

//	@Bean
//	public FilterDao<ProcessAgreement, ProcessAgreementFilter> agreementDao() {
//		FilterDao<ProcessAgreement, ProcessAgreementFilter> dao =  
//			repositoryFactory.filterDaoFactory(ProcessAgreement.class, ProcessAgreementFilter.class, "entity", "internalNumber");
//		logger.info("Created agreementDao");
//		return dao;
//	}

	// collabs
    
    private RepositoryFactory repositoryFactory;
    
    @Resource
	public void setRepositoryFactory(RepositoryFactory repositoryFactory) {
		this.repositoryFactory = repositoryFactory;
	}

    private static final Logger logger = LoggerFactory.getLogger(MessageRepositoryConfiguration.class);

}
