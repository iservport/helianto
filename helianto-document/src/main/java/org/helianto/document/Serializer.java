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

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
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
 * Wraps a number pattern to be used to generate a sequence of documents.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="doc_doccodebuilder",
	    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "builderCode"})}
	)
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="type",
    discriminatorType=DiscriminatorType.CHAR
)
@DiscriminatorValue("B")
public class Serializer implements Customizer {

	private static final long serialVersionUID = 1L;
    private int id;
    private Entity entity;
    private int version;
	private String builderCode;
	private String numberPattern;
	private String builderName;
    private char contentType;
    private Set<Document> documents = new HashSet<Document>();
    // transient
    private List<Document> documentList;
    
    /** 
     * Default constructor.
     */
    public Serializer() {
    	this(null);
    }

    /** 
     * Entity constructor.
     * 
     * @param entity
     */
    public Serializer(Entity entity) {
    	this(entity, "");
    }

    /** 
     * Key constructor.
     * 
     * @param entity
     * @param builderCode
     */
    public Serializer(Entity entity, String builderCode) {
    	super();
    	setEntity(entity);
    	setBuilderCode(builderCode);
    	setNumberPattern("0000");
    	setBuilderName("");
    	setContentType(' ');
    }

    /**
     * Primary key.
     */
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Version (database record version).
     */
    @Version
    public int getVersion() {
        return this.version;
    }
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * <<NaturalKey>> Entity.
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
     * <<NaturalKey>> Code used to distinguish the builder.
     */
    @Column(length=24)
    public String getBuilderCode() {
        return this.builderCode;
    }
    public void setBuilderCode(String builderCode) {
        this.builderCode = builderCode;
    }

    /**
     * Pattern used in association with <code>SequenceMgr</code>.
     * 
     * <p>
     * Patterns like "P0000" will produce document codes
     * like P0001, P0002, etc., while 0000/'09' builds
     * 0001/09, 0002/09, etc.
     * </p>
     */
    @Column(length=20)
	public String getNumberPattern() {
		return numberPattern;
	}
	public void setNumberPattern(String numberPattern) {
		this.numberPattern = numberPattern;
	}
	
	/**
	 * Builder name.
	 */
    @Column(length=128)
	public String getBuilderName() {
		return builderName;
	}
	public void setBuilderName(String builderName) {
		this.builderName = builderName;
	}
	
    /**
     * Build the code.
     */
	@Transient
	public String buildCode(long internalNumber) {
		return new DecimalFormat(getNumberPattern()).format(internalNumber);
	}

    /**
     * Content type.
     */
	public char getContentType() {
		return contentType;
	}
	public void setContentType(char contentType) {
		this.contentType = contentType;
	}
	
	/**
	 * Document set.
	 */
	@OneToMany(mappedBy="documentCodeBuilder")
	public Set<Document> getDocuments() {
		return documents;
	}
	public void setDocuments(Set<Document> documents) {
		this.documents = documents;
	}
	
	/**
	 * <<Transient>> Document list.
	 */
	@Transient
	public List<Document> getDocumentList() {
		return documentList;
	}
	public void setDocumentList(List<Document> documentList) {
		this.documentList = documentList;
	}
	
    /**
     * toString
     * @return String
     */
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("builderCode").append("='").append(getBuilderCode()).append("' ");
        buffer.append("numberPattern").append("='").append(getNumberPattern()).append("' ");
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
         if ( !(other instanceof Serializer) ) return false;
         Serializer castOther = (Serializer) other; 
         
         return ( ( this.getEntity()==castOther.getEntity()) 
        		    || ( this.getEntity()!=null 
        			     && castOther.getEntity()!=null 
        			     && this.getEntity().equals(castOther.getEntity()
        			   ) 
        		))
             && ( ( this.getBuilderCode()==castOther.getBuilderCode()) 
            	    || ( this.getBuilderCode()!=null 
            	    	 && castOther.getBuilderCode()!=null 
            	    	 && this.getBuilderCode().equals(castOther.getBuilderCode()
            	    	)
            	));
   }
   
   /**
    * hashCode
    */
   @Override
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getBuilderCode() == null ? 0 : this.getBuilderCode().hashCode() );
         return result;
   }   

}
