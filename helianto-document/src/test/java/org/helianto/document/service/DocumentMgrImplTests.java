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
import org.helianto.core.repository.FilterDao;
import org.helianto.core.service.SequenceMgr;
import org.helianto.document.Document;
import org.helianto.document.DocumentCodeBuilder;
import org.helianto.document.DocumentCodeBuilderFilter;
import org.helianto.document.DocumentFilter;
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
		Document managedDocument = new Document();
		
		EasyMock.expect(documentDao.merge(document)).andReturn(managedDocument);
		EasyMock.replay(documentDao);
		EasyMock.replay(sequenceMgr);
		
		assertSame(managedDocument, documentMgr.storeDocument(document));
		EasyMock.verify(documentDao);
		EasyMock.verify(sequenceMgr);
	}
	
	@Test
	public void storeDocument() {
		Document document= new Document();
		document.setDocumentCodeBuilder(new DocumentCodeBuilder());
		Document managedDocument = new Document();
		
		EasyMock.expect(documentDao.merge(document)).andReturn(managedDocument);
		EasyMock.replay(documentDao);
		sequenceMgr.validateInternalNumber(document);
		EasyMock.replay(sequenceMgr);
		
		assertSame(managedDocument, documentMgr.storeDocument(document));
		EasyMock.verify(documentDao);
		EasyMock.verify(sequenceMgr);
	}
	
	@Test
	public void prepareDocument() {
		Document document= new Document();
		Document managedDocument = new Document();
		
		EasyMock.expect(documentDao.merge(document)).andReturn(managedDocument);
		documentDao.evict(managedDocument);
		EasyMock.replay(documentDao);
		
		documentMgr.prepareDocument(document);
		EasyMock.verify(documentDao);
	}
	
	@Test
	public void findDocuments() {
		List<Document> documentList = new ArrayList<Document>();
		DocumentFilter documentFilter = new DocumentFilter();
		
		EasyMock.expect(documentDao.find(documentFilter)).andReturn(documentList);
		EasyMock.replay(documentDao);
		
		assertSame(documentList, documentMgr.findDocuments(documentFilter));
		EasyMock.verify(documentDao);
	}
	
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
		DocumentCodeBuilder documentCodeBuilder = new DocumentCodeBuilder();
		DocumentCodeBuilder managedDocumentCodeBuilder = new DocumentCodeBuilder();
		
		EasyMock.expect(documentCodeBuilderDao.merge(documentCodeBuilder)).andReturn(managedDocumentCodeBuilder);
		EasyMock.replay(documentCodeBuilderDao);
		
		assertSame(managedDocumentCodeBuilder, documentMgr.storeDocumentCodeBuilder(documentCodeBuilder));
		EasyMock.verify(documentCodeBuilderDao);
	}
	
	@Test
	public void findDocumentCodeBuilders() {
		List<DocumentCodeBuilder> documentCodeBuilderList = new ArrayList<DocumentCodeBuilder>();
		DocumentCodeBuilderFilter documentCodeBuilderFilter = new DocumentCodeBuilderFilter();
		
		EasyMock.expect(documentCodeBuilderDao.find(documentCodeBuilderFilter)).andReturn(documentCodeBuilderList);
		EasyMock.replay(documentCodeBuilderDao);
		
		assertSame(documentCodeBuilderList, documentMgr.findDocumentCodeBuilders(documentCodeBuilderFilter));
		EasyMock.verify(documentCodeBuilderDao);
	}
	
	//
	
	private DocumentMgrImpl documentMgr;
	private FilterDao<Document, DocumentFilter> documentDao;
	private FilterDao<DocumentCodeBuilder, DocumentCodeBuilderFilter> documentCodeBuilderDao;
	private SequenceMgr sequenceMgr;
	
	@SuppressWarnings("unchecked")
	@Before
	public void setUp() {
		documentMgr = new DocumentMgrImpl();
		documentDao = EasyMock.createMock(FilterDao.class);
		documentMgr.setDocumentDao(documentDao);
		documentCodeBuilderDao = EasyMock.createMock(FilterDao.class);
		documentMgr.setDocumentCodeBuilderDao(documentCodeBuilderDao);
		sequenceMgr = EasyMock.createMock(SequenceMgr.class);
		documentMgr.setSequenceMgr(sequenceMgr);
	}
	
	@After
	public void tearDown() {
		EasyMock.reset(documentDao);
		EasyMock.reset(documentCodeBuilderDao);
		EasyMock.reset(sequenceMgr);
	}

}
