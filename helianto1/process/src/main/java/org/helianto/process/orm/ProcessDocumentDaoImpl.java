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

package org.helianto.process.orm;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.hibernate.GenericDaoImpl;
import org.helianto.process.ProcessDocument;
import org.helianto.process.dao.ProcessDocumentDao;
import org.springframework.stereotype.Repository;
/**
 * Default implementation of <code>Document</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Repository("documentDao")
public class ProcessDocumentDaoImpl extends GenericDaoImpl implements ProcessDocumentDao {
     
    public void persistProcessDocument(ProcessDocument document) {
        if (logger.isDebugEnabled()) {
            logger.debug("Persisting "+document);
        }
        persist(document);
    }
    
    public ProcessDocument mergeProcessDocument(ProcessDocument document) {
        if (logger.isDebugEnabled()) {
            logger.debug("Merging "+document);
        }
        return (ProcessDocument) merge(document);
    }
    
    public void removeProcessDocument(ProcessDocument document) {
        if (logger.isDebugEnabled()) {
            logger.debug("Removing "+document);
        }
        remove(document);
    }
    
    public ProcessDocument findProcessDocumentByNaturalId(Entity entity, String docCode) {
        if (logger.isDebugEnabled()) {
            logger.debug("Finding unique document with entity='"+entity+"' and docCode='"+docCode+"' ");
        }
        return (ProcessDocument) findUnique(ProcessDocument.getDocumentNaturalIdQueryString(), entity, docCode);
    }
    
    @SuppressWarnings("unchecked")
	public List<ProcessDocument> findProcessDocuments(String criteria) {
        if (logger.isDebugEnabled()) {
            logger.debug("Finding document list with criteria ='"+criteria+"'");
        }
        if (criteria.equals("")) {
            return (ArrayList<ProcessDocument>) find(ProcessDocument.getDocumentQueryStringBuilder());
        }
        return (ArrayList<ProcessDocument>) find(new StringBuilder(ProcessDocument.getDocumentQueryStringBuilder()).append("where ").append(criteria));
    }
    
}
