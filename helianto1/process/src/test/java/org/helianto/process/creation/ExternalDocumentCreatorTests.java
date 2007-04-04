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
import org.helianto.process.DocumentType;
import org.helianto.process.ExternalDocument;

import junit.framework.TestCase;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ExternalDocumentCreatorTests extends TestCase {
    
    public void testCreateDocument() {
        Entity entity = new Entity();
        ExternalDocument externalDocument = 
            ExternalDocumentCreator.externalDocumentFactory(entity, "CODE", DocumentType.CATEGORY);
        assertSame(entity, externalDocument.getEntity());
        assertEquals("CODE", externalDocument.getDocCode());
        assertEquals(DocumentType.CATEGORY.getValue(), externalDocument.getDocType());
    }

    public void testCreateDocumentChild() {
        ExternalDocument parent = new ExternalDocument();
        Entity entity = new Entity();
        parent.setEntity(entity);
        ExternalDocument externalDocument = 
            ExternalDocumentCreator.externalDocumentFactory(parent, "CODE", DocumentType.CATEGORY);
        assertSame(entity, externalDocument.getEntity());
        assertEquals("CODE", externalDocument.getDocCode());
        assertEquals(DocumentType.CATEGORY.getValue(), externalDocument.getDocType());
        assertSame(externalDocument, externalDocument.getParentAssociations().iterator().next().getChild());
        assertSame(parent, externalDocument.getParentAssociations().iterator().next().getParent());
        assertSame(externalDocument, parent.getChildAssociations().iterator().next().getChild());
        assertSame(parent, parent.getChildAssociations().iterator().next().getParent());
        assertEquals(0, parent.getChildAssociations().iterator().next().getSequence());
    }

}
