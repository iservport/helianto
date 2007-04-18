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
import org.helianto.partner.Partner;
import org.helianto.partner.PartnerRegistry;
import org.helianto.partner.dao.PartnerDao;

/**
 * Default implementation of <code>Partner</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class PartnerDaoImpl extends GenericDaoImpl implements PartnerDao {
     
    public void persistPartner(Partner partner) {
        if (logger.isDebugEnabled()) {
            logger.debug("Persisting "+partner);
        }
        persist(partner);
    }
    
    public Partner mergePartner(Partner partner) {
        if (logger.isDebugEnabled()) {
            logger.debug("Merging "+partner);
        }
        return (Partner) merge(partner);
    }
    
    public void removePartner(Partner partner) {
        if (logger.isDebugEnabled()) {
            logger.debug("Removing "+partner);
        }
        remove(partner);
    }
    
    public Partner findPartnerByNaturalId(PartnerRegistry partnerRegistry, int sequence) {
        if (logger.isDebugEnabled()) {
            logger.debug("Finding unique partner with partnerRegistry='"+partnerRegistry+"' and sequence='"+sequence+"' ");
        }
        return (Partner) findUnique(Partner.getPartnerNaturalIdQueryString(), partnerRegistry, sequence);
    }
    
    
	static String PARTNER_ENTITY_QRY = "select partner from Partner partner "+
	    "where partner.entity = ? ";


}
