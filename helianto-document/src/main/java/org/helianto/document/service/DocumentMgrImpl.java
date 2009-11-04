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

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.filter.Filter;
import org.helianto.core.repository.FilterDao;
import org.helianto.core.service.SequenceMgr;
import org.helianto.core.utils.CoreUtils;
import org.helianto.document.Document;
import org.helianto.document.DocumentCodeBuilder;
import org.helianto.document.DocumentCodeBuilderFilter;
import org.helianto.document.DocumentFilter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Default implemtation for <code>DocumentMgr</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Service("documentMgr")
@Transactional
public class DocumentMgrImpl implements DocumentMgr {

	public Document storeDocument(Document document) {
		if (document.isLocked()) {
			throw new IllegalArgumentException("Tried to change a locked document.");
		}
		if (document.getDocumentCodeBuilder()!=null) {
			sequenceMgr.validateInternalNumber(document);
		}
		return documentDao.merge(document);
	}
	
	public Document prepareDocument(Document document) {
		Document managedDocument = documentDao.merge(document);
		managedDocument.setDocumentKeyList(CoreUtils.createSortedList(managedDocument.getDocumentKeys()));
		managedDocument.setDocumentTagList(CoreUtils.createSortedList(managedDocument.getDocumentTags()));
		managedDocument.setParentList(CoreUtils.createList(managedDocument.getParents()));
		managedDocument.setChildList(CoreUtils.createList(managedDocument.getChildren()));
		Collections.sort(managedDocument.getChildList());
		documentDao.evict(managedDocument);
		return managedDocument;
	}
	
	public List<? extends Document> findDocuments(Filter documentFilter) {
    	List<? extends Document> documentList = (List<? extends Document>) documentDao.find((DocumentFilter) documentFilter);
    	if (logger.isDebugEnabled() && documentList!=null) {
    		logger.debug("Found document list of size "+documentList.size());
    	}
    	return documentList;
	}
	
	public void removeDocument(Document document) {
    	if (logger.isDebugEnabled()) {
    		logger.debug("Removing document "+document);
    	}
    	documentDao.remove(document);
    	logger.info("Removed document "+document);
	}
	
	public DocumentCodeBuilder storeDocumentCodeBuilder(DocumentCodeBuilder documentCodeBuilder) {
		return documentCodeBuilderDao.merge(documentCodeBuilder);
	}
	
	public DocumentCodeBuilder prepareDocumentCodeBuilder(DocumentCodeBuilder documentCodeBuilder) {
		DocumentCodeBuilder managedDocumentCodeBuilder = documentCodeBuilderDao.merge(documentCodeBuilder);
		managedDocumentCodeBuilder.setDocumentList(CoreUtils.createSortedList(managedDocumentCodeBuilder.getDocuments()));
		documentCodeBuilderDao.evict(managedDocumentCodeBuilder);
		return managedDocumentCodeBuilder;
	}
	
	public List<DocumentCodeBuilder> findDocumentCodeBuilders(Filter documentCodeBuilderFilter) {
    	List<DocumentCodeBuilder> documentCodeBuilderList = (List<DocumentCodeBuilder>) documentCodeBuilderDao.find((DocumentCodeBuilderFilter) documentCodeBuilderFilter);
    	if (logger.isDebugEnabled() && documentCodeBuilderList!=null) {
    		logger.debug("Found document code builder list of size "+documentCodeBuilderList.size());
    	}
    	return documentCodeBuilderList;
	}
	
	// collabs
	
	private FilterDao<Document, DocumentFilter> documentDao;
	private FilterDao<DocumentCodeBuilder, DocumentCodeBuilderFilter> documentCodeBuilderDao;
	private SequenceMgr sequenceMgr;
	
	@Resource(name="documentDao")
	public void setDocumentDao(FilterDao<Document, DocumentFilter> documentDao) {
		this.documentDao = documentDao;
	}
	
	@Resource(name="documentCodeBuilderDao")
	public void setDocumentCodeBuilderDao(FilterDao<DocumentCodeBuilder, DocumentCodeBuilderFilter> documentCodeBuilderDao) {
		this.documentCodeBuilderDao = documentCodeBuilderDao;
	}
	
	@Resource
	public void setSequenceMgr(SequenceMgr sequenceMgr) {
		this.sequenceMgr = sequenceMgr;
	}
	
	private static final Log logger = LogFactory.getLog(DocumentMgrImpl.class);

}
