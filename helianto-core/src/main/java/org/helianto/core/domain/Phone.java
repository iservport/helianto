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

import javax.persistence.Column;

import org.helianto.core.def.PhoneType;

/**
 * Phone basic info.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Embeddable
public class Phone implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    
    @Column(length=20)
    private String phoneNumber = "";
    
    @Column(length=3)
    private String areaCode = "";
    
    @Column(length=10)
    private String branch = "";
    
    private char phoneType = PhoneType.MAIN.getValue();

    /** 
     * Default constructor
     */
    public Phone() {
    	super();
    }

    /** 
     * Phone constructor.
     * 
     * @param areaCode
     * @param phoneNumber
     */
    public Phone(String areaCode, String phoneNumber) {
    	this(phoneNumber, areaCode, PhoneType.MAIN);
    }

    /** 
     * Phone type constructor.
     * 
     * @param areaCode
     * @param phoneNumber
     * @param phoneType
     */
    public Phone(String areaCode, String phoneNumber, PhoneType phoneType) {
    	super();
    	setPhoneNumber(phoneNumber);
    	setAreaCode(areaCode);
    	setPhoneTypeAsEnum(phoneType);
    }

    /**
     * Phone number.
     */
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Area code.
     */
    public String getAreaCode() {
        return this.areaCode;
    }
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
    
    /**
     * Branch.
     */
    public String getBranch() {
		return branch;
	}
    public void setBranch(String branch) {
		this.branch = branch;
	}

    /**
     * Phone type.
     */
    public char getPhoneType() {
        return this.phoneType;
    }
    public void setPhoneType(char phoneType) {
        this.phoneType = phoneType;
    }
    public void setPhoneTypeAsEnum(PhoneType phoneType) {
        this.phoneType = phoneType.getValue();
    }

    /**
     * toString
     * @return String
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer
        .append("(")
        .append(getAreaCode())
        .append(") ")
        .append(getPhoneNumber());
        
        if (getBranch()!=null && getBranch().length()>0) {
        	buffer
        	.append(" - ")
            .append(getBranch());
        }

        return buffer.toString();
    }

}
