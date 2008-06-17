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

import org.helianto.core.hibernate.GenericDaoImpl;
import org.helianto.partner.Partner;
import org.helianto.partner.PartnerRegistry;
import org.helianto.partner.SimplePartnerRegistry;
import org.helianto.partner.dao.PartnerDao;
import org.springframework.stereotype.Repository;

/**
 * Default implementation of <code>Partner</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Repository("partnerDao")
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
    
    public Partner findPartnerByNaturalId(PartnerRegistry partnerRegistry) {
        if (logger.isDebugEnabled()) {
            logger.debug("Finding unique partner with partnerRegistry='"+partnerRegistry+"' ");
        }
        return (Partner) findUnique(Partner.getPartnerNaturalIdQueryString(), partnerRegistry);
    }
    
    
	@SuppressWarnings("unchecked")
	public List<Partner> findPartners(String criteria) {
        if (criteria!=null && !criteria.equals("")) {
            if (logger.isDebugEnabled()) {
                logger.debug("Finding partner list with criteria='"+criteria+"' ");
            }
            return (ArrayList<Partner>) find(Partner.getPartnerQueryStringBuilder().append("where ").append(criteria));
        }
        else {
            if (logger.isDebugEnabled()) {
                logger.debug("Finding full partner list");
            }
            return (ArrayList<Partner>) find(Partner.getPartnerQueryStringBuilder());
        }
	}
    
	@SuppressWarnings("unchecked")
	public List<SimplePartnerRegistry> findSimplePartnerRegistries(String criteria) {
        if (criteria!=null && !criteria.equals("")) {
            if (logger.isDebugEnabled()) {
                logger.debug("Finding simple partner registry list with criteria='"+criteria+"' ");
            }
            return (ArrayList<SimplePartnerRegistry>) find(SimplePartnerRegistry.getSimplePartnerRegistryQueryStringBuilder().append("where ").append(criteria));
        }
        else {
            if (logger.isDebugEnabled()) {
                logger.debug("Finding full simple partner registry list");
            }
            return (ArrayList<SimplePartnerRegistry>) find(SimplePartnerRegistry.getSimplePartnerRegistryQueryStringBuilder());
        }
	}
    
}
