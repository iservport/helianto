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

package org.helianto.core.internal;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.helianto.core.Address;
import org.helianto.core.domain.City;
import org.helianto.core.domain.State;

/**
 * Base class to instances having an Address.
 * 
 * @author Mauricio Fernandes de Castro
 */
@SuppressWarnings("serial")
@javax.persistence.MappedSuperclass
public abstract class AbstractAddress 
	implements Address, Serializable {

    private int id;
    private String address1 = "";
    private String addressClassifier = "";
    private String address2 = "";
    private String postalCode = "";
    private State state;
    private City city;
    private String cityName = "";

    private String addressNumber = "";
	private String addressDetail = "";
    private String address3 = "";
    private String postOfficeBox = "";

    /** 
     * Empty constructor.
	 */
    public AbstractAddress() {
    	super();
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
     * Convenience to chain a city.
     * 
     * @param city
     * @param postalCode
     */
    public AbstractAddress appendCity(City city, String postalCode) {
        setCity(city);
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
     * State.
     */
    @ManyToOne
    @JoinColumn(name="stateId")
    public State getState() {
    	if (getCity()!=null) {
    		return getCity().getState();
    	}
		return state;
	}
    public void setState(State state) {
		this.state = state;
	}
    
    /**
     * <<Transient>> Convenience method to expose the state code.
     */
    @Transient
    public String getStateCode() {
    	if (getState()!=null) {
    		return getState().getStateCode();
    	}
    	return "";
    }
    
    /**
     * <<Transient>> Convenience method to expose the state name.
     */
    @Transient
    public String getStateName() {
    	if (getState()!=null) {
    		return getState().getStateName();
    	}
    	return "";
    }
    
    /**
     * City.
     */
    @ManyToOne
    @JoinColumn(name="cityId")
    public City getCity() {
		return city;
	}
    public void setCity(City city) {
		this.city = city;
	}
    
    /**
     * <<Transient>> Convenience method to expose the city code.
     */
    @Transient
    public String getCityCode() {
    	if (getCity()!=null) {
    		return getCity().getCityCode();
    	}
    	return "";
    }
    
    /**
     * The city name.
     */
    @Column(length=64)
    public String getCityName() {
    	if (getCity()!=null) {
    		return getCity().getCityName();
    	}
        return this.cityName;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
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