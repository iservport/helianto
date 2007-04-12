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

package org.helianto.core.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.helianto.core.KeyType;
import org.helianto.core.Operator;
import org.helianto.core.Server;
import org.helianto.core.Service;
import org.helianto.core.dao.OperatorDao;
import org.helianto.core.type.ActivityState;

public class OperatorDaoImpl extends GenericDaoImpl implements OperatorDao {
    
    // operator
    
    public void persistOperator(Operator operator) {
        persist(operator);
    }
    
    public Operator mergeOperator(Operator operator) {
        return (Operator) merge(operator);
    }
    
    public void removeOperator(Operator operator) {
        remove(operator);
    }
    
    public Operator findOperator(Object key) {
        return (Operator) load(Operator.class, (Serializable) key);
    }
    
    public List<Operator> findOperatorAll() {
        return (ArrayList<Operator>) find(OPERATOR_ALL_QRY);
    }
    
    static String OPERATOR_ALL_QRY = "from Operator operator ";

    public Operator findOperatorByNaturalId(String operatorName) {
        return (Operator) findUnique(OPERATOR_QRY, operatorName);
    }
    
    static String OPERATOR_QRY = "from Operator operator " +
            "where operator.operatorName = ? ";
    
    // key type

    public void persistKeyType(KeyType keyType) {
        persist(keyType);
    }
    
    public KeyType mergeKeyType(KeyType keyType) {
        return (KeyType) merge(keyType);
    }
    
    public void removeKeyType(KeyType keyType) {
        remove(keyType);
    }
    
    public KeyType findKeyTypeByNaturalId(Operator operator, String keyCode) {
        return (KeyType) findUnique(KEYTYPE_QRY, operator, keyCode);
    }
    
    static String KEYTYPE_QRY = "from KeyType keyType "+
        "where keyType.operator = ? and keyType.keyCode = ? ";
    
    // server

    public void persistServer(Server server) {
        persist(server);
    }
    
    public Server mergeServer(Server server) {
        return (Server) merge(server);
    }
    
    public void removeServer(Server server) {
        remove(server);
    }
    
    public Server findServerByNaturalId(Operator operator, String serverName) {
        return (Server) findUnique(SERVER_QRY, operator, serverName);
    }
    
    static String SERVER_QRY = "from Server server "+
        "where server.operator = ? " +
        "and server.serverName = ? ";

    public List<Server> findServerActive(Operator operator) {
        return (ArrayList<Server>) find(SERVER_QRY_ACTIVE, operator, ActivityState.ACTIVE.getValue());
    }
    
    static String SERVER_QRY_ACTIVE = "from Server server "+
        "where server.operator = ? " +
        "and server.serverState = ? " +
        "order by server.priority ";
    
    // service

    public void persistService(Service service) {
        persist(service);
    }
    
    public Service mergeService(Service service) {
        return (Service) merge(service);
    }
    
    public void removeService(Service service) {
        remove(service);
    }
    
    public Service findServiceByNaturalId(Operator operator, String serviceName) {
        return (Service) findUnique(SERVICE_QRY, operator, serviceName);
    }
    
    static String SERVICE_QRY = "from Service service "+
        "where service.operator = ? and service.serviceName = ? ";

}
