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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.helianto.core.domain.type.RootEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Represent key types like customer, supplier or government assigned numbers.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="core_keytype1",
    uniqueConstraints = {@UniqueConstraint(columnNames={"operatorId", "keyCode"})}
)
public class KeyType 
	implements RootEntity 
{

    private static final long serialVersionUID = 1L;
    
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="operatorId", nullable=true)
    private Operator operator;
    
    @Column(length=20)
    private String keyCode = "";
    
    @Column(length=48)
    private String keyName = "";
    
    @Column(length=255)
    private String purpose = "";

    /** 
     * Default constructor
     */
    public KeyType() {
    	super();
    }

    /** 
     * Key constructor
     * 
     * @param operator
     * @param keyCode
     */
    public KeyType(Operator operator, String keyCode) {
    	this();
    	setOperator(operator);
    	setKeyCode(keyCode);
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
     * <<NaturalKey>> Operator.
     */
    public Operator getOperator() {
        return this.operator;
    }
    public void setOperator(Operator operator) {
        this.operator = operator;
    }

//    @Transient
    public int getContextId() {
    	if (getOperator()!=null) {
    		return getOperator().getId();
    	}
    	return 0;
    }
    
    /**
     * <<NaturalKey>> Key code.
     */
    public String getKeyCode() {
        return this.keyCode;
    }
    public KeyType setKeyCode(String keyCode) {
        this.keyCode = keyCode;
        return this;
    }

    /**
     * Key name.
     */
    public String getKeyName() {
        return this.keyName;
    }
    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    /**
     * Purpose description.
     */
    public String getPurpose() {
        return this.purpose;
    }
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    /**
     * toString
     * @return String
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("operator").append("='").append(getOperator()).append("' ");
        buffer.append("keyCode").append("='").append(getKeyCode()).append("' ");
        buffer.append("]");
      
        return buffer.toString();
    }

   /**
    * equals
    */
   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
         if ( (other == null ) ) return false;
         if ( !(other instanceof KeyType) ) return false;
         KeyType castOther = (KeyType) other; 
         
         return ((this.getOperator()==castOther.getOperator()) || ( this.getOperator()!=null && castOther.getOperator()!=null && this.getOperator().equals(castOther.getOperator()) ))
             && ((this.getKeyCode()==castOther.getKeyCode()) || ( this.getKeyCode()!=null && castOther.getKeyCode()!=null && this.getKeyCode().equals(castOther.getKeyCode()) ));
   }
   
   /**
    * hashCode
    */
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getOperator() == null ? 0 : this.getOperator().hashCode() );
         result = 37 * result + ( getKeyCode() == null ? 0 : this.getKeyCode().hashCode() );
         return result;
   }   

}
