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
import org.helianto.partner.PartnerRegistry;
import org.helianto.partner.Supplier;
import org.helianto.partner.dao.SupplierDao;
import org.springframework.stereotype.Repository;

/**
 * Default implementation of <code>Supplier</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Repository("supplierDao")
public class SupplierDaoImpl extends GenericDaoImpl implements SupplierDao {
     
    public Supplier findSupplierByNaturalId(PartnerRegistry partnerRegistry, int sequence) {
        if (logger.isDebugEnabled()) {
            logger.debug("Finding unique supplier with partnerRegistry='"+partnerRegistry+"' and sequence='"+sequence+"' ");
        }
        return (Supplier) findUnique(Supplier.getSupplierNaturalIdQueryString(), partnerRegistry, sequence);
    }
    
    
	static String SUPPLIER_ENTITY_QRY = "select supplier from Supplier supplier "+
	    "where supplier.entity = ? ";
    
}
