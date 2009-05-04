package org.helianto.support;

import org.helianto.core.test.CredentialTestSupport;
import org.helianto.core.test.DomainTestSupport;

import junit.framework.TestCase;

import org.helianto.core.ActivityState;
import org.helianto.core.Credential;
import org.helianto.core.Encription;
import org.helianto.core.Operator;

import org.helianto.support.Server;

/**
 * <code>Server</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ServerTests extends TestCase {
    
    /**
     * Test <code>Server</code> static factory method.
     */
    public void testServerFactory() {
        Operator operator = new Operator();
        String serverName = DomainTestSupport.STRING_TEST_VALUE;
        
        Server server = Server.serverFactory(operator, serverName);
        
        assertSame(operator, server.getOperator());
        assertEquals(serverName, server.getServerName());
        assertEquals(ServerType.SMTP_SERVER.getValue(), server.getServerType());
        assertTrue(server.getCredential() instanceof Credential);
        assertEquals(-1, server.getServerPort());
        assertEquals(1, server.getPriority());
        assertEquals(ActivityState.ACTIVE.getValue(), server.getServerState());
        assertEquals(Encription.PLAIN_PASSWORD.getValue(), server.getRequiredEncription());
        
    }
    
    /**
     * Test <code>Server</code> static factory method.
     */
    public void testServerFactoryServerType() {
        Operator operator = new Operator();
        String serverName = DomainTestSupport.STRING_TEST_VALUE;
        ServerType serverType = ServerType.SMTP_SERVER;
        
        Server server = Server.serverFactory(operator, serverName, serverType);
        
        assertSame(operator, server.getOperator());
        assertEquals(serverName, server.getServerName());
        assertEquals(serverType.getValue(), server.getServerType());
        assertTrue(server.getCredential() instanceof Credential);
        assertEquals(-1, server.getServerPort());
        assertEquals(1, server.getPriority());
        assertEquals(ActivityState.ACTIVE.getValue(), server.getServerState());
        assertEquals(Encription.PLAIN_PASSWORD.getValue(), server.getRequiredEncription());
        
    }
    
    /**
     * Test <code>Server</code> static factory method.
     */
    public void testServerFactoryCredential() {
        Operator operator = new Operator();
        String serverName = DomainTestSupport.STRING_TEST_VALUE;
        ServerType serverType = ServerType.SMTP_SERVER;
        Credential credential = CredentialTestSupport.createCredential();
        
        Server server = Server.serverFactory(operator, serverName, serverType, credential);
        
        assertSame(operator, server.getOperator());
        assertEquals(serverName, server.getServerName());
        assertEquals(serverType.getValue(), server.getServerType());
        assertSame(credential, server.getCredential());
        assertEquals(-1, server.getServerPort());
        assertEquals(1, server.getPriority());
        assertEquals(ActivityState.ACTIVE.getValue(), server.getServerState());
        assertEquals(Encription.PLAIN_PASSWORD.getValue(), server.getRequiredEncription());
        
    }
    
    /**
     * Test <code>Server</code> defaults.
     */
    public void testDefaults() {
        Server server = new Server();
        
        Server.setServerDefaults(server);
        
        assertEquals(-1, server.getServerPort());
        assertEquals(1, server.getPriority());
        assertEquals(ActivityState.ACTIVE.getValue(), server.getServerState());
        assertEquals(Encription.PLAIN_PASSWORD.getValue(), server.getRequiredEncription());
        
    }
    
    /**
     * Test <code>Server</code> equals() method.
     */
    public void testServerEquals() {
        Operator operator = new Operator();
        String serverName = DomainTestSupport.STRING_TEST_VALUE;
        
        Server server = Server.serverFactory(operator, serverName);
        Server copy = (Server) DomainTestSupport.minimalEqualsTest(server);
        
        copy.setOperator(null);
        copy.setServerName(serverName);
        assertFalse(server.equals(copy));

        copy.setOperator(operator);
        copy.setServerName(null);
        assertFalse(server.equals(copy));

        copy.setOperator(operator);
        copy.setServerName(serverName);

        assertTrue(server.equals(copy));
    }

}
    
    
