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

package org.helianto.partner;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.helianto.core.AbstractKeyStringValue;
import org.helianto.core.KeyType;
/**
 * The content of a key associated to the public entity.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="prtnr_publicEntityKey",
    uniqueConstraints = {@UniqueConstraint(columnNames={"publicEntityId", "keyTypeId"})}
)
public class PublicEntityKey extends AbstractKeyStringValue {

	/**
	 * <<Transient>> Delegate to the actual key owner.
	 */
	@Transient
	@Override
	protected Object getKeyOwner() {
		return getPublicEntity();
	}   

    private static final long serialVersionUID = 1L;
    private PublicEntity publicEntity;

    /** 
     * Default constructor.
     */
    public PublicEntityKey() {
    	super();
    }
    
    /** 
     * Public entity constructor.
     * 
     * @param publicEntity
     */
    public PublicEntityKey(PublicEntity publicEntity) {
    	this();
    	setPublicEntity(publicEntity);
    }
    
    /** 
     * Key type constructor.
     * 
     * @param publicEntity
     * @param keyType
     */
    public PublicEntityKey(PublicEntity publicEntity, KeyType keyType) {
    	this(publicEntity);
    	setKeyType(keyType);
    }
    
    /** 
     * Full constructor.
     * 
     * @param publicEntity
     * @param keyType
     * @param keyValue
     */
    public PublicEntityKey(PublicEntity publicEntity, KeyType keyType, String keyValue) {
    	this(publicEntity, keyType);
    	setKeyValue(keyValue);
    }

    /**
     * Public entity.
     */
    @ManyToOne
    @JoinColumn(name="publicEntityId", nullable=true)
    public PublicEntity getPublicEntity() {
		return publicEntity;
	}
    public void setPublicEntity(PublicEntity publicEntity) {
		this.publicEntity = publicEntity;
	}

   /**
    * equals
    */
   public boolean equals(Object other) {
         if (other instanceof PublicEntityKey) {
        	 return super.equals(other);
         }
         return false;
   }
   
}
