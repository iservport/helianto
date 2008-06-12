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

package org.helianto.process;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

/**
 * <p>
 * Specification limits.
 * </p>
 * @author Mauricio Fernandes de Castro	
 */
@Embeddable
public class SpecificationLimit  implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private BigDecimal leftLimit;
    private BigDecimal rightLimit;
    private BigDecimal nominalValue;
    private int specificationType;


    /** default constructor */
    public SpecificationLimit() {
    }

    public BigDecimal getLeftLimit() {
        return this.leftLimit;
    }
    public void setLeftLimit(BigDecimal leftLimit) {
        this.leftLimit = leftLimit;
    }

    public BigDecimal getRightLimit() {
        return this.rightLimit;
    }
    public void setRightLimit(BigDecimal rightLimit) {
        this.rightLimit = rightLimit;
    }

    public BigDecimal getNominalValue() {
        return this.nominalValue;
    }
    public void setNominalValue(BigDecimal nominalValue) {
        this.nominalValue = nominalValue;
    }

    public int getSpecificationType() {
        return this.specificationType;
    }    
    public void setSpecificationType(int specificationType) {
        this.specificationType = specificationType;
    }
    
    /**
	 * Factory method.
	 * 
	 * @param leftLimit
	 * @param rightLimit
	 * @param nominalValue
	 */
    public SpecificationLimit specificationLimitFactory(BigDecimal leftLimit, BigDecimal rightLimit, BigDecimal nominalValue, int specificationType) {
    	SpecificationLimit specificationLimit = new SpecificationLimit();
    	specificationLimit.setLeftLimit(leftLimit);
    	specificationLimit.setRightLimit(rightLimit);
    	specificationLimit.setNominalValue(nominalValue);
    	specificationLimit.setSpecificationType(specificationType);
    	return specificationLimit;
    }
    
}
