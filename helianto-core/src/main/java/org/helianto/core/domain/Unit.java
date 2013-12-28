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

package org.helianto.core.domain;

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

import org.helianto.core.domain.type.TrunkEntity;
import org.helianto.core.utils.StringListUtils;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * <p>
 * Represents scale <code>Unit</code>s .  
 * </p>
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="core_unit2",
    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "unitCode"})}
)
public class Unit implements TrunkEntity {

    private static final long serialVersionUID = 1L;
    
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @Version
    private int version;
    
    @JsonBackReference 
    @ManyToOne
    @JoinColumn(name="entityId", nullable=true)
    private Entity entity;
    
    @Column(length=20)
    private String unitCode = "";
    
    @Column(length=20)
    private String unitSymbol = "";
    
    @Column(length=64)
    private String unitName = "";
    
    @Column(length=32)
    private String nature = "";
    
    private char priority = '1';

    /** 
     * Default constructor
     */
    public Unit() {
    	super();
    }

    /**
     * Entity constructor.
     * 
     * @param entity
     */
    public Unit(Entity entity) {
    	this();
        setEntity(entity);
    }

    /**
     * Key constructor.
     * 
     * @param entity
     * @param unitCode
     */
    public Unit(Entity entity, String unitCode) {
    	this(entity);
        setUnitCode(unitCode);
    }

    /**
     * Name constructor.
     * 
     * @param entity
     * @param unitCode
     * @param unitSymbol
     * @param unitName
     */
    public Unit(Entity entity, String unitCode, String unitSymbol, String unitName) {
    	this(entity, unitCode);
    	setUnitSymbol(unitSymbol);
    	setUnitName(unitName);
    }

    /**
     * Primary key.
     */
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Optimistic lock version.
     */
    public int getVersion() {
		return version;
	}
    public void setVersion(int version) {
		this.version = version;
	}

    /**
     * Entity getter.
     */
    public Entity getEntity() {
        return this.entity;
    }
    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    /**
     * Unique unit code.
     */
    public String getUnitCode() {
        return this.unitCode;
    }
    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    /**
     * Symbol (mm, m, Ton, etc.).
     */
    public String getUnitSymbol() {
		return unitSymbol;
	}
    public void setUnitSymbol(String unitSymbol) {
		this.unitSymbol = unitSymbol;
	}

    /**
     * Unit name.
     */
    public String getUnitName() {
        return this.unitName;
    }
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

	/**
	 * Unit nature.
	 */
	public String getNature() {
		return nature;
	}
	public void setNature(String nature) {
		this.nature = nature;
	}
	
	/**
	 * <<Transient>> True if nature already exists.
	 * 
	 * @param nature
	 */
	@Transient
	public boolean hasNature(char nature) {
		return (getNature()!=null && getNature().indexOf(nature)>=0);
	}
	
	/**
	 * <<Transient>> Nature as array.
	 */
	@Transient
	public String[] getNatureAsArray() {
		return StringListUtils.stringToArray(getNature());
	}
	public void setNatureAsArray(String[] natureArray) {
		setNature(StringListUtils.arrayToString(natureArray));
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((entity == null) ? 0 : entity.hashCode());
		result = prime * result
				+ ((unitCode == null) ? 0 : unitCode.hashCode());
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
		if (!(obj instanceof Unit)) {
			return false;
		}
		Unit other = (Unit) obj;
		if (entity == null) {
			if (other.entity != null) {
				return false;
			}
		} else if (!entity.equals(other.entity)) {
			return false;
		}
		if (unitCode == null) {
			if (other.unitCode != null) {
				return false;
			}
		} else if (!unitCode.equals(other.unitCode)) {
			return false;
		}
		return true;
	}

}
