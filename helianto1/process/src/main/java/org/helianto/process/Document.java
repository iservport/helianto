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

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.helianto.core.Entity;
/**
 * <p>
 * Represents a <code>Document</code>.  
 * </p>
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="proc_document",
    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "docCode"})}
)
@Inheritance(strategy = InheritanceType.JOINED)
public class Document implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private Entity entity;
    private String docCode;
    private int version;
    private String docName;
    private String docFile;
    private Set<DocumentAssociation> parentAssociations = new HashSet<DocumentAssociation>();
    private Set<DocumentAssociation> childAssociations = new HashSet<DocumentAssociation>();

    /** default constructor */
    public Document() {
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
     * Entity getter.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="entityId", nullable=true)
    public Entity getEntity() {
        return this.entity;
    }
    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    /**
     * DocCode getter.
     */
    @Column(length=24)
    public String getDocCode() {
        return this.docCode;
    }
    /**
     * DocCode setter.
     */
    public void setDocCode(String docCode) {
        this.docCode = docCode;
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
     * DocName getter.
     */
    @Column(length=128)
    public String getDocName() {
        return this.docName;
    }
    /**
     * DocName setter.
     */
    public void setDocName(String docName) {
        this.docName = docName;
    }

    /**
     * DocFile getter.
     */
    @Column(length=128)
    public String getDocFile() {
        return this.docFile;
    }
    /**
     * DocFile setter.
     */
    public void setDocFile(String docFile) {
        this.docFile = docFile;
    }

    /**
     * ParentAssociations getter.
     */
    @OneToMany(mappedBy="parent", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    public Set<DocumentAssociation> getParentAssociations() {
        return this.parentAssociations;
    }
    /**
     * ParentAssociations setter.
     */
    public void setParentAssociations(Set<DocumentAssociation> parentAssociations) {
        this.parentAssociations = parentAssociations;
    }

    /**
     * ChildAssociations getter.
     */
    @OneToMany(mappedBy="child", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    public Set<DocumentAssociation> getChildAssociations() {
        return this.childAssociations;
    }
    /**
     * ChildAssociations setter.
     */
    public void setChildAssociations(Set<DocumentAssociation> childAssociations) {
        this.childAssociations = childAssociations;
    }

    //1
    /**
     * <code>Document</code> factory.
     * 
     * @param entity
     * @param docCode
     */
    public static Document documentFactory(Entity entity, String docCode) {
        return documentFactory(Document.class, entity, docCode);
    }

    //2
    /**
     * <code>Document</code> general factory.
     * 
     * @param entity
     * @param docCode
     */
    public static <T extends Document> T documentFactory(Class<T> clazz, Entity entity, String docCode) {
        T document = null;
        try {
            document = clazz.newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to create document of class "+clazz);
        }
        document.setEntity(entity);
        document.setDocCode(docCode);
        return document;
    }

    //3
    /**
     * <code>Document</code> general factory.
     * 
     * @param clazz
     * @param parent
     * @param docCode
     * @param associationType
     */
    public static <T extends Document> T documentFactory(Class<T> clazz, Document parent, String docCode, double coefficient, AssociationType associationType) {
        T document = documentFactory(clazz, parent.getEntity(), docCode);
        DocumentAssociation association = DocumentAssociation.documentAssociationFactory(parent, document);
        association.setCoefficient(coefficient);
        association.setAssociationType(associationType.getValue());
        return document;
    }

    /**
     * <code>Document</code> query <code>StringBuilder</code>.
     */
    @Transient
    public static StringBuilder getDocumentQueryStringBuilder() {
        return new StringBuilder("select document from Document document ");
    }

    /**
     * <code>Document</code> natural id query.
     */
    @Transient
    public static String getDocumentNaturalIdQueryString() {
        return getDocumentQueryStringBuilder().append("where document.entity = ? and document.docCode = ? ").toString();
    }

    /**
     * <code>Document</code> all records query.
     * @deprecated use getDocumentQueryStringBuilder()
     */
    @Transient
    public static String getDocumentAllQueryString() {
        return "select document from Document document where ";
    }

    /**
     * toString
     * @return String
     */
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("entity").append("='").append(getEntity()).append("' ");
        buffer.append("docCode").append("='").append(getDocCode()).append("' ");
        buffer.append("]");
      
        return buffer.toString();
    }

   /**
    * equals
    */
   @Override
   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
         if ( (other == null ) ) return false;
         if ( !(other instanceof Document) ) return false;
         Document castOther = (Document) other; 
         
         return ((this.getEntity()==castOther.getEntity()) || ( this.getEntity()!=null && castOther.getEntity()!=null && this.getEntity().equals(castOther.getEntity()) ))
             && ((this.getDocCode()==castOther.getDocCode()) || ( this.getDocCode()!=null && castOther.getDocCode()!=null && this.getDocCode().equals(castOther.getDocCode()) ));
   }
   
   /**
    * hashCode
    */
   @Override
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getEntity() == null ? 0 : this.getEntity().hashCode() );
         result = 37 * result + ( getDocCode() == null ? 0 : this.getDocCode().hashCode() );
         return result;
   }   

}
