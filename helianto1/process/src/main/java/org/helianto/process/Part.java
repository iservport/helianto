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
public class Part extends ProcessDocument implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private boolean hasDrawing;
    private Partner designResponsibility;
    private float weight;

    /** default constructor */
    public Part() {
    }

    /**
     * Has drawing flag.
     */
    public boolean getHasDrawing() {
        return this.hasDrawing;
    }
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
     * <code>Part</code> component factory.
     * 
     * @param componentCode
     * @param sequence
     * @param coefficient
     */
    public DocumentAssociation partComponentFactory(String componentCode, int sequence) {
    	DocumentAssociation documentAssociation = documentAssociationFactory(Part.class, componentCode, sequence);
        return documentAssociation;
    }

    //1.3
    /**
     * <code>Part</code> characteristic factory.
     * 
     * @param characteristicCode
     * @param sequence
     */
    public DocumentAssociation partCharacteristicFactory(String characteristicCode, int sequence) {
    	DocumentAssociation documentAssociation = documentAssociationFactory(Characteristic.class, characteristicCode, sequence);
    	((Characteristic) documentAssociation.getChild()).setCharacteristicType(CharacteristicType.PRODUCT);
        return documentAssociation;
    }

    /**
     * <code>Part</code> query <code>StringBuilder</code>.
     */
    @Transient
    public static StringBuilder getPartQueryStringBuilder() {
        return new StringBuilder("select part from Part part ");
    }

    /**
     * <code>Part</code> natural id query.
     */
    @Transient
    public static String getPartNaturalIdQueryString() {
        return getPartQueryStringBuilder().append("where part.entity = ? and part.docCode = ? ").toString();
    }
    
    public boolean equals(Object other) {
		 if ( !(other instanceof Part) ) return false;
		 return super.equals(other);
 }
 
}
