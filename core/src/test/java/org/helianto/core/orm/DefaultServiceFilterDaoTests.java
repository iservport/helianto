package org.helianto.core.orm;

import static org.junit.Assert.assertEquals;

import org.helianto.core.ServiceFilter;
import org.helianto.core.test.OperatorTestSupport;
import org.helianto.core.test.UserTestSupport;
import org.junit.Before;
import org.junit.Test;
/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultServiceFilterDaoTests {

    public static String C1 = "service.operator.id = 1 AND service.serviceName = 'SERVICE' ";
    public static String C2 = "lower(service.serviceName) like '%service%' ";

    @Test
    public void testEmpty() {
        assertEquals("", serviceDao.createCriteriaAsString(filter, false));
    }
    
    @Test
    public void testSelect() {
    	filter.setOperator(OperatorTestSupport.createOperator());
    	filter.getOperator().setId(1);
    	filter.setServiceName("SERVICE");
        assertEquals(C1, serviceDao.createCriteriaAsString(filter, false));
    }
    
    @Test
    public void testFilter() {
        filter.setServiceName("SERVICE");
        assertEquals(C2, serviceDao.createCriteriaAsString(filter, false));
    }
    
    private DefaultServiceDao serviceDao;
    private ServiceFilter filter;
    
    @Before
    public void setUp() {
    	filter = ServiceFilter.serviceFilterFactory(UserTestSupport.createUser());
    	serviceDao = new DefaultServiceDao();
    }
}

