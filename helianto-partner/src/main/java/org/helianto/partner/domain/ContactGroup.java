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

import javax.persistence.DiscriminatorValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.helianto.core.Entity;
import org.helianto.core.UserGroup;
/**
 * A contact group.
 * 
 * @author Mauricio Fernandes de Castro
 * 			
 */
@javax.persistence.Entity
@DiscriminatorValue("C")
public class ContactGroup extends UserGroup {

	/**
	 * <<Transient>> Exposes the discriminator.
	 */
	@Transient
	public char getDiscriminator() {
		return 'C';
	}

    private static final long serialVersionUID = 1L;
    private PrivateEntity2 privateEntity;

	/** 
	 * Empty constructor.
	 */
    public ContactGroup() {
    	super();
    }

	/** 
	 * Entity constructor.
	 * 
	 * @param entity
	 */
    public ContactGroup(Entity entity) {
    	this();
    	setEntity(entity);
    }

	/** 
	 * User group constructor.
	 * 
	 * @param entity
	 * @param userKey
	 */
    public ContactGroup(Entity entity, String userKey) {
    	this(entity);
    	setUserKey(userKey);
    }

	/** 
	 * Parent constructor.
	 * 
	 * @param parent
	 */
    public ContactGroup(PrivateEntity2 parent) {
    	this(parent.getEntity(), "");
    	setPrivateEntity(parent);
    }

    /**
     * Private entity.
     */
    @ManyToOne
    @JoinColumn(name="partnerRegistryId", nullable=true)
    public PrivateEntity2 getPrivateEntity() {
        return this.privateEntity;
    }
    public void setPrivateEntity(PrivateEntity2 privateEntity) {
        this.privateEntity = privateEntity;
    }

    @Transient
    public String getEntityAlias() {
    	if (getPrivateEntity()==null) return "";
    	return getPrivateEntity().getEntityAlias();
    }
    
    @Transient
    public String getEntityName() {
    	if (getPrivateEntity()==null) return "";
    	return getPrivateEntity().getEntityName();
    }

    /**
     * toString
     * @return String
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("privateEntity").append("='").append(getPrivateEntity()).append("' ");
        buffer.append("userKey").append("='").append(getUserKey()).append("' ");
        buffer.append("]");
      
        return buffer.toString();
    }

   /**
    * equals
    */
   public boolean equals(Object other) {
         if (other instanceof ContactGroup) return super.equals(other);
         return false;
   }
   
}
