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

import org.helianto.core.NonUniqueResultException;
import org.helianto.core.filter.Filter;
import org.helianto.core.repository.FilterDao;
import org.helianto.core.service.SequenceMgr;
import org.helianto.core.utils.CoreUtils;
import org.helianto.document.Document;
import org.helianto.document.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Default implemtation for <code>DocumentMgr</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Service("documentMgr")
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
    	List<? extends Document> documentList = (List<? extends Document>) documentDao.find(documentFilter);
    	if (logger.isDebugEnabled() && documentList!=null) {
    		logger.debug("Found document list of size {}", documentList.size());
    	}
    	return documentList;
	}
	
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
	
	public void removeDocument(Document document) {
    	if (logger.isDebugEnabled()) {
    		logger.debug("Removing document "+document);
    	}
    	documentDao.remove(document);
    	logger.info("Removed document "+document);
	}
	
	public Serializer storeSerializer(Serializer serializer) {
		serializerDao.saveOrUpdate(serializer);
		return serializer;
	}
	
	public List<? extends Serializer> findSerializers(Filter serializerFilter) {
    	List<Serializer> serializerList = (List<Serializer>) serializerDao.find(serializerFilter);
    	if (logger.isDebugEnabled() && serializerList!=null) {
    		logger.debug("Found serializer list of size {}", serializerList.size());
    	}
    	return serializerList;
	}
	
	// collabs
	
	private FilterDao<Document> documentDao;
	private FilterDao<Serializer> serializerDao;
	private SequenceMgr sequenceMgr;
	
	@Resource(name="documentDao")
	public void setDocumentDao(FilterDao<Document> documentDao) {
		this.documentDao = documentDao;
	}
	
	@Resource(name="serializerDao")
	public void setSerializerDao(FilterDao<Serializer> serializerDao) {
		this.serializerDao = serializerDao;
	}
	
	@Resource
	public void setSequenceMgr(SequenceMgr sequenceMgr) {
		this.sequenceMgr = sequenceMgr;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(DocumentMgrImpl.class);

}
