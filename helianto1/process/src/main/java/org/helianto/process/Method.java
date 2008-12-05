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

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * <p>
 * A method provides sampling and control information.
 * </p>
 * @author Mauricio Fernandes de Castro
 */
@Embeddable
public class Method implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private int sampleSize;
    private String sampleFrequency;
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
     * Control method.
     */
    @Column(length=32)
    public String getControlMethod() {
        return this.controlMethod;
    }
    public void setControlMethod(String control) {
        this.controlMethod = control;
    }

}


