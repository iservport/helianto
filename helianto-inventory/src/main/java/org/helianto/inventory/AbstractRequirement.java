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

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.helianto.core.Entity;
import org.helianto.core.TopLevelNumberedEntity;
import org.helianto.core.Unit;
import org.helianto.document.AbstractControl;
import org.helianto.process.DerivedProcessDocument;
import org.helianto.process.ProcessDocument;


/**
 * Common properties to customer requirements, stock, purchase and production 
 * orders, deliveries and agreements.
 * 
 * @author Mauricio Fernandes de Castro
 */
@MappedSuperclass
public abstract class AbstractRequirement extends AbstractControl {

    /**
     * Internal factory method.
     * 
     * @param clazz
     * @param part
     * @param requirementDate
     */
    public static <T extends AbstractRequirement> T internalRequirementFactory(Class<T> clazz, Entity entity, Date requirementDate) {
        T requirement;
        try {
            requirement = clazz.newInstance();
            requirement.setEntity(entity);
            requirement.setRequirementDate(requirementDate);
            requirement.setResolution(RequirementState.FORECAST.getValue());
            return requirement;
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to instantiate "+clazz);
        }
    }

    private static final long serialVersionUID = 1L;
    protected ProcessDocument document;
    protected Date requirementDate;
    protected BigDecimal requirementAmount = BigDecimal.ZERO;
    protected int requirementSign;

    /** 
     * Constructor 
	 */
    public AbstractRequirement() {
    	super();
    }

    /**
     * Owning process document.
     * 
     * <p>
     * Usually a part or a process operation.
     * </p>
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="documentId", nullable=true)
    public ProcessDocument getProcessDocument() {
    	ProcessDocument externalProcessDocument = resolveExternalProcessDocument();
    	if (externalProcessDocument!=null) {
    		return externalProcessDocument;
    	}
        return this.document;
    }
    @Transient
    protected ProcessDocument resolveExternalProcessDocument() {
    	return null;
    }
    @Transient
    public String getDocCode() {
    	if (getProcessDocument()!=null) {
    		return getProcessDocument().getDocCode();
    	}
        return "";
    }
    @Transient
    public String getDocName() {
    	if (getProcessDocument()!=null) {
    		return getProcessDocument().getDocName();
    	}
        return "";
    }
    @Transient
    public String[] getColorChain() {
    	if (getProcessDocument()!=null && getProcessDocument() instanceof DerivedProcessDocument) {
    		return ((DerivedProcessDocument) getProcessDocument()).getProcessColorChain();
    	}
        return new String[] {""};
    }
    public void setProcessDocument(ProcessDocument document) {
        this.document = document;
    }

    /**
     * Requirement date.
     */
    @Temporal(TemporalType.TIMESTAMP)
    public Date getRequirementDate() {
        return this.requirementDate;
    }
    @Transient
    public String getRequirementDateTimeAsString() {
    	return SimpleDateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, getLocale()).format(getRequirementDate());
    }
    @Transient
    public String getRequirementDateAsString() {
    	return SimpleDateFormat.getDateInstance(DateFormat.SHORT, getLocale()).format(getRequirementDate());
    }
    @Transient
    public String getRequirementTimeAsString() {
    	return SimpleDateFormat.getTimeInstance(DateFormat.SHORT, getLocale()).format(getRequirementDate());
    }
    public void setRequirementDate(Date requirementDate) {
        this.requirementDate = requirementDate;
    }

    /**
     * Requirement amount.
     */
    @Column(precision=10, scale=4)
    public BigDecimal getRequirementAmount() {
        return this.requirementAmount;
    }
    public void setRequirementAmount(BigDecimal requirementAmount) {
        this.requirementAmount = requirementAmount;
    }

    /**
     * <<Transient>> Unit.
     */
    @Transient
    public Unit getUnit() {
		return getProcessDocument().getUnit();
	}

    /**
     * Resolution.
     */
    public void setResolution(char resolution) {
    	super.setResolution(validateResolutionChange(resolution));
    }
    public void setResolution(RequirementState resolution) {
        setResolution(resolution.getValue());
    }
    public char validateResolutionChange(char newResolution) {
    	return newResolution;
    }

    /**
     * Signal input or output.
     */
    @Column(precision=1, scale=0)
    public int getRequirementSign() {
        return this.requirementSign;
    }
    public void setRequirementSign(int requirementSign) {
        this.requirementSign = requirementSign;
    }
    public void setRequirementSign(RequirementSign requirementSign) {
        this.requirementSign = requirementSign.getValue();
    }

	public TopLevelNumberedEntity setKey(Entity entity, long internalNumber) {
        this.setEntity(entity);
        this.setInternalNumber(internalNumber);
		return this;
	}

    /**
     * equals
     */
    @Override
    public boolean equals(Object other) {
          if ( !(other instanceof AbstractRequirement) ) return false;
          return super.equals(other);
    }
    
 }
