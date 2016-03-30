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

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Base class to instances having an Address.
 * 
 * @author Mauricio Fernandes de Castro
 */
@SuppressWarnings("serial")
@javax.persistence.MappedSuperclass
public abstract class AbstractAddress 
	implements Address, Serializable {

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @Column(length=64)
    private String address1 = "";
    
    @Column(length=10)
    private String addressClassifier = "";
    
    @Column(length=32)
    private String address2 = "";
    
    @Column(length=10)
    private String postalCode = "";
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="stateId")
    private State state;
    
    @Transient
    private Integer stateId;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="cityId")
    private City city;
    
    @Transient
    private Integer cityId;
    
    @Column(length=64)
    private String cityName = "";

    @Column(length=8)
    private String addressNumber = "";
    
    @Column(length=24)
	private String addressDetail = "";
	
    @Column(length=32)
    private String address3 = "";
    
    @Column(length=10)
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
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Address1.
     */
    public String getAddress1() {
        return this.address1;
    }
    public void setAddress1(String address1) {
        this.address1 = address1;
    }
    
    /**
     * Address classifier (like St, Av.)
     */
    public String getAddressClassifier() {
		return addressClassifier;
	}
    public void setAddressClassifier(String addressClassifier) {
		this.addressClassifier = addressClassifier;
	}

    /**
     * Address2.
     */
    public String getAddress2() {
        return this.address2;
    }
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    /**
     * Postal code.
     */
    public String getPostalCode() {
        return this.postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * State.
     */
    public State getState() {
    	if (getCity()!=null) {
    		return getCity().getState();
    	}
		return state;
	}
    public void setState(State state) {
		this.state = state;
	}
    
    public Integer getStateId() {
    	if (getState()!=null) {
    		return getState().getId();
    	}
		return stateId;
	}
    public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}
    
    /**
     * <<Transient>> Convenience method to expose the state code.
     */
    public String getStateCode() {
    	if (getState()!=null) {
    		return getState().getStateCode();
    	}
    	return "";
    }
    
    /**
     * <<Transient>> Convenience method to expose the state name.
     */
    public String getStateName() {
    	if (getState()!=null) {
    		return getState().getStateName();
    	}
    	return "";
    }
    
    /**
     * City.
     */
    public City getCity() {
		return city;
	}
    public void setCity(City city) {
		this.city = city;
	}
    
    public Integer getCityId() {
    	if (getCity()!=null) {
    		return getCity().getId();
    	}
		return cityId;
	}
    public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
    
    /**
     * <<Transient>> Convenience method to expose the city code.
     */
    public String getCityCode() {
    	if (getCity()!=null) {
    		return getCity().getCityCode();
    	}
    	return "";
    }
    
    /**
     * The city name.
     */
    public String getCityName() {
    	if (getCity()!=null) {
    		return getCity().getCityName();
    	}
        return this.cityName;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAddressNumber() {
		return addressNumber;
	}
	public void setAddressNumber(String addressNumber) {
		this.addressNumber = addressNumber;
	}

	public String getAddressDetail() {
		return addressDetail;
	}
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

    public String getAddress3() {
        return this.address3;
    }
    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getPostOfficeBox() {
        return this.postOfficeBox;
    }
    public void setPostOfficeBox(String postOfficeBox) {
        this.postOfficeBox = postOfficeBox;
    }
    
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
    
    public AbstractAddress merge(AbstractAddress command) {
    	setAddress1(command.getAddress1());
    	setAddress2(command.getAddress2());
    	setAddress3(command.getAddress3());
    	setAddressClassifier(command.getAddressClassifier());
    	setAddressDetail(command.getAddressDetail());
    	setAddressNumber(command.getAddressNumber());
    	setPostalCode(command.getPostalCode());
    	setPostOfficeBox(command.getPostOfficeBox());
    	return this;
    }

}
