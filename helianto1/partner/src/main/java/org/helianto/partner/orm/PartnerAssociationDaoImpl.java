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

import org.helianto.core.Entity;
import org.helianto.core.hibernate.GenericDaoImpl;
import org.helianto.partner.PartnerAssociation;
import org.helianto.partner.dao.PartnerAssociationDao;

/**
 * Default implementation of <code>PartnerAssociation</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class PartnerAssociationDaoImpl extends GenericDaoImpl implements PartnerAssociationDao {
     
    public void persistPartnerAssociation(PartnerAssociation partnerAssociation) {
        if (logger.isDebugEnabled()) {
            logger.debug("Persisting "+partnerAssociation);
        }
        persist(partnerAssociation);
    }
    
    public PartnerAssociation mergePartnerAssociation(PartnerAssociation partnerAssociation) {
        if (logger.isDebugEnabled()) {
            logger.debug("Merging "+partnerAssociation);
        }
        return (PartnerAssociation) merge(partnerAssociation);
    }
    
    public void removePartnerAssociation(PartnerAssociation partnerAssociation) {
        if (logger.isDebugEnabled()) {
            logger.debug("Removing "+partnerAssociation);
        }
        remove(partnerAssociation);
    }
    
    public PartnerAssociation findPartnerAssociationByNaturalId(Entity entity, String partnerAlias) {
        if (logger.isDebugEnabled()) {
            logger.debug("Finding unique partnerAssociation with entity='"+entity+"' and partnerAlias='"+partnerAlias+"' ");
        }
        return (PartnerAssociation) findUnique(PartnerAssociation.getPartnerAssociationNaturalIdQueryString(), entity, partnerAlias);
    }
    
    
	static String PARTNERASSOCIATION_ENTITY_QRY = "select partnerAssociation from PartnerAssociation partnerAssociation "+
	    "where partnerAssociation.entity = :entity ";


}
