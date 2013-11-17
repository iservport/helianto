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

import javax.annotation.Resource;

import org.helianto.core.NonUniqueResultException;
import org.helianto.core.SequenceMgr;
import org.helianto.core.domain.Entity;
import org.helianto.core.filter.Filter;
import org.helianto.document.DocumentMgr;
import org.helianto.document.domain.Document;
import org.helianto.document.domain.DocumentFolder;
import org.helianto.document.domain.PrivateDocument;
import org.helianto.document.filter.DocumentFolderFormFilterAdapter;
import org.helianto.document.filter.PrivateDocumentFilterAdapter;
import org.helianto.document.form.DocumentFolderForm;
import org.helianto.document.form.PrivateDocumentForm;
import org.helianto.document.repository.DocumentFolderRepository;
import org.helianto.document.repository.DocumentRepository;
import org.helianto.document.repository.PrivateDocumentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Default implemtation for <code>DocumentMgr</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Service("documentMgr")
public class DocumentMgrImpl 
	implements DocumentMgr {

	@Transactional
	public Document storeDocument(Document document) {
		if (document.isLocked()) {
			throw new IllegalArgumentException("Tried to change a locked document.");
		}
		document = documentRepository.save(document);
		if (document.getSeries()!=null) {
			Long lastNumber = documentRepository.findLastNumber(document.getEntity(), document.getInternalNumberKey());
			if (lastNumber==null) {
				document.setInternalNumber(1);
			}
			else {
				document.setInternalNumber(lastNumber+1);
			}
		}
		documentRepository.flush();
		return document;
	}
	
	@Transactional(readOnly=true)
	public List<? extends Document> findDocuments(Filter documentFilter) {
    	List<? extends Document> documentList = (List<? extends Document>) documentRepository.find(documentFilter);
    	if (logger.isDebugEnabled() && documentList!=null) {
    		logger.debug("Found document list of size {}", documentList.size());
    	}
    	return documentList;
	}
	
	@Transactional(readOnly=true)
	public List<PrivateDocument> findPrivateDocuments(PrivateDocumentForm form) {
		Filter privateDocumentFilter = new PrivateDocumentFilterAdapter(form);
    	List<PrivateDocument> privateDocumentList = (List<PrivateDocument>) privateDocumentRepository.find(privateDocumentFilter);
    	if (logger.isDebugEnabled() && privateDocumentList!=null) {
    		logger.debug("Found private document list of size {}", privateDocumentList.size());
    	}
    	return privateDocumentList;
	}
	
	@Transactional
	public PrivateDocument storePrivateDocument(PrivateDocument privateDocument) {
		return privateDocumentRepository.saveAndFlush(privateDocument);
	}
	
	@Transactional(readOnly=true)
	public Document findDocument(Filter documentFilter) throws NonUniqueResultException {
		List<? extends Document> documentList = findDocuments(documentFilter);
		if (documentList.size()>1) {
			throw new NonUniqueResultException("Found more than one document", documentList);
		}
		else if(documentList.size()==1) {
			logger.debug("Found document {}", documentList.get(0));
			return documentList.get(0);
		}
		return null;
	}
	
	@Transactional
	public void removeDocument(Document document) {
    	if (logger.isDebugEnabled()) {
    		logger.debug("Removing document "+document);
    	}
    	documentRepository.delete(document);
    	logger.info("Removed document "+document);
	}
	
	@Transactional
	public DocumentFolder loadDocumentFolder(Entity entity, String folderCode) {
		return documentFolderRepository.findByEntityAndFolderCode(entity, folderCode);
	}
	
	@Transactional(readOnly=true)
	public List<? extends DocumentFolder> findDocumentFolders(DocumentFolderForm form) {
		Filter filter = new DocumentFolderFormFilterAdapter<DocumentFolderForm>(form);
    	List<DocumentFolder> serializerList = (List<DocumentFolder>) documentFolderRepository.find(filter);
    	if (logger.isDebugEnabled() && serializerList!=null) {
    		logger.debug("Found serializer list of size {}", serializerList.size());
    	}
    	return serializerList;
	}
	
	@Transactional
	public DocumentFolder storeDocumentFolder(DocumentFolder serializer) {
		return documentFolderRepository.saveAndFlush(serializer);
	}
	
	// collabs
	
	private DocumentRepository documentRepository;
	private PrivateDocumentRepository privateDocumentRepository;
	private DocumentFolderRepository documentFolderRepository;
	private SequenceMgr sequenceMgr;
	
	@Resource
	public void setDocumentRepository(DocumentRepository documentRepository) {
		this.documentRepository = documentRepository;
	}
	
	@Resource
	public void setPrivateDocumentRepository(PrivateDocumentRepository privateDocumentRepository) {
		this.privateDocumentRepository = privateDocumentRepository;
	}
	
	@Resource
	public void setDocumentFolderRepository(DocumentFolderRepository documentFolderRepository) {
		this.documentFolderRepository = documentFolderRepository;
	}
	
	@Resource
	public void setSequenceMgr(SequenceMgr sequenceMgr) {
		this.sequenceMgr = sequenceMgr;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(DocumentMgrImpl.class);

}
