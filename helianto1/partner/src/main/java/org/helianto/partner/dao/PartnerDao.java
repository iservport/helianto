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

import java.util.List;

import org.helianto.core.dao.CommonOrmDao;
import org.helianto.partner.Partner;
import org.helianto.partner.PartnerRegistry;
import org.helianto.partner.SimplePartnerRegistry;

/**
 * <code>Partner</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface PartnerDao extends CommonOrmDao {
     
    /**
     * Persist <code>Partner</code>.
     */
    public void persistPartner(Partner partner);
    
    /**
     * Merge <code>Partner</code>.
     */
    public Partner mergePartner(Partner partner);
    
    /**
     * Remove <code>Partner</code>.
     */
    public void removePartner(Partner partner);
    
    /**
     * Find <code>Partner</code> by <code>PartnerRegistry</code>.
     */
    public Partner findPartnerByNaturalId(PartnerRegistry partnerRegistry);
    
    /**
     * Find <code>Partner</code> by string criteria.
     */
    public List<Partner> findPartners(String criteria);
    
    /**
     * Find <code>SimplePartnerRegistry</code> by string criteria.
     */
    public List<SimplePartnerRegistry> findSimplePartnerRegistries(String criteria);
    
    
    
}
