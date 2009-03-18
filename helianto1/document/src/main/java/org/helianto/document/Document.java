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

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.helianto.core.Entity;

/**
 * Concrete documents.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="doc_doc",
    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "docCode"})}
)
@Inheritance(strategy=InheritanceType.JOINED)
public class Document extends AbstractDocument implements java.io.Serializable {

    /**
     * Factory method.
     * 
     * @param entity
     * @param docCode
     */
    public static Document userDocumentFactory(Entity entity, String docCode) {
        Document userDocument = AbstractDocument.documentFactory(Document.class, entity, docCode);
        return userDocument;
    }
    
    private static final long serialVersionUID = 1L;
    private char contentType;

	/** 
	 * Default constructor 
	 */
    public Document() {
    	super();
    }

    /**
     * Content type.
     */
	public char getContentType() {
		return contentType;
	}
	public void setContentType(char contentType) {
		this.contentType = contentType;
	}
    /**
     * True if document is editable.
     */
    @Transient
    public boolean isEditable() {
    	return true;
    }
    
    /**
     * <code>UserDocument</code> query <code>StringBuilder</code>.
     * @deprecated
     */
    @Transient
    public static StringBuilder getUserDocumentQueryStringBuilder() {
        return new StringBuilder("select userDocument from UserDocument userDocument ");
    }

    /**
     * <code>UserDocument</code> natural id query.
     * @deprecated
     */
    @Transient
    public static String getUserDocumentNaturalIdQueryString() {
        return getUserDocumentQueryStringBuilder().append("where userDocument.entiy = ? and userDocument.docCode = ? ").toString();
    }

    @Override
    public boolean equals(Object other) {
		 if ( !(other instanceof Document) ) return false;
		 return super.equals(other);
    }

}
