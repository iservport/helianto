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

package org.helianto.process.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.helianto.core.domain.Entity;
/**
 * Makes a process document able to inherit associations from ancestors.  
 * <p>
 * Base process documents may be used to create derived documents in a 
 * chain. Modifications on the base document are transmitted to derivations
 * unless they are overriden locally, a concept borrowed from OO programming
 * languages (as Java!).
 * </p>
 * @author Mauricio Fernandes de Castro
 */
@MappedSuperclass
public abstract class DerivedProcessDocument extends ProcessDocument {

    private static final long serialVersionUID = 1L;
    private ProcessDocument parent;

    /** 
     * Default constructor.
     */
    public DerivedProcessDocument() {
    	super();
    }

    /** 
     * Key constructor.
     * 
     * @param entity
     * @param docCode
     */
    public DerivedProcessDocument(Entity entity, String docCode) {
    	this();
    	setEntity(entity);
    	setDocCode(docCode);
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
     * List of child associations.
     */
    @Transient
    public List<ProcessDocumentAssociation> getChildAssociationList() {
    	List<ProcessDocumentAssociation> childAssociationList = new ArrayList<ProcessDocumentAssociation>(this.getChildAssociations());
    	if (getParent()!=null && parent.getChildAssociationList().size()>0) {
    		Map<Integer, ProcessDocumentAssociation> documentAssociationMap = new HashMap<Integer, ProcessDocumentAssociation>();
    		List<ProcessDocumentAssociation> parentAssociations = parent.getChildAssociationList();
    		// copy in documentAssociationMap all associations from local process
    		for(ProcessDocumentAssociation association: childAssociationList) {
        		documentAssociationMap.put(association.getSequence(), association);
    		}
    		// copy in documentAssociationMap only associations that ...
    		for (ProcessDocumentAssociation parentAssociation: parentAssociations) {
    			ProcessDocumentAssociation localAssociation = documentAssociationMap.get(parentAssociation.getSequence());
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
    		childAssociationList = new ArrayList<ProcessDocumentAssociation>(documentAssociationMap.values());
    	}
    	Collections.sort(childAssociationList, this);
		return childAssociationList;
	}
    
    @Transient
    public String[] getProcessColorChain() {
    	Stack<ProcessDocument> processDocumentStack = getProcessDocumentStack();
    	int processStackSize = processDocumentStack.size();
    	String[] processColorChain = new String[processStackSize];
    	for (int i = 0;i<processStackSize;i++) {
    		processColorChain[i] = processDocumentStack.pop().getProcessColor();
    	}
    	return processColorChain;
    }

    @Transient
    public Stack<ProcessDocument> getProcessDocumentStack() {
    	Stack<ProcessDocument> processStack = new Stack<ProcessDocument>();
    	DerivedProcessDocument parent = this;
    	do {
    		processStack.push(parent);
    		try {
        		parent = (DerivedProcessDocument) parent.getParent();
    		}
    		catch (Exception e) {
    			parent = null;
    		}
    	} while (parent!=null);
    	return processStack;
    }

}
