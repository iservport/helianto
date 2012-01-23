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

package org.helianto.partner.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.helianto.core.Identity;
import org.helianto.core.Privacy;
import org.helianto.core.def.AddressType;
import org.helianto.core.def.PrivacyLevel;

/**
 * Contact
 * 
 * @author Mauricio Fernandes de Castro 
 */
@javax.persistence.Entity
@DiscriminatorValue("C")
public class Contact extends PrivateAddress implements Privacy {

	private static final long serialVersionUID = 1L;
	private Identity owner;
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
     * Key constructor.
     * 
     * @param partnerRegistry
     * @param sequence
	 */
    public Contact(PrivateEntity2 partnerRegistry, int sequence) {
    	this();
    	setPrivateEntity(partnerRegistry);
    	setSequence(sequence);
    }
    
    /** 
     * Owner constructor.
     * 
     * @param partnerRegistry
     * @param sequence
     * @param owner
	 */
    public Contact(PrivateEntity2 partnerRegistry, int sequence, Identity owner) {
    	this(partnerRegistry, sequence);
    	setOwner(owner);
    }
    
    @Transient
    public void reset() {
        setAddressType(' ');
        setPrivacyLevel(' ');
    }
    
    /**
     * Owner.
     */
    @ManyToOne
    @JoinColumn(name="identityId", nullable=true)
    public Identity getOwner() {
		return owner;
	}
    public void setOwner(Identity owner) {
		this.owner = owner;
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
          if ( !(other instanceof PrivateAddress) ) return false;
          return super.equals(other);
    }

}
