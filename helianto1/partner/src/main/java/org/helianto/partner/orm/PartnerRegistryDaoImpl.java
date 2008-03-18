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

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.hibernate.GenericDaoImpl;
import org.helianto.partner.PartnerRegistry;
import org.helianto.partner.dao.PartnerRegistryDao;

/**
 * Default implementation of <code>PartnerRegistry</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class PartnerRegistryDaoImpl extends GenericDaoImpl implements PartnerRegistryDao {
     
    public void persistPartnerRegistry(PartnerRegistry partnerRegistry) {
        if (logger.isDebugEnabled()) {
            logger.debug("Persisting "+partnerRegistry);
        }
        persist(partnerRegistry);
    }
    
    public PartnerRegistry mergePartnerRegistry(PartnerRegistry partnerRegistry) {
        if (logger.isDebugEnabled()) {
            logger.debug("Merging "+partnerRegistry);
        }
        return (PartnerRegistry) merge(partnerRegistry);
    }
    
    public void removePartnerRegistry(PartnerRegistry partnerRegistry) {
        if (logger.isDebugEnabled()) {
            logger.debug("Removing "+partnerRegistry);
        }
        remove(partnerRegistry);
    }
    
    public PartnerRegistry findPartnerRegistryByNaturalId(Entity entity, String partnerAlias) {
        if (logger.isDebugEnabled()) {
            logger.debug("Finding unique partnerRegistry with entity='"+entity+"' and partnerAlias='"+partnerAlias+"' ");
        }
        return (PartnerRegistry) findUnique(PartnerRegistry.getPartnerRegistryNaturalIdQueryString(), entity, partnerAlias);
    }
    
	@SuppressWarnings("unchecked")
	public List<PartnerRegistry> findPartnerRegistries(String criteria) {
        if (criteria!=null && !criteria.equals("")) {
            if (logger.isDebugEnabled()) {
                logger.debug("Finding partner list with criteria='"+criteria+"' ");
            }
            return (ArrayList<PartnerRegistry>) find(PartnerRegistry.getPartnerRegistryQueryStringBuilder().append("where ").append(criteria));
        }
        else {
            if (logger.isDebugEnabled()) {
                logger.debug("Finding full entity list");
            }
            return (ArrayList<PartnerRegistry>) find(PartnerRegistry.getPartnerRegistryQueryStringBuilder());
        }
	}
        
	static String PARTNERASSOCIATION_ENTITY_QRY = "select partnerRegistry from PartnerRegistry partnerRegistry "+
	    "where partnerRegistry.entity = ? ";


}
