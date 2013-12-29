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

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.helianto.core.def.AddressType;
import org.helianto.core.def.PrivacyLevel;
import org.helianto.core.internal.AbstractAddress;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Address.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="prtnr_contact",
    uniqueConstraints = {@UniqueConstraint(columnNames={"partnerRegistryId", "sequence"})}
)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="type",
    discriminatorType=DiscriminatorType.CHAR
)
@DiscriminatorValue("A")
public class PrivateAddress 
	extends AbstractAddress 
	implements Comparable<PrivateAddress> 
{

    private static final long serialVersionUID = 1L;
    
    @JsonBackReference("privateEntity")
    @ManyToOne
    @JoinColumn(name="partnerRegistryId", nullable=true)
    private PrivateEntity privateEntity;
    
    private int sequence;
    
    private char addressType = AddressType.MAIN.getValue();
    
    private char privacyLevel = PrivacyLevel.PUBLIC.getValue();

    /** 
     * Empty constructor.
	 */
    public PrivateAddress() {
        super();
    }
    
    /** 
     * Key constructor.
     * 
     * @param privateEntity
	 */
    public PrivateAddress(PrivateEntity privateEntity, int sequence) {
    	this();
    	setPrivateEntity(privateEntity);
    	setSequence(sequence);
    }
    
    /**
     * Partner registry (old name).
     */
//    @Transient
    public PrivateEntity getPartnerRegistry() {
        return this.privateEntity;
    }
    
    /**
     * Private entity.
     */
    public PrivateEntity getPrivateEntity() {
        return this.privateEntity;
    }
    public void setPrivateEntity(PrivateEntity privateEntity) {
        this.privateEntity = privateEntity;
    }

//    @Transient
    public String getEntityAlias() {
    	if (getPrivateEntity()==null) return "";
    	return getPrivateEntity().getEntityAlias();
    }
    
//    @Transient
    public String getEntityName() {
    	if (getPrivateEntity()==null) return "";
    	return getPrivateEntity().getEntityName();
    }

    /**
     * Sequence.
     */
    public int getSequence() {
        return this.sequence;
    }
    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    /**
     * Address type.
     */
    public char getAddressType() {
        return this.addressType;
    }
    public void setAddressType(char addressType) {
        this.addressType = addressType;
    }
    public void setAddressTypeAsEnum(AddressType addressType) {
        this.addressType = addressType.getValue();
    }

    /**
     * Privacy level.
     */
    public char getPrivacyLevel() {
        return this.privacyLevel;
    }
    public void setPrivacyLevel(char privacyLevel) {
        this.privacyLevel = privacyLevel;
    }
    public void setPrivacyLevelAsEnum(PrivacyLevel privacyLevel) {
        this.privacyLevel = privacyLevel.getValue();
    }

    /**
     * Compare by sequence.
     */
    public int compareTo(PrivateAddress next) {
    	return this.getSequence() - next.getSequence();
    }   

    /**
     * toString
     * @return String
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("partnerRegistry").append("='").append(getPartnerRegistry()).append("' ");
        buffer.append("sequence").append("='").append(getSequence()).append("' ");
        buffer.append("]");
      
        return buffer.toString();
    }

   /**
    * equals
    */
   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
         if ( (other == null ) ) return false;
         if ( !(other instanceof PrivateAddress) ) return false;
         PrivateAddress castOther = (PrivateAddress) other; 
         
         return ((this.getPartnerRegistry()==castOther.getPartnerRegistry()) || ( this.getPartnerRegistry()!=null && castOther.getPartnerRegistry()!=null && this.getPartnerRegistry().equals(castOther.getPartnerRegistry()) ))
             && ((this.getSequence()==castOther.getSequence()));
   }
   
   /**
    * hashCode
    */
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getPartnerRegistry() == null ? 0 : this.getPartnerRegistry().hashCode() );
         result = 37 * result + (int) this.getSequence();
         return result;
   }

}
