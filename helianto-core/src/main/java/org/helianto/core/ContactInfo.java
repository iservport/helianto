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

import org.helianto.core.def.ContactType;

/**
 * Contact basic info.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Embeddable
public class ContactInfo implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private String contactAddress;
    private char contactType;

    /** 
     * Default constructor
     */
    public ContactInfo() {
    	this("");
    }

    /** 
     * Email constructor.
     * 
     * @param contactAddress
     */
    public ContactInfo(String contactAddress) {
    	this(contactAddress, ContactType.EMAIL);
    }

    /** 
     * Contact type constructor.
     * 
     * @param contactAddress
     * @param contactType
     */
    public ContactInfo(String contactAddress, ContactType contactType) {
    	setContactAddress(contactAddress);
    	setContactTypeAsEnum(contactType);
    }

    /**
     * Contact address.
     */
    @Column(length=64)
    public String getContactAddress() {
		return contactAddress;
	}
    public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

    /**
     * Contact type.
     */
    public char getContactType() {
		return contactType;
	}
    public void setContactType(char contactType) {
		this.contactType = contactType;
	}
    public void setContactTypeAsEnum(ContactType contactType) {
        this.contactType = contactType.getValue();
    }

    /**
     * toString
     * @return String
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer
        .append(getContactAddress())
        .append(" type ")
        .append(getContactType());

        return buffer.toString();
    }

}
