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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.helianto.core.PhoneType;
/**
 * Phones are always related to an address.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="prtnr_phone2",
    uniqueConstraints = {@UniqueConstraint(columnNames={"partnerRegistryId", "sequence"})}
)
public class Phone extends org.helianto.core.Phone {

    private static final long serialVersionUID = 1L;
    private int id;
    private PrivateEntity partnerRegistry;
    private int sequence;
    private String comment;
    private char privacyLevel;

    /** 
     * Default constructor
     */
    public Phone() {
    	this("");
    }

    /** 
     * Phone constructor.
     * 
     * @param phoneNumber
     */
    public Phone(String phoneNumber) {
    	this("", phoneNumber);
    }

    /** 
     * Phone area constructor.
     * 
     * @param areaCode
     * @param phoneNumber
     */
    public Phone(String areaCode, String phoneNumber) {
    	this(areaCode, phoneNumber, PhoneType.MAIN);
    }

    /** 
     * Phone area type constructor.
     * 
     * @param areaCode
     * @param phoneNumber
     * @param phoneType
     */
    public Phone(String areaCode, String phoneNumber, PhoneType phoneType) {
    	setAreaCode(areaCode);
    	setPhoneNumber(phoneNumber);
    	setPhoneTypeAsEnum(phoneType);
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
     * Partner registry.
     */
    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="partnerRegistryId", nullable=true)
    public PrivateEntity getPartnerRegistry() {
		return partnerRegistry;
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
     * Comment.
     */
    @Column(length=20)
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
         if ( !(other instanceof Phone) ) return false;
         Phone castOther = (Phone) other; 
         
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
