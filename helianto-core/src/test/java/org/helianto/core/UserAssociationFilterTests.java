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
public class UserAssociationFilterTests {

    @Test
	public void constructor() {
		assertTrue(filter instanceof Serializable);
		assertTrue(filter instanceof UserBackedFilter);
	}
	
    @Test
	public void factory() {
		assertSame(filter.getUser(), user);
	}
	
    public static String C1 = "userassociation.entity.id = 0 ";
    public static String C2 = "AND userassociation.parent.id = 1 ";
    public static String C3 = "AND userassociation.child.id = 2 ";
    public static String C0 = "order by userassociation.child.userKey ";

    @Test
    public void empty() {
        assertEquals(C1+C0, filter.createCriteriaAsString());
    }
    
    @Test
    public void parent() {
    	UserGroup parent = new UserGroup(user.getEntity(), "PARENT");
    	parent.setId(1);
    	filter.setParent(parent);
        assertEquals(C1+C2+C0, filter.createCriteriaAsString());
    }
    
    @Test
    public void child() {
    	UserGroup child = new UserGroup(user.getEntity(), "CHILD");
    	child.setId(2);
    	filter.setChild(child);
        assertEquals(C1+C3+C0, filter.createCriteriaAsString());
    }
    
    @Test
    public void selection() {
    	UserGroup parent = new UserGroup(user.getEntity(), "PARENT");
    	parent.setId(1);
    	filter.setParent(parent);
    	UserGroup child = new UserGroup(user.getEntity(), "CHILD");
    	child.setId(2);
    	filter.setChild(child);
        assertEquals(C1+C2+C3, filter.createCriteriaAsString(false));
    }
    
    private UserAssociationFilter filter;
    private User user;
    
    @Before
    public void setUp() {
    	user = UserTestSupport.createUser();
    	filter = new UserAssociationFilter(user);
    }
}

