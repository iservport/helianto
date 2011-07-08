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

package org.helianto.document.repository;

import org.helianto.core.repository.FilterDao;
import org.helianto.core.repository.base.AbstractRepositoryConfiguration;
import org.helianto.document.Document;
import org.helianto.document.DocumentAssociation;
import org.helianto.document.DocumentTag;
import org.helianto.document.LoginRequest;
import org.helianto.document.PrivateDocument;
import org.helianto.document.Role;
import org.helianto.document.Serializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Java config to the repository.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Configuration
public class DocumentRepositoryConfiguration extends AbstractRepositoryConfiguration {
	
	/**
	 * Document association data access.
	 */
	@Bean
	public FilterDao<DocumentAssociation> documentAssociationDao() {
		return getFilterDao(DocumentAssociation.class, "parent", "child");
	}

	/**
	 * Document data access.
	 */
	@Bean
	public FilterDao<Document> documentDao() {
		return getFilterDao(Document.class, "entity", "docCode");
	}

	/**
	 * Private document data access.
	 */
	@Bean
	public FilterDao<PrivateDocument> privateDocumentDao() {
		return getFilterDao(PrivateDocument.class, "entity", "docCode");
	}

	/**
	 * Serializer data access.
	 */
	@Bean
	public FilterDao<Serializer> serializerDao() {
		return getFilterDao(Serializer.class, "entity", "builderCode");
	}

	/**
	 * Document tag data access.
	 */
	@Bean
	public FilterDao<DocumentTag> documentTagDao() {
		return getFilterDao(DocumentTag.class, "document", "tagCode");
	}

	/**
	 * Role data access.
	 */
	@Bean
	public FilterDao<Role> roleDao() {
		return getFilterDao(Role.class, "entity", "docCode");
	}
	
	/**
	 * Login request data access.
	 */
	@Bean
	public FilterDao<LoginRequest> loginRequestDao() {
		return getFilterDao(LoginRequest.class);
	}
	
}
