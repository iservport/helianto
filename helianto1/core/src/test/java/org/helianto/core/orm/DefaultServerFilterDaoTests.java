package org.helianto.core.orm;

import static org.junit.Assert.assertEquals;

import org.helianto.core.ActivityState;
import org.helianto.core.ServerFilter;
import org.helianto.core.ServerType;
import org.helianto.core.test.OperatorTestSupport;
import org.helianto.core.test.UserTestSupport;
import org.junit.Before;
import org.junit.Test;
/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultServerFilterDaoTests {

    public static String C0 = "order by server.priority ";
    public static String C1 = "server.operator.id = 1 AND server.serverName = 'NAME' ";
    public static String C2 = "lower(server.serverName) like '%name%' ";
    public static String C3 = "server.serverType = 'H' ";
    public static String C4 = "server.priority = 1 ";
    public static String C5 = "server.serverState = 'A' ";

    @Test
    public void testEmpty() {
        assertEquals(C0, serverDao.createCriteriaAsString(filter, false));
    }
    
    @Test
    public void testSelect() {
    	filter.setOperator(OperatorTestSupport.createOperator());
    	filter.getOperator().setId(1);
    	filter.setServerName("NAME");
        assertEquals(C1, serverDao.createCriteriaAsString(filter, false));
    }
    
    @Test
    public void testFilterLikeName() {
        filter.setServerName("NAME");
        assertEquals(C2+C0, serverDao.createCriteriaAsString(filter, false));
    }
    
    @Test
    public void testFilterType() {
        filter.setServerType(ServerType.HTTP_SERVER.getValue());
        assertEquals(C3+C0, serverDao.createCriteriaAsString(filter, false));
    }
    
    @Test
    public void testFilterPriority() {
        filter.setPriority((byte) 1);
        assertEquals(C4+C0, serverDao.createCriteriaAsString(filter, false));
    }
    
    @Test
    public void testFilterServerState() {
        filter.setServerState(ActivityState.ACTIVE.getValue());
        assertEquals(C5+C0, serverDao.createCriteriaAsString(filter, false));
    }
    
    private DefaultServerDao serverDao;
    private ServerFilter filter;
    
    @Before
    public void setUp() {
    	filter = ServerFilter.serverFilterFactory(UserTestSupport.createUser());
    	serverDao = new DefaultServerDao();
    }
}

