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

package org.helianto.document;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.helianto.core.AbstractAssociation;


/**
 * Document parent-child associations.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="doc_assoc",
    uniqueConstraints = {@UniqueConstraint(columnNames={"parentId", "childId"})}
)
public class DocumentAssociation extends AbstractAssociation<Document, Document> {

    /**
     * Factory method.
     * 
     * @param parent
     * @param child
     */
    public static DocumentAssociation documentAssociationFactory(Document parent, Document child) {
    	DocumentAssociation documentAssociation = (DocumentAssociation) AbstractAssociation.associationFactory(DocumentAssociation.class, parent, child);
    	child.getParents().add(documentAssociation);
    	parent.getChildren().add(documentAssociation);
        return documentAssociation;
    }
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Grupo de documentos associado.
     * @see {@link UserDocumentGroup}
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="parentId", nullable=true)
	public Document getParent() {
		return parent;
	}
    
    /**
     * Documento de usuário associado.
     * @see {@link AbstractDocument}
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="childId", nullable=true)
	public Document getChild() {
		return child;
	}

}
