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

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.helianto.core.domain.Entity;
import org.helianto.document.Event;
import org.helianto.document.base.AbstractCustomDocument;


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
public class Document 
	extends AbstractCustomDocument 
	implements 
	  Event
	, Comparable<Document> 

{

    private static final long serialVersionUID = 1L;
    private Set<DocumentAssociation> parents = new HashSet<DocumentAssociation>(0);
    private Set<DocumentAssociation> children = new HashSet<DocumentAssociation>(0);

	/** 
	 * Default constructor 
	 */
    public Document() {
    	super();
    }

	/** 
	 * Key constructor.
	 * 
	 * @param entity
	 * @param docCode
	 */
    public Document(Entity entity, String docCode) {
    	super(entity, docCode);
    }

    /**
     * Parent document associations.
     */
    @OneToMany(mappedBy="child")
    public Set<DocumentAssociation> getParents() {
        return this.parents;
    }
    public void setParents(Set<DocumentAssociation> parents) {
        this.parents = parents;
    }
    
    /**
     * Child document associations.
     */
    @OneToMany(mappedBy="parent", cascade={CascadeType.ALL})
    public Set<DocumentAssociation> getChildren() {
        return this.children;
    }
    public void setChildren(Set<DocumentAssociation> children) {
        this.children = children;
    }
    
	/**
	 * Sort by docCode.
	 */
	public int compareTo(Document next) {
		return getDocCode().compareTo(next.getDocCode());
	}

	@Override
    public boolean equals(Object other) {
		 if ( !(other instanceof Document) ) return false;
		 return super.equals(other);
    }

}
