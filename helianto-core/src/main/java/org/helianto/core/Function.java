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


import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Transient;

/**
 * Fun�tion as a group of users.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@DiscriminatorValue("F")
public class Function extends UserGroup {

	private static final long serialVersionUID = 1L;
    private String functionName;
    private String nature;
//	private Set<CompetenceRequirement> requirements = new HashSet<CompetenceRequirement>(0);

	/** 
	 * Default constructor.
	 */
    public Function() {
    	super();
    }

	/** 
	 * Entity constructor.
	 * 
	 * @param entity
	 * @param userKey
	 */
    public Function(Entity entity, String userKey) {
    	this();
    	setEntity(entity);
    	setUserKey(userKey);
    }
    
    /**
     * Function name.
     */
    @Column(length=32)
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	
    /**
     * Group nature, as a keyword csv.
     */
    @Column(length=40)
	public String getNature() {
		return nature;
	}
	public void setNature(String nature) {
		this.nature = nature;
	}
	
	@Transient
	public String[] getNatureAsArray() {
		if (getNature()!=null) {
			return getNature().split(",");
		}
		return new String[] {};
	}
	public void setNatureAsArray(String[] natureArray) {
		setNature(natureArray.toString().replace("[", "").replace("]", ""));
	}

    /**
	 * equals
	 */
	public boolean equals(Object other) {
		if (other instanceof Function) {
			return super.equals(other);
		}
		return false;
	}

}

