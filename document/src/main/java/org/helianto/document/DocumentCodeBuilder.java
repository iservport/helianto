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

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.Format;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.helianto.core.Entity;

/**
 * Represents groups of documents having a common docCode pattern.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="doc_doccodebuilder",
	    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "prefix"})}
	)
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class DocumentCodeBuilder implements Serializable {

	private static final long serialVersionUID = 1L;
    private int id;
    private Entity entity;
    private int version;
	private String prefix;
	private String numberPattern;
    
    /** 
     * Default constructor
     */
    public DocumentCodeBuilder() {
    	super();
    	setPrefix("");
    	setNumberPattern("0000");
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
     * Entity.
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
     * Prefix.
     */
    @Column(length=12)
    public String getPrefix() {
        return this.prefix;
    }
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * Number pattern.
     */
    @Column(length=64)
	public String getNumberPattern() {
		return numberPattern;
	}
	public void setNumberPattern(String numberPattern) {
		this.numberPattern = numberPattern;
	}
	
    /**
     * Build the code.
     */
	@Transient
	public String buildCode(long internalNumber) {
		Format formatter = new DecimalFormat(getNumberPattern());
		return new StringBuilder(getPrefix().trim())
		    .append(formatter.format(internalNumber)).toString();
	}

    /**
     * toString
     * @return String
     */
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("prefix").append("='").append(getPrefix()).append("' ");
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
         if ( !(other instanceof AbstractDocument) ) return false;
         DocumentCodeBuilder castOther = (DocumentCodeBuilder) other; 
         
         return ( ( this.getEntity()==castOther.getEntity()) 
        		    || ( this.getEntity()!=null 
        			     && castOther.getEntity()!=null 
        			     && this.getEntity().equals(castOther.getEntity()
        			   ) 
        		))
             && ( ( this.getPrefix()==castOther.getPrefix()) 
            	    || ( this.getPrefix()!=null 
            	    	 && castOther.getPrefix()!=null 
            	    	 && this.getPrefix().equals(castOther.getPrefix()
            	    	)
            	));
   }
   
   /**
    * hashCode
    */
   @Override
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getPrefix() == null ? 0 : this.getPrefix().hashCode() );
         return result;
   }   

}
