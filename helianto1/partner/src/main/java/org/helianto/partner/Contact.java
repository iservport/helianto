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
import javax.persistence.Transient;

import org.helianto.core.Identity;
/**
 * <p>
 * Represents the relationship between the organization and other entities.  
 * </p>
 * @author Vlademir Teixeira 
 */
@javax.persistence.Entity
@DiscriminatorValue("C")
public class Contact extends Address {

	private static final long serialVersionUID = 1L;
	private Identity identity;
    private String departament;
    private String jobReference;
    private int priority;

    /** default constructor */
    public Contact() {
    }

    // Property accessors
    /**
     * Identity getter.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="identityId", nullable=true)
    public Identity getIdentity() {
        return this.identity;
    }
    /**
     * Identity setter.
     */
    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    /**
     * Departament getter.
     */
    @Column(length=20)
    public String getDepartament() {
        return this.departament;
    }
    /**
     * Departament setter.
     */
    public void setDepartament(String departament) {
        this.departament = departament;
    }

    /**
     * JobReference getter.
     */
    @Column(length=64)
    public String getJobReference() {
        return this.jobReference;
    }
    /**
     * JobReference setter.
     */
    public void setJobReference(String jobReference) {
        this.jobReference = jobReference;
    }

    /**
     * Priority getter.
     */
    public int getPriority() {
        return this.priority;
    }
    /**
     * Priority setter.
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * <code>Contact</code> factory.
     * 
     * @param identity
     */
    public static Contact contactFactory(PartnerAssociation partnerAssociation, int sequence, Identity identity) {
        Contact contact = new Contact();
        contact.setPartnerAssociation(partnerAssociation);
        contact.setSequence(sequence);
        contact.setAddressType(AddressType.PERSONAL.getValue());
        contact.setPrivacyLevel(PrivacyLevel.PUBLIC.getValue());
        contact.setIdentity(identity);
        partnerAssociation.getAddresses().add(contact);
        return contact;
    }

    /**
     * <code>Contact</code> natural id query.
     */
    @Transient
    public static String getContactNaturalIdQueryString() {
        return "select contact from Contact contact where contact.partnerAssociation = ? and contact.sequence = ? ";
    }

}
