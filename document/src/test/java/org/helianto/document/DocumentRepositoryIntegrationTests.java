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


/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class DocumentRepositoryIntegrationTests extends AbstractDocumentDaoIntegrationTest {

	@Resource BasicDao<DocumentAssociation> documentAssociationDao;
	@Test
	public void address() {
		DocumentAssociation target = new DocumentAssociation();
		target.setParent(DocumentTestSupport.create(Document.class));
		target.setChild(DocumentTestSupport.create(Document.class));
		assertEquals(documentAssociationDao.merge(target), documentAssociationDao.findUnique(target.getParent(), target.getChild()));
	}
	
	@Resource FilterDao<Document, DocumentFilter> documentDao;
	@Test
	public void document() {
		Document target = DocumentTestSupport.create(Document.class);
		assertEquals(documentDao.merge(target), documentDao.findUnique(target.getEntity(), target.getDocCode()));
	}
	
	@Resource BasicDao<DocumentTag> documentTagDao;
	@Test
	public void documentTag() {
		DocumentTag target = DocumentTagTestSupport.create(DocumentTag.class);
		assertEquals(documentTagDao.merge(target), documentTagDao.findUnique(target.getDocument(), target.getTagCode()));
	}

}
