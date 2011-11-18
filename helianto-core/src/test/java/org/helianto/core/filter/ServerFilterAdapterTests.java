package org.helianto.core.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.core.Operator;
import org.helianto.core.Server;
import org.helianto.core.def.ActivityState;
import org.helianto.core.def.ServerType;
import org.helianto.core.test.OperatorTestSupport;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Mauricio Fernandes de Castro
 */
public class ServerFilterAdapterTests {

    public static String C0 = "order by alias.priority ";
    public static String C1 = "alias.operator.id = 1 AND alias.serverName = 'NAME' ";
    public static String C2 = "alias.serverType = 'H' ";
    public static String C3 = "alias.priority = 1 ";
    public static String C4 = "alias.serverState = 'A' ";

    @Test
    public void empty() {
        assertEquals(C0, filter.createCriteriaAsString());
    }
    
    @Test
    public void select() {
    	target.setOperator(OperatorTestSupport.createOperator());
    	target.getOperator().setId(1);
    	target.setServerName("NAME");
        assertEquals(C1, filter.createCriteriaAsString());
    }
    
    @Test
    public void filterType() {
    	target.setServerType(ServerType.HTTP_SERVER.getValue());
        assertEquals(C2+C0, filter.createCriteriaAsString());
    }
    
    @Test
    public void filterPriority() {
    	target.setPriority((byte) 1);
        assertEquals(C3+C0, filter.createCriteriaAsString());
    }
    
    @Test
    public void filterServerState() {
    	target.setServerState(ActivityState.ACTIVE.getValue());
        assertEquals(C4+C0, filter.createCriteriaAsString());
    }
    
    private Server target;
    private ServerFilterAdapter filter;
    
    @Before
    public void setUp() {
    	Operator operator = new Operator("DEFAULT");
    	target = new Server(operator, "");
    	filter = new ServerFilterAdapter(target);
    }
}

