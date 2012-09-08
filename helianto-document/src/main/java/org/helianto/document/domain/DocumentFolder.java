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

import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.helianto.core.Entity;
import org.helianto.document.base.AbstractSerializer;

/**
 * Wraps a number pattern to be used to generate a sequence of documents.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="doc_doccodebuilder",
	    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "builderCode"})}
	)
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="type",
    discriminatorType=DiscriminatorType.CHAR
)
@DiscriminatorValue("B")
public class DocumentFolder 

	extends AbstractSerializer<Document> 

{

	private static final long serialVersionUID = 1L;
    // transient
    private List<Document> documentList;
    
    /** 
     * Default constructor.
     */
    public DocumentFolder() {
    	this(null, "");
    }

    /** 
     * Key constructor.
     * 
     * @param entity
     * @param builderCode
     */
    public DocumentFolder(Entity entity, String builderCode) {
    	super(entity, builderCode);
    	setNumberPattern("0000");
    	setFolderName("");
    	setContentType(' ');
    }

	/**
	 * <<Transient>> Document list.
	 */
	@Transient
	public List<Document> getDocumentList() {
		return documentList;
	}
	public void setDocumentList(List<Document> documentList) {
		this.documentList = documentList;
	}
	
   /**
    * equals
    */
   @Override
   public boolean equals(Object other) {
         if ( !(other instanceof DocumentFolder) ) return false;
         return super.equals(other);
   }
   
}