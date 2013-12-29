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

package org.helianto.inventory.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;


/**
 * A special inventory movement, where a previous process agreement
 * is referenced.
 * 
 * <p>
 * A shipment is appropriate to work as an invoice item because it exposes
 * properties like price and taxes.
 * </p>
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@DiscriminatorValue("S")
public class Shipment extends Movement implements Comparable<Shipment> {

	private static final long serialVersionUID = 1L;
	
	@JsonBackReference 
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="processAgreementId")
	private ProcessAgreement processAgreement;
	
	@Column(precision=4)
	private int sequence;
	
	@Column(length=512)
	private String info = "";
	
	/**
	 * Default constructor.
	 */
	public Shipment() {
		super();
	}
	
	/**
	 * Process agreement.
	 */
	public ProcessAgreement getProcessAgreement() {
		return processAgreement;
	}
	public void setProcessAgreement(ProcessAgreement processAgreement) {
		this.processAgreement = processAgreement;
	}

	/**
	 * Process agreement.
	 */
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	/**
	 * Additional information concerning the shipment.
	 */
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	/**
	 * Implementation of Comparable interface.
	 */
	public int compareTo(Shipment other) {
		return this.getSequence() - other.getSequence();
	}
   
    @Override
    public boolean equals(Object other) {
		if (other instanceof Shipment) {
			return super.equals(other);
		}
		return false;
	}

}
