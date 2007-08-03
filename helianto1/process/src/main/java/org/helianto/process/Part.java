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
import javax.persistence.UniqueConstraint;

import org.helianto.core.Entity;
import org.helianto.partner.Partner;
/**
 * <p>
 * Represents a <code>Part</code>.  
 * </p>
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="proc_part")
public class Part extends Document implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private boolean hasDrawing;
    private Partner designResponsibility;
    private float weight;

    /** default constructor */
    public Part() {
    }

    // Property accessors
    /**
     * HasDrawing getter.
     */
    public boolean getHasDrawing() {
        return this.hasDrawing;
    }
    /**
     * HasDrawing setter.
     */
    public void setHasDrawing(boolean hasDrawing) {
        this.hasDrawing = hasDrawing;
    }

    /**
     * DesignResponsibility getter.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="partnerId", nullable=true)
    public Partner getDesignResponsibility() {
        return this.designResponsibility;
    }
    /**
     * DesignResponsibility setter.
     */
    public void setDesignResponsibility(Partner designResponsibility) {
        this.designResponsibility = designResponsibility;
    }

    /**
     * Weight getter.
     */
    @Column(precision=10, scale=4)
    public float getWeight() {
        return this.weight;
    }
    /**
     * Weight setter.
     */
    public void setWeight(float weight) {
        this.weight = weight;
    }
    //1.1
    /**
     * <code>Part</code> factory.
     * 
     * @param entity
     * @param docCode
     */
    public static Part partFactory(Entity entity, String docCode) {
        return (Part) documentFactory(Part.class, entity, docCode);
    }

    //1.2
    /**
     * <code>Part</code> factory.
     * 
     * @param parent
     * @param docCode
     * @param coefficient
     */
    public static Part partFactory(Part parent, String docCode, double coefficient) {
        return (Part) documentFactory(Part.class, parent, docCode, coefficient, AssociationType.PART_PART);
    }

    //1.3
    /**
     * <code>Part</code> factory.
     * 
     * @param parent
     * @param docCode
     * @param coefficient
     */
    public static Part partFactory(Operation parent, String docCode, double coefficient) {
        return (Part) documentFactory(Part.class, parent, docCode, coefficient, AssociationType.OPERATION_PART);
    }

    //1.4
    /**
     * <code>Part</code> factory.
     * 
     * @param parent
     * @param docCode
     * @param coefficient
     */
    public static Part partFactory(Process parent, String docCode, double coefficient) {
        return (Part) documentFactory(Part.class, parent, docCode, coefficient, AssociationType.PROCESS_PART);
    }

    /**
     * <code>Part</code> natural id query.
     */
    @Transient
    public static String getDocumentNaturalIdQueryString() {
        return "select part from Part part where part.entity = ? and part.docCode = ? ";
    }

}
