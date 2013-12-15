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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.FetchType;
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

import org.helianto.core.domain.type.RootEntity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Provinces.
 * 
 * <p>
 * Province may be extended to represent also a city.
 * </p>
 * 
 * @deprecated
 * @see State
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="core_province",
    uniqueConstraints = {@UniqueConstraint(columnNames={"operatorId", "provinceCode"})}
)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="type",
    discriminatorType=DiscriminatorType.CHAR
)
@DiscriminatorValue("P")
public class Province  implements RootEntity, Comparable<Province> {

    private static final long serialVersionUID = 1L;
    private int id;
    private Operator operator;
    private Province parent;
    private String provinceCode = "";
    private String provinceName = "";
    private Country country;
    private char priority;

	/**
	 * Empty constructor.
	 */
    public Province() {
        super();
    }

    /**
     * Key constructor.
     * 
     * @param operator
     * @param provinceCode
     */
    public Province(Operator operator, String provinceCode) {
        this();
        this.operator = operator;
        setProvinceCode(provinceCode);
    }

    /**
     * Name constructor.
     * 
     * @param operator
     * @param provinceCode
     * @param provinceName
     */
    public Province(Operator operator, String provinceCode, String provinceName) {
    	this(operator, provinceCode);
    	setProvinceName(provinceName);
    }

    /**
     * Parent constructor.
     * 
     * @param provinceCode
     * @param provinceName
     * @param parent
     */
    public Province(String provinceCode, String provinceName, Province parent) {
    	this(parent.getOperator(), provinceCode, provinceName);
    	setParent(parent);
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
     * Namespace operator.
     */
    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="operatorId", nullable=true)
    public Operator getOperator() {
        return this.operator;
    }
    public void setOperator(Operator operator) {
        this.operator = operator;
    }
    
    @Transient
    public int getContextId() {
    	if (getOperator()!=null) {
    		return getOperator().getId();
    	}
    	return 0;
    }
    
	/**
	 * Parent province.
	 */
	@JsonBackReference 
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="parentId", nullable=true)
	public Province getParent() {
		return parent;
	}
	public void setParent(Province parent) {
		this.parent = parent;
	}
	
    /**
     * Province code.
     */
    @Column(length=12)
    public String getProvinceCode() {
        return this.provinceCode;
    }
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }
    
    /**
     * Province name.
     */
    @Column(length=32)
    public String getProvinceName() {
        return this.provinceName;
    }
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    /**
     * Country.
     */
    @JsonBackReference 
    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="countryId")
    public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}

	/**
	 * Province priority.
	 */
	public char getPriority() {
		return priority;
	}
	public void setPriority(char priority) {
		this.priority = priority;
	}

	public int compareTo(Province next) {
		if (getPriority()==next.getPriority()) {
			if (getProvinceCode()!=null && next.getProvinceCode()!=null) {
				return getProvinceCode().compareTo(next.getProvinceCode());
			}
			return 0;
		}
		return getPriority()-next.getPriority();
	}
	
    /**
     * toString
     * @return String
     */
     public String toString() {
      return getProvinceCode();
     }

   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Province) ) return false;
		 Province castOther = ( Province ) other; 
		 return ( (this.getOperator()==castOther.getOperator()) || ( this.getOperator()!=null && castOther.getOperator()!=null && this.getOperator().equals(castOther.getOperator()) ) )
		 && ( (this.getProvinceCode()==castOther.getProvinceCode()) || ( this.getProvinceCode()!=null && castOther.getProvinceCode()!=null && this.getProvinceCode().equals(castOther.getProvinceCode()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getOperator() == null ? 0 : this.getOperator().hashCode() );
         result = 37 * result + ( getProvinceCode() == null ? 0 : this.getProvinceCode().hashCode() );
         return result;
   }

}


