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

import org.helianto.core.dao.CommonOrmDao;
import org.helianto.partner.Laboratory;
import org.helianto.partner.PartnerRegistry;

/**
 * <code>Laboratory</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface LaboratoryDao extends CommonOrmDao {
     
    /**
     * Find <code>Laboratory</code> by <code>PartnerRegistry</code> and type.
     */
    public Laboratory findLaboratoryByNaturalId(PartnerRegistry partnerRegistry);
    
    
    
}
