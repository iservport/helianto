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
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.helianto.core.base.AbstractAddress;
import org.helianto.core.def.AddressType;
import org.helianto.core.def.PrivacyLevel;

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
public class PrivateAddress extends AbstractAddress implements Comparable<PrivateAddress> {

    private static final long serialVersionUID = 1L;
    private PrivateEntity partnerRegistry;
    private int sequence;
    private char addressType;
    private char privacyLevel;

    /** 
     * Empty constructor.
	 */
    public PrivateAddress() {
        super();
        setAddressTypeAsEnum(AddressType.MAIN);
        setPrivacyLevelAsEnum(PrivacyLevel.PUBLIC);
    }
    
    /** 
     * Key constructor.
     * 
     * @param partnerRegistry
	 */
    public PrivateAddress(PrivateEntity partnerRegistry, int sequence) {
    	this();
    	setPartnerRegistry(partnerRegistry);
    	setSequence(sequence);
    }
    
    @Transient
    public void reset() {
        setAddressType(' ');
        setPrivacyLevel(' ');
    }
    
    /**
     * Partner registry.
     */
    @ManyToOne
    @JoinColumn(name="partnerRegistryId", nullable=true)
    public PrivateEntity getPartnerRegistry() {
        return this.partnerRegistry;
    }
    @Transient
    public String getPartnerAlias() {
    	if (this.partnerRegistry==null) return "";
    	return this.partnerRegistry.getPartnerAlias();
    }
    @Transient
    public String getPartnerName() {
    	if (this.partnerRegistry==null) return "";
    	return this.partnerRegistry.getPartnerName();
    }
    public void setPartnerRegistry(PrivateEntity partnerRegistry) {
        this.partnerRegistry = partnerRegistry;
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
