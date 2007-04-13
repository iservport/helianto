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

package org.helianto.partner.orm;

import org.helianto.core.hibernate.GenericDaoImpl;
import org.helianto.partner.Address;
import org.helianto.partner.PartnerAssociation;
import org.helianto.partner.dao.AddressDao;

/**
 * Default implementation of <code>Address</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class AddressDaoImpl extends GenericDaoImpl implements AddressDao {
     
    public void persistAddress(Address address) {
        if (logger.isDebugEnabled()) {
            logger.debug("Persisting "+address);
        }
        persist(address);
    }
    
    public Address mergeAddress(Address address) {
        if (logger.isDebugEnabled()) {
            logger.debug("Merging "+address);
        }
        return (Address) merge(address);
    }
    
    public void removeAddress(Address address) {
        if (logger.isDebugEnabled()) {
            logger.debug("Removing "+address);
        }
        remove(address);
    }
    
    public Address findAddressByNaturalId(PartnerAssociation partnerAssociation, int sequence) {
        if (logger.isDebugEnabled()) {
            logger.debug("Finding unique address with partnerAssociation='"+partnerAssociation+"' and sequence='"+sequence+"' ");
        }
        return (Address) findUnique(Address.getAddressNaturalIdQueryString(), partnerAssociation, sequence);
    }
    
    
	static String ADDRESS_ENTITY_QRY = "select address from Address address "+
	    "where address.entity = :entity ";


}