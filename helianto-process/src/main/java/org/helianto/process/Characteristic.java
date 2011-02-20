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
public class Characteristic extends DerivedProcessDocument {
	
	private static final long serialVersionUID = 1L;
	private String nominalValue;
	private Unit unit;
	private char characteristicType;
	private int classification;

    /** default package constructor */
    public Characteristic() {
		setCharacteristicType(CharacteristicType.PROCESS);
		setInheritanceType(InheritanceType.FINAL);
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
    @Transient
    public String getUnitCode() {
    	if (unit!=null) {
    		return unit.getUnitCode();
    	}
    	return "";
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
    
   /**
    * equals
    */
   @Override
   public boolean equals(Object other) {
         if ( !(other instanceof Characteristic) ) return false;
         return super.equals(other);
   }
   
}
