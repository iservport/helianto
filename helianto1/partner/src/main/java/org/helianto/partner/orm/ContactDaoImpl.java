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
import org.helianto.partner.Contact;
import org.helianto.partner.PartnerRegistry;
import org.helianto.partner.dao.ContactDao;

/**
 * Default implementation of <code>Contact</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ContactDaoImpl extends GenericDaoImpl implements ContactDao {
     
    public void persistContact(Contact contact) {
        if (logger.isDebugEnabled()) {
            logger.debug("Persisting "+contact);
        }
        persist(contact);
    }
    
    public Contact mergeContact(Contact contact) {
        if (logger.isDebugEnabled()) {
            logger.debug("Merging "+contact);
        }
        return (Contact) merge(contact);
    }
    
    public void removeContact(Contact contact) {
        if (logger.isDebugEnabled()) {
            logger.debug("Removing "+contact);
        }
        remove(contact);
    }
    
    public Contact findContactByNaturalId(PartnerRegistry partnerRegistry, int sequence) {
        if (logger.isDebugEnabled()) {
            logger.debug("Finding unique contact with partnerRegistry='"+partnerRegistry+"' and sequence='"+sequence+"' ");
        }
        return (Contact) findUnique(Contact.getAddressNaturalIdQueryString(), partnerRegistry, sequence);
    }
    
    
	static String CONTACT_ENTITY_QRY = "select contact from Contact contact "+
	    "where contact.entity = :entity ";


}
