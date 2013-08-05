package org.helianto.core.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.helianto.core.def.ActivityState;
import org.helianto.core.def.Encription;
import org.helianto.core.def.ServerType;
import org.helianto.core.test.DomainTestSupport;
import org.junit.Test;

/**
 * <code>Category</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ServerTests  {
    
	@Test
    public void constructor() {
        Operator operator = new Operator();
        Credential credential = new Credential();
        Server server = new Server(operator, "NAME", ServerType.HTTP_SERVER, credential);
        
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

    /**
     * Test <code>Category</code> equals() method.
     */
	@Test
    public void serverEquals() {
        Operator operator = new Operator();
        Credential credential = new Credential();
        Server server = new Server(operator, "NAME", ServerType.HTTP_SERVER, credential);
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
    
    
