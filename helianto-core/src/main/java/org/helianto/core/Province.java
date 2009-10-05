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
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Provinces.
 * 
 * <p>
 * Province may be extended to represent also a city.
 * </p>
 * 
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
public class Province  implements java.io.Serializable {

    /**
     * Factory method.
     * 
     * @param requiredOperator
     */
    public static Province provinceFactory(Operator requiredOperator) {
        Province province = new Province();
        province.setOperator(requiredOperator);
        return province;
    }


    private static final long serialVersionUID = 1L;
    private int id;
    private Operator operator;
    private String provinceCode;
    private String provinceName;
    private Country country;

	/** default constructor */
    public Province() {
        this("");
    }

    /** Code constructor */
    public Province(String provinceCode) {
        setProvinceCode(provinceCode);
        setProvinceName("");
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
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="operatorId", nullable=true)
    public Operator getOperator() {
        return this.operator;
    }
    public void setOperator(Operator operator) {
        this.operator = operator;
    }
    
    /**
     * Province code.
     */
    @Column(length=7)
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
    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="countryId")
    public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("code").append("='").append(getProvinceCode()).append("' ");			
      buffer.append("]");
      return buffer.toString();
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


