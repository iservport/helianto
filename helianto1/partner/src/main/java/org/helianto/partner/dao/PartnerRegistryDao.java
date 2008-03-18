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

import org.helianto.core.Entity;
import org.helianto.core.dao.CommonOrmDao;
import org.helianto.partner.PartnerRegistry;

/**
 * <code>PartnerRegistry</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface PartnerRegistryDao extends CommonOrmDao {
     
    /**
     * Persist <code>PartnerRegistry</code>.
     */
    public void persistPartnerRegistry(PartnerRegistry partnerRegistry);
    
    /**
     * Merge <code>PartnerRegistry</code>.
     */
    public PartnerRegistry mergePartnerRegistry(PartnerRegistry partnerRegistry);
    
    /**
     * Remove <code>PartnerRegistry</code>.
     */
    public void removePartnerRegistry(PartnerRegistry partnerRegistry);
    
    /**
     * Find <code>PartnerRegistry</code> by <code>Entity</code> and partnerAlias.
     */
    public PartnerRegistry findPartnerRegistryByNaturalId(Entity entity, String partnerAlias);
    
    /**
     * Find <code>PartnerRegistry</code> by string criteria.
     */
    public List<PartnerRegistry> findPartnerRegistries(String criteria);
    
    
    
}
