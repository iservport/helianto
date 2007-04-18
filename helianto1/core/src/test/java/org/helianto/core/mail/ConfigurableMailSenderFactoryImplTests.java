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

package org.helianto.core.mail;

import java.util.List;

import org.helianto.core.ActivityState;
import org.helianto.core.Encription;
import org.helianto.core.Server;
import org.helianto.core.ServerType;
import org.helianto.core.test.OperatorTestSupport;

import junit.framework.TestCase;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ConfigurableMailSenderFactoryImplTests extends TestCase {
    
    public void testCreate() {
        List<Server> serverList = createServerList();
        ConfigurableMailSenderFactoryImpl configurableMailSenderFactory = new ConfigurableMailSenderFactoryImpl();
        ConfigurableMailSenderImpl configurableMailSender = (ConfigurableMailSenderImpl) configurableMailSenderFactory.create(serverList);
        assertEquals("HOST_ADDRESS", configurableMailSender.getHost());
        assertSame(serverList.get(1), configurableMailSender.getAccessServer());
    }
    
    //helper methods

    private List<Server> createServerList() {
        List<Server> serverList = OperatorTestSupport.createServerList(3, 1);
        prepareServer(serverList.get(0), ServerType.SMTP_SERVER);
        serverList.get(0).setServerHostAddress("HOST_ADDRESS");
        prepareServer(serverList.get(1), ServerType.POP3_SERVER);
        prepareServer(serverList.get(2), ServerType.HTTP_SERVER);
        return serverList;
    }
    
    private void prepareServer(Server server, ServerType serverType) {
        server.setServerType(serverType.getValue());
        server.setRequiredEncription(Encription.PLAIN_PASSWORD.getValue());
        server.getCredential().setCredentialState(ActivityState.ACTIVE.getValue());
        server.getCredential().setEncription(Encription.PLAIN_PASSWORD.getValue());
    }

}
