package org.helianto.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;

import org.helianto.core.filter.UserBackedFilter;
import org.helianto.core.test.UserTestSupport;
import org.junit.Before;
import org.junit.Test;
/**
 * @author Mauricio Fernandes de Castro
 */
public class OperatorFilterTests {

    @Test
    public void testConstructor() {
		assertTrue(filter instanceof Serializable);
		assertTrue(filter instanceof UserBackedFilter);
	}
	
    @Test
	public void testFactory() {
		assertSame(filter.getUser(), user);
		assertEquals("", filter.getOperatorName());
		assertEquals("", filter.getOperatorNameLike());
	}
	
    @Test
	public void testReset() {
		filter.reset();
		assertEquals("", filter.getOperatorNameLike());
	}

    public static String C1 = "operator.operatorName = 'NAME' ";
    public static String C2 = "lower(operator.operatorName) like '%name%' ";

    @Test
    public void testEmpty() {
        assertEquals("", filter.createCriteriaAsString(false));
    }
    
    @Test
    public void testSelect() {
    	filter.setOperatorName("NAME");
        assertEquals(C1, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void testFilter() {
        filter.setOperatorNameLike("NAME");
        assertEquals(C2, filter.createCriteriaAsString(false));
    }
    
    private OperatorFilter filter;
    private User user;
    
    @Before
    public void setUp() {
    	user = UserTestSupport.createUser();
    	filter = OperatorFilter.filterFactory(user);
    }
    
}

