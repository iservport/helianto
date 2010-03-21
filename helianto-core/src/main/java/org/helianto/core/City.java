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

package org.helianto.core;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Transient;

/**
 * Cities.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@DiscriminatorValue("C")
public class City extends Province {

    private static final long serialVersionUID = 1L;
    private boolean capital;

	/**
	 * Empty constructor.
	 */
    public City() {
        super();
    }

    /**
     * Parent constructor.
     * 
     * @param provinceCode
     * @param provinceName
     * @param parent
     */
    public City(String provinceCode, String provinceName, Province parent) {
    	super(provinceCode, provinceName, parent);
    }
    
    /**
     * <<Transient>> City code is the same as province code.
     */
    @Transient
    public String getCityCode() {
    	return super.getProvinceCode();
    }
    public void setCityCode(String cityCode) {
		setProvinceCode(cityCode);
	}
    
    /**
     * <<Transient>> City name is the same as province name.
     */
    @Transient
    public String getCityName() {
    	return super.getProvinceName();
    }
    public void setCityName(String cityName) {
		setProvinceName(cityName);
	}
    
    /**
	 * True if capital.
	 */
	public boolean isCapital() {
		return capital;
	}
	public void setCapital(boolean capital) {
		this.capital = capital;
	}

   public boolean equals(Object other) {
		 if ( !(other instanceof City) ) return false;
		 return super.equals(other);
   }
   
}


