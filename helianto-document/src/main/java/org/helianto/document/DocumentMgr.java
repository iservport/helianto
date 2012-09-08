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

import java.util.List;

import org.helianto.core.NonUniqueResultException;
import org.helianto.core.filter.Filter;
import org.helianto.document.domain.Document;
import org.helianto.document.domain.DocumentFolder;
import org.helianto.document.domain.PrivateDocument;
import org.helianto.document.form.DocumentFolderForm;

/**
 * Document service interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface DocumentMgr {
	
	/**
	 * Find a <code>Document</code> list.
	 * 
	 * @param documentFilter
	 */
	public List<? extends Document> findDocuments(Filter documentFilter);
	
	/**
	 * Find a <code>Document</code>.
	 * 
	 * @param documentFilter
	 */
	public Document findDocument(Filter documentFilter) throws NonUniqueResultException;
	
	/**
	 * Store <code>Document</code>.
	 * 
	 * @param document
	 */
	public Document storeDocument(Document document);
	
	/**
	 * Find a <code>PrivateDocument</code> list.
	 * 
	 * @param privateDocumentFilter
	 */
	public List<PrivateDocument> findPrivateDocuments(Filter privateDocumentFilter);
	
	/**
	 * Store <code>PrivateDocument</code>.
	 * 
	 * @param privateDocument
	 */
	public PrivateDocument storeDocument(PrivateDocument privateDocument);

	/**
	 * Remove the <code>Document</code> from the datastore.
	 * 
	 * @param document
	 */
	public void removeDocument(Document document);

	/**
	 * Store a <code>Serializer</code>.
	 * 
	 * @param serializer
	 */
	public DocumentFolder storeSerializer(DocumentFolder serializer);

	/**
	 * Find a <code>Serializer</code> list.
	 * 
	 * @param serializerFilter
	 * @deprecated
	 */
	public List<? extends DocumentFolder> findSerializers(Filter serializerFilter);
	
	/**
	 * Find a <code>Serializer</code> list.
	 * 
	 * @param serializerFilter
	 */
	public List<? extends DocumentFolder> findSerializers(DocumentFolderForm form);
	
}