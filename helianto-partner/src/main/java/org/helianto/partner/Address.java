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

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.helianto.core.Province;

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
public class Address implements java.io.Serializable, Comparable<Address> {

    /**
     * Factory method.
     * 
     * @param partnerRegistry
     * @param sequence
     */
    public static Address addressFactory(PartnerRegistry partnerRegistry, int sequence) {
        Address address = new Address();
        address.setPartnerRegistry(partnerRegistry);
        address.setSequence(sequence);
        return address;
    }

    private static final long serialVersionUID = 1L;
    private int id;
    private PartnerRegistry partnerRegistry;
    private int sequence;
    private char addressType;
    private String address1;
    private String addressNumber;
	private String addressDetail;
    private String address2;
    private String address3;
    private String cityName;
    private String postalCode;
    private String postOfficeBox;
    private char privacyLevel;
    private Province province;
    private Set<Phone> phones = new HashSet<Phone>(0);

    /** 
     * Empty constructor.
	 */
    public Address() {
        setAddressType(AddressType.MAIN.getValue());
        setPrivacyLevel(PrivacyLevel.PUBLIC.getValue());
        setAddress1("");
        setAddressNumber("");
        setAddressDetail("");
        setAddress2("");
        setAddress3("");
        setCityName("");
        setPostalCode("");
        setPostOfficeBox("");
    }
    
    /** 
     * Preferred constructor.
     * 
     * @param partnerRegistry
	 */
    public Address(PartnerRegistry partnerRegistry) {
    	this();
    	setPartnerRegistry(partnerRegistry);
    }
    
    /**
     * Convenience to chain a street address.
     * 
     * @param address1
     * @param addressNumber
     */
    public Address appendStreet(String address1, String addressNumber) {
        setAddress1(address1);
        setAddressNumber(addressNumber);
        return this;
    }

    /**
     * Convenience to chain a street address.
     * 
     * @param address1
     * @param addressNumber
     * @param addressDetail
     */
    public Address appendStreet(String address1, String addressNumber, String addressDetail) {
        setAddress1(address1);
        setAddressNumber(addressNumber);
        setAddressDetail(addressDetail);
        return this;
    }

    /**
     * Convenience to chain a street address.
     * 
     * @param address1
     * @param addressNumber
     * @param addressDetail
     * @param county
     */
    public Address appendStreet(String address1, String addressNumber, String addressDetail, String county) {
        setAddress1(address1);
        setAddressNumber(addressNumber);
        setAddressDetail(addressDetail);
        setAddress2(county);
        return this;
    }

    /**
     * Convenience to chain a city or province.
     * 
     * @param address1
     * @param addressNumber
     * @param addressDetail
     */
    public Address appendCity(Province province, String postalCode) {
        setProvince(province);
        setPostalCode(postalCode);
        return this;
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
    @ManyToOne
    @JoinColumn(name="partnerRegistryId", nullable=true)
    public PartnerRegistry getPartnerRegistry() {
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
    public void setPartnerRegistry(PartnerRegistry partnerRegistry) {
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
    public void setAddressType(AddressType addressType) {
        this.addressType = addressType.getValue();
    }

    /**
     * Address1.
     */
    @Column(length=64)
    public String getAddress1() {
        return this.address1;
    }
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    /**
     * Optional address number.
     */
    @Column(length=8)
    public String getAddressNumber() {
		return addressNumber;
	}
	public void setAddressNumber(String addressNumber) {
		this.addressNumber = addressNumber;
	}

    /**
     * Optional address detail.
     */
    @Column(length=12)
	public String getAddressDetail() {
		return addressDetail;
	}
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

    /**
     * Address2.
     */
    @Column(length=32)
    public String getAddress2() {
        return this.address2;
    }
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    /**
     * Address3.
     */
    @Column(length=32)
    public String getAddress3() {
        return this.address3;
    }
    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    /**
     * City name.
     */
    @Column(length=32)
    public String getCityName() {
        return this.cityName;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * Postal code.
     */
    @Column(length=10)
    public String getPostalCode() {
        return this.postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Post office box.
     */
    @Column(length=10)
    public String getPostOfficeBox() {
        return this.postOfficeBox;
    }
    public void setPostOfficeBox(String postOfficeBox) {
        this.postOfficeBox = postOfficeBox;
    }
    
    /**
     * <<Transient>> Short string for address.
     */
    @Transient
    public String getShortAddress() {
    	StringBuilder sb = new StringBuilder(getAddress1())
    		.append(getAddressNumber());
    	if (getAddressNumber().length()>0) {
    		sb.append(", ").append(getAddressNumber());
    	}
    	if (getAddressDetail().length()>0) {
    		sb.append(" ").append(getAddressDetail());
    	}
    	if (getAddress2().length()>0) {
    		sb.append(" - ").append(getAddress2());
    	}
    	return sb.toString();
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
     * Province.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="provinceId", nullable=true)
    public Province getProvince() {
        return this.province;
    }
    public void setProvince(Province province) {
        this.province = province;
    }

    /**
     * Phones.
     */
    @OneToMany(mappedBy="address", fetch=FetchType.EAGER)
    public Set<Phone> getPhones() {
        return this.phones;
    }
    public void setPhones(Set<Phone> phones) {
        this.phones = phones;
    }

    /**
     * Convenient to get first phone of type {@link PhoneType}.MAIN.
     */
    @Transient
    public String getMainPhone() {
    	for (Phone mainPhone: getPhones()) {
    		if (mainPhone.getPhoneType()==PhoneType.MAIN.getValue()) {
    			return mainPhone.toString();
    		}
    	}
    	return "";
    }
    
    /**
     * Compare by sequence.
     */
    public int compareTo(Address next) {
    	return this.sequence - next.sequence;
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
         if ( !(other instanceof Address) ) return false;
         Address castOther = (Address) other; 
         
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
