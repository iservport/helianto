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

package org.helianto.document.domain;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.helianto.core.domain.KeyType;
import org.helianto.core.internal.AbstractKeyStringValue;
/**
 * The content of a key associated to the process document.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="doc_processKey",
    uniqueConstraints = {@UniqueConstraint(columnNames={"processDocumentId", "keyTypeId"})}
)
public class ProcessDocumentKey 
	extends AbstractKeyStringValue {

	/**
	 * <<Transient>> Delegate to the actual key owner.
	 */
	@Transient
	@Override
	protected Object getKeyOwner() {
		return getProcessDocument();
	}   

    private static final long serialVersionUID = 1L;
    private ProcessDocument processDocument;

    /** 
     * Empty constructor.
     */
    public ProcessDocumentKey() {
    	super();
    }

    /**
     * Key constructor.
     * 
     * @param document
     * @param keyType
     */
    public ProcessDocumentKey(ProcessDocument document, KeyType keyType) {
    	this();
        setProcessDocument(document);
        setKeyType(keyType);
    }

    /** 
     * Preferred constructor.
     * 
     * @param processDocument
     */
    public ProcessDocumentKey(ProcessDocument processDocument) {
    	super();
    	setProcessDocument(processDocument);
    }

    /**
     * Process document.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="processDocumentId", nullable=true)
    public ProcessDocument getProcessDocument() {
        return this.processDocument;
    }
    public void setProcessDocument(ProcessDocument processDocument) {
        this.processDocument = processDocument;
    }

   /**
    * equals
    */
   public boolean equals(Object other) {
         if (other instanceof ProcessDocumentKey) {
        	 return super.equals(other);
         }
         return false;
   }
   
}
