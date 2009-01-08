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

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;


/**
 * <p>
 * Methods to associate with characteristic in a control plan.
 * </p>
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@DiscriminatorValue("M")
public class Method extends DocumentAssociation {
	
	private static final long serialVersionUID = 1L;
    private MeasurementTechnique measurementTechnique;
    private boolean leftLimitRequired;
	private boolean rightLimitRequired;
    private BigDecimal sampleSize = BigDecimal.ONE;

    /** default constructor */
    public Method() {
    }
    
    /**
     * Measurement technique.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="measurementTechniqueId", nullable=true)
    public MeasurementTechnique getMeasurementTechnique() {
		return measurementTechnique;
	}
	public void setMeasurementTechnique(MeasurementTechnique measurementTechnique) {
		this.measurementTechnique = measurementTechnique;
	}

	@Transient
	public String getUnitCode() {
		return ((Characteristic) getParent()).getUnitCode();
	}

    /**
	 * True if left limit is required
	 */
	public boolean isLeftLimitRequired() {
		return leftLimitRequired;
	}
	public void setLeftLimitRequired(boolean leftLimitRequired) {
		this.leftLimitRequired = leftLimitRequired;
	}

	/**
	 * True if right limit is required
	 */
	public boolean isRightLimitRequired() {
		return rightLimitRequired;
	}
	public void setRightLimitRequired(boolean rightLimitRequired) {
		this.rightLimitRequired = rightLimitRequired;
	}

    /**
     * Specification type.
     */
	@Transient
    public SpecificationType getSpecificationType() {
		if (isLeftLimitRequired() && isRightLimitRequired()) {
			return SpecificationType.VARIABLE_BILATERAL;
		}
		if (isLeftLimitRequired()) {
			return SpecificationType.VARIABLE_UNILATERAL_MIN;
		}
		if (isRightLimitRequired()) {
			return SpecificationType.VARIABLE_UNILATERAL_MAX;
		}
		return SpecificationType.ATTRIBUTE;
	}

    /**
     * Sample size.
     */
    public BigDecimal getSampleSize() {
        return this.sampleSize;
    }
    public void setSampleSize(BigDecimal sampleSize) {
        this.sampleSize = sampleSize;
    }
    
    
    /**
     * <code>Method</code> query <code>StringBuilder</code>.
     */
    @Transient
    public static StringBuilder getCharacteristicQueryStringBuilder() {
        return new StringBuilder("select method from Method method ");
    }

    /**
     * <code>Characteristic</code> natural id query.
     */
    @Transient
    public static String getCharacteristicNaturalIdQueryString() {
        return getCharacteristicQueryStringBuilder().append("where method.parent = ? and method.child = ? ").toString();
    }

   /**
    * equals
    */
   @Override
   public boolean equals(Object other) {
         if ( !(other instanceof Method) ) return false;
         return super.equals(other);
   }
   
}
