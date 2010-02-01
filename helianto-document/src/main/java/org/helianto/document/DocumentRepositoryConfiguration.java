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

package org.helianto.document;

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
public class DocumentRepositoryConfiguration {
	
	/**
	 * Document association data access.
	 */
	@Bean
	public BasicDao<DocumentAssociation> documentAssociationDao() {
		BasicDao<DocumentAssociation> dao =  
			repositoryFactory.basicDaoFactory(DocumentAssociation.class, "parent", "child");
		logger.info("Created documentAssociationDao");
		return dao;
	}

	/**
	 * Document data access.
	 */
	@Bean
	public FilterDao<Document, DocumentFilter> documentDao() {
		FilterDao<Document, DocumentFilter> dao =  
			repositoryFactory.filterDaoFactory(Document.class, DocumentFilter.class, "entity", "docCode");
		logger.info("Created documentDao");
		return dao;
	}

	/**
	 * Document code builder data access.
	 */
	@Bean
	public FilterDao<DocumentCodeBuilder, DocumentCodeBuilderFilter> documentCodeBuilderDao() {
		FilterDao<DocumentCodeBuilder, DocumentCodeBuilderFilter> dao =  
			repositoryFactory.filterDaoFactory(DocumentCodeBuilder.class, DocumentCodeBuilderFilter.class, "entity", "builderCode");
		logger.info("Created documentCodeBuilderDao");
		return dao;
	}

	/**
	 * Document tag data access.
	 */
	@Bean
	public BasicDao<DocumentTag> documentTagDao() {
		BasicDao<DocumentTag> dao =  
			repositoryFactory.basicDaoFactory(DocumentTag.class, "document", "tagCode");
		logger.info("Created documentTagDao");
		return dao;
	}

	/**
	 * Function data access.
	 */
	@Bean
	public FilterDao<AbstractFunction, AbstractDocumentFilter> functionDao() {
		FilterDao<AbstractFunction, AbstractDocumentFilter> dao =  
			repositoryFactory.filterDaoFactory(AbstractFunction.class, AbstractDocumentFilter.class, "entity", "docCode");
		logger.info("Created functionDao");
		return dao;
	}
	
	// collabs
    
    private RepositoryFactory repositoryFactory;
    
    @Resource
	public void setRepositoryFactory(RepositoryFactory repositoryFactory) {
		this.repositoryFactory = repositoryFactory;
	}

    private static final Logger logger = LoggerFactory.getLogger(DocumentRepositoryConfiguration.class);

}
