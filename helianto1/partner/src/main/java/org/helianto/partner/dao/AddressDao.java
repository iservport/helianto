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

package org.helianto.partner.dao;

import org.helianto.core.dao.CommonOrmDao;
import org.helianto.partner.Address;
import org.helianto.partner.PartnerAssociation;

/**
 * <code>Address</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface AddressDao extends CommonOrmDao {
     
    /**
     * Persist <code>Address</code>.
     */
    public void persistAddress(Address address);
    
    /**
     * Merge <code>Address</code>.
     */
    public Address mergeAddress(Address address);
    
    /**
     * Remove <code>Address</code>.
     */
    public void removeAddress(Address address);
    
    /**
     * Find <code>Address</code> by <code>PartnerAssociation</code> and sequence.
     */
    public Address findAddressByNaturalId(PartnerAssociation partnerAssociation, int sequence);
    
    
    
}
