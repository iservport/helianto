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

package org.helianto.process.dao;

import java.util.List;

import org.helianto.core.dao.CommonOrmDao;


import org.helianto.core.Entity;
import org.helianto.process.Document;

/**
 * <code>Document</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface DocumentDao extends CommonOrmDao {
     
    /**
     * Persist <code>Document</code>.
     */
    public void persistDocument(Document document);
    
    /**
     * Merge <code>Document</code>.
     */
    public Document mergeDocument(Document document);
    
    /**
     * Remove <code>Document</code>.
     */
    public void removeDocument(Document document);
    
    /**
     * Find <code>Document</code> by <code>Entity</code> and docCode.
     */
    public Document findDocumentByNaturalId(Entity entity, String docCode);
    
    /**
     * Find <code>Document</code> by criteria.
     */
    public List<Document> findDocumentByCriteria(String criteria);
    
    
    
}
