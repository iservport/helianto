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

package org.helianto.inventory.internal;

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

import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Unit;
import org.helianto.core.internal.AbstractEventControl;
import org.helianto.core.number.Sequenceable;
import org.helianto.document.domain.ProcessDocument;
import org.helianto.inventory.RequirementSign;
import org.helianto.inventory.RequirementState;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;


/**
 * Common properties to customer requirements, stock, purchase and production 
 * orders, deliveries and agreements.
 * 
 * @author Mauricio Fernandes de Castro
 */
@MappedSuperclass
public abstract class AbstractRequirement 
	extends AbstractEventControl 
	implements Sequenceable 
{

    private static final long serialVersionUID = 1L;
    
    @JsonBackReference 
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="documentId", nullable=true)
    protected ProcessDocument document;
    
    @DateTimeFormat(style="SS")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date requirementDate = new Date();
    
    @Column(precision=10, scale=4)
    protected BigDecimal requirementAmount = BigDecimal.ZERO;
    
    @Column(precision=1, scale=0)
    protected int requirementSign = RequirementSign.INCREMENT.getValue();

    /** 
     * Constructor 
	 */
    public AbstractRequirement() {
    	super();
    	setResolution(RequirementState.FORECAST.getValue());
    }

    /**
     * Key constructor.
     * 
     * @param entity
     * @param internalNumber
     */
	public AbstractRequirement(Entity entity, long internalNumber) {
		this();
        setEntity(entity);
        setInternalNumber(internalNumber);
	}

    /**
     * Owning process document.
     * 
     * <p>
     * Usually a part or a process operation.
     * </p>
     */
    public ProcessDocument getProcessDocument() {
    	ProcessDocument externalProcessDocument = resolveExternalProcessDocument();
    	if (externalProcessDocument!=null) {
    		return externalProcessDocument;
    	}
        return this.document;
    }
//    @Transient
    protected ProcessDocument resolveExternalProcessDocument() {
    	return null;
    }
//    @Transient
    public String getDocCode() {
    	if (getProcessDocument()!=null) {
    		return getProcessDocument().getDocCode();
    	}
        return "";
    }
//    @Transient
    public String getDocName() {
    	if (getProcessDocument()!=null) {
    		return getProcessDocument().getDocName();
    	}
        return "";
    }
//    @Transient
    public String[] getColorChain() {
    	if (getProcessDocument()!=null && getProcessDocument() instanceof ProcessDocument) {
    		return ((ProcessDocument) getProcessDocument()).getProcessColorChain();
    	}
        return new String[] {""};
    }
    public void setProcessDocument(ProcessDocument document) {
        this.document = document;
    }

    /**
     * Requirement date.
     */
    public Date getRequirementDate() {
        return this.requirementDate;
    }
//    @Transient
    public String getRequirementDateTimeAsString() {
    	return SimpleDateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, getLocale()).format(getRequirementDate());
    }
//    @Transient
    public String getRequirementDateAsString() {
    	return SimpleDateFormat.getDateInstance(DateFormat.SHORT, getLocale()).format(getRequirementDate());
    }
//    @Transient
    public String getRequirementTimeAsString() {
    	return SimpleDateFormat.getTimeInstance(DateFormat.SHORT, getLocale()).format(getRequirementDate());
    }
    public void setRequirementDate(Date requirementDate) {
        this.requirementDate = requirementDate;
    }

    /**
     * Requirement amount.
     */
    public BigDecimal getRequirementAmount() {
        return this.requirementAmount;
    }
    public void setRequirementAmount(BigDecimal requirementAmount) {
        this.requirementAmount = requirementAmount;
    }

    /**
     * <<Transient>> Unit.
     */
//    @Transient
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
    public int getRequirementSign() {
        return this.requirementSign;
    }
    public void setRequirementSign(int requirementSign) {
        this.requirementSign = requirementSign;
    }
    public void setRequirementSignAsEnum(RequirementSign requirementSign) {
        this.requirementSign = requirementSign.getValue();
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
