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

package org.helianto.document.base;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.helianto.core.Entity;
import org.helianto.document.Customizable;
import org.helianto.document.Customizer;

/**
 * Base class to wrap a number pattern to be used to generate a sequence of documents.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.MappedSuperclass
public abstract class AbstractSerializer<D extends Customizable> implements Customizer {

	private static final long serialVersionUID = 1L;
    private int id;
    private Entity entity;
    private int version;
	private String builderCode;
	private String numberPattern;
	private String builderName;
    private char contentType;
    private Set<D> documents = new HashSet<D>();
    
    /** 
     * Key constructor.
     * 
     * @param entity
     * @param builderCode
     */
    public AbstractSerializer(Entity entity, String builderCode) {
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
        return getInternalBuilderCode();
    }
    public void setBuilderCode(String builderCode) {
        this.builderCode = builderCode;
    }
    
    /**
     * Sublcasses may change the way a builder code is retrieved.
     */
    @Transient
    protected String getInternalBuilderCode() {
        return this.builderCode;
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
		return getInternalBuilderName();
	}
	public void setBuilderName(String builderName) {
		this.builderName = builderName;
	}
	
    /**
     * Sublcasses may change the way a builder code is retrieved.
     */
    @Transient
    protected String getInternalBuilderName() {
    	return builderName;
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
	@OneToMany(mappedBy="series")
	public Set<D> getDocuments() {
		return documents;
	}
	public void setDocuments(Set<D> documents) {
		this.documents = documents;
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
         if ( !(other instanceof AbstractSerializer) ) return false;
         @SuppressWarnings("unchecked")
		AbstractSerializer<D> castOther = (AbstractSerializer<D>) other; 
         
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
