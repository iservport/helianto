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

import org.helianto.core.Server;
import org.helianto.core.test.OperatorTestSupport;
import org.helianto.core.type.ActivityState;
import org.helianto.core.type.Encription;

import junit.framework.TestCase;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ConfigurableMailSenderImplTests extends TestCase {
    
    private ConfigurableMailSenderImpl configurableMailSenderImpl;
    
    private Server createValidServer() {
        Server server = OperatorTestSupport.createServer();
        server.setRequiredEncription(Encription.PLAIN_PASSWORD.getValue());
        server.getCredential().setCredentialState(ActivityState.ACTIVE.getValue());
        server.getCredential().setEncription(Encription.PLAIN_PASSWORD.getValue());
        return server;
    }

    public void testSetTransportServer() {
        //success
        Server transportServer = createValidServer();
        configurableMailSenderImpl.setTransportServer(transportServer);
        //not an active credential
        transportServer.getCredential().setCredentialState(ActivityState.SUSPENDED.getValue());
        transportServer.getCredential().setEncription(Encription.PLAIN_PASSWORD.getValue());
        doTestIvalidServer(transportServer);
        //invalid encription
        transportServer.getCredential().setCredentialState(ActivityState.ACTIVE.getValue());
        transportServer.getCredential().setEncription(Byte.MAX_VALUE);
        doTestIvalidServer(transportServer);
        //null
        doTestIvalidServer(null);
    }
    
    public void testSetAccessServer() {
        //tolerate null access
        configurableMailSenderImpl.setAccessServer(null);
        Server accessServer = createValidServer();
        configurableMailSenderImpl.setAccessServer(accessServer);
        assertSame(accessServer, configurableMailSenderImpl.getAccessServer());
    }
    
    public void doTestIvalidServer(Server server) {
        try {
            configurableMailSenderImpl.setTransportServer(server);
            fail();
        } 
        catch (IllegalArgumentException e) {
            assertEquals("Server is not valid.", e.getMessage());
        }
        catch (Exception e) {
            fail("Previos exception should have been caught!");
        }
    }

    public void testInit() {
        Server transportServer = createValidServer();

        transportServer.setServerHostAddress("HOST_ADDRESS");
        transportServer.getCredential().getIdentity()
            .setPrincipal("USERNAME");
        transportServer.getCredential().setPassword("PASSWORD");
        transportServer.setServerPort(Integer.MAX_VALUE);
        transportServer.getOperator().setDefaultEncoding("ENCODING");
        configurableMailSenderImpl.setTransportServer(transportServer);
        configurableMailSenderImpl.init();
        assertEquals("HOST_ADDRESS", configurableMailSenderImpl.getHost());
        assertEquals("USERNAME", configurableMailSenderImpl.getUsername());
        assertEquals("PASSWORD", configurableMailSenderImpl.getPassword());
        assertEquals(Integer.MAX_VALUE, configurableMailSenderImpl.getPort());
        assertEquals("ENCODING", configurableMailSenderImpl.getDefaultEncoding());
    }

    @Override
    public void setUp() {
        configurableMailSenderImpl = new ConfigurableMailSenderImpl();
    }

}
