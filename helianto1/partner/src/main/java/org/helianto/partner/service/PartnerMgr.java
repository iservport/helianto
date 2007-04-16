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

package org.helianto.partner.service;

import java.util.List;

import org.helianto.core.Operator;
import org.helianto.core.Province;
import org.helianto.partner.PartnerAssociation;

/**
 * Default service layer interface for the partner package.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface PartnerMgr {

    /**
     * Find a <code>Province</code> list by <code>Operator</code>.
     */
    public List<Province> findProvinceByOperator(Operator operator);

    /**
     * Write <code>PartnerAssociation</code> to the datastore.
     */
    public void writePartnerAssociation(PartnerAssociation partnerAssociation);

    /**
     * Remove <code>PartnerAssociation</code> from the datastore.
     */
    public void removePartnerAssociation(PartnerAssociation partnerAssociation);

    /**
     * Find a <code>PartnerAssociation</code> list using text criteria.
     */
    public List<PartnerAssociation> findPartnerAssociation(String partnerAssociationSearchString);

}
