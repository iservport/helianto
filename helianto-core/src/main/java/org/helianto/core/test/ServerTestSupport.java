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

package org.helianto.core.test;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.ActivityState;
import org.helianto.core.Credential;
import org.helianto.core.Operator;
import org.helianto.core.Server;
import org.helianto.core.ServerType;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class ServerTestSupport {

    private static int testKey = 1;

    public static Server createServer(Object... args) {
        Operator operator;
        try {
            operator = (Operator) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            operator = OperatorTestSupport.createOperator();
        }
        String serverName;
        try {
        	serverName = (String) args[1];
        } catch(ArrayIndexOutOfBoundsException e) {
        	serverName = DomainTestSupport.getNonRepeatableStringValue(20, testKey++);
        }
        Credential credential;
        try {
            credential = (Credential) args[2];
        } catch(ArrayIndexOutOfBoundsException e) {
            credential = CredentialTestSupport.createCredential();
        }
        ServerType serverType = ServerType.values()[testKey % 3];
        Server server = Server.serverFactory(operator, serverName, serverType, credential);
        return server;
    }

    public static List<Server> createServerList(int size, int operatorListSize) {
        List<Operator> operatorList = OperatorTestSupport.createOperatorList(operatorListSize);
        return createServerList(size, operatorList);
    }

    public static List<Server> createServerList(int size, List<Operator> operatorList) {
        List<Server> serverList = new ArrayList<Server>();
        for (Operator o: operatorList) {
            for (int i=0;i<size;i++) {
                Server server = createServer(o);
                server.setPriority((byte) (Math.random()*size));  //random priority
                server.setServerState(ActivityState.values()[(int) ((int) 100*Math.random()*ActivityState.values().length)].getValue());  //random state
                serverList.add(server);
            }
        }
        return serverList;
    }

    public static List<Server> createAndPersistServerList(HibernateTemplate hibernateTemplate, int i, int o) {
        List<Server> serverList = createServerList(i, o);
        for (Server s: serverList) {
            hibernateTemplate.merge(s);
        }
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        return serverList;
    }
    
}
