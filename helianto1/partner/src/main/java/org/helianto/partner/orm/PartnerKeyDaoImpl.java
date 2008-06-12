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

import org.helianto.core.KeyType;
import org.helianto.core.hibernate.GenericDaoImpl;
import org.helianto.partner.PartnerRegistry;
import org.helianto.partner.PartnerKey;
import org.helianto.partner.dao.PartnerKeyDao;
import org.springframework.stereotype.Repository;

/**
 * Default implementation of <code>PartnerKey</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Repository("partnerKeyDao")
public class PartnerKeyDaoImpl extends GenericDaoImpl implements PartnerKeyDao {
     
    public void persistPartnerKey(PartnerKey partnerKey) {
        if (logger.isDebugEnabled()) {
            logger.debug("Persisting "+partnerKey);
        }
        persist(partnerKey);
    }
    
    public PartnerKey mergePartnerKey(PartnerKey partnerKey) {
        if (logger.isDebugEnabled()) {
            logger.debug("Merging "+partnerKey);
        }
        return (PartnerKey) merge(partnerKey);
    }
    
    public void removePartnerKey(PartnerKey partnerKey) {
        if (logger.isDebugEnabled()) {
            logger.debug("Removing "+partnerKey);
        }
        remove(partnerKey);
    }
    
    public PartnerKey findPartnerKeyByNaturalId(PartnerRegistry partnerRegistry, KeyType keyType) {
        if (logger.isDebugEnabled()) {
            logger.debug("Finding unique partnerKey with partnerRegistry='"+partnerRegistry+"' and keyType='"+keyType+"' ");
        }
        return (PartnerKey) findUnique(PartnerKey.getPartnerKeyNaturalIdQueryString(), partnerRegistry, keyType);
    }
    
    
	static String PARTNERKEY_ENTITY_QRY = "select partnerKey from PartnerKey partnerKey "+
	    "where partnerKey.entity = ? ";


}
