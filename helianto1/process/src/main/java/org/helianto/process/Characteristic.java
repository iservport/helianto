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

import org.helianto.core.Unit;


/**
 * <p>
 * Characteristics associated to documents.
 * </p>
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="proc_charac")
public class Characteristic extends ProcessDocument {
	
	private static final long serialVersionUID = 1L;
	private String nominalValue;
	private Unit unit;
	private char characteristicType;
	private int classification;

    /** default constructor */
    public Characteristic() {
    }
    
    /**
     * Nominal value.
     */
    @Column(length=20)
	public String getNominalValue() {
		return nominalValue;
	}
	public void setNominalValue(String nominalValue) {
		this.nominalValue = nominalValue;
	}
	
    /**
     * Unit.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="unitId", nullable=true)
	public Unit getUnit() {
		return unit;
	}
	public void setUnit(Unit unit) {
		this.unit = unit;
	}

    /**
     * Type of characteristic.
     */
	public char getCharacteristicType() {
		return characteristicType;
	}
	public void setCharacteristicType(char characteristicType) {
		this.characteristicType = characteristicType;
	}
	public void setCharacteristicType(CharacteristicType characteristicType) {
		this.characteristicType = characteristicType.getValue();
	}

    /**
     * Classification.
     */
    public int getClassification() {
        return this.classification;
    }
    public void setClassification(int classification) {
        this.classification = classification;
    }
    
    //1.1
    /**
     * <code>Characteristic</code> specification factory.
     * 
     * @param specificationCode
     * @param internalNumber
     * @param sequence
     */
    public DocumentAssociation characteristicSpecificationFactory(String specificationCode, long internalNumber, int sequence) {
    	DocumentAssociation documentAssociation = documentAssociationFactory(Specification.class, specificationCode, sequence);
        return documentAssociation;
    }

    /**
     * <code>Characteristic</code> query <code>StringBuilder</code>.
     */
    @Transient
    public static StringBuilder getCharacteristicQueryStringBuilder() {
        return new StringBuilder("select characteristic from Characteristic characteristic ");
    }

    /**
     * <code>Characteristic</code> natural id query.
     */
    @Transient
    public static String getCharacteristicNaturalIdQueryString() {
        return getCharacteristicQueryStringBuilder().append("where characteristic.document = ? and characteristic.sequence = ? ").toString();
    }

   /**
    * equals
    */
   @Override
   public boolean equals(Object other) {
         if ( !(other instanceof Characteristic) ) return false;
         return super.equals(other);
   }
   
}
