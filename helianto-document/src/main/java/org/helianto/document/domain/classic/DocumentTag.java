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


package org.helianto.document.domain.classic;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.helianto.document.domain.Document;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Tags applicable to documents.
 * 
 * @author Mauricio Fernandes de Castro
 * @deprecated tag will be only a string
 */
@javax.persistence.Entity
@Table(name="doc_tag",
    uniqueConstraints = {@UniqueConstraint(columnNames={"documentId", "tagCode"})}
)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="type",
    discriminatorType=DiscriminatorType.CHAR
)
@DiscriminatorValue("T")
public class DocumentTag extends AbstractTag implements Serializable, Comparable<DocumentTag> {

	private static final long serialVersionUID = 1L;
	private Document document;
	
	/**
	 * Default constructor.
	 */
	public DocumentTag() {
		super();
	}

	/**
	 * Key constructor.
	 * 
	 * @param document
	 * @param tagCode
	 */
	public DocumentTag(Document document, String tagCode) {
		this();
		setDocument(document);
		setTagCode(tagCode);
	}

	/**
     * Document.
     */
    @JsonBackReference 
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="documentId", nullable=true)
    public Document getDocument() {
        return this.document;
    }
    public void setDocument(Document document) {
        this.document = document;
    }
    
    /**
     * Implements <code>Comparable</code> interface using {@link #getTagCode()}.
     */
    public int compareTo(DocumentTag other) {
    	return this.getTagCode().compareTo(other.getTagCode());
    }

    /**
     * equals
     */
    @Override
    public boolean equals(Object other) {
          if ( (this == other ) ) return true;
          if ( (other == null ) ) return false;
          if ( !(other instanceof DocumentTag) ) return false;
          DocumentTag castOther = (DocumentTag) other; 
          
          return ( ( this.getDocument()==castOther.getDocument()) 
         		    || ( this.getDocument()!=null 
         			     && castOther.getDocument()!=null 
         			     && this.getDocument().equals(castOther.getDocument()
         			   ) 
         		))
              && ( ( this.getTagCode()==castOther.getTagCode()) 
             	    || ( this.getTagCode()!=null 
             	    	 && castOther.getTagCode()!=null 
             	    	 && this.getTagCode().equals(castOther.getTagCode()
             	    	)
             	));
    }
    
}
