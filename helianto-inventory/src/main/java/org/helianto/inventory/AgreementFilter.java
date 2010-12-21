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

import org.helianto.core.User;
import org.helianto.core.criteria.CriteriaBuilder;
import org.helianto.core.filter.classic.AbstractUserBackedCriteriaFilter;
import org.helianto.partner.Partner;
import org.helianto.process.ProcessDocument;

public class AgreementFilter extends AbstractUserBackedCriteriaFilter {
	
	private static final long serialVersionUID = 1L;
	private long internalNumber;
	private char agreementState;
	private Partner customer;
	private String customerAliasLike;
	private ProcessDocument process;
	private int processIndex;
	private String summaryLike;

	public static AgreementFilter agreementFilterFactory(User user) {
		AgreementFilter agreementFilter = new AgreementFilter();
		agreementFilter.setUser(user);
		agreementFilter.reset();
		return agreementFilter;
	}

	public void reset() {
		setAgreementState(' ');
		setSummaryLike("");
		setCustomerAliasLike("");
	}

	@Override
	protected void preProcessFilter(CriteriaBuilder mainCriteriaBuilder) {
		if (getCustomer()!=null) {
			appendEqualFilter("partner.id", getCustomer().getId(), mainCriteriaBuilder);
		}
	}
	
	@Override
	public void doFilter(CriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("resolution", getAgreementState(), mainCriteriaBuilder);
		appendLikeFilter("summary", getSummaryLike(), mainCriteriaBuilder);
		appendLikeFilter("partner.partnerRegistry.partnerAlias", getCustomerAliasLike(), mainCriteriaBuilder);
		if (getProcess()!=null) {
			mainCriteriaBuilder.appendAnd().append(processCriteriaBuilder(mainCriteriaBuilder.getPrefix()));
		}
	}
	
	protected CriteriaBuilder processCriteriaBuilder(String prefix) {
		CriteriaBuilder processCriteriaBuilder = new CriteriaBuilder(prefix);
		processCriteriaBuilder.appendSegment("processDocument.id", "=").append(getProcess().getId());
		processCriteriaBuilder.appendOr().appendSegment("processDocument.parent.id", "=").append(getProcess().getId());
		return processCriteriaBuilder;
	}

	@Override
	protected void doSelect(CriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("internalNumber", getInternalNumber(), mainCriteriaBuilder);
	}

	@Override
	public boolean isSelection() {
		return getInternalNumber()!=0;
	}

	/**
	 * Filtro de número de identificação.
	 */
	public long getInternalNumber() {
		return internalNumber;
	}
	public void setInternalNumber(long internalNumber) {
		this.internalNumber = internalNumber;
	}

	/**
	 * Filtro de situação
	 */
	public char getAgreementState() {
		return agreementState;
	}
	public void setAgreementState(char agreementState) {
		this.agreementState = agreementState;
	}

	/**
	 * Cliente 
	 */
	public Partner getCustomer() {
		return customer;
	}
	public void setCustomer(Partner customer) {
		this.customer = customer;
	}

	/**
	 * Nome do cliente 
	 */
	public String getCustomerAliasLike() {
		return customerAliasLike;
	}
	public void setCustomerAliasLike(String customerAliasLike) {
		this.customerAliasLike = customerAliasLike;
	}

	/**
	 * Processo 
	 */
	public ProcessDocument getProcess() {
		return process;
	}
	public void setProcess(ProcessDocument process) {
		this.process = process;
	}

	/**
	 * Índice na lista de processo 
	 */
	public int getProcessIndex() {
		return processIndex;
	}
	public void setProcessIndex(int processIndex) {
		this.processIndex = processIndex;
	}
	/**
	 * Nome dado ao orçamento 
	 */
	public String getSummaryLike() {
		return summaryLike;
	}
	public void setSummaryLike(String summaryLike) {
		this.summaryLike = summaryLike;
	}

	public String getObjectAlias() {
		return "agreement";
	}

}