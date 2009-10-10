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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.helianto.core.AbstractKeyStringValue;
import org.helianto.core.KeyType;
/**
 * The content of a key associated to the partner.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="prtnr_partnerKey",
    uniqueConstraints = {@UniqueConstraint(columnNames={"partnerId", "keyTypeId"})}
)
public class PartnerKey extends AbstractKeyStringValue {

	/**
	 * <<Transient>> Delegate to the actual key owner.
	 */
	@Transient
	@Override
	protected Object getKeyOwner() {
		return getPartner();
	}   

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
    private Partner partner;

    /** default constructor */
    public PartnerKey() {
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
    * equals
    */
   public boolean equals(Object other) {
         if (other instanceof PartnerKey) {
        	 return super.equals(other);
         }
         return false;
   }
   
}
