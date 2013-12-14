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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.helianto.core.domain.Entity;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.document.domain.Document;
import org.helianto.document.domain.DocumentFolder;
import org.helianto.document.domain.PrivateDocument;
import org.helianto.document.filter.classic.DocumentFilter;
import org.helianto.document.repository.DocumentFolderRepository;
import org.helianto.document.repository.DocumentRepository;
import org.helianto.document.repository.PrivateDocumentRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class DocumentMgrImplTests {
	
	@Test
	public void storeDocument() {
		Entity entity = EntityTestSupport.createEntity();
		Document document= new Document(entity, "");
		document.setSeries(new DocumentFolder(entity, "KEY"));
		
		EasyMock.expect(documentRepository.save(document)).andReturn(document);
		EasyMock.expect(documentRepository.findLastNumber(entity, "KEY")).andReturn(new Long(1000));
		documentRepository.flush();
		EasyMock.replay(documentRepository);
//		sequenceMgr.validateInternalNumber(document);
//		EasyMock.replay(sequenceMgr);
		
		assertSame(document, documentMgr.storeDocument(document));
		EasyMock.verify(documentRepository);
		assertEquals(1001, document.getInternalNumber());
//		EasyMock.verify(sequenceMgr);
	}
	
	@Test
	public void storePrivateDocument() {
		PrivateDocument privateDocument= new PrivateDocument();
		
		EasyMock.expect(privateDocumentRepository.saveAndFlush(privateDocument)).andReturn(privateDocument);
		EasyMock.replay(privateDocumentRepository);
		
		assertSame(privateDocument, documentMgr.storePrivateDocument(privateDocument));
		EasyMock.verify(privateDocumentRepository);
	}
	
	@Test
	public void findDocuments() {
		List<Document> documentList = new ArrayList<Document>();
		DocumentFilter documentFilter = new DocumentFilter(new Entity(), "");
		
		EasyMock.expect(documentRepository.find(documentFilter)).andReturn(documentList);
		EasyMock.replay(documentRepository);
		
		assertSame(documentList, documentMgr.findDocuments(documentFilter));
		EasyMock.verify(documentRepository);
	}
	
	@Test
	public void removeDocument() {
		Document document= new Document();
		
		documentRepository.delete(document);
		EasyMock.replay(documentRepository);
		
		documentRepository.delete(document);
		EasyMock.verify(documentRepository);
	}
	
	@Test
	public void storeDocumentCodeBuilder() {
		DocumentFolder documentFolder = new DocumentFolder();
		
		EasyMock.expect(documentFolderRepository.saveAndFlush(documentFolder)).andReturn(documentFolder);
		EasyMock.replay(documentFolderRepository);
		
		assertSame(documentFolder, documentMgr.storeDocumentFolder(documentFolder));
		EasyMock.verify(documentFolderRepository);
	}
	
	//
	
	private DocumentMgrImpl documentMgr;
	private DocumentRepository documentRepository;
	private PrivateDocumentRepository privateDocumentRepository;
	private DocumentFolderRepository documentFolderRepository;
	
	@Before
	public void setUp() {
		documentMgr = new DocumentMgrImpl();
		documentRepository = EasyMock.createMock(DocumentRepository.class);
		documentMgr.setDocumentRepository(documentRepository);
		privateDocumentRepository = EasyMock.createMock(PrivateDocumentRepository.class);
		documentMgr.setPrivateDocumentRepository(privateDocumentRepository);
		documentFolderRepository = EasyMock.createMock(DocumentFolderRepository.class);
		documentMgr.setDocumentFolderRepository(documentFolderRepository);
	}
	
	@After
	public void tearDown() {
		EasyMock.reset(documentRepository);
		EasyMock.reset(privateDocumentRepository);
		EasyMock.reset(documentFolderRepository);
	}

}
