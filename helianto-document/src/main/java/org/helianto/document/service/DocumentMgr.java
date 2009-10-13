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

package org.helianto.document.service;

import java.util.List;

import org.helianto.core.filter.Filter;
import org.helianto.document.Document;
import org.helianto.document.DocumentCodeBuilder;
import org.springframework.security.annotation.Secured;

/**
 * Document service interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface DocumentMgr {
	
	/**
	 * Store <code>Document</code> in the datastore and return a managed instance.
	 * 
	 * @param document
	 */
	@Secured("ROLE_DOCUMENT_CHANGE")
	public Document storeDocument(Document document);

	/**
	 * Prepare a <code>Document</code> instance to return a
	 * managed instance loaded with lazy collections.
	 * 
	 * @param document
	 */
	public Document prepareDocument(Document document);

	/**
	 * Find a <code>Document</code> list.
	 * 
	 * @param documentFilter
	 */
	public List<Document> findDocuments(Filter documentFilter);
	
	/**
	 * Remove the <code>Document</code> from the datastore.
	 * 
	 * @param document
	 */
	@Secured("ROLE_DOCUMENT_DEL")
	public void removeDocument(Document document);

	/**
	 * Store <code>DocumentCodeBuilder</code> in the datastore and return a managed instance.
	 * 
	 * @param documentCodeBuilder
	 */
	@Secured("ROLE_DOCUMENT_CHANGE")
	public DocumentCodeBuilder storeDocumentCodeBuilder(DocumentCodeBuilder documentCodeBuilder);

	/**
	 * Find a <code>DocumentCodeBuilder</code> list.
	 * 
	 * @param documentCodeBuilderFilter
	 */
	public List<DocumentCodeBuilder> findDocumentCodeBuilders(Filter documentCodeBuilderFilter);
	
}
