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

package org.helianto.core.orm;

import org.helianto.core.Operator;
import org.helianto.core.Service;
import org.helianto.core.dao.ServiceDao;
import org.helianto.core.hibernate.GenericDaoImpl;
import org.springframework.stereotype.Repository;
/**
 * Default implementation of <code>Service</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Repository("serviceDao")
public class ServiceDaoImpl extends GenericDaoImpl implements ServiceDao {
     
    public void persistService(Service service) {
        if (logger.isDebugEnabled()) {
            logger.debug("Persisting "+service);
        }
        persist(service);
    }
    
    public Service mergeService(Service service) {
        if (logger.isDebugEnabled()) {
            logger.debug("Merging "+service);
        }
        return (Service) merge(service);
    }
    
    public void removeService(Service service) {
        if (logger.isDebugEnabled()) {
            logger.debug("Removing "+service);
        }
        remove(service);
    }
    
    public Service findServiceByNaturalId(Operator operator, String serviceName) {
        if (logger.isDebugEnabled()) {
            logger.debug("Finding unique service with operator='"+operator+"' and serviceName='"+serviceName+"' ");
        }
        return (Service) findUnique(Service.getServiceNaturalIdQueryString(), operator, serviceName);
    }
    
    
	static String SERVICE_ENTITY_QRY = "select service from Service service "+
	    "where service.entity = ? ";
    
}
