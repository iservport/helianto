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

import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.helianto.core.SequenceMgr;
import org.helianto.core.domain.Entity;
import org.helianto.core.repository.FilterDao;
import org.helianto.document.domain.Document;
import org.helianto.document.domain.DocumentFolder;
import org.helianto.document.domain.PrivateDocument;
import org.helianto.document.filter.classic.DocumentFilter;
import org.helianto.document.filter.classic.SerializerFilter;
import org.helianto.document.repository.DocumentFolderRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class DocumentMgrImplTests {
	
	@Test
	public void storeDocumentNoBuilder() {
		Document document= new Document();
		
		documentDao.saveOrUpdate(document);
		documentDao.flush();
		EasyMock.replay(documentDao);
		EasyMock.replay(sequenceMgr);
		
		assertSame(document, documentMgr.storeDocument(document));
		EasyMock.verify(documentDao);
		EasyMock.verify(sequenceMgr);
	}
	
	@Test
	public void storeDocument() {
		Document document= new Document();
		document.setSeries(new DocumentFolder());
		
		documentDao.saveOrUpdate(document);
		documentDao.flush();
		EasyMock.replay(documentDao);
		sequenceMgr.validateInternalNumber(document);
		EasyMock.replay(sequenceMgr);
		
		assertSame(document, documentMgr.storeDocument(document));
		EasyMock.verify(documentDao);
		EasyMock.verify(sequenceMgr);
	}
	
	@Test
	public void storePrivateDocument() {
		PrivateDocument privateDocument= new PrivateDocument();
		
		privateDocumentDao.saveOrUpdate(privateDocument);
		EasyMock.replay(privateDocumentDao);
		
		assertSame(privateDocument, documentMgr.storePrivateDocument(privateDocument));
		EasyMock.verify(privateDocumentDao);
	}
	
	@Test
	public void findDocuments() {
		List<Document> documentList = new ArrayList<Document>();
		DocumentFilter documentFilter = new DocumentFilter(new Entity(), "");
		
		EasyMock.expect(documentDao.find(documentFilter)).andReturn(documentList);
		EasyMock.replay(documentDao);
		
		assertSame(documentList, documentMgr.findDocuments(documentFilter));
		EasyMock.verify(documentDao);
	}
	
//	@Test
//	public void findPrivateDocuments() {
//		List<PrivateDocument> documentList = new ArrayList<PrivateDocument>();
//		Filter filter = new TestingFilter();
//		
//		EasyMock.expect(privateDocumentDao.find(filter)).andReturn(documentList);
//		EasyMock.replay(privateDocumentDao);
//		
//		assertSame(documentList, documentMgr.findPrivateDocuments(filter));
//		EasyMock.verify(privateDocumentDao);
//	}
	
	@Test
	public void removeDocument() {
		Document document= new Document();
		
		documentDao.remove(document);
		EasyMock.replay(documentDao);
		
		documentDao.remove(document);
		EasyMock.verify(documentDao);
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
	private FilterDao<Document> documentDao;
	private FilterDao<PrivateDocument> privateDocumentDao;
	private DocumentFolderRepository documentFolderRepository;
	private SequenceMgr sequenceMgr;
	
	@SuppressWarnings("unchecked")
	@Before
	public void setUp() {
		documentMgr = new DocumentMgrImpl();
		documentDao = EasyMock.createMock(FilterDao.class);
		documentMgr.setDocumentDao(documentDao);
		privateDocumentDao = EasyMock.createMock(FilterDao.class);
		documentMgr.setPrivateDocumentDao(privateDocumentDao);
		documentFolderRepository = EasyMock.createMock(DocumentFolderRepository.class);
		documentMgr.setDocumentFolderRepository(documentFolderRepository);
		sequenceMgr = EasyMock.createMock(SequenceMgr.class);
		documentMgr.setSequenceMgr(sequenceMgr);
	}
	
	@After
	public void tearDown() {
		EasyMock.reset(documentDao);
		EasyMock.reset(privateDocumentDao);
		EasyMock.reset(documentFolderRepository);
		EasyMock.reset(sequenceMgr);
	}

}
