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
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.helianto.core.AbstractKeyStringValue;
import org.helianto.core.KeyType;
/**
 * The content of a key associated to the document.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="doc_docKey",
    uniqueConstraints = {@UniqueConstraint(columnNames={"documentId", "keyTypeId"})}
)
public class DocumentKey extends AbstractKeyStringValue {

	/**
	 * <<Transient>> Delegate to the actual key owner.
	 */
	@Transient
	@Override
	protected Object getKeyOwner() {
		return getDocument();
	}   

    /**
     * Factory method.
     * 
     * @param document
     * @param keyType
     */
    public static DocumentKey documentKeyFactory(Document document, KeyType keyType) {
    	DocumentKey documentKey = new DocumentKey();
        documentKey.setDocument(document);
        documentKey.setKeyType(keyType);
        return documentKey;
    }

    private static final long serialVersionUID = 1L;
    private Document document;

    /** 
     * Default constructor.
     */
    public DocumentKey() {
    	super();
    }

    /**
     * Document.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="documentId", nullable=true)
    public Document getDocument() {
        return this.document;
    }
    public void setDocument(Document document) {
        this.document = document;
    }

   /**
    * equals
    */
   public boolean equals(Object other) {
         if (other instanceof DocumentKey) {
        	 return super.equals(other);
         }
         return false;
   }
   
}
