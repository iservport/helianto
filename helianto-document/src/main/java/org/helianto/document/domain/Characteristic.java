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

package org.helianto.document.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.helianto.core.domain.Unit;
import org.helianto.document.def.CharacteristicType;


/**
 * <p>
 * Characteristics associated to documents.
 * </p>
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="doc_charac",
    uniqueConstraints = {@UniqueConstraint(columnNames={"documentId", "sequence"})}
)
public class Characteristic 
	implements Serializable {
	
    private static final long serialVersionUID = 1L;
    private int id;
    private int version;
    private Document document;
    private int sequence;
	private String nominalValue;
	private Unit unit;
	private char characteristicType;
	private int classification;

    /** 
     * Default constructor 
     */
    public Characteristic() {
    	super();
		setCharacteristicType(CharacteristicType.PROCESS);
    }
    
    /** 
     * Key constructor.
     * 
     * @param entity
     * @param sequence
     */
    public Characteristic(Document document, int sequence) {
    	this();
    	setDocument(document);
    	setSequence(sequence);
    }
    
    /**
     * Primary key.
     */
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Version.
     */
    @Version
    public int getVersion() {
        return this.version;
    }
    public void setVersion(int version) {
        this.version = version;
    }
    
    /**
     * <<NaturalKey>> Document
     */
    @ManyToOne
    @JoinColumn(name="documentId", nullable=true)
    public Document getDocument() {
		return document;
	}
    public void setDocument(Document document) {
		this.document = document;
	}
    
    /**
     * Characteristic sequence.
     */
    public int getSequence() {
		return sequence;
	}
    public void setSequence(int sequence) {
		this.sequence = sequence;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((document == null) ? 0 : document.hashCode());
		result = prime * result + sequence;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Characteristic)) {
			return false;
		}
		Characteristic other = (Characteristic) obj;
		if (document == null) {
			if (other.document != null) {
				return false;
			}
		} else if (!document.equals(other.document)) {
			return false;
		}
		if (sequence != other.sequence) {
			return false;
		}
		return true;
	}
    
   
}
