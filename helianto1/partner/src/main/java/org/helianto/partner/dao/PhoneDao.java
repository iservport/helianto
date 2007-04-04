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
import org.helianto.partner.Phone;

/**
 * <code>Phone</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface PhoneDao extends CommonOrmDao {
     
    /**
     * Persist <code>Phone</code>.
     */
    public void persistPhone(Phone phone);
    
    /**
     * Merge <code>Phone</code>.
     */
    public Phone mergePhone(Phone phone);
    
    /**
     * Remove <code>Phone</code>.
     */
    public void removePhone(Phone phone);
    
    /**
     * Find <code>Phone</code> by <code>Address</code> and sequence.
     */
    public Phone findPhoneByNaturalId(Address address, int sequence);
    
    
    
}
