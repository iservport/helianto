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
import org.helianto.process.ProcessDocument;

/**
 * <code>ProcessDocument</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface ProcessDocumentDao extends CommonOrmDao {
     
    /**
     * Persist <code>ProcessDocument</code>.
     */
    public void persistProcessDocument(ProcessDocument processDocument);
    
    /**
     * Merge <code>ProcessDocument</code>.
     */
    public ProcessDocument mergeProcessDocument(ProcessDocument processDocument);
    
    /**
     * Remove <code>ProcessDocument</code>.
     */
    public void removeProcessDocument(ProcessDocument processDocument);
    
    /**
     * Find <code>ProcessDocument</code> by <code>Entity</code> and docCode.
     */
    public ProcessDocument findProcessDocumentByNaturalId(Entity entity, String docCode);
    
    /**
     * Find <code>ProcessDocument</code> by criteria.
     */
    public List<ProcessDocument> findProcessDocuments(String criteria);

	/**
	 * Detach the object from the session.
	 */
	public void evict(ProcessDocument processDocument);    
    
}
