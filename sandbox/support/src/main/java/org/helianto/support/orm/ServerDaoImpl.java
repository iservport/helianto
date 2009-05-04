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

package org.helianto.support.orm;

import java.util.ArrayList;
import java.util.List;

import org.helianto.support.Server;
import org.helianto.support.ServerType;
import org.helianto.support.dao.ServerDao;
import org.helianto.core.hibernate.GenericDaoImpl;



import org.helianto.core.Operator;
/**
 * Default implementation of <code>Server</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ServerDaoImpl extends GenericDaoImpl implements ServerDao {
     
    public void persistServer(Server server) {
        if (logger.isDebugEnabled()) {
            logger.debug("Persisting "+server);
        }
        persist(server);
    }
    
    public Server mergeServer(Server server) {
        if (logger.isDebugEnabled()) {
            logger.debug("Merging "+server);
        }
        return (Server) merge(server);
    }
    
    public void removeServer(Server server) {
        if (logger.isDebugEnabled()) {
            logger.debug("Removing "+server);
        }
        remove(server);
    }
    
    public Server findServerByNaturalId(Operator operator, String serverName) {
        if (logger.isDebugEnabled()) {
            logger.debug("Finding unique server with operator='"+operator+"' and serverName='"+serverName+"' ");
        }
        return (Server) findUnique(Server.getServerNaturalIdQueryString(), operator, serverName);
    }
    
    
    public List<Server> findServerByOperatorAndType(Operator operator, ServerType serverType) {
        if (logger.isDebugEnabled()) {
            logger.debug("Finding ordered server list with operator='"+operator+"' and serverType='"+serverType+"' ");
        }
        return (ArrayList<Server>) find(Server.getServerOperatorAndTypeQueryString(), operator, serverType);
    }
    
}
