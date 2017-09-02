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

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;


/**
 * City.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="core_city",
    uniqueConstraints = {@UniqueConstraint(columnNames={"contextName", "cityCode"})
    	,@UniqueConstraint(columnNames={"stateId", "cityCode"})}
)
public class City 
	implements Serializable, Comparable<City> {

    private static final long serialVersionUID = 1L;
    
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @Version
    private int version;

	@Column(length=20)
	private String contextName;
    
    @Column(length=12)
    private String cityCode = "";
    
    @Column(length=64)
    private String cityName = "";
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="stateId", nullable=true)
    private State state;
    
    @Transient
    public Integer stateId;
    
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
     * @param contextName
     * @param cityCode
     */
    public City(String contextName, String cityCode) {
    	this();
    	setContextName(contextName);
    	setCityCode(cityCode);
    }
    
    /**
     * State constructor.
     * 
     * @param state
     * @param cityCode
     */
    public City(State state, String cityCode) {
    	this(state.getContextName(), cityCode);
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
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Version.
     */
    public int getVersion() {
		return version;
	}
    public void setVersion(int version) {
		this.version = version;
	}
    
    /**
     * Context.
     */
	public String getContextName() {
		return contextName;
	}
	public void setContextName(String contextName) {
		this.contextName = contextName;
	}

	/**
     * State.
     */
    public State getState() {
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
     * City code.
     */
    public String getCityCode() {
    	return cityCode;
    }
    public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
    
    /**
     * City name.
     */
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
	
	/**
	 * Merger.
	 * 
	 * @param command
	 */
	public City merge(City command) {
		setCityCode(command.getCityCode());
		setCityName(command.getCityName());
		setCapital(command.isCapital());
		setPriority(command.getPriority());
		return this;
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
	
}


