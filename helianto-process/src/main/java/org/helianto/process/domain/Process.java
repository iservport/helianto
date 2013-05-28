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

package org.helianto.process.domain;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.helianto.core.domain.Entity;
import org.helianto.core.number.Sequenceable;
import org.helianto.document.domain.ProcessDocument;
import org.helianto.partner.domain.Partner;

/**
 * Represents a <code>Process</code>.
 * 
 * <p>
 * A process is an ordered list of operations.
 * </p>
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="proc_process")
public class Process extends ProcessDocument implements Sequenceable {

    private static final long serialVersionUID = 1L;
    private long internalNumber;
    private Partner partner;

    /** 
     * Default constructor.
     */
    public Process() {
    	super();
    }

    /** 
     * Key constructor.
     * 
     * @param entity
     * @param docCode
     */
    public Process(Entity entity, String docCode) {
    	this();
    	setEntity(entity);
    	setDocCode(docCode);
    }

    /**
     * Internal number.
     */
    public long getInternalNumber() {
        return this.internalNumber;
    }
    public void setInternalNumber(long internalNumber) {
        this.internalNumber = internalNumber;
    }
//    @Transient
//	public String getInternalNumberKey() {
//		return "PROC";
//	}
 
    @Transient
    public int getStartNumber() {
    	return 1;
    }

    /**
     * Partner, if exists.
     */
    @ManyToOne(cascade={CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name="partnerId", nullable=true)
    public Partner getPartner() {
		return partner;
	}
	public void setPartner(Partner partner) {
		this.partner = partner;
	}

    public boolean equals(Object other) {
		 if ( !(other instanceof Process) ) return false;
		 return super.equals(other);
 }

}
