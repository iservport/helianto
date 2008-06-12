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
import org.helianto.partner.Phone;
import org.helianto.partner.dao.PhoneDao;
import org.springframework.stereotype.Repository;

/**
 * Default implementation of <code>Phone</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Repository("phoneDao")
public class PhoneDaoImpl extends GenericDaoImpl implements PhoneDao {
     
    public void persistPhone(Phone phone) {
        if (logger.isDebugEnabled()) {
            logger.debug("Persisting "+phone);
        }
        persist(phone);
    }
    
    public Phone mergePhone(Phone phone) {
        if (logger.isDebugEnabled()) {
            logger.debug("Merging "+phone);
        }
        return (Phone) merge(phone);
    }
    
    public void removePhone(Phone phone) {
        if (logger.isDebugEnabled()) {
            logger.debug("Removing "+phone);
        }
        remove(phone);
    }
    
    public Phone findPhoneByNaturalId(Address address, int sequence) {
        if (logger.isDebugEnabled()) {
            logger.debug("Finding unique phone with address='"+address+"' and sequence='"+sequence+"' ");
        }
        return (Phone) findUnique(Phone.getPhoneNaturalIdQueryString(), address, sequence);
    }
    
    
	static String PHONE_ENTITY_QRY = "select phone from Phone phone "+
	    "where phone.entity = :entity ";



}
