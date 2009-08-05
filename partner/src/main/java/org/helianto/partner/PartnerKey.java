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

import javax.persistence.CascadeType;
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
 * <p>
 * Represents the relationship between the organization and other entities.  
 * </p>
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="prtnr_partnerKey",
    uniqueConstraints = {@UniqueConstraint(columnNames={"partnerId", "keyTypeId"})}
)
public class PartnerKey implements java.io.Serializable {

    /**
     * Factory method.
     * 
     * @param partnerRegistry
     * @param keyType
     */
    public static PartnerKey partnerKeyFactory(Partner partner, KeyType keyType) {
        PartnerKey partnerKey = new PartnerKey();
        partnerKey.setPartner(partner);
        partnerKey.setKeyType(keyType);
        return partnerKey;
    }

    private static final long serialVersionUID = 1L;
    private int id;
    private Partner partner;
    private KeyType keyType;
    private String keyValue;

    /** default constructor */
    public PartnerKey() {
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
     * Partner.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="partnerId", nullable=true)
    public Partner getPartner() {
        return this.partner;
    }
    public void setPartner(Partner partner) {
        this.partner = partner;
    }

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
        buffer.append("partner").append("='").append(getPartner()).append("' ");
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
         if ( !(other instanceof PartnerKey) ) return false;
         PartnerKey castOther = (PartnerKey) other; 
         
         return ((this.getPartner()==castOther.getPartner()) || 
        		 ( this.getPartner()!=null 
        		&& castOther.getPartner()!=null && this.getPartner().equals(castOther.getPartner()) ))
             && ((this.getKeyType()==castOther.getKeyType()) || ( this.getKeyType()!=null && castOther.getKeyType()!=null && this.getKeyType().equals(castOther.getKeyType()) ));
   }
   
   /**
    * hashCode
    */
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getPartner() == null ? 0 : this.getPartner().hashCode() );
         result = 37 * result + ( getKeyType() == null ? 0 : this.getKeyType().hashCode() );
         return result;
   }   

}
