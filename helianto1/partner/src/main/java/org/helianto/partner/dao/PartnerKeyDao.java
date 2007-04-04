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

import org.helianto.core.KeyType;
import org.helianto.core.dao.CommonOrmDao;
import org.helianto.partner.PartnerAssociation;
import org.helianto.partner.PartnerKey;

/**
 * <code>PartnerKey</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface PartnerKeyDao extends CommonOrmDao {
     
    /**
     * Persist <code>PartnerKey</code>.
     */
    public void persistPartnerKey(PartnerKey partnerKey);
    
    /**
     * Merge <code>PartnerKey</code>.
     */
    public PartnerKey mergePartnerKey(PartnerKey partnerKey);
    
    /**
     * Remove <code>PartnerKey</code>.
     */
    public void removePartnerKey(PartnerKey partnerKey);
    
    /**
     * Find <code>PartnerKey</code> by <code>PartnerAssociation</code> and <code>KeyType</code>.
     */
    public PartnerKey findPartnerKeyByNaturalId(PartnerAssociation partnerAssociation, KeyType keyType);
    
    
    
}
