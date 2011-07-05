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

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
/**
 * <p>
 * Represents scale <code>Unit</code>s .  
 * </p>
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="core_unit",
    uniqueConstraints = {@UniqueConstraint(columnNames={"categoryId", "unitCode"})}
)
public class Unit implements TrunkEntity {

    private static final long serialVersionUID = 1L;
    private int id;
    private Entity entity;
    private Category category;
    private String unitCode;
    private String unitName;
    private char priority;

    /** 
     * Default constructor
     */
    public Unit() {
    	setUnitCode("");
    	setUnitName("");
        setPriority('1');
    }

    /**
     * Key constructor.
     * 
     * @param entity
     * @param unitCode
     */
    public Unit(Entity entity, String unitCode) {
    	this();
        setEntity(entity);
        setUnitCode(unitCode);
    }

    /**
     * Convenience constructor.
     * 
     * @param category
     * @param unitCode
     */
    public Unit(Category category, String unitCode) {
    	this(category.getEntity(), unitCode);
    	setCategory(category);
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
     * Entity getter.
     */
    @ManyToOne
    @JoinColumn(name="entityId", nullable=true)
    public Entity getEntity() {
        return this.entity;
    }
    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    /**
     * Category getter.
     */
    @ManyToOne
    @JoinColumn(name="categoryId", nullable=true)
    public Category getCategory() {
        return this.category;
    }
    @Transient
    public String getCategoryName() {
    	if (this.category==null) return "";
    	return this.category.getCategoryName();
    }
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * UnitCode getter.
     */
    @Column(length=12)
    public String getUnitCode() {
        return this.unitCode;
    }
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
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    /**
     * Priority.
     */
	public char getPriority() {
		return priority;
	}
	public void setPriority(char priority) {
		this.priority = priority;
	}

    /**
     * toString
     * @return String
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
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
         result = 37 * result + ( getUnitCode() == null ? 0 : this.getUnitCode().hashCode() );
         return result;
   }

}
