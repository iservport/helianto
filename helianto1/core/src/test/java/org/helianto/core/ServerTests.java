package org.helianto.core;

import junit.framework.TestCase;

import org.helianto.core.test.DomainTestSupport;

/**
 * <code>Category</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ServerTests extends TestCase {
    
    public void testServerFactory() {
        Operator operator = new Operator();
        Credential credential = new Credential();
        Server server = Server.serverFactory(operator, "NAME", ServerType.HTTP_SERVER, credential);
        
        assertSame(operator, server.getOperator());
        assertEquals("NAME", server.getServerName());
        assertEquals(-1, server.getServerPort());
        assertEquals(ServerType.HTTP_SERVER.getValue(), server.getServerType());
        assertSame(credential, server.getCredential());
        
        assertEquals("", server.getServerHostAddress());
        assertEquals("", server.getServerDesc());
        assertEquals((byte) 1, server.getPriority());
        assertEquals(ActivityState.ACTIVE.getValue(), server.getServerState());
        assertEquals(Encription.PLAIN_PASSWORD.getValue(), server.getRequiredEncription());
    }

    public void testServerFactoryDefaults() {
        Operator operator = new Operator();
        Server server = Server.serverFactory(operator, "NAME", null, null);
        
        assertEquals(ServerType.SMTP_SERVER.getValue(), server.getServerType());
        assertEquals("NAME".toLowerCase(), server.getCredential().getIdentity().getPrincipal());
        assertSame("", server.getCredential().getPassword());
    }

    /**
     * Test <code>Category</code> equals() method.
     */
    public void testCategoryEquals() {
        Operator operator = new Operator();
        Credential credential = new Credential();
        Server server = Server.serverFactory(operator, "NAME", ServerType.HTTP_SERVER, credential);
        Server copy = (Server) DomainTestSupport.minimalEqualsTest(server);
        
        copy.setOperator(server.getOperator());
        assertFalse(server.equals(copy));

        copy.setOperator(null);
        copy.setServerName("NAME");
        assertFalse(server.equals(copy));

        copy.setOperator(server.getOperator());
        copy.setServerName("NAME");
        assertTrue(server.equals(copy));
    }

}
    
    
