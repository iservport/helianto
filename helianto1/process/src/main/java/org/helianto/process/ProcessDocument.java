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

package org.helianto.process;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.helianto.core.Entity;
/**
 * <p>
 * Represents a document to be used in processes.  
 * </p>
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="proc_document",
    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "docCode"})}
)
@Inheritance(strategy = InheritanceType.JOINED)
public class ProcessDocument extends Document implements java.io.Serializable, Comparator<DocumentAssociation> {

    private static final long serialVersionUID = 1L;
    private ProcessDocument parent;
    private Set<DocumentAssociation> parentAssociations = new HashSet<DocumentAssociation>();
    private Set<DocumentAssociation> childAssociations = new HashSet<DocumentAssociation>();

    /** default constructor */
    public ProcessDocument() {
    }

    /**
	 * Parent process document
	 */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="parentId", nullable=true)
	public ProcessDocument getParent() {
		return parent;
	}
	public void setParent(ProcessDocument parent) {
		this.parent = parent;
	}

    /**
     * ParentAssociations getter.
     */
    @OneToMany(mappedBy="child", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    public Set<DocumentAssociation> getParentAssociations() {
        return this.parentAssociations;
    }
    public void setParentAssociations(Set<DocumentAssociation> parentAssociations) {
        this.parentAssociations = parentAssociations;
    }

    /**
     * ChildAssociations getter.
     */
    @OneToMany(mappedBy="parent", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    public Set<DocumentAssociation> getChildAssociations() {
        return this.childAssociations;
    }
    public void setChildAssociations(Set<DocumentAssociation> childAssociations) {
        this.childAssociations = childAssociations;
    }
	/**
     * List of child associations.
     */
    @Transient
    public List<DocumentAssociation> getChildAssociationList() {
    	List<DocumentAssociation> childAssociationList = new ArrayList<DocumentAssociation>(this.getChildAssociations());
    	if (getParent()!=null && parent.getChildAssociationList().size()>0) {
    		Map<Integer, DocumentAssociation> documentAssociationMap = new HashMap<Integer, DocumentAssociation>();
    		List<DocumentAssociation> parentAssociations = parent.getChildAssociationList();
    		// copy in documentAssociationMap all associations from local process
    		for(DocumentAssociation association: childAssociationList) {
        		documentAssociationMap.put(association.getSequence(), association);
    		}
    		// copy in documentAssociationMap only associations that ...
    		for (DocumentAssociation parentAssociation: parentAssociations) {
    			DocumentAssociation localAssociation = documentAssociationMap.get(parentAssociation.getSequence());
    			// do not exist as local,
    			if (localAssociation==null) {
        			documentAssociationMap.put(parentAssociation.getSequence(), parentAssociation);
    			}
    			// or is marked incomplete
    			else if (false) {
    				// TODO incomplete associations must result in a combination
    				// between parent and local associations
    				// for those who are familiar with OO programming (!)
    				// incomplete is a kind of abstract association that
    				// provides only part of the information required by the process
    			}
    			// or is marked suppressing
    			else if (false) {
    				// TODO a suppressing local association
    				// removes the parent association from the resulting list
    				documentAssociationMap.remove(localAssociation.getSequence());
    			}
    		}
    		// replace the previously computed list with the complete one
    		childAssociationList = new ArrayList<DocumentAssociation>(documentAssociationMap.values());
    	}
    	Collections.sort(childAssociationList, this);
		return childAssociationList;
	}

    public int compare(DocumentAssociation first, DocumentAssociation last) {
    	return first.getSequence() - last.getSequence();
    }
       
    //1.1
    /**
     * <code>ProcessDocument</code> factory.
     * 
     * @param entity
     * @param docCode
     */
    public static ProcessDocument processDocumentFactory(Entity entity, String docCode) {
        return Document.documentFactory(ProcessDocument.class, entity, docCode);
    }

    //1.2
    /**
     * <code>ProcessDocument</code> general factory.
     * 
     * @param child
     * @param sequence
     */
    public DocumentAssociation documentAssociationFactory(ProcessDocument child, int sequence) {
        DocumentAssociation association = DocumentAssociation.documentAssociationFactory(this, child, AssociationType.GENERAL, sequence);
        return association;
    }

    //1.3
    /**
     * <code>ProcessDocument</code> general factory.
     * 
     * <p>
     * Create a new child <code>ProcessDocument</code> and a new
     * <code>DocumentAssociation</code> to contain it.
     * </p>
     * 
     * @param clazz
     * @param docCode
     * @param sequence
     */
    protected <T extends ProcessDocument> DocumentAssociation documentAssociationFactory(Class<T> childClazz, String childCode, int sequence) {
    	ProcessDocument document = ProcessDocument.documentFactory(childClazz, getEntity(), childCode);
        DocumentAssociation association = DocumentAssociation.documentAssociationFactory(this, document, AssociationType.GENERAL, sequence);
        return association;
    }

    /**
     * <code>ProcessDocument</code> query <code>StringBuilder</code>.
     */
    @Transient
    public static StringBuilder getProcessDocumentQueryStringBuilder() {
        return new StringBuilder("select processDocument from ProcessDocument processDocument ");
    }

    /**
     * <code>ProcessDocument</code> natural id query.
     */
    @Transient
    public static String getProcessDocumentNaturalIdQueryString() {
        return getProcessDocumentQueryStringBuilder().append("where processDocument.entity = ? and processDocument.docCode = ? ").toString();
    }

   /**
    * equals
    */
   @Override
   public boolean equals(Object other) {
         if ( !(other instanceof ProcessDocument) ) return false;
         return super.equals(other);
   }

}
