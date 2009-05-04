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

package org.helianto.support.test;

import java.util.ArrayList;
import java.util.List;



import org.helianto.core.Operator;
import org.helianto.core.test.DomainTestSupport;
import org.helianto.core.test.OperatorTestSupport;

import org.helianto.support.Server;

/**
 * Class to support <code>ServerDao</code> tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ServerTestSupport {

    private static int testKey;

    /**
     * Test support method to create a <code>Server</code>.
     * @param operator optional Operator 
     * @param serverName optional String 
     */
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
            serverName = DomainTestSupport.getNonRepeatableStringValue(testKey++, 20);
        }
        Server server = Server.serverFactory(operator, serverName);
        return server;
    }

    /**
     * Test support method to create a <code>Server</code> list.
     *
     * @param serverListSize
     */
    public static List<Server> createServerList(int serverListSize) {
        return createServerList(serverListSize, 1);
    }

    /**
     * Test support method to create a <code>Server</code> list.
     *
     * @param serverListSize
     * @param operatorListSize
     */
    public static List<Server> createServerList(int serverListSize, int operatorListSize) {
        List<Operator> operatorList = OperatorTestSupport.createOperatorList(operatorListSize);

        return createServerList(serverListSize, operatorList);
    }

    /**
     * Test support method to create a <code>Server</code> list.
     *
     * @param serverListSize
     * @param operatorList
     */
    public static List<Server> createServerList(int serverListSize, List<Operator> operatorList) {
        List<Server> serverList = new ArrayList<Server>();
        for (Operator operator: operatorList) {
	        for (int i=0;i<serverListSize;i++) {
    	        serverList.add(createServer(operator));
        	}
        }
        return serverList;
    }

}
