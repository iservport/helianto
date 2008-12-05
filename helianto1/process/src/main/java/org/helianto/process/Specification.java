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

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


/**		
 * <p>
 * A process characteristic specification.
 * </p>
 * @author Mauricio Fernandes de Castro	
 */
@javax.persistence.Entity
@Table(name="proc_spec")
public class Specification extends ProcessDocument {
    
	private static final long serialVersionUID = 1L;
	private int specificationType;
	private SpecificationLimit specificationLimit;
    private MeasurementTechnique measurementTechnique;
    private Method method;

	/** default constructor */
    public Specification() {
    }

    /**
     * Specification type.
     */
    public int getSpecificationType() {
		return specificationType;
	}
	public void setSpecificationType(int specificationType) {
		this.specificationType = specificationType;
	}
	public void setSpecificationType(SpecificationType specificationType) {
		this.specificationType = specificationType.getValue();
	}

    /**
     * Specification limit.
     */
    @Embedded
    public SpecificationLimit getSpecificationLimit() {
        return this.specificationLimit;
    }
    public void setSpecificationLimit(SpecificationLimit specificationLimit) {
        this.specificationLimit = specificationLimit;
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

    /**
     * Method.
     */
    @Embedded
	public Method getMethod() {
		return method;
	}
    public void setMethod(Method method) {
		this.method = method;
	}

	/**
     * <code>Specification</code> query <code>StringBuilder</code>.
     */
    @Transient
    public static StringBuilder getSpecificationQueryStringBuilder() {
        return new StringBuilder("select specification from Specification specification ");
    }

    /**
     * <code>Specification</code> natural id query.
     */
    @Transient
    public static String getSpecificationNaturalIdQueryString() {
        return getSpecificationQueryStringBuilder().append("where specification.entity = ? and specification.docCode = ? ").toString();
    }

    /**
     * toString
     * @return String
     */
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("specificationType").append("='").append(getSpecificationType()).append("' ");
        buffer.append("specificationLimit").append("='").append(getSpecificationLimit()).append("' ");
        buffer.append("]");
      
        return buffer.toString();
    }

    /**
     * equals
     */
    @Override
    public boolean equals(Object other) {
          if ( !(other instanceof Specification) ) return false;
          return super.equals(other);
    }
    
}
