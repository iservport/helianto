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

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Unit;
import org.helianto.document.def.InheritanceType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * Base class to a document hierarchy to be used in engineering structures.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="doc_process")
public class ProcessDocument 
	extends Document 
	implements Comparator<DocumentAssociation> {

    private static final long serialVersionUID = 1L;
    private Unit unit;
    private Set<ProcessDocumentKey> processDocumentKeys = new HashSet<ProcessDocumentKey>(0);
    private char inheritanceType = InheritanceType.FINAL.getValue();
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
    @JsonBackReference 
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
    @JsonManagedReference 
    @OneToMany(mappedBy="processDocument", cascade={CascadeType.ALL})
    public Set<ProcessDocumentKey> getProcessDocumentKeys() {
        return this.processDocumentKeys;
    }
    public void setProcessDocumentKeys(Set<ProcessDocumentKey> processDocumentKeys) {
        this.processDocumentKeys = processDocumentKeys;
    }
    
    public int compare(DocumentAssociation first, DocumentAssociation last) {
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
	public void setInheritanceType(InheritanceType inheritanceType) {
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

   /**
    * equals
    */
   @Override
   public boolean equals(Object other) {
         if ( !(other instanceof ProcessDocument) ) return false;
         return super.equals(other);
   }

   @Transient
	public String[] getProcessColorChain() {
		// TODO Auto-generated method stub
		return null;
	}

}
