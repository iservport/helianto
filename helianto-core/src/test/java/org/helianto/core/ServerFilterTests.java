package org.helianto.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;

import org.helianto.core.ActivityState;
import org.helianto.core.ServerFilter;
import org.helianto.core.ServerType;
import org.helianto.core.filter.UserBackedFilter;
import org.helianto.core.test.OperatorTestSupport;
import org.helianto.core.test.UserTestSupport;
import org.junit.Before;
import org.junit.Test;
/**
 * @author Mauricio Fernandes de Castro
 */
public class ServerFilterTests {

    @Test
    public void constructor() {
		assertTrue(filter instanceof Serializable);
		assertTrue(filter instanceof UserBackedFilter);
	}
	
    @Test
	public void factory() {
		assertSame(filter.getUser(), user);
		assertEquals("", filter.getServerName());
		assertEquals(' ', filter.getServerType());
		assertEquals((byte) 0, filter.getPriority());
		assertEquals(' ', filter.getServerState());
	}
	
    @Test
	public void reset() {
		filter.reset();
		assertEquals(' ', filter.getServerType());
		assertEquals((byte) 0, filter.getPriority());
		assertEquals(' ', filter.getServerState());
	}

    public static String C0 = "order by server.priority ";
    public static String C1 = "server.operator.id = 1 AND server.serverName = 'NAME' ";
    public static String C2 = "lower(server.serverName) like '%name%' ";
    public static String C3 = "server.serverType = 'H' ";
    public static String C4 = "server.priority = 1 ";
    public static String C5 = "server.serverState = 'A' ";

    @Test
    public void empty() {
        assertEquals(C0, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void select() {
    	filter.setOperator(OperatorTestSupport.createOperator());
    	filter.getOperator().setId(1);
    	filter.setServerName("NAME");
        assertEquals(C1, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void filterLikeName() {
        filter.setServerName("NAME");
        assertEquals(C2+C0, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void filterType() {
        filter.setServerType(ServerType.HTTP_SERVER.getValue());
        assertEquals(C3+C0, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void filterPriority() {
        filter.setPriority((byte) 1);
        assertEquals(C4+C0, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void filterServerState() {
        filter.setServerState(ActivityState.ACTIVE.getValue());
        assertEquals(C5+C0, filter.createCriteriaAsString(false));
    }
    
    private ServerFilter filter;
    private User user;
    
    @Before
    public void setUp() {
    	user = UserTestSupport.createUser();
    	filter = ServerFilter.serverFilterFactory(user);
    }
}

