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

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.helianto.core.domain.KeyType;
import org.helianto.core.internal.AbstractKeyStringValue;

import com.fasterxml.jackson.annotation.JsonBackReference;
/**
 * Represents the relationship between the organization and other entities.  
 * 
 * <p>
 * </p>
 */
@javax.persistence.Entity
@Table(name="prtnr_partnerRegistryKey",
    uniqueConstraints = {@UniqueConstraint(columnNames={"partnerRegistryId", "keyTypeId"})}
)
public class PrivateEntityKey 
	extends AbstractKeyStringValue 
{

    private static final long serialVersionUID = 1L;
    
    @JsonBackReference 
    @ManyToOne
    @JoinColumn(name="partnerRegistryId", nullable=true)
    private PrivateEntity privateEntity;

    /** 
     * Default constructor.
     */
    public PrivateEntityKey() {
    	super();
    }

    /** 
     * PrivateEntity constructor.
     * 
     * @param privateEntity
     */
    public PrivateEntityKey(PrivateEntity privateEntity) {
    	this();
    	setPrivateEntity(privateEntity);
    }

    /** 
     * Key constructor.
     * 
     * @param privateEntity
     * @param keyType
     * @param keyValue
     */
    public PrivateEntityKey(PrivateEntity privateEntity, KeyType keyType, String keyValue) {
    	this(privateEntity, keyType);
    	setKeyValue(keyValue);
    }

    /** 
     * Full constructor.
     * 
     * @param privateEntity
     * @param keyType
     */
    public PrivateEntityKey(PrivateEntity privateEntity, KeyType keyType) {
    	this(privateEntity);
    	setKeyType(keyType);
    }

    /**
     * Partner registry.
     */
    public PrivateEntity getPrivateEntity() {
        return this.privateEntity;
    }
    public void setPrivateEntity(PrivateEntity privateEntity) {
        this.privateEntity = privateEntity;
    }
    
//    @Transient
    @Override
    protected Object getKeyOwner() {
    	return getPrivateEntity();
    }

   /**
    * equals
    */
   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
         if ( (other == null ) ) return false;
         if ( !(other instanceof PrivateEntityKey) ) return false;
         PrivateEntityKey castOther = (PrivateEntityKey) other; 
         
         return ((this.getPrivateEntity()==castOther.getPrivateEntity()) || ( this.getPrivateEntity()!=null && castOther.getPrivateEntity()!=null && this.getPrivateEntity().equals(castOther.getPrivateEntity()) ))
             && ((this.getKeyType()==castOther.getKeyType()) || ( this.getKeyType()!=null && castOther.getKeyType()!=null && this.getKeyType().equals(castOther.getKeyType()) ));
   }
   
   /**
    * hashCode
    */
   public int hashCode() {
         return super.hashCode();
   }   

}
