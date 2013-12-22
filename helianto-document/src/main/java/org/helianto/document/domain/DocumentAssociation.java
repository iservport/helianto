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
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.helianto.core.internal.AbstractAssociation;

import com.fasterxml.jackson.annotation.JsonBackReference;


/**
 * Document parent-child associations.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="doc_assoc",
    uniqueConstraints = {@UniqueConstraint(columnNames={"parentId", "childId"})}
)
public class DocumentAssociation 
	extends AbstractAssociation<Document, Document> 
{

    private static final long serialVersionUID = 1L;
    
    /**
     * Parent and child constructor.
     * 
     * @param parent
     * @param child
     */
    public DocumentAssociation(Document parent, Document child) {
		super();
		setParent(parent);
		setChild(child);
	}
    
    /**
     * Associated parent document.
     */
    @JsonBackReference("parent")
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="parentId", nullable=true)
	public Document getParent() {
		return parent;
	}
    
    /**
     * Associated child document.
     */
    @JsonBackReference("child")
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="childId", nullable=true)
	public Document getChild() {
		return child;
	}

    /**
     * Natural key info.
     */
    @Transient
    public boolean isKeyEmpty() {
    	if (this.getChild()!=null) {
    		return this.getChild().isKeyEmpty();
    	}
    	throw new IllegalArgumentException("Natural key must not be null");
    }

}
