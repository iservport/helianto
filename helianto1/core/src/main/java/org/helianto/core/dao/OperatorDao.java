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

import org.helianto.core.ActivityState;
import org.helianto.core.Operator;
import org.helianto.core.Server;
import org.helianto.core.ServerType;

/**
 * <code>Operator</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface OperatorDao extends CommonOrmDao {
    
    // operator

    /**
     * Persist <code>Operator</code>.
     */
    public void persistOperator(Operator operator);
    
    /**
     * Merge <code>Operator</code>.
     */
    public Operator mergeOperator(Operator operator);
    
    /**
     * Remove <code>Operator</code>.
     */
    public void removeOperator(Operator operator);
    
    /**
     * Find <code>Operator</code> by primary key.
     */
    public Operator findOperator(Object key);
    
    /**
     * Find any registered <code>Operator</code>.
     */
    public List<Operator> findOperatorAll();
    
    /**
     * Find <code>Operator</code> by  operatorName.
     */
    public Operator findOperatorByNaturalId(String operatorName);
    
    // server
    
    /**
     * Persist <code>Server</code>.
     */
    public void persistServer(Server server);
    
    /**
     * Merge <code>Server</code>.
     */
    public Server mergeServer(Server server);
    
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
    public List<Server> findServerActive(Operator operator);
    
}
