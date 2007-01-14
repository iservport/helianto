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

package org.helianto.process.creation;

import org.helianto.core.Entity;
import org.helianto.process.ExternalDocument;
import org.helianto.process.Tree;
import org.helianto.process.type.DocumentType;

/**
 * <code>ExternalDocument</code> factory methods.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ExternalDocumentCreator extends AbstractDocumentCreator {

    /**
     * Creates an <code>ExternalDocument</code>.
     * 
     * @param entity
     * @param documentCode
     * @param documentType
     */
    public static ExternalDocument externalDocumentFactory(Entity entity, String documentCode, DocumentType documentType) {
        ExternalDocument externalDocument = (ExternalDocument) documentFactory(ExternalDocument.class, entity, documentCode);
        externalDocument.setDocType(documentType.getValue());
        return externalDocument;
    }
    
    /**
     * Creates an <code>ExternalDocument</code> and the corresponding
     * <code>Tree</code> to represent the association to a parent.
     * 
     * @param parent
     * @param documentCode
     * @param documentType
     */
    public static ExternalDocument externalDocumentFactory(ExternalDocument parent, String documentCode, DocumentType documentType) {
        ExternalDocument externalDocument = (ExternalDocument) documentFactory(ExternalDocument.class, parent.getEntity(), documentCode);
        externalDocument.setDocType(documentType.getValue());
        associationFactory(parent, externalDocument, 0);
        return externalDocument;
    }
    
}
