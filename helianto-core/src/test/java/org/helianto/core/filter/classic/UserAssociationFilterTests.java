package org.helianto.core.filter.classic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;

import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.UserGroup;
import org.helianto.core.filter.ListFilter;
import org.helianto.core.filter.classic.UserAssociationFilter;
import org.helianto.core.test.EntityTestSupport;
import org.junit.Before;
import org.junit.Test;
/**
 * @author Mauricio Fernandes de Castro
 */
public class UserAssociationFilterTests {

    @Test
	public void constructor() {
		assertTrue(filter instanceof Serializable);
		assertTrue(filter instanceof ListFilter);
	}
	
    public static String C1 = "alias.parent.id = 1 ";
    public static String C2 = "alias.child.id = 2 ";
    public static String C3 = "AND alias.parent.userKey = 'PARENTKEY' ";
    public static String C4 = "AND alias.child.identity.id = 3 ";
    public static String C0 = "order by alias.child.userKey ";

    @Test
    public void empty() {
        assertEquals(C0, filter.createCriteriaAsString());
    }
    
    @Test
    public void parent() {
    	UserGroup parent = new UserGroup(entity, "PARENT");
    	parent.setId(1);
    	filter.setParent(parent);
        assertEquals(C1+C0, filter.createCriteriaAsString());
    }
    
    @Test
    public void child() {
    	UserGroup child = new UserGroup(entity, "CHILD");
    	child.setId(2);
    	filter.setChild(child);
        assertEquals(C2+C0, filter.createCriteriaAsString());
    }
    
    @Test
    public void selection() {
    	UserGroup parent = new UserGroup(entity, "PARENT");
    	parent.setId(1);
    	filter.setParent(parent);
    	UserGroup child = new UserGroup(entity, "CHILD");
    	child.setId(2);
    	filter.setChild(child);
        assertEquals(C1+"AND "+C2, filter.createCriteriaAsString());
    }
    
    @Test
    public void parentKey() {
    	UserGroup child = new UserGroup(entity, "CHILD");
    	child.setId(2);
    	filter.setChild(child);
    	filter.setParentKey("PARENTKEY");
        assertEquals(C2+C3+C0, filter.createCriteriaAsString());
    }
    
    @Test
    public void childIdentity() {
    	UserGroup parent = new UserGroup(entity, "PARENT");
    	parent.setId(1);
    	Identity childIdentity = new Identity("identity");
    	childIdentity.setId(3);
    	filter.setParent(parent);
    	filter.setChildIdentity(childIdentity);
        assertEquals(C1+C4+C0, filter.createCriteriaAsString());
    }
    
    private UserAssociationFilter filter;
    private Entity entity;
    
    @Before
    public void setUp() {
    	entity = EntityTestSupport.createEntity();
    	filter = new UserAssociationFilter();
    }
}

