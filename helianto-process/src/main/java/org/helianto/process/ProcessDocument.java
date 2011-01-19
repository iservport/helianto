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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.helianto.core.Entity;
import org.helianto.core.Unit;
import org.helianto.document.AbstractDocument;

/**
 * Base class to a document hierarchy to be used in engineering structures.  
 * <p>
 * Concrete classes based on <code>ProcessDocument</code> are
 * allowed to participate in complex product and process structures just
 * adding appropriate parent and child associations. See {@link ProcessDocumentAssociation}
 * and {@link AssociationType} for restrictions on structure creation.
 * </p>
 * <p>
 * Having a common base class makes the API service layer free to take full 
 * advantage of polimorphism to organize trees. The key value of such
 * concept is flexibility. Many different industries require the 
 * engineering structure to be created in a particular manner. Some focus on the
 * production process first and then associate parts to be manufactured in 
 * accordance to that process. Other prefer to develop a process for a given
 * part, in some cases, group of parts. Processes may have characteristics, 
 * as well as parts.
 * <p>
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="proc_document",
    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "docCode"})}
)
@Inheritance(strategy = InheritanceType.JOINED)
public class ProcessDocument extends AbstractDocument implements Comparator<ProcessDocumentAssociation> {

    private static final long serialVersionUID = 1L;
    private Unit unit;
    private Set<ProcessDocumentKey> processDocumentKeys = new HashSet<ProcessDocumentKey>(0);
	private Set<ProcessDocumentAssociation> parentAssociations = new HashSet<ProcessDocumentAssociation>();
    private Set<ProcessDocumentAssociation> childAssociations = new HashSet<ProcessDocumentAssociation>();
    private char inheritanceType = org.helianto.process.InheritanceType.FINAL.getValue();
    private String processColor = "";


    /** 
	 * Default constructor.
	 */
    public ProcessDocument() {
    	super();
    }

    /** 
	 * Key constructor.
	 * 
	 * @param entity
	 * @param docCode
	 */
    public ProcessDocument(Entity entity, String docCode) {
    	this();
    	setEntity(entity);
    	setDocCode(docCode);
    }

    /**
     * Unit.
     * 
     * <p>
     * Process documents often collaborate in material flows. In
     * this case, it is appropriate to define a default unit to
     * the Inventory class.
     * </p>
     */
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="unitId")
    public Unit getUnit() {
		return unit;
	}
	public void setUnit(Unit unit) {
		this.unit = unit;
	}

    /**
     * Process document keys.
     */
    @OneToMany(mappedBy="processDocument", cascade={CascadeType.ALL})
    public Set<ProcessDocumentKey> getProcessDocumentKeys() {
        return this.processDocumentKeys;
    }
    public void setProcessDocumentKeys(Set<ProcessDocumentKey> processDocumentKeys) {
        this.processDocumentKeys = processDocumentKeys;
    }
    
    /**
     * Association to parent members.
     */
    @OneToMany(mappedBy="child", 
    		   cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    public Set<ProcessDocumentAssociation> getParentAssociations() {
        return this.parentAssociations;
    }
    public void setParentAssociations(Set<ProcessDocumentAssociation> parentAssociations) {
        this.parentAssociations = parentAssociations;
    }

    /**
     * Association to child members.
     * 
     * <p>
     * If this document is an instance of Process, its child
     * associations may be, for example, operations to define
     * the process sequence. As each operation is also an instance
     * of this class, it may be associated to a set of characteristics.
     * </p>
     */
    @OneToMany(mappedBy="parent", 
    		   cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    public Set<ProcessDocumentAssociation> getChildAssociations() {
        return this.childAssociations;
    }
    public void setChildAssociations(Set<ProcessDocumentAssociation> childAssociations) {
        this.childAssociations = childAssociations;
    }
	/**
     * List of child associations.
     */
    @Transient
    public List<ProcessDocumentAssociation> getChildAssociationList() {
    	List<ProcessDocumentAssociation> childAssociationList = new ArrayList<ProcessDocumentAssociation>(this.getChildAssociations());
    	Collections.sort(childAssociationList, this);
		return childAssociationList;
	}

    public int compare(ProcessDocumentAssociation first, ProcessDocumentAssociation last) {
    	return first.getSequence() - last.getSequence();
    }
       
	/**
	 * Inheritance type.
	 */
	public char getInheritanceType() {
		return inheritanceType;
	}
	public void setInheritanceType(char inheritanceType) {
		this.inheritanceType = inheritanceType;
	}
	public void setInheritanceType(org.helianto.process.InheritanceType inheritanceType) {
		this.inheritanceType = inheritanceType.getValue();
	}

	/**
	 * Optional 3-byte hexadecimal color (RGB).
	 */
	@Column(length=6)
	public String getProcessColor() {
		return processColor;
	}
    public void setProcessColor(String processColor) {
		this.processColor = processColor;
	}

	//1.0
    /**
     * <code>ProcessDocument</code> factory.
     * 
     * @param entity
     * @param docCode
     */
    public static ProcessDocument processDocumentFactory(Entity entity, String docCode) {
        return AbstractDocument.documentFactory(ProcessDocument.class, entity, docCode);
    }

    //1.2
    /**
     * <code>ProcessDocument</code> general factory.
     * 
     * @param child
     * @param sequence
     */
    public ProcessDocumentAssociation documentAssociationFactory(ProcessDocument child, int sequence) {
        ProcessDocumentAssociation association = ProcessDocumentAssociation.documentAssociationFactory(this, child, AssociationType.GENERAL, sequence);
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
    protected <T extends ProcessDocument> ProcessDocumentAssociation documentAssociationFactory(Class<T> childClazz, String childCode, int sequence) {
    	ProcessDocument document = ProcessDocument.documentFactory(childClazz, getEntity(), childCode);
    	AssociationType associationType = AssociationType.resolveAssociationType(getClass(), childClazz);
        ProcessDocumentAssociation association = ProcessDocumentAssociation.documentAssociationFactory(this, document, associationType, sequence);
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
