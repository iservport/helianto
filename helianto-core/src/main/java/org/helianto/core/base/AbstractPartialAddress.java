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

package org.helianto.core.base;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.helianto.core.City;
import org.helianto.core.Province;

/**
 * Base class to instances having a partial Address.
 * 
 * @author Mauricio Fernandes de Castro
 */
@SuppressWarnings("serial")
@javax.persistence.MappedSuperclass
public abstract class AbstractPartialAddress implements Serializable {

    private int id;
    private String address1;
    private String addressClassifier;
    private String address2;
    private String postalCode;
    private Province province;
    private String cityName;

    /** 
     * Empty constructor.
	 */
    public AbstractPartialAddress() {
        setAddress1("");
        setAddress2("");
        setPostalCode("");
        setCityName("");
    }
    
    /** 
     * Province constructor.
     * 
     * @param province
	 */
    public AbstractPartialAddress(Province province) {
    	this();
        setProvince(province);
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
     * Address classifier (like St, Av.)
     */
    @Column(length=10)
    public String getAddressClassifier() {
		return addressClassifier;
	}
    public void setAddressClassifier(String addressClassifier) {
		this.addressClassifier = addressClassifier;
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
     * Province.
     */
    @ManyToOne
    @JoinColumn(name="provinceId", nullable=true)
    public Province getProvince() {
        return this.province;
    }
    public void setProvince(Province province) {
        this.province = province;
    }
    
    /**
     * <<Transient>> Convenience method to expose the city.
     * 
     * <p>
     * Return null when {@link #getProvince()} is not an instance
     * of the class <code>City</code>.
     * </p>
     */
    @Transient
    public City getCity() {
    	if (getProvince() instanceof City) {
    		return (City) getProvince();
    	}
    	return null;
    }
    
    /**
     * <<Transient>> Convenience method to expose the parent province.
     * 
     * <p>
     * Return null when {@link #getProvince()} is not an instance
     * of the class <code>City</code>.
     * </p>
     */
    @Transient
    public Province getParentProvince() {
    	if (getCity()!=null) {
    		return getCity().getParent();
    	}
    	return null;
    }
    
    /**
     * <<Transient>> Convenience method to expose the province code.
     * 
     * <p>
     * Select the most appropriate code based on {@link #getProvince()}.
     * </p>
     */
    @Transient
    public String getProvinceCode() {
    	if (getParentProvince()!=null) {
    		return getParentProvince().getProvinceCode();
    	}
    	if (getProvince()!=null) {
    		return getProvince().getProvinceCode();
    	}
    	return "";
    }
    
    /**
     * <<Transient>> Convenience method to expose the province name.
     * 
     * <p>
     * Select the most appropriate code based on {@link #getProvince()}.
     * </p>
     */
    @Transient
    public String getProvinceName() {
    	if (getParentProvince()!=null) {
    		return getParentProvince().getProvinceName();
    	}
    	if (getProvince()!=null) {
    		return getProvince().getProvinceName();
    	}
    	return "";
    }
    
    /**
     * <<Transient>> Convenience method to expose the city code.
     * 
     * <p>
     * Select the most appropriate code based on {@link #getCity()}.
     * </p>
     */
    @Transient
    public String getCityCode() {
    	if (getCity()!=null) {
    		return getCity().getProvinceCode();
    	}
    	return "";
    }
    
    /**
     * The city name.
     * 
     * <p>
     * Invokes {@link #getCity()} to return either the actual content from the <code>cityName</code> field or 
     * the corresponding property when {@link #getProvince()} is an instance
     * of the class <code>City</code>.
     * </p>
     */
    @Column(length=32)
    public String getCityName() {
    	if (getCity()!=null) {
    		return getCity().getProvinceName();
    	}
        return this.cityName;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

}
