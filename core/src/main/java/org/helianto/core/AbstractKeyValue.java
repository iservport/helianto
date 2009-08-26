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
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
/**
 * The content of a key to be associated.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@MappedSuperclass
public abstract class AbstractKeyValue implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private KeyType keyType;
    private String keyValue;

    /** 
     * Default constructor
     */
    public AbstractKeyValue() {
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
     * Hook to the actual key owner.
     * 
     * <p>
     * For example, key owner may be a partner or a document.
     * </p>
     */
    @Transient
    protected abstract Object getKeyOwner();

    /**
     * Key type.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="keyTypeId", nullable=true)
    public KeyType getKeyType() {
        return this.keyType;
    }
    public void setKeyType(KeyType keyType) {
        this.keyType = keyType;
    }

    /**
     * Key value.
     */
    @Column(length=20)
    public String getKeyValue() {
        return this.keyValue;
    }
    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    /**
     * toString
     * @return String
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("owner").append("='").append(getKeyOwner()).append("' ");
        buffer.append("keyType").append("='").append(getKeyType()).append("' ");
        buffer.append("]");
      
        return buffer.toString();
    }

   /**
    * equals
    */
   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
         if ( (other == null ) ) return false;
         if ( !(other instanceof AbstractKeyValue) ) return false;
         AbstractKeyValue castOther = (AbstractKeyValue) other; 
         
         return ((this.getKeyOwner()==castOther.getKeyOwner()) || 
        		 ( this.getKeyOwner()!=null 
        		&& castOther.getKeyOwner()!=null && this.getKeyOwner().equals(castOther.getKeyOwner()) ))
             && ((this.getKeyType()==castOther.getKeyType()) || ( this.getKeyType()!=null && castOther.getKeyType()!=null && this.getKeyType().equals(castOther.getKeyType()) ));
   }
   
   /**
    * hashCode
    */
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getKeyOwner() == null ? 0 : this.getKeyOwner().hashCode() );
         result = 37 * result + ( getKeyType() == null ? 0 : this.getKeyType().hashCode() );
         return result;
   }   

}
