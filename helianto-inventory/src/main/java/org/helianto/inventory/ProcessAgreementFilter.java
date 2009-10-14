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

package org.helianto.inventory;

import org.helianto.core.filter.CriteriaBuilder;
import org.helianto.document.AbstractControlFilter;
import org.helianto.partner.Partner;
import org.helianto.process.ProcessDocument;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ProcessAgreementFilter extends AbstractControlFilter {

	private static final long serialVersionUID = 1L;
	private String customerAliasLike;
	private char agreementState;
	private ProcessDocument process;
	private int processIndex;
	private Partner customer;

	@Override
	protected void doFilter(CriteriaBuilder mainCriteriaBuilder) {
		// TODO Auto-generated method stub
		
	}

	public String getObjectAlias() {
		return "processagreement";
	}

	/**
	 * Reference customer alias filter.
	 */
	public String getCustomerAliasLike() {
		return customerAliasLike;
	}
	public void setCustomerAliasLike(String customerAliasLike) {
		this.customerAliasLike = customerAliasLike;
	}

	/**
	 * Agreement state filter.
	 */
	public char getAgreementState() {
		return agreementState;
	}
	public void setAgreementState(char agreementState) {
		this.agreementState = agreementState;
	}

	/**
	 * Process document filter.
	 */
	public ProcessDocument getProcess() {
		return process;
	}
	public void setProcess(ProcessDocument process) {
		this.process = process;
	}

	/**
	 * Customer filter.
	 */
	public Partner getCustomer() {
		return customer;
	}
	public void setCustomer(Partner customer) {
		this.customer = customer;
	}

	/**
	 * Process index filter.
	 */
	public int getProcessIndex() {
		return this.processIndex;
	}
	public void setProcessIndex(int processIndex) {
		this.processIndex = processIndex;
	}


}
