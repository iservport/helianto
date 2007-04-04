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
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
/**
 * <p>
 * Represents the relationship between the organization and other entities.  
 * </p>
 * @author Vlademir Teixeira
 */
@javax.persistence.Entity
@Table(name="prtnr_phone",
    uniqueConstraints = {@UniqueConstraint(columnNames={"addressId", "sequence"})}
)
public class Phone implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private Address address;
    private int sequence;
    private String phoneNumber;
    private String areaCode;
    private int phoneType;
    private String comment;
    private char privacyLevel;

    /** default constructor */
    public Phone() {
    }

    // Property accessors
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Address getter.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="addressId", nullable=true)
    public Address getAddress() {
        return this.address;
    }
    /**
     * Address setter.
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Sequence getter.
     */
    public int getSequence() {
        return this.sequence;
    }
    /**
     * Sequence setter.
     */
    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    /**
     * PhoneNumber getter.
     */
    @Column(length=20)
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    /**
     * PhoneNumber setter.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * AreaCode getter.
     */
    @Column(length=2)
    public String getAreaCode() {
        return this.areaCode;
    }
    /**
     * AreaCode setter.
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    /**
     * PhoneType getter.
     */
    public int getPhoneType() {
        return this.phoneType;
    }
    /**
     * PhoneType setter.
     */
    public void setPhoneType(int phoneType) {
        this.phoneType = phoneType;
    }

    /**
     * Comment getter.
     */
    @Column(length=20)
    public String getComment() {
        return this.comment;
    }
    /**
     * Comment setter.
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * PrivacyLevel getter.
     */
    public char getPrivacyLevel() {
        return this.privacyLevel;
    }
    /**
     * PrivacyLevel setter.
     */
    public void setPrivacyLevel(char privacyLevel) {
        this.privacyLevel = privacyLevel;
    }

    /**
     * <code>Phone</code> factory.
     * 
     * @param address
     * @param sequence
     */
    public static Phone phoneFactory(Address address, int sequence) {
        Phone phone = new Phone();
        phone.setAddress(address);
        phone.setSequence(sequence);
        return phone;
    }

    /**
     * <code>Phone</code> natural id query.
     */
    @Transient
    public static String getPhoneNaturalIdQueryString() {
        return "select phone from Phone phone where phone.address = ? and phone.sequence = ? ";
    }

    /**
     * toString
     * @return String
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("address").append("='").append(getAddress()).append("' ");
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
         if ( !(other instanceof Phone) ) return false;
         Phone castOther = (Phone) other; 
         
         return ((this.getAddress()==castOther.getAddress()) || ( this.getAddress()!=null && castOther.getAddress()!=null && this.getAddress().equals(castOther.getAddress()) ))
             && ((this.getSequence()==castOther.getSequence()));
   }
   
   /**
    * hashCode
    */
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getAddress() == null ? 0 : this.getAddress().hashCode() );
         result = 37 * result + (int) this.getSequence();
         return result;
   }   

}
