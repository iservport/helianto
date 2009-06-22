package org.helianto.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;

import org.helianto.core.EntityFilter;
import org.helianto.core.filter.UserBackedFilter;
import org.helianto.core.test.OperatorTestSupport;
import org.helianto.core.test.UserTestSupport;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Mauricio Fernandes de Castro
 */
public class EntityFilterTests {

    @Test
    public void testConstructor() {
		assertTrue(filter instanceof Serializable);
		assertTrue(filter instanceof UserBackedFilter);
	}
	
    @Test
	public void testFactory() {
		assertSame(filter.getUser(), user);
		assertEquals("", filter.getEntityAlias());
		assertEquals("", filter.getEntityAliasLike());
	}
	
    @Test
	public void testReset() {
		filter.reset();
		assertEquals("", filter.getEntityAliasLike());
	}

    public static String C1 = "entity.operator.id = 1 AND entity.alias = 'ALIAS' ";
    public static String C2 = "lower(entity.alias) like '%alias%' ";

    @Test
    public void testEmpty() {
        assertEquals("", filter.createCriteriaAsString(false));
    }
    
    @Test
    public void testSelect() {
    	filter.setOperator(OperatorTestSupport.createOperator());
    	filter.getOperator().setId(1);
    	filter.setEntityAlias("ALIAS");
        assertEquals(C1, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void testFilter() {
        filter.setEntityAliasLike("ALIAS");
        assertEquals(C2, filter.createCriteriaAsString(false));
    }
    
    private EntityFilter filter;
    private User user;
    
    @Before
    public void setUp() {
    	user = UserTestSupport.createUser();
    	filter = EntityFilter.entityFilterFactory(user);
    }
    
}

