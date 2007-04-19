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

package org.helianto.core.dao;

import org.helianto.core.Operator;
import org.helianto.core.Service;

/**
 * <code>Service</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface ServiceDao extends CommonOrmDao {
     
    /**
     * Persist <code>Service</code>.
     */
    public void persistService(Service service);
    
    /**
     * Merge <code>Service</code>.
     */
    public Service mergeService(Service service);
    
    /**
     * Remove <code>Service</code>.
     */
    public void removeService(Service service);
    
    /**
     * Find <code>Service</code> by <code>Operator</code> and serviceName.
     */
    public Service findServiceByNaturalId(Operator operator, String serviceName);
    
    
    
}
