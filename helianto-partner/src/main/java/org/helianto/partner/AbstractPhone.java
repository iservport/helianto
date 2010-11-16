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
/**
 * Phone basic info.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.MappedSuperclass
public class AbstractPhone implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private String phoneNumber;
    private String areaCode;
    private char phoneType;

    /** 
     * Default constructor
     */
    public AbstractPhone() {
    	setPhoneNumber("");
    	setAreaCode("");
    	setPhoneType(PhoneType.MAIN);
    }

    /**
     * Phone number.
     */
    @Column(length=20)
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Area code.
     */
    @Column(length=2)
    public String getAreaCode() {
        return this.areaCode;
    }
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
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
    public void setPhoneType(PhoneType phoneType) {
        this.phoneType = phoneType.getValue();
    }

    /**
     * toString
     * @return String
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer
        .append(getAreaCode())
        .append(" ")
        .append(getPhoneNumber());

        return buffer.toString();
    }

}