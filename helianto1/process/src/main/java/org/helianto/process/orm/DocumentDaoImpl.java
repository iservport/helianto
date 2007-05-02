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
import org.helianto.process.Document;
import org.helianto.process.dao.DocumentDao;
/**
 * Default implementation of <code>Document</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class DocumentDaoImpl extends GenericDaoImpl implements DocumentDao {
     
    public void persistDocument(Document document) {
        if (logger.isDebugEnabled()) {
            logger.debug("Persisting "+document);
        }
        persist(document);
    }
    
    public Document mergeDocument(Document document) {
        if (logger.isDebugEnabled()) {
            logger.debug("Merging "+document);
        }
        return (Document) merge(document);
    }
    
    public void removeDocument(Document document) {
        if (logger.isDebugEnabled()) {
            logger.debug("Removing "+document);
        }
        remove(document);
    }
    
    public Document findDocumentByNaturalId(Entity entity, String docCode) {
        if (logger.isDebugEnabled()) {
            logger.debug("Finding unique document with entity='"+entity+"' and docCode='"+docCode+"' ");
        }
        return (Document) findUnique(Document.getDocumentNaturalIdQueryString(), entity, docCode);
    }
    
    public List<Document> findDocumentByCriteria(String criteria) {
        if (criteria.equals("")) {
            throw new IllegalArgumentException("Criteria must not be empty!");
        }
        if (logger.isDebugEnabled()) {
            logger.debug("Finding document list with criteria='"+criteria+"' ");
        }
        return (ArrayList<Document>) find(Document.getDocumentAllQueryString()+criteria);
    }
    
}
