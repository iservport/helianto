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

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.helianto.core.domain.Entity;
import org.helianto.inventory.AgreementLevel;
import org.helianto.inventory.AgreementState;
import org.helianto.inventory.ProcurementOption;
import org.helianto.inventory.domain.internal.AbstractRequirement;
import org.helianto.partner.domain.Partner;


/**
 * Join a partner to a requirement to represent an agreement to buy
 * or to sell something.
 * 
 * <p>
 * Subclass to tenders or quotations.
 * </p>
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="inv_agreement",
    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "internalNumber"})}
)
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="type",
    discriminatorType=DiscriminatorType.CHAR
)
@DiscriminatorValue("A")
public class ProcessAgreement extends AbstractRequirement {

	private static final long serialVersionUID = 1L;
	private Partner partner;
	private String agreementDesc;
	private char agreementLevel;
	private BigDecimal agreementPrice = BigDecimal.ZERO;
	private int minimalOrderDuration;
	private char procurementOption;
	private Map<String, Tax> taxes = new HashMap<String, Tax>(); 

	/** 
	 * Default constructor.
	 */
    public ProcessAgreement() {
    	super();
    	setResolution(AgreementState.PENDING);
    	setAgreementLevel(AgreementLevel.REQUIRE_CREDIT_ASSESSMENT);
    }

	/** 
	 * Key constructor.
	 * 
	 * @param entity
	 * @param internalNumber
	 */
    public ProcessAgreement(Entity entity, long internalNumber) {
    	this();
    	setEntity(entity);
    	setInternalNumber(internalNumber);
    }

	/** 
	 * Partner constructor.
	 * 
	 * @param partner
	 */
    public ProcessAgreement(Partner partner) {
    	this(partner.getEntity(), 0);
    	setPartner(partner);
    }

    @Transient
	public String getInternalNumberKey() {
		return "AGREEM";
	}

    @Transient
    public int getStartNumber() {
    	return 1;
    }

    /**
     * Customer or supplier.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="partnerId", nullable=true)
    public Partner getPartner() {
        return this.partner;
    }
    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    /**
     * <<Transient>> Customer or supplier alias.
     */
    @Transient
    public String getPartnerAlias() {
    	if (partner==null) return "";
        return this.partner.getEntityAlias();
    }

    /**
     * Description.
     */
    @Column(length=512)
    public String getAgreementDesc() {
		return agreementDesc;
	}
	public void setAgreementDesc(String agreementDesc) {
		this.agreementDesc = agreementDesc;
	}

    /**
     * Validate resolution.
     */
    @Override
    protected final char validateResolution(char agreementState) {
    	if (getState().isLate()) {
    		return AgreementState.EXPIRED.getValue();
    	}
        return agreementState;
    }

    public void setResolution(char agreementState) {
        super.setResolution(agreementState);
    }

    public void setResolution(AgreementState agreementState) {
        setResolution(agreementState.getValue());
    }
    /**
     * True if approved.
     */
    @Transient
    public boolean isApproved() {
    	if (getResolution()==AgreementState.APPROVED.getValue()) {
    		return true;
    	}
    	return false;
    }

    /**
     * Credit level assigned to the partner.
     */
	public char getAgreementLevel() {
		return agreementLevel;
	}
	public void setAgreementLevel(char agreementLevel) {
		this.agreementLevel = agreementLevel;
	}
	public void setAgreementLevel(AgreementLevel agreementLevel) {
		this.agreementLevel = agreementLevel.getValue();
	}

	/**
	 * Agreement price.
	 */
	@Column(precision=10, scale=3)
	public BigDecimal getAgreementPrice() {
		return agreementPrice;
	}
    public void setAgreementPrice(BigDecimal agreementPrice) {
		this.agreementPrice = agreementPrice;
	}

	/**
	 * Minimal order duration.
	 */
    public int getMinimalOrderDuration() {
		return minimalOrderDuration;
	}
	public void setMinimalOrderDuration(int minimalOrderDuration) {
		this.minimalOrderDuration = minimalOrderDuration;
	}
	
	/**
	 * Procurement option (make, buy local, import)
	 */
	public char getProcurementOption() {
		return procurementOption;
	}
	public void setProcurementOption(char procurementOption) {
		this.procurementOption = procurementOption;
	}
	public void setProcurementOption(ProcurementOption procurementOption) {
		this.procurementOption = procurementOption.getValue();
	}

	/**
	 * A map of taxes.
	 */
	@OneToMany(mappedBy="processAgreement", cascade=CascadeType.ALL)
	@MapKey(name="taxCode")
	public Map<String, Tax> getTaxes() {
		return taxes;
	}
	public void setTaxes(Map<String, Tax> taxes) {
		this.taxes = taxes;
	}

    /**
     * equals
     */
    @Override
    public boolean equals(Object other) {
          if ( !(other instanceof ProcessAgreement) ) return false;
          return super.equals(other);
    }
    
}
