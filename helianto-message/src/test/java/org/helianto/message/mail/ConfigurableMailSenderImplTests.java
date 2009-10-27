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

package org.helianto.message.mail;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import org.helianto.core.ActivityState;
import org.helianto.core.Encription;
import org.helianto.core.Server;
import org.helianto.core.test.ServerTestSupport;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ConfigurableMailSenderImplTests {
    
    private ConfigurableMailSenderImpl configurableMailSenderImpl;
    
    private Server createValidServer() {
        Server server = ServerTestSupport.createServer();
        server.setRequiredEncription(Encription.PLAIN_PASSWORD.getValue());
        server.getCredential().setCredentialState(ActivityState.ACTIVE.getValue());
        server.getCredential().setEncription(Encription.PLAIN_PASSWORD.getValue());
        return server;
    }

	@Test
    public void setTransportServer() {
        //success
        Server transportServer = createValidServer();
        configurableMailSenderImpl.setTransportServer(transportServer);
        //not an active credential
        transportServer.getCredential().setCredentialState(ActivityState.SUSPENDED.getValue());
        transportServer.getCredential().setEncription(Encription.PLAIN_PASSWORD.getValue());
        doTestInvalidServer(transportServer);
        //invalid encription
        transportServer.getCredential().setCredentialState(ActivityState.ACTIVE.getValue());
        transportServer.getCredential().setEncription(' ');
        doTestInvalidServer(transportServer);
        //null
        doTestInvalidServer(null);
    }
    
	@Test
    public void setAccessServer() {
        //tolerate null access
        configurableMailSenderImpl.setAccessServer(null);
        Server accessServer = createValidServer();
        configurableMailSenderImpl.setAccessServer(accessServer);
        assertSame(accessServer, configurableMailSenderImpl.getAccessServer());
    }
    
    public void doTestInvalidServer(Server server) {
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

	@Test
    public void init() {
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
        assertEquals("USERNAME".toLowerCase(), configurableMailSenderImpl.getUsername());
        assertEquals("PASSWORD", configurableMailSenderImpl.getPassword());
        assertEquals(Integer.MAX_VALUE, configurableMailSenderImpl.getPort());
        assertEquals("ENCODING", configurableMailSenderImpl.getDefaultEncoding());
    }

    @Before
    public void setUp() {
        configurableMailSenderImpl = new ConfigurableMailSenderImpl();
    }

}