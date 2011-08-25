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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.helianto.core.Entity;
import org.helianto.core.TrunkEntity;
import org.helianto.core.Unit;
import org.helianto.process.filter.MeasurementTechniqueForm;

/**
 * <p>
 * Represents a measurement technique.  
 * </p>
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="proc_measurement",
    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "measurementTechniqueCode"})}
)
@Inheritance(strategy = InheritanceType.JOINED)
public class MeasurementTechnique implements TrunkEntity, MeasurementTechniqueForm {

	private static final long serialVersionUID = 1L;
	private int id;
    private Entity entity;
    private String measurementTechniqueCode;
    private String measurementTechniqueName;
    private Unit unit;

    /**
     * Default constructor.
     */
    public MeasurementTechnique() {
    	setMeasurementTechniqueName("");
    }

    /**
     * Key constructor.
     * 
     * @param entity
     * @param measurementTechniqueCode
     */
    public MeasurementTechnique(Entity entity, String measurementTechniqueCode) {
    	this();
    	setEntity(entity);
    	setMeasurementTechniqueCode(measurementTechniqueCode);
    }

    /**
     * Unit constructor.
     * 
     * @param unit
     * @param measurementTechniqueCode
     */
    public MeasurementTechnique(Unit unit, String measurementTechniqueCode) {
    	this(unit.getEntity(), measurementTechniqueCode);
    	setUnit(unit);
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
     * Entity.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="entityId", nullable=true)
    public Entity getEntity() {
        return this.entity;
    }
    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    /**
     * Measurement unit.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="unitId", nullable=true)
    public Unit getUnit() {
        return this.unit;
    }
    @Transient
    public String getUnitCode() {
    	if (this.unit==null) return "";
    	return this.unit.getUnitCode();
    }
    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    /**
     * Measurement technique code.
     */
    @Column(length=20)
    public String getMeasurementTechniqueCode() {
        return this.measurementTechniqueCode;
    }
    public void setMeasurementTechniqueCode(String measurementTechniqueCode) {
        this.measurementTechniqueCode = measurementTechniqueCode;
    }
    
    /**
     * Measurement technique name.
     */
    @Column(length=64)
    public String getMeasurementTechniqueName() {
        return this.measurementTechniqueName;
    }
    @Transient
    public String getName() {
        return this.measurementTechniqueName;
    }
    public void setMeasurementTechniqueName(String measurementTechniqueName) {
        this.measurementTechniqueName = measurementTechniqueName;
    }
    
    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("measurementTechniqueCode").append("='").append(getMeasurementTechniqueCode()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }

   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof MeasurementTechnique) ) return false;
		 MeasurementTechnique castOther = ( MeasurementTechnique ) other; 
         
		 return ( (this.getUnit()==castOther.getUnit()) || ( this.getUnit()!=null && castOther.getUnit()!=null && this.getUnit().equals(castOther.getUnit()) ) )
             && ( (this.getMeasurementTechniqueCode()==castOther.getMeasurementTechniqueCode()) || ( this.getMeasurementTechniqueCode()!=null && castOther.getMeasurementTechniqueCode()!=null && this.getMeasurementTechniqueCode().equals(castOther.getMeasurementTechniqueCode()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getMeasurementTechniqueCode() == null ? 0 : this.getMeasurementTechniqueCode().hashCode() );
         return result;
   }   

}
