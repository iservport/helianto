package org.helianto.core.filter.classic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;

import org.helianto.core.User;
import org.helianto.core.filter.classic.OperatorFilter;
import org.helianto.core.filter.classic.UserBackedFilter;
import org.helianto.core.test.UserTestSupport;
import org.junit.Before;
import org.junit.Test;
/**
 * @author Mauricio Fernandes de Castro
 */
public class OperatorFilterTests {

    @Test
    public void constructor() {
		assertTrue(filter instanceof Serializable);
		assertTrue(filter instanceof UserBackedFilter);
	}
	
    @Test
	public void factory() {
		assertSame(filter.getUser(), user);
		assertEquals("", filter.getOperatorName());
		assertEquals("", filter.getOperatorNameLike());
	}
	
    @Test
	public void reset() {
		filter.reset();
		assertEquals("", filter.getOperatorNameLike());
	}

    public static String C1 = "alias.operatorName = 'NAME' ";
    public static String C2 = "lower(alias.operatorName) like '%name%' ";

    @Test
    public void empty() {
        assertEquals("", filter.createCriteriaAsString(false));
    }
    
    @Test
    public void select() {
    	filter.setOperatorName("NAME");
        assertEquals(C1, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void filter() {
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
