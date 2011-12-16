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
import javax.persistence.Transient;

import org.helianto.core.Address;
import org.helianto.core.Phone;
import org.helianto.core.Province;
import org.helianto.core.PublicEntity;
/**
 * A partner backed by a public entity.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.MappedSuperclass
public class PublicPartner extends Partner {

    private static final long serialVersionUID = 1L;
    private PublicEntity publicEntity;

	/**
	 *  Empty constructor
	 */
    public PublicPartner() {
    	super();
    }

	/**
     * Key constructor.
     * 
     * @param partnerRegistry
     */
    public PublicPartner(PublicEntity partnerRegistry) {
    	this();
    	setPublicEntity(partnerRegistry);
    }

    /**
     * Public entity.
     */
    @ManyToOne
    @JoinColumn(name="publicEntityId", nullable=true)
    public PublicEntity getPublicEntity() {
        return this.publicEntity;
    }
    public void setPublicEntity(PublicEntity publicEntity) {
        this.publicEntity = publicEntity;
    }
    
    @Transient
    public String getEntityAlias() {
    	return getPublicEntity().getEntityAlias();
    }
    
    @Transient
    public String getEntityName() {
    	return getPublicEntity().getEntityName();
    }

    @Transient
    public String getAddress1() {
        return this.getPublicEntity().getAddress1();
    }
    
    @Transient
    public String getAddressClassifier() {
        return this.getPublicEntity().getAddressClassifier();
    }
    
    @Transient
    public String getAddressNumber() {
    	return this.getPublicEntity().getAddressNumber();
    }
    
    @Transient
    public String getAddressDetail() {
    	return this.getPublicEntity().getAddressDetail();
    }

    @Transient
    public String getAddress2() {
        return this.getPublicEntity().getAddress2();
    }

    @Transient
    public String getAddress3() {
        return this.getPublicEntity().getAddress3();
    }
    
    @Transient
    public String getPostOfficeBox() {
    	return this.getPublicEntity().getPostOfficeBox();
    }

    @Transient
    public String getPostalCode() {
        return this.getPublicEntity().getPostalCode();
    }

    @Transient
    public Province getProvince() {
        return this.getPublicEntity().getProvince();
    }
    
    @Transient
    public String getCityName() {
    	return this.getPublicEntity().getCityName();
    }
    
    @Transient
    public String getShortAddress() {
    	return this.getPublicEntity().getShortAddress();
    }
    
    @Transient
    public Phone getMainPhone() {
    	return this.getPublicEntity().getMainPhone();
    }

    @Transient
    public String getMainEmail() {
    	return this.getPublicEntity().getMainEmail();
    }

    // 
    
    /**
     * <<Transient>> Return the current addressee.
     * 
     * <p>
     * Current implementation defines the current addressee as the parent
     * partner registry.
     * </p>
     */
    @Transient
    protected Address getAddresse() {
    	return getAddressee(-1);
    }

    /**
     * <<Transient>> Return the addressee selected by an index.
     * 
     * <p>
     * Current implementation allways returns the parent
     * partner registry.
     * </p>
     * 
     * @param index
     */
    @Transient
    protected Address getAddressee(int index) {
    	return getPublicEntity();
    }

    // setter methods rely on getAddresse to update its contents.

    /**
     * Set the current addressee address1.
     * 
     * @param address1
     */
    public void setAddress1(String address1) {
        getPublicEntity().setAddress1(address1);
    }
    
    /**
     * Set the current addressee addressNumber.
     * 
     * @param addressNumber
     */
    public void setAddressNumber(String addressNumber) {
        getPublicEntity().setAddressNumber(addressNumber);
    }
    
    /**
     * Set the current addressee addressDetail.
     * 
     * @param addressDetail
     */
    public void setAddressDetail(String addressDetail) {
        getPublicEntity().setAddressDetail(addressDetail);
    }

    /**
     * Set the current addressee address2.
     * 
     * @param address2
     */
    public void setAddress2(String address2) {
        getPublicEntity().setAddress2(address2);
    }

    /**
     * Set the current addressee address3.
     * 
     * @param address3
     */
    public void setAddress3(String address3) {
        getPublicEntity().setAddress3(address3);
    }
    
    /**
     * Set the current addressee postOfficeBox.
     * 
     * @param postOfficeBox
     */
    public void setPostOfficeBox(String postOfficeBox) {
        getPublicEntity().setPostOfficeBox(postOfficeBox);
    }

    /**
     * Set the current addressee postalCode.
     * 
     * @param postalCode
     */
    public void setPostalCode(String postalCode) {
        getPublicEntity().setPostalCode(postalCode);
    }

    /**
     * Set the current addressee province.
     * 
     * @param province
     */
    public void setProvince(Province province) {
        getPublicEntity().setProvince(province);
    }
    
    /**
     * Set the current addressee cityName.
     * 
     * @param cityName
     */
    public void setCityName(String cityName) {
        getPublicEntity().setCityName(cityName);
    }
    
   /**
    * equals
    */
   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
         if ( (other == null ) ) return false;
         if ( !(other instanceof PublicPartner) ) return false;
         PublicPartner castOther = (PublicPartner) other; 
         
         return ((this.getPublicEntity()==castOther.getPublicEntity()) || ( this.getPublicEntity()!=null && castOther.getPublicEntity()!=null && this.getPublicEntity().equals(castOther.getPublicEntity()) ));
   }
   
}
