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

import org.helianto.core.hibernate.GenericDaoImpl;
import org.helianto.process.Document;
import org.helianto.process.DocumentAssociation;
import org.helianto.process.dao.DocumentAssociationDao;

/**
 * Default implementation of <code>DocumentAssociation</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class DocumentAssociationDaoImpl extends GenericDaoImpl implements DocumentAssociationDao {
     
    public void removeDocumentAssociation(DocumentAssociation documentAssociation) {
        if (logger.isDebugEnabled()) {
            logger.debug("Removing "+documentAssociation);
        }
        remove(documentAssociation);
    }
    
    public DocumentAssociation findDocumentAssociationByNaturalId(Document parent, Document child) {
        if (logger.isDebugEnabled()) {
            logger.debug("Finding unique documentAssociation with parent='"+parent+"' and child='"+child+"' ");
        }
        return (DocumentAssociation) findUnique(DocumentAssociation.getDocumentAssociationNaturalIdQueryString(), parent, child);
    }
    
    
}
