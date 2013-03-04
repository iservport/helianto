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

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.helianto.core.base.AbstractAssociation;
import org.helianto.process.def.AssociationRole;
import org.helianto.process.def.AssociationType;

/**
 * <p>
 * Represents a <code>DocumentAssociation</code>.  
 * </p>
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="proc_documentAssoc",
    uniqueConstraints = {@UniqueConstraint(columnNames={"parentId", "childId"})}
)
@DiscriminatorColumn(
    name="type",
    discriminatorType=DiscriminatorType.CHAR
)
@DiscriminatorValue("A")
public class ProcessDocumentAssociation extends AbstractAssociation<ProcessDocument, ProcessDocument> {

    private static final long serialVersionUID = 1L;
    private char associationType;
    private char associationRole;
    private BigDecimal leftLimit = BigDecimal.ZERO;
	private BigDecimal rightLimit = BigDecimal.ZERO;

	/** 
	 * Default constructor.
	 */
    public ProcessDocumentAssociation() {
    	super();
        setAssociationType(AssociationType.GENERAL);
        setAssociationRole(AssociationRole.NONE);
    }

	/** 
	 * Parent constructor.
	 * 
	 * @param parent
	 */
    public ProcessDocumentAssociation(ProcessDocument parent) {
    	this();
    	setParent(parent);
    }

    /**
     * Parent document (lazy loaded).
     */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="parentId", nullable=true)
    public ProcessDocument getParent() {
        return this.parent;
    }

    /**
     * Child document, require two phase store.
     */
    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="childId", nullable=true)
    public ProcessDocument getChild() {
        return this.child;
    }

    /**
     * Association type.
     */
    public char getAssociationType() {
        return this.associationType;
    }
    public void setAssociationType(char associationType) {
        this.associationType = associationType;
    }
    public void setAssociationType(AssociationType associationType) {
        this.associationType = associationType.getValue();
    }

    /**
     * Association role.
     */
    public char getAssociationRole() {
		return associationRole;
	}
	public void setAssociationRole(char associationRole) {
		this.associationRole = associationRole;
	}
	public void setAssociationRole(AssociationRole associationRole) {
		this.associationRole = associationRole.getValue();
	}
	@Transient
	public boolean isControlled() {
		return (associationRole!=AssociationRole.NONE.getValue());
	}

	/**
     * Associated left limit.
     */
    public BigDecimal getLeftLimit() {
		return leftLimit;
	}
	public void setLeftLimit(BigDecimal leftLimit) {
		this.leftLimit = leftLimit;
	}

	/**
     * Associated right limit.
     */
	public BigDecimal getRightLimit() {
		return rightLimit;
	}
	public void setRightLimit(BigDecimal rightLimit) {
		this.rightLimit = rightLimit;
	}


    //1.1
    /**
     * <code>DocumentAssociation</code> factory.
     * 
     * @param parent
     * @param child
     * @param associationType
     */
    public static ProcessDocumentAssociation documentAssociationFactory(ProcessDocument parent, ProcessDocument child, AssociationType associationType) {
        return ProcessDocumentAssociation.documentAssociationFactory(parent, child, associationType, 0);
    }

    //1.2
    /**
     * <code>DocumentAssociation</code> factory.
     * 
     * @param parent
     * @param child
     * @param associationType
     * @param sequence
     */
    protected static ProcessDocumentAssociation documentAssociationFactory(ProcessDocument parent, ProcessDocument child, AssociationType associationType, int sequence) {
        return documentAssociationFactory(ProcessDocumentAssociation.class, parent, child, associationType, sequence);
    }

    //1.3
    /**
     * <code>DocumentAssociation</code> factory.
     * 
     * @param clazz
     * @param parent
     * @param child
     * @param associationType
     * @param sequence
     */
    protected static ProcessDocumentAssociation documentAssociationFactory(Class<? extends ProcessDocumentAssociation> clazz, ProcessDocument parent, ProcessDocument child, AssociationType associationType, int sequence) {
        ProcessDocumentAssociation documentAssociation;
		try {
			documentAssociation = clazz.newInstance();
	        documentAssociation.setParent(parent);
	        documentAssociation.setChild(child);
	        parent.getChildAssociations().add(documentAssociation);
	        child.getParentAssociations().add(documentAssociation);
	        documentAssociation.setSequence(sequence);
	        documentAssociation.setAssociationType(associationType);
	        return documentAssociation;
		} catch (Exception e) {
			throw new IllegalArgumentException("Unable to create instance of "+clazz, e);
		}
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

   /**
    * equals
    */
   public boolean equals(Object other) {
         if ( !(other instanceof ProcessDocumentAssociation) ) return false;
         return super.equals(other);
   }
   
}
