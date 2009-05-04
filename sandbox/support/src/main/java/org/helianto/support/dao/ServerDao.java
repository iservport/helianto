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

package org.helianto.support.dao;

import java.util.List;

import org.helianto.core.Operator;
import org.helianto.core.dao.CommonOrmDao;
import org.helianto.support.Server;
import org.helianto.support.ServerType;

/**
 * <code>Server</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface ServerDao extends CommonOrmDao {
     
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
     * Find <code>Server</code> by <code>Operator</code> and serverName.
     */
    public Server findServerByNaturalId(Operator operator, String serverName);
    
    /**
     * Find <code>Server</code> list by <code>Operator</code> and <code>ServerType</code>.
     */
    public List<Server> findServerByOperatorAndType(Operator operator, ServerType serverType);
    
    
    
}
