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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
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
public class Document extends AbstractNumberedDocument implements Comparable<Document> {

    /**
     * Factory method.
     * 
     * @param entity
     * @param docCode
     */
    public static Document documentFactory(Entity entity, String docCode) {
        Document document = AbstractDocument.documentFactory(Document.class, entity, docCode);
        return document;
    }
    
    private static final long serialVersionUID = 1L;
    private Set<DocumentKey> documentKeys = new HashSet<DocumentKey>(0);
    private Set<DocumentTag> documentTags = new HashSet<DocumentTag>(0);
    private Set<DocumentAssociation> parents = new HashSet<DocumentAssociation>(0);
    private Set<DocumentAssociation> children = new HashSet<DocumentAssociation>(0);
    // transient
    private List<DocumentKey> documentKeyList;
    private List<DocumentTag> documentTagList;
    private List<DocumentAssociation> parentList;
	private List<DocumentAssociation> childList;

	/** 
	 * Default constructor 
	 */
    public Document() {
    	super();
    }

    /**
     * Document keys.
     */
    @OneToMany(mappedBy="document", cascade={CascadeType.ALL})
    public Set<DocumentKey> getDocumentKeys() {
        return this.documentKeys;
    }
    public void setDocumentKeys(Set<DocumentKey> documentKeys) {
        this.documentKeys = documentKeys;
    }
    
    /**
     * Document key list.
     */
    @Transient
    public List<DocumentKey> getDocumentKeyList() {
		return documentKeyList;
	}
	public void setDocumentKeyList(List<DocumentKey> documentKeyList) {
		this.documentKeyList = documentKeyList;
	}

    /**
     * Document tags.
     */
    @OneToMany(mappedBy="document", cascade={CascadeType.ALL})
    public Set<DocumentTag> getDocumentTags() {
        return this.documentTags;
    }
    public void setDocumentTags(Set<DocumentTag> documentTags) {
        this.documentTags = documentTags;
    }
    
    /**
     * Document tag list.
     */
    @Transient
    public List<DocumentTag> getDocumentTagList() {
		return documentTagList;
	}
	public void setDocumentTagList(List<DocumentTag> documentTagList) {
		this.documentTagList = documentTagList;
	}

    /**
     * Parent document associations.
     */
    @OneToMany(mappedBy="child", cascade={CascadeType.ALL})
    public Set<DocumentAssociation> getParents() {
        return this.parents;
    }
    public void setParents(Set<DocumentAssociation> parents) {
        this.parents = parents;
    }
    
    /**
     * Parent document association list.
     */
    @Transient
    public List<DocumentAssociation> getParentList() {
		return parentList;
	}
	public void setParentList(List<DocumentAssociation> parentList) {
		this.parentList = parentList;
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
     * Child document association list.
     */
    @Transient
	public List<DocumentAssociation> getChildList() {
		return childList;
	}
	public void setChildList(List<DocumentAssociation> childList) {
		this.childList = childList;
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
