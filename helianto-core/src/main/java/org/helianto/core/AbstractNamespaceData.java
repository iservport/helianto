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
import javax.persistence.MappedSuperclass;

/**
 * Base class to namespace data.
 * 
 * @author Mauricio Fernandes de Castro
 */
@MappedSuperclass
public class AbstractNamespaceData implements RootEntity {

    /**
     * Factory method.
     * 
     * @param requiredOperator
     */
    public static AbstractNamespaceData dataFactory(Operator requiredOperator) {
    	AbstractNamespaceData data = new AbstractNamespaceData();
        data.setOperator(requiredOperator);
        return data;
    }


    private static final long serialVersionUID = 1L;
    private int id;
    private Operator operator;
    private String dataCode;
    private String dataName;

    /** default constructor */
    public AbstractNamespaceData() {
        this("");
    }

    /** Code constructor */
    public AbstractNamespaceData(String dataCode) {
        setDataCode(dataCode);
        setDataName("");
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
     * Data code.
     */
    @Column(length=12)
    public String getDataCode() {
        return this.dataCode;
    }
    public void setDataCode(String dataCode) {
        this.dataCode = dataCode;
    }
    
    /**
     * Data name.
     */
    @Column(length=256)
    public String getDataName() {
        return this.dataName;
    }
    public void setDataName(String countryName) {
        this.dataName = countryName;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("data").append("='").append(getDataCode()).append("' ");			
      buffer.append("]");
      return buffer.toString();
     }

   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof AbstractNamespaceData) ) return false;
		 AbstractNamespaceData castOther = ( AbstractNamespaceData ) other; 
		 return ( (this.getOperator()==castOther.getOperator()) || ( this.getOperator()!=null && castOther.getOperator()!=null && this.getOperator().equals(castOther.getOperator()) ) )
		 && ( (this.getDataCode()==castOther.getDataCode()) || ( this.getDataCode()!=null && castOther.getDataCode()!=null && this.getDataCode().equals(castOther.getDataCode()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getOperator() == null ? 0 : this.getOperator().hashCode() );
         result = 37 * result + ( getDataCode() == null ? 0 : this.getDataCode().hashCode() );
         return result;
   }



}
