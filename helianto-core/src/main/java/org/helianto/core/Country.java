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
import javax.persistence.UniqueConstraint;

/**
 * Countries.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="core_country",
    uniqueConstraints = {@UniqueConstraint(columnNames={"operatorId", "countryCode"})}
)
public class Country implements RootEntity {

    /**
     * Factory method.
     * 
     * @param requiredOperator
     */
    public static Country countryFactory(Operator requiredOperator) {
    	Country country = new Country();
        country.setOperator(requiredOperator);
        return country;
    }


    private static final long serialVersionUID = 1L;
    private int id;
    private Operator operator;
    private String countryCode;
    private String countryName;

    /** default constructor */
    public Country() {
        this("");
    }

    /** Code constructor */
    public Country(String countryCode) {
        setCountryCode(countryCode);
        setCountryName("");
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
    @ManyToOne
    @JoinColumn(name="operatorId", nullable=true)
    public Operator getOperator() {
        return this.operator;
    }
    public void setOperator(Operator operator) {
        this.operator = operator;
    }
    
    /**
     * Country code.
     */
    @Column(length=7)
    public String getCountryCode() {
        return this.countryCode;
    }
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    
    /**
     * Country name.
     */
    @Column(length=32)
    public String getCountryName() {
        return this.countryName;
    }
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("code").append("='").append(getCountryCode()).append("' ");			
      buffer.append("]");
      return buffer.toString();
     }

   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Country) ) return false;
		 Country castOther = ( Country ) other; 
		 return ( (this.getOperator()==castOther.getOperator()) || ( this.getOperator()!=null && castOther.getOperator()!=null && this.getOperator().equals(castOther.getOperator()) ) )
		 && ( (this.getCountryCode()==castOther.getCountryCode()) || ( this.getCountryCode()!=null && castOther.getCountryCode()!=null && this.getCountryCode().equals(castOther.getCountryCode()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getOperator() == null ? 0 : this.getOperator().hashCode() );
         result = 37 * result + ( getCountryCode() == null ? 0 : this.getCountryCode().hashCode() );
         return result;
   }



}
