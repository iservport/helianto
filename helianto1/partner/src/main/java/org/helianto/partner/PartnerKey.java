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
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.helianto.core.KeyType;
/**
 * <p>
 * Represents the relationship between the organization and other entities.  
 * </p>
 * @author Vlademir Teixeira
 */
@javax.persistence.Entity
@Table(name="prtnr_partnerKey",
    uniqueConstraints = {@UniqueConstraint(columnNames={"partnerAssociationId", "keyTypeId"})}
)
public class PartnerKey implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private PartnerAssociation partnerAssociation;
    private KeyType keyType;
    private String keyValue;

    /** default constructor */
    public PartnerKey() {
    }

    // Property accessors
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    /**
     * PartnerAssociation getter.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="partnerAssociationId", nullable=true)
    public PartnerAssociation getPartnerAssociation() {
        return this.partnerAssociation;
    }
    /**
     * PartnerAssociation setter.
     */
    public void setPartnerAssociation(PartnerAssociation partnerAssociation) {
        this.partnerAssociation = partnerAssociation;
    }

    /**
     * KeyType getter.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="keyTypeId", nullable=true)
    public KeyType getKeyType() {
        return this.keyType;
    }
    /**
     * KeyType setter.
     */
    public void setKeyType(KeyType keyType) {
        this.keyType = keyType;
    }

    /**
     * KeyValue getter.
     */
    @Column(length=20)
    public String getKeyValue() {
        return this.keyValue;
    }
    /**
     * KeyValue setter.
     */
    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    /**
     * <code>PartnerKey</code> factory.
     * 
     * @param partnerAssociation
     * @param keyType
     */
    public static PartnerKey partnerKeyFactory(PartnerAssociation partnerAssociation, KeyType keyType) {
        PartnerKey partnerKey = new PartnerKey();
        partnerKey.setPartnerAssociation(partnerAssociation);
        partnerKey.setKeyType(keyType);
        return partnerKey;
    }

    /**
     * <code>PartnerKey</code> natural id query.
     */
    @Transient
    public static String getPartnerKeyNaturalIdQueryString() {
        return "select partnerKey from PartnerKey partnerKey where partnerKey.partnerAssociation = ? and partnerKey.keyType = ? ";
    }

    /**
     * toString
     * @return String
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("partnerAssociation").append("='").append(getPartnerAssociation()).append("' ");
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
         
         return ((this.getPartnerAssociation()==castOther.getPartnerAssociation()) || ( this.getPartnerAssociation()!=null && castOther.getPartnerAssociation()!=null && this.getPartnerAssociation().equals(castOther.getPartnerAssociation()) ))
             && ((this.getKeyType()==castOther.getKeyType()) || ( this.getKeyType()!=null && castOther.getKeyType()!=null && this.getKeyType().equals(castOther.getKeyType()) ));
   }
   
   /**
    * hashCode
    */
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getPartnerAssociation() == null ? 0 : this.getPartnerAssociation().hashCode() );
         result = 37 * result + ( getKeyType() == null ? 0 : this.getKeyType().hashCode() );
         return result;
   }   

}
