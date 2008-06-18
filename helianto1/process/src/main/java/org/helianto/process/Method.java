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
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * <p>
 * A method extends a specification to add the evaluation or measurement technique.
 * </p>
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="proc_method")
public class Method extends Specification implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private int sampleSize;
    private String sampleFrequency;
    private MeasurementTechnique measurementTechnique;
    private String controlMethod;

    /** default constructor */
    public Method() {
    }

    /**
     * Sample size.
     */
    public int getSampleSize() {
        return this.sampleSize;
    }
    public void setSampleSize(int sampleSize) {
        this.sampleSize = sampleSize;
    }
    
    /**
     * Sample frequency.
     */
    @Column(length=20)
    public String getSampleFrequency() {
        return this.sampleFrequency;
    }
    public void setSampleFrequency(String sampleFrequency) {
        this.sampleFrequency = sampleFrequency;
    }
    
    /**
     * Measurement technique.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="measurementTechniqueId", nullable=true)
    public MeasurementTechnique getMeasurementTechnique() {
        return this.measurementTechnique;
    }
    public void setMeasurementTechnique(MeasurementTechnique measurementTechnique) {
        this.measurementTechnique = measurementTechnique;
    }
    
    /**
     * Control method.
     */
    @Column(length=32)
    public String getControlMethod() {
        return this.controlMethod;
    }
    public void setControlMethod(String control) {
        this.controlMethod = control;
    }

    /**
     * <code>Method</code> factory.
     * 
     * @param document
     * @param characteristic
     */
    public static Method methodFactory(ProcessDocument document, Characteristic characteristic) {
        return specificationFactory(Method.class, document, characteristic);
    }

    /**
     * <code>Method</code> query <code>StringBuilder</code>.
     */
    @Transient
    public static StringBuilder getMethodQueryStringBuilder() {
        return new StringBuilder("select method from Method method ");
    }

    /**
     * <code>Method</code> natural id query.
     */
    @Transient
    public static String getMethodNaturalIdQueryString() {
        return getMethodQueryStringBuilder().append("where method.document = ? and method.characteristic = ? ").toString();
    }


}


