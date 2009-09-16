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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.helianto.core.Identity;

/**
 * Contact
 * 
 * @author Mauricio Fernandes de Castro 
 */
@javax.persistence.Entity
@DiscriminatorValue("C")
public class Contact extends Address {

    /**
     * Factory method.
     * 
     * @param partnerRegistry
     * @param sequence
     * @param identity
     */
    public static Contact contactFactory(PartnerRegistry partnerRegistry, int sequence, Identity identity) {
        Contact contact = new Contact();
        contact.setPartnerRegistry(partnerRegistry);
        contact.setSequence(sequence);
        contact.setIdentity(identity);
        partnerRegistry.getAddresses().add(contact);
        return contact;
    }

	private static final long serialVersionUID = 1L;
	private Identity identity;
    private String departament;
    private String jobReference;
    private int priority;

    /** 
     * Empty constructor.
	 */
    public Contact() {
    	super();
        setAddressType(AddressType.PERSONAL.getValue());
        setPrivacyLevel(PrivacyLevel.PUBLIC.getValue());
        setDepartament("");
        setJobReference("");
    }

    /** 
     * Preferred constructor.
     * 
     * @param partnerRegistry
	 */
    public Contact(PartnerRegistry partnerRegistry) {
    	this();
    	setPartnerRegistry(partnerRegistry);
    }
    
    /**
     * Identity getter.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="identityId", nullable=true)
    public Identity getIdentity() {
        return this.identity;
    }
    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    /**
     * Departament.
     */
    @Column(length=20)
    public String getDepartament() {
        return this.departament;
    }
    public void setDepartament(String departament) {
        this.departament = departament;
    }

    /**
     * Job reference.
     */
    @Column(length=64)
    public String getJobReference() {
        return this.jobReference;
    }
    public void setJobReference(String jobReference) {
        this.jobReference = jobReference;
    }

    /**
     * Priority.
     */
    public int getPriority() {
        return this.priority;
    }
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * equals
     */
    public boolean equals(Object other) {
          if ( !(other instanceof Address) ) return false;
          return super.equals(other);
    }

}
