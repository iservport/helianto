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

package org.helianto.partner;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.helianto.core.KeyType;
/**
 * Represents the relationship between the organization and other entities.  
 * 
 * <p>
 * </p>
 */
@javax.persistence.Entity
@Table(name="prtnr_partnerRegistryKey",
    uniqueConstraints = {@UniqueConstraint(columnNames={"partnerRegistryId", "keyTypeId"})}
)
public class PartnerRegistryKey implements java.io.Serializable {

    /**
     * Factory method.
     * 
     * @param partnerRegistry
     * @param keyType
     */
    public static PartnerRegistryKey partnerRegistryKeyFactory(PartnerRegistry partnerRegistry, KeyType keyType) {
        PartnerRegistryKey partnerKey = new PartnerRegistryKey();
        partnerKey.setPartnerRegistry(partnerRegistry);
        partnerKey.setKeyType(keyType);
        return partnerKey;
    }

    private static final long serialVersionUID = 1L;
    private int id;
    private PartnerRegistry partnerRegistry;
    private KeyType keyType;
    private String keyValue;

    /** default constructor */
    public PartnerRegistryKey() {
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
     * Partner registry.
     */
    @ManyToOne
    @JoinColumn(name="partnerRegistryId", nullable=true)
    public PartnerRegistry getPartnerRegistry() {
        return this.partnerRegistry;
    }
    public void setPartnerRegistry(PartnerRegistry partnerRegistry) {
        this.partnerRegistry = partnerRegistry;
    }

    /**
     * Key type.
     */
    @ManyToOne
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
        buffer.append("partnerRegistry").append("='").append(getPartnerRegistry()).append("' ");
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
         if ( !(other instanceof PartnerRegistryKey) ) return false;
         PartnerRegistryKey castOther = (PartnerRegistryKey) other; 
         
         return ((this.getPartnerRegistry()==castOther.getPartnerRegistry()) || ( this.getPartnerRegistry()!=null && castOther.getPartnerRegistry()!=null && this.getPartnerRegistry().equals(castOther.getPartnerRegistry()) ))
             && ((this.getKeyType()==castOther.getKeyType()) || ( this.getKeyType()!=null && castOther.getKeyType()!=null && this.getKeyType().equals(castOther.getKeyType()) ));
   }
   
   /**
    * hashCode
    */
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getPartnerRegistry() == null ? 0 : this.getPartnerRegistry().hashCode() );
         result = 37 * result + ( getKeyType() == null ? 0 : this.getKeyType().hashCode() );
         return result;
   }   

}
