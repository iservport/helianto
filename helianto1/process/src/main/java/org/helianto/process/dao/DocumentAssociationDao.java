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

import org.helianto.core.dao.CommonOrmDao;
import org.helianto.process.Document;
import org.helianto.process.DocumentAssociation;


/**
 * <code>DocumentAssociation</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface DocumentAssociationDao extends CommonOrmDao {
     
    /**
     * Remove <code>DocumentAssociation</code>.
     */
    public void removeDocumentAssociation(DocumentAssociation documentAssociation);
    
    /**
     * Find <code>DocumentAssociation</code> by <code>Document</code> and <code>Document</code>.
     */
    public DocumentAssociation findDocumentAssociationByNaturalId(Document parent, Document child);
    
    
    
}
