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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.helianto.core.base.AbstractKeyValue;
import org.helianto.core.domain.KeyType;

/**
 * Simple tax calculation.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="inv_tax",
    uniqueConstraints = {@UniqueConstraint(columnNames={"processAgreementId", "keyTypeId"})}
)
@DiscriminatorColumn(
    name="type",
    discriminatorType=DiscriminatorType.CHAR
)
@DiscriminatorValue("T")
public class Tax 
	extends AbstractKeyValue {

	/**
	 * <<Transient>> Delegate to the actual key owner.
	 */
	@Transient
	@Override
	protected Object getKeyOwner() {
		return getProcessAgreement();
	}   

    /**
     * Factory method.
     * 
     * @param processAgreement
     * @param keyType
     */
    public static Tax taxFactory(ProcessAgreement processAgreement, KeyType keyType) {
    	Tax tax = new Tax();
        tax.setProcessAgreement(processAgreement);
        tax.setKeyType(keyType);
        return tax;
    }

    private static final long serialVersionUID = 1L;
    private ProcessAgreement processAgreement;
    private BigDecimal taxBaseValue;
    private BigDecimal taxRate;
    private BigDecimal taxValue;

	/** 
     * Default constructor.
     */
    public Tax() {
    	super();
    	taxBaseValue = BigDecimal.ZERO;
    	taxRate = BigDecimal.ZERO;
    	taxValue = BigDecimal.ZERO;
    }
    
    /**
     * Key constructor.
     * 
     * @param processAgreement
     * @param keyType
     */
    public Tax(ProcessAgreement processAgreement, KeyType keyType) {
    	this();
        setProcessAgreement(processAgreement);
        setKeyType(keyType);
    }

    /**
	 * <<Immutable>> Tax code, a reference to the actual key code.
	 * 
	 * <p>
	 * This is a workaround to provide a string key to an
	 * associated Map. JPA associations with maps does not 
	 * allow for a key that is not a true property of the value
	 * class (unlike Hibernate).
	 * </p>
	 */
    @Column(length=20)
	public String getTaxCode() {
    	if (getKeyType()!=null) {
    		return getKeyType().getKeyCode();
    	}
		return "";
	}
	public void setTaxCode(String taxCode) {
		// this field is immutable
	}

    /**
     * Process agreement.
     */
    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="processAgreementId")
    public ProcessAgreement getProcessAgreement() {
		return processAgreement;
	}
	public void setProcessAgreement(ProcessAgreement processAgreement) {
		this.processAgreement = processAgreement;
	}

    /**
     * The tax base value.
     */
	public BigDecimal getTaxBaseValue() {
		return taxBaseValue;
	}
	public Tax setTaxBaseValue(BigDecimal taxBaseValue) {
		this.taxBaseValue = taxBaseValue;
		return this;
	}

    /**
     * The tax rate.
     */
	public BigDecimal getTaxRate() {
		return taxRate;
	}
	public Tax setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
		return this;
	}

    /**
     * <<Transient>> Actual tax calculation.
     */
	@Transient
	public Tax calculate() {
		return setTaxValue(getTaxBaseValue().multiply(getTaxRate()).divide(new BigDecimal(100)));
	}

    /**
     * The tax value.
     */
	public BigDecimal getTaxValue() {
		return taxValue;
	}
	public Tax setTaxValue(BigDecimal taxValue) {
		this.taxValue = taxValue;
		return this;
	}

}
