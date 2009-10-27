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
import javax.persistence.UniqueConstraint;
/**
 * <p>
 * Represent key types like customer, supplier or government assigned numbers.
 * </p>
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="core_keytype1",
    uniqueConstraints = {@UniqueConstraint(columnNames={"operatorId", "keyCode"})}
)
public class KeyType implements java.io.Serializable {

    /**
     * <code>KeyType</code> factory.
     * 
     * @param operator
     * @param keyCode
     */
    public static KeyType keyTypeFactory(Operator operator, String keyCode) {
        KeyType keyType = new KeyType();
        keyType.setOperator(operator);
        keyType.setKeyCode(keyCode);
        return keyType;
    }

    private static final long serialVersionUID = 1L;
    private int id;
    private Operator operator;
    private String keyCode;
    private char keyMetaType;
    private String keyName;
    private String purpose;

    /** default constructor */
    public KeyType() {
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
     * <<NaturalKey>> Operator.
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
     * <<NaturalKey>> Key code.
     */
    @Column(length=20)
    public String getKeyCode() {
        return this.keyCode;
    }
    public KeyType setKeyCode(String keyCode) {
        this.keyCode = keyCode;
        return this;
    }

    /**
	 * The type discriminator for this keyType.
	 */
	public char getKeyMetaType() {
		return keyMetaType;
	}
	public void setKeyMetaType(char keyMetaType) {
		this.keyMetaType = keyMetaType;
	}
	public void setKeyMetaType(KeyMetaType keyMetaType) {
		this.keyMetaType = keyMetaType.getValue();
	}
	
    /**
     * Key name.
     */
    @Column(length=48)
    public String getKeyName() {
        return this.keyName;
    }
    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    /**
     * Purpose description.
     */
    @Column(length=255)
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