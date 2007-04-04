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

package org.helianto.core;

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

import org.helianto.core.Entity;
/**
 * <p>
 * Represents scale <code>Unit</code>s .  
 * </p>
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="core_unit",
    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "unitCode"})}
)
public class Unit implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private Entity entity;
    private String unitCode;
    private String unitName;

    /** default constructor */
    public Unit() {
    }

    // Property accessors
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Entity getter.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="entityId", nullable=true)
    public Entity getEntity() {
        return this.entity;
    }
    /**
     * Entity setter.
     */
    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    /**
     * UnitCode getter.
     */
    @Column(length=12)
    public String getUnitCode() {
        return this.unitCode;
    }
    /**
     * UnitCode setter.
     */
    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    /**
     * UnitName getter.
     */
    @Column(length=64)
    public String getUnitName() {
        return this.unitName;
    }
    /**
     * UnitName setter.
     */
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    /**
     * <code>Unit</code> factory.
     * 
     * @param entity
     * @param unitCode
     */
    public static Unit unitFactory(Entity entity, String unitCode) {
        Unit unit = new Unit();
        unit.setEntity(entity);
        unit.setUnitCode(unitCode);
        return unit;
    }

    /**
     * <code>Unit</code> natural id query.
     */
    @Transient
    public static String getUnitNaturalIdQueryString() {
        return "select unit from Unit unit where unit.entity = ? and unit.unitCode = ? ";
    }

    /**
     * <code>Unit</code> master query.
     */
    @Transient
    public static String getUnitEntityQueryString() {
        return "select unit from Unit unit where unit.entity = ? ";
    }

    /**
     * toString
     * @return String
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("entity").append("='").append(getEntity()).append("' ");
        buffer.append("unitCode").append("='").append(getUnitCode()).append("' ");
        buffer.append("]");
      
        return buffer.toString();
    }

   /**
    * equals
    */
   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
         if ( (other == null ) ) return false;
         if ( !(other instanceof Unit) ) return false;
         Unit castOther = (Unit) other; 
         
         return ((this.getEntity()==castOther.getEntity()) || ( this.getEntity()!=null && castOther.getEntity()!=null && this.getEntity().equals(castOther.getEntity()) ))
             && ((this.getUnitCode()==castOther.getUnitCode()) || ( this.getUnitCode()!=null && castOther.getUnitCode()!=null && this.getUnitCode().equals(castOther.getUnitCode()) ));
   }
   
   /**
    * hashCode
    */
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getEntity() == null ? 0 : this.getEntity().hashCode() );
         result = 37 * result + ( getUnitCode() == null ? 0 : this.getUnitCode().hashCode() );
         return result;
   }   

}
