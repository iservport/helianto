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

package org.helianto.partner.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.helianto.core.EntityAddress;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.PublicEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Partner registry, a common class to represent Customers, Suppliers and other parties that relate to the owning
 * entity.
 * 
 * @author Mauricio Fernandes de Castro
 */

@javax.persistence.Entity
@DiscriminatorValue("R")
public class PrivateEntity 
	extends PublicEntity
	implements EntityAddress 
{

	/**
	 * Exposes the discriminator.
	 */
	public char getDiscriminator() {
		return 'R';
	}

    private static final long serialVersionUID = 1L;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="publicEntityId", nullable=true)
    private PublicEntity publicEntity;
    
    @Transient
    private Integer publicEntityId;
    
    @Column(length=36)
    private String entityCode;
    
    private boolean autoNumber = false;
    
    @Column(length=512)
    private String parsedContent;
    
    /** 
     * Empty constructor.
     */
    public PrivateEntity() {
    	super();
    }

    /** 
     * Entity constructor.
     * 
     * @param entity
     */
    public PrivateEntity(Entity entity) {
    	this();
    	setEntity(entity);
    }

    /** 
     * Key constructor.
     * 
     * @param entity
     * @param partnerAlias
     */
    public PrivateEntity(Entity entity, String partnerAlias) {
    	this(entity);
    	setEntityAlias(partnerAlias);
    }

    /**
     * True forces entityAlias to be generated from a sequence of numbers.
     */
    public boolean isAutoNumber() {
		return autoNumber;
	}
    public void setAutoNumber(boolean autoNumber) {
		this.autoNumber = autoNumber;
	}

    /**
     * Public entity.
     */
    public PublicEntity getPublicEntity() {
        return this.publicEntity;
    }
    public void setPublicEntity(PublicEntity publicEntity) {
        this.publicEntity = publicEntity;
    }
    
    /**
     * <<Transient>> public entity id.
     */
    public Integer getPublicEntityId() {
    	if (getPublicEntity()!=null) {
    		return getPublicEntity().getId();
    	}
		return publicEntityId;
	}
    public void setPublicEntityId(Integer publicEntityId) {
		this.publicEntityId = publicEntityId;
	}
    
    /**
     * Entity code.
     */
    public String getEntityCode() {
		return entityCode;
	}
    public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}
    
    /**
     * Text content to be parsed on binding to a custom form.
     */
    public String getParsedContent() {
		return parsedContent;
	}
    public void setParsedContent(String parsedContent) {
		this.parsedContent = parsedContent;
	}
    
    /**
     * Merger.
     * 
     * @param command
     */
    public PrivateEntity merger(PrivateEntity command) {
    	super.merge(command);
    	setEntityCode(command.getEntityCode());
    	setParsedContent(command.getParsedContent());
    	return this;
    }

    /**
     * toString
     * @return String
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("partnerAlias").append("='").append(getEntityAlias()).append("' ");
        buffer.append("]");
      
        return buffer.toString();
    }

   /**
    * equals
    */
   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
         if ( (other == null ) ) return false;
         if ( !(other instanceof PrivateEntity) ) return false;
         PrivateEntity castOther = (PrivateEntity) other; 
         
         return ((this.getEntity()==castOther.getEntity()) || ( this.getEntity()!=null && castOther.getEntity()!=null && this.getEntity().equals(castOther.getEntity()) ))
             && ((this.getEntityAlias()==castOther.getEntityAlias()) || ( this.getEntityAlias()!=null && castOther.getEntityAlias()!=null && this.getEntityAlias().equals(castOther.getEntityAlias()) ));
   }
   
   /**
    * hashCode
    */
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getEntityAlias() == null ? 0 : this.getEntityAlias().hashCode() );
         return result;
   }   

}
