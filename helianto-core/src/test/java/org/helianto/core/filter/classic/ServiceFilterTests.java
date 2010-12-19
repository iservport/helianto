package org.helianto.core.filter.classic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;

import org.helianto.core.User;
import org.helianto.core.filter.classic.ServiceFilter;
import org.helianto.core.filter.classic.UserBackedFilter;
import org.helianto.core.test.OperatorTestSupport;
import org.helianto.core.test.UserTestSupport;
import org.junit.Before;
import org.junit.Test;
/**
 * @author Mauricio Fernandes de Castro
 */
public class ServiceFilterTests {

    @Test
    public void constructor() {
		assertTrue(filter instanceof Serializable);
		assertTrue(filter instanceof UserBackedFilter);
	}
	
    @Test
	public void factory() {
		assertSame(filter.getUser(), user);
		assertEquals("", filter.getServiceName());
	}
	
    public static String C1 = "service.operator.id = 1 AND service.serviceName = 'SERVICE' ";
    public static String C2 = "lower(service.serviceName) like '%service%' ";

    @Test
    public void empty() {
        assertEquals("", filter.createCriteriaAsString(false));
    }
    
    @Test
    public void select() {
    	filter.setOperator(OperatorTestSupport.createOperator());
    	filter.getOperator().setId(1);
    	filter.setServiceName("SERVICE");
        assertEquals(C1, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void filter() {
        filter.setServiceName("SERVICE");
        assertEquals(C2, filter.createCriteriaAsString(false));
    }
    
    private ServiceFilter filter;
    private User user;
    
    @Before
    public void setUp() {
    	user = UserTestSupport.createUser();
    	filter = ServiceFilter.serviceFilterFactory(user);
    }
}

