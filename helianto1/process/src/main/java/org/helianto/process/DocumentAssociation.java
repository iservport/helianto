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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
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
public class DocumentAssociation implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private Document parent;
    private Document child;
    private int version;
    private int sequence;
    private char associationType;
    private double coefficient;

    /** default constructor */
    public DocumentAssociation() {
    }

    // Property accessors
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Parent getter.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="parentId", nullable=true)
    public Document getParent() {
        return this.parent;
    }
    /**
     * Parent setter.
     */
    public void setParent(Document parent) {
        this.parent = parent;
    }

    /**
     * Child getter.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="childId", nullable=true)
    public Document getChild() {
        return this.child;
    }
    /**
     * Child setter.
     */
    public void setChild(Document child) {
        this.child = child;
    }

    /**
     * Version getter.
     */
    @Version
    public int getVersion() {
        return this.version;
    }
    /**
     * Version setter.
     */
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * Sequence getter.
     */
    public int getSequence() {
        return this.sequence;
    }
    /**
     * Sequence setter.
     */
    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    /**
     * AssociationType getter.
     */
    public char getAssociationType() {
        return this.associationType;
    }
    /**
     * AssociationType setter.
     */
    public void setAssociationType(char associationType) {
        this.associationType = associationType;
    }

    /**
     * Coefficient getter.
     */
    public double getCoefficient() {
        return this.coefficient;
    }
    /**
     * Coefficient setter.
     */
    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    /**
     * <code>DocumentAssociation</code> factory.
     * 
     * @param parent
     * @param child
     */
    public static DocumentAssociation documentAssociationFactory(Document parent, Document child) {
        DocumentAssociation documentAssociation = new DocumentAssociation();
        documentAssociation.setParent(parent);
        documentAssociation.setChild(child);
        parent.getChildAssociations().add(documentAssociation);
        child.getParentAssociations().add(documentAssociation);
        return documentAssociation;
    }

    /**
     * <code>DocumentAssociation</code> natural id query.
     */
    @Transient
    public static String getDocumentAssociationNaturalIdQueryString() {
        return "select documentAssociation from DocumentAssociation documentAssociation where documentAssociation.parent = ? and documentAssociation.child = ? ";
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
        buffer.append("]");
      
        return buffer.toString();
    }

   /**
    * equals
    */
   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
         if ( (other == null ) ) return false;
         if ( !(other instanceof DocumentAssociation) ) return false;
         DocumentAssociation castOther = (DocumentAssociation) other; 
         
         return ((this.getParent()==castOther.getParent()) || ( this.getParent()!=null && castOther.getParent()!=null && this.getParent().equals(castOther.getParent()) ))
             && ((this.getChild()==castOther.getChild()) || ( this.getChild()!=null && castOther.getChild()!=null && this.getChild().equals(castOther.getChild()) ));
   }
   
   /**
    * hashCode
    */
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getParent() == null ? 0 : this.getParent().hashCode() );
         result = 37 * result + ( getChild() == null ? 0 : this.getChild().hashCode() );
         return result;
   }   

}
