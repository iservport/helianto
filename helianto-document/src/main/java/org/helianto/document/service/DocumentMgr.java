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

import org.helianto.core.NonUniqueResultException;
import org.helianto.core.filter.Filter;
import org.helianto.document.Document;
import org.helianto.document.Serializer;

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
	public List<? extends Document> findDocuments(Filter documentFilter);
	
	/**
	 * Find a <code>Document</code>.
	 * 
	 * @param documentFilter
	 */
	public Document findDocument(Filter documentFilter) throws NonUniqueResultException;
	
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
	public Serializer storeSerializer(Serializer serializer);

	/**
	 * Find a <code>Serializer</code> list.
	 * 
	 * @param serializerFilter
	 */
	public List<? extends Serializer> findSerializers(Filter serializerFilter);
	
}
