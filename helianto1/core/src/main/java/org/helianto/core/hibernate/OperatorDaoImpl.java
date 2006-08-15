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

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.KeyType;
import org.helianto.core.Operator;
import org.helianto.core.Province;
import org.helianto.core.Server;
import org.helianto.core.Service;
import org.helianto.core.dao.OperatorDao;
import org.helianto.core.type.ActivityState;
import org.helianto.core.type.ServerType;

public class OperatorDaoImpl extends GenericDaoImpl implements OperatorDao {
    
    public void persistOperator(Operator operator) {
        merge(operator);
    }
    
    public void removeOperator(Operator operator) {
        remove(operator);
    }
    
    public Operator findOperatorByNaturalId(String operatorName) {
        return (Operator) findUnique(OPERATOR_QRY, operatorName);
    }
    
    static String OPERATOR_QRY = "from Operator operator " +
            "where operator.operatorName = ? ";

    public void persistKeyType(KeyType keyType) {
        merge(keyType);
    }
    
    public void removeKeyType(KeyType keyType) {
        remove(keyType);
    }
    
    public KeyType findKeyTypeByNaturalId(Operator operator, String keyCode) {
        return (KeyType) findUnique(KEYTYPE_QRY, operator, keyCode);
    }
    
    static String KEYTYPE_QRY = "from KeyType keyType "+
        "where keyType.operator = ? and keyType.keyCode = ? ";

    public void persistServer(Server server) {
        merge(server);
    }
    
    public void removeServer(Server server) {
        remove(server);
    }
    
    public Server findServerByNaturalId(Operator operator, String serverName) {
        return (Server) findUnique(SERVER_QRY, operator, serverName);
    }
    
    static String SERVER_QRY = "from Server server "+
        "where server.operator = ? and server.serverName = ? ";

    public List<Server> findServerActiveByType(Operator operator, ServerType serverType) {
        return (ArrayList<Server>) find(SERVER_QRY_BY_TYPE, operator, serverType.getValue(), ActivityState.ACTIVE.getValue());
    }
    
    static String SERVER_QRY_BY_TYPE = "from Server server "+
        "where server.operator = ? and server.serverType = ? and server.serverState = ? " +
        "order by server.priority ";

    public void persistProvince(Province province) {
        merge(province);
    }
    
    public void removeProvince(Province province) {
        remove(province);
    }
    
    public Province findProvinceByNaturalId(Operator operator, String code) {
        return (Province) findUnique(PROVINCE_QRY, operator, code);
    }
    
    static String PROVINCE_QRY = "from Province province "+
        "where province.operator = ? and province.code = ? ";

    public void persistService(Service service) {
        merge(service);
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
