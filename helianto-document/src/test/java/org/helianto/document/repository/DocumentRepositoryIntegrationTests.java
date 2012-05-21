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

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.helianto.core.repository.BasicDao;
import org.helianto.core.repository.FilterDao;
import org.helianto.document.domain.Document;
import org.helianto.document.domain.DocumentAssociation;
import org.helianto.document.domain.DocumentFolder;
import org.helianto.document.domain.PrivateDocument;
import org.helianto.document.test.AbstractDocumentDaoIntegrationTest;
import org.helianto.document.test.DocumentTestSupport;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;


/**
 * 
 * @author Mauricio Fernandes de Castro
 */
@Transactional
public class DocumentRepositoryIntegrationTests extends AbstractDocumentDaoIntegrationTest {
	
	@Resource BasicDao<DocumentAssociation> documentAssociationDao;
	@Resource FilterDao<Document> documentDao;
	@Resource FilterDao<PrivateDocument> privateDocumentDao;
	@Resource FilterDao<DocumentFolder> documentFolderDao;

	@Test
	public void commit() {
		DocumentAssociation documentAssociation = new DocumentAssociation();
		Document parent = DocumentTestSupport.create(Document.class, entity);
		documentDao.saveOrUpdate(parent);
		documentAssociation.setParent(parent);
		documentAssociation.setChild(DocumentTestSupport.create(Document.class, entity));
		documentAssociationDao.saveOrUpdate(documentAssociation);
		assertEquals(documentAssociation, documentAssociationDao.findUnique(documentAssociation.getParent(), documentAssociation.getChild()));

		Document document = DocumentTestSupport.create(Document.class, entity);
		documentDao.saveOrUpdate(document);
		assertEquals(document, documentDao.findUnique(entity, document.getDocCode()));
		
		PrivateDocument privateDocument = new PrivateDocument(entity, "PRIVATE");
		privateDocumentDao.saveOrUpdate(privateDocument);
		assertEquals(privateDocument, privateDocumentDao.findUnique(entity, "PRIVATE"));
		
		DocumentFolder serializer = new DocumentFolder(entity, "CODE");
		documentFolderDao.saveOrUpdate(serializer);
		assertEquals(serializer, documentFolderDao.findUnique(serializer.getEntity(), serializer.getFolderCode()));

	}
	
}
