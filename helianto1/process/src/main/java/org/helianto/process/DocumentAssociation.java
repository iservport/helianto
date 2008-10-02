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

import javax.persistence.CascadeType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.helianto.core.AbstractAssociation;
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
@Inheritance(strategy = InheritanceType.JOINED)
public class DocumentAssociation extends AbstractAssociation<ProcessDocument, ProcessDocument> {

    private static final long serialVersionUID = 1L;
    private char associationType;
    private double coefficient;

	/** default constructor */
    public DocumentAssociation() {
    }

    /**
     * Parent document.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="parentId", nullable=true)
    public ProcessDocument getParent() {
        return this.parent;
    }

    /**
     * Child document.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
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
     * Coefficient.
     */
    public double getCoefficient() {
        return this.coefficient;
    }
    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    //1.1
    /**
     * <code>DocumentAssociation</code> factory.
     * 
     * @param parent
     * @param child
     */
    public static DocumentAssociation documentAssociationFactory(ProcessDocument parent, ProcessDocument child) {
        return DocumentAssociation.documentAssociationFactory(parent, child, 0);
    }

    //1.2
    /**
     * <code>DocumentAssociation</code> factory.
     * 
     * @param parent
     * @param child
     * @param sequence
     */
    protected static DocumentAssociation documentAssociationFactory(ProcessDocument parent, ProcessDocument child, int sequence) {
    	AssociationType associationType = AssociationType.resolveAssociationType(parent.getClass(), child.getClass());
    	if (associationType==null) {
    		throw new IllegalArgumentException("Invalid association");
    	}
        DocumentAssociation documentAssociation = new DocumentAssociation();
        documentAssociation.setParent(parent);
        documentAssociation.setChild(child);
        parent.getChildAssociations().add(documentAssociation);
        child.getParentAssociations().add(documentAssociation);
        documentAssociation.setSequence(sequence);
        documentAssociation.setAssociationType(associationType);
        return documentAssociation;
    }

    /**
     * <code>Process</code> query <code>StringBuilder</code>.
     */
    @Transient
    public static StringBuilder getDocumentAssociationQueryStringBuilder() {
        return new StringBuilder("select documentAssociation from DocumentAssociation documentAssociation ");
    }

    /**
     * <code>DocumentAssociation</code> natural id query.
     */
    @Transient
    public static String getDocumentAssociationNaturalIdQueryString() {
        return getDocumentAssociationQueryStringBuilder().append("where documentAssociation.parent = ? and documentAssociation.child = ? ").toString();
    }
    
    /**
     * toString
     * @return String
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("parent").append("='").append(getParent()).append("' ");
        buffer.append("child").append("='").append(getChild()).append("' ");
        buffer.append("sequence").append("='").append(getSequence()).append("' ");
        buffer.append("associationType").append("='").append(getAssociationType()).append("' ");
        buffer.append("]");
      
        return buffer.toString();
    }

   /**
    * equals
    */
   public boolean equals(Object other) {
         if ( !(other instanceof DocumentAssociation) ) return false;
         return super.equals(other);
   }
   
}
