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
import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.helianto.core.def.PhoneType;
import org.helianto.core.def.PrivacyLevel;
import org.helianto.core.domain.Phone;

import com.fasterxml.jackson.annotation.JsonBackReference;
/**
 * Private entity phones.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="prtnr_phone2",
    uniqueConstraints = {@UniqueConstraint(columnNames={"partnerRegistryId", "sequence"})}
)
public class PartnerPhone 
	implements java.io.Serializable 
{

    private static final long serialVersionUID = 1L;
    
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @JsonBackReference 
    @ManyToOne
    @JoinColumn(name="partnerRegistryId", nullable=true)
    private PrivateEntity privateEntity;
    
    private int sequence;
    
    @Embedded
    private Phone phone;
    
    @Column(length=20)
    private String comment;
    
    private char privacyLevel;

    /** 
     * Default constructor
     */
    public PartnerPhone() {
    	this("");
    }

    /** 
     * Key constructor.
     * 
     * @param privateEntity
     * @param sequence
     */
    public PartnerPhone(PrivateEntity privateEntity, int sequence) {
    	this();
    	setPrivateEntity(privateEntity);
    	setSequence(sequence);
    }

    /** 
     * Phone constructor.
     * 
     * @param phoneNumber
     */
    public PartnerPhone(String phoneNumber) {
    	this("", phoneNumber);
    }

    /** 
     * Phone area constructor.
     * 
     * @param areaCode
     * @param phoneNumber
     */
    public PartnerPhone(String areaCode, String phoneNumber) {
    	this(areaCode, phoneNumber, PhoneType.MAIN);
    }

    /** 
     * Phone area type constructor.
     * 
     * @param areaCode
     * @param phoneNumber
     * @param phoneType
     */
    public PartnerPhone(String areaCode, String phoneNumber, PhoneType phoneType) {
    	setPhone(new Phone());
    	setAreaCode(areaCode);
    	setPhoneNumber(phoneNumber);
    	setPhoneTypeAsEnum(phoneType);
    	setPrivacyLevelAsEnum(PrivacyLevel.PUBLIC);
    }

    /**
     * Primary key.
     */
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Private entity.
     */
    public PrivateEntity getPrivateEntity() {
		return privateEntity;
	}
    public void setPrivateEntity(PrivateEntity privateEntity) {
		this.privateEntity = privateEntity;
	}

    /**
     * Old name to private entity, kept for legacy.
     * @deprecated
     */
//    @Transient
    public PrivateEntity getPartnerRegistry() {
		return privateEntity;
	}
    public void setPartnerRegistry(PrivateEntity partnerRegistry) {
		this.privateEntity = partnerRegistry;
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
     * Phone.
     */
    public Phone getPhone() {
		return phone;
	}
    public void setPhone(Phone phone) {
		this.phone = phone;
	}
    
    /**
     * Phone number.
     */
//    @Transient
    public String getPhoneNumber() {
        return getPhone().getPhoneNumber();
    }
    public void setPhoneNumber(String phoneNumber) {
    	getPhone().setPhoneNumber(phoneNumber);
    }

    /**
     * Area code.
     */
//    @Transient
    public String getAreaCode() {
        return getPhone().getAreaCode();
    }
    public void setAreaCode(String areaCode) {
    	getPhone().setAreaCode(areaCode);
    }

    /**
     * Phone type.
     */
//    @Transient
    public char getPhoneType() {
        return getPhone().getPhoneType();
    }
    public void setPhoneType(char phoneType) {
    	getPhone().setPhoneType(phoneType);
    }
    public void setPhoneTypeAsEnum(PhoneType phoneType) {
    	getPhone().setPhoneTypeAsEnum(phoneType);
    }

    /**
     * Branch.
     */
//    @Transient
    public String getBranch() {
        return getPhone().getBranch();
    }
    public void setBranch(String branch) {
    	getPhone().setBranch(branch);
    }

    /**
     * Comment.
     */
    public String getComment() {
        return this.comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
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
     * toString
     * @return String
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer
        .append(getAreaCode())
        .append(getPhoneNumber());

        return buffer.toString();
    }
    /**
     * toFormatedString
     * @return String
     */
    public String toFormattedString() {
        StringBuffer buffer = new StringBuffer();

        buffer
        .append("(")
        .append(getAreaCode())
        .append(") ")
        .append(getPhoneNumber());

        return buffer.toString();
    }

   /**
    * equals
    */
   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
         if ( (other == null ) ) return false;
         if ( !(other instanceof PartnerPhone) ) return false;
         PartnerPhone castOther = (PartnerPhone) other; 
         
         return ((this.getPrivateEntity()==castOther.getPrivateEntity()) || ( this.getPrivateEntity()!=null && castOther.getPrivateEntity()!=null && this.getPrivateEntity().equals(castOther.getPrivateEntity()) ))
             && ((this.getSequence()==castOther.getSequence()));
   }
   
   /**
    * hashCode
    */
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getPrivateEntity() == null ? 0 : this.getPrivateEntity().hashCode() );
         result = 37 * result + (int) this.getSequence();
         return result;
   }   

}
