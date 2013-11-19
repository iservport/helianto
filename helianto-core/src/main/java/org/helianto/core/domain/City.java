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

package org.helianto.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonBackReference;


/**
 * City.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="core_city",
    uniqueConstraints = {@UniqueConstraint(columnNames={"contextId", "cityCode"})
    	,@UniqueConstraint(columnNames={"stateId", "cityCode"})}
)
public class City 
	implements Serializable, Comparable<City> {

    private static final long serialVersionUID = 1L;
    private int id;
    private int version;
    private Operator context;
    private String cityCode = "";
    private String cityName = "";
    private State state;
    private boolean capital;
    private char priority;

	/**
	 * Default constructor.
	 */
    public City() {
        super();
    }

    /**
     * Key constructor.
     * 
     * @param context
     * @param cityCode
     */
    public City(Operator context, String cityCode) {
    	this();
    	setContext(context);
    	setCityCode(cityCode);
    }
    
    /**
     * State constructor.
     * 
     * @param state
     * @param cityCode
     */
    public City(State state, String cityCode) {
    	this(state.getContext(), cityCode);
    	setState(state);
    }
    
    /**
     * Name constructor.
     * 
     * @param state
     * @param cityCode
     * @param cityName
     */
    public City(State state, String cityCode, String cityName) {
    	this(state, cityCode);
    	setCityName(cityName);
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
     * Version.
     */
    @Version
    public int getVersion() {
		return version;
	}
    public void setVersion(int version) {
		this.version = version;
	}
    
    /**
     * Context.
     */
    @JsonBackReference 
    @ManyToOne
    @JoinColumn(name="contextId", nullable=true)
    public Operator getContext() {
		return context;
	}
    public void setContext(Operator context) {
		this.context = context;
	}
    
    /**
     * State.
     */
    @JsonBackReference 
    @ManyToOne
    @JoinColumn(name="stateId", nullable=true)
    public State getState() {
		return state;
	}
    public void setState(State state) {
		this.state = state;
	}
    
    /**
     * City code.
     */
    @Column(length=12)
    public String getCityCode() {
    	return cityCode;
    }
    public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
    
    /**
     * City name.
     */
    @Column(length=64)
    public String getCityName() {
    	return cityName;
    }
    public void setCityName(String cityName) {
		this.cityName = cityName;
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

	/**
	 * State priority.
	 */
	public char getPriority() {
		return priority;
	}
	public void setPriority(char priority) {
		this.priority = priority;
	}

	public int compareTo(City next) {
		if (getPriority()==next.getPriority()) {
			if (getCityCode()!=null && next.getCityCode()!=null) {
				return getCityCode().compareTo(next.getCityCode());
			}
			return 0;
		}
		return getPriority()-next.getPriority();
	}
	
    /**
     * toString
     * @return String
     */
	public String toString() {
    	 return getCityCode();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cityCode == null) ? 0 : cityCode.hashCode());
		result = prime * result + ((context == null) ? 0 : context.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof City)) {
			return false;
		}
		City other = (City) obj;
		if (cityCode == null) {
			if (other.cityCode != null) {
				return false;
			}
		} else if (!cityCode.equals(other.cityCode)) {
			return false;
		}
		if (context == null) {
			if (other.context != null) {
				return false;
			}
		} else if (!context.equals(other.context)) {
			return false;
		}
		return true;
	}

}


