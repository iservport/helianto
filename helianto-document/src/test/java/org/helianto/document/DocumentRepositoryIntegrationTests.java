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

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.helianto.core.repository.BasicDao;
import org.helianto.core.repository.FilterDao;
import org.helianto.document.test.AbstractDocumentDaoIntegrationTest;
import org.helianto.document.test.DocumentTagTestSupport;
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
	@Resource FilterDao<Document, DocumentFilter> documentDao;
	@Resource FilterDao<DocumentCodeBuilder, DocumentCodeBuilderFilter> documentCodeBuilderDao;
	@Resource BasicDao<DocumentTag> documentTagDao;
	@Resource FilterDao<AbstractFunction, AbstractDocumentFilter> functionDao;

	@Test
	public void commit() {
		DocumentAssociation documentAssociation = new DocumentAssociation();
		documentAssociation.setParent(DocumentTestSupport.create(Document.class, entity));
		documentAssociation.setChild(DocumentTestSupport.create(Document.class, entity));
		assertEquals(documentAssociationDao.merge(documentAssociation), documentAssociationDao.findUnique(documentAssociation.getParent(), documentAssociation.getChild()));

		Document document = DocumentTestSupport.create(Document.class, entity);
		assertEquals(documentDao.merge(document), documentDao.findUnique(document.getEntity(), document.getDocCode()));

		DocumentCodeBuilder documentCodeBuilder = documentCodeBuilderDao.merge(new DocumentCodeBuilder(entity).setBuilderCode("CODE"));
		assertEquals(documentCodeBuilder, documentCodeBuilderDao.findUnique(documentCodeBuilder.getEntity(), documentCodeBuilder.getBuilderCode()));

		DocumentTag documentTag = DocumentTagTestSupport.create(DocumentTag.class, document);
		assertEquals(documentTagDao.merge(documentTag), documentTagDao.findUnique(documentTag.getDocument(), documentTag.getTagCode()));

		AbstractFunction function = functionDao.merge(new FunctionStub(entity));
		assertEquals(function, functionDao.findUnique(function.getEntity(), function.getDocCode()));
	}
	
}
