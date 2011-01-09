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

import javax.persistence.Column;
import javax.persistence.Transient;

import org.helianto.core.Province;

/**
 * Base class to instances having an Address.
 * 
 * @author Mauricio Fernandes de Castro
 */
@SuppressWarnings("serial")
@javax.persistence.MappedSuperclass
public abstract class AbstractAddress extends AbstractPartialAddress implements Addressee {

    private String addressNumber;
	private String addressDetail;
    private String address3;
    private String postOfficeBox;

    /** 
     * Empty constructor.
	 */
    public AbstractAddress() {
    	super();
        setAddressNumber("");
        setAddressDetail("");
        setAddress3("");
        setPostOfficeBox("");
    }
    
    /** 
     * Province constructor.
     * 
     * @param province
	 */
    public AbstractAddress(Province province) {
    	this();
        setProvince(province);
    }
    
    /**
     * Convenience to chain a street address.
     * 
     * @param address1
     * @param addressNumber
     */
    public AbstractAddress appendStreet(String address1, String addressNumber) {
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
    public AbstractAddress appendStreet(String address1, String addressNumber, String addressDetail) {
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
    public AbstractAddress appendStreet(String address1, String addressNumber, String addressDetail, String county) {
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
    public AbstractAddress appendCity(Province province, String postalCode) {
        setProvince(province);
        setPostalCode(postalCode);
        return this;
    }

    @Column(length=8)
    public String getAddressNumber() {
		return addressNumber;
	}
	public void setAddressNumber(String addressNumber) {
		this.addressNumber = addressNumber;
	}

    @Column(length=24)
	public String getAddressDetail() {
		return addressDetail;
	}
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

    @Column(length=32)
    public String getAddress3() {
        return this.address3;
    }
    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    @Column(length=10)
    public String getPostOfficeBox() {
        return this.postOfficeBox;
    }
    public void setPostOfficeBox(String postOfficeBox) {
        this.postOfficeBox = postOfficeBox;
    }
    
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

}
