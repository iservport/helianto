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

import java.util.List;

import org.helianto.core.KeyType;
import org.helianto.core.Operator;
import org.helianto.core.Province;
import org.helianto.core.Server;
import org.helianto.core.Service;
import org.helianto.core.type.ServerType;

/**
 * <code>Operator</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface OperatorDao {

    /**
     * Persist <code>Operator</code>.
     */
    public void persistOperator(Operator operator);
    
    /**
     * Remove <code>Operator</code>.
     */
    public void removeOperator(Operator operator);
    
    /**
     * Find any registered <code>Operator</code>.
     */
    public List<Operator> findOperatorAll();
    
    /**
     * Find <code>Operator</code> by  operatorName.
     */
    public Operator findOperatorByNaturalId(String operatorName);
    
    /**
     * Persist <code>KeyType</code>.
     */
    public void persistKeyType(KeyType keyType);
    
    /**
     * Remove <code>KeyType</code>.
     */
    public void removeKeyType(KeyType keyType);
    
    /**
     * Find <code>KeyType</code> by operator and keyCode.
     */
    public KeyType findKeyTypeByNaturalId(Operator operator, String keyCode);
    
    /**
     * Persist <code>Server</code>.
     */
    public void persistServer(Server server);
    
    /**
     * Remove <code>Server</code>.
     */
    public void removeServer(Server server);
    
    /**
     * Find <code>Server</code> by  operator and serverName.
     */
    public Server findServerByNaturalId(Operator operator, String serverName);
    
    /**
     * Find active <code>Server</code>s by operator and type, ordered by priority.
     * 
     * @param operator
     * @param serverType
     * 
     * @see ServerType
     * @see ActivityState
     */
    public List<Server> findServerActiveByType(Operator operator, ServerType serverType);
    
    /**
     * Persist <code>Province</code>.
     */
    public void persistProvince(Province province);
    
    /**
     * Remove <code>Province</code>.
     */
    public void removeProvince(Province province);
    
    /**
     * Find <code>Province</code> by  operator and code.
     */
    public Province findProvinceByNaturalId(Operator operator, String code);
    
    /**
     * Persist <code>Service</code>.
     */
    public void persistService(Service service);
    
    /**
     * Remove <code>Service</code>.
     */
    public void removeService(Service service);
    
    /**
     * Find <code>Service</code> by  operator and serviceName.
     */
    public Service findServiceByNaturalId(Operator operator, String serviceName);
    
}
