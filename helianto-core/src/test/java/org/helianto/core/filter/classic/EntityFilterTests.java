package org.helianto.core.filter.classic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.helianto.core.domain.Operator;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Mauricio Fernandes de Castro
 */
public class EntityFilterTests {

    @Test
    public void constructor() {
		assertTrue(filter instanceof AbstractOperatorBackedCriteriaFilter);
		assertEquals("", filter.getEntityAlias());
		assertEquals("", filter.getEntityAliasLike());
	}
	
    @Test
	public void reset() {
    	filter.setEntityAlias("ALIAS");
        filter.setEntityAliasLike("ALIAS");
		filter.reset();
		assertEquals("", filter.getEntityAlias());
		assertEquals("", filter.getEntityAliasLike());
	}

    public static String ORDER = "order by entity.alias ";
    public static String C1 = "entity.operator.id = 1 ";
    public static String C2 = "AND entity.alias = 'ALIAS' ";
    public static String C3 = "AND lower(entity.alias) like '%alias%' ";

    @Test
    public void empty() {
        assertEquals(C1+ORDER, filter.createCriteriaAsString());
    }
    
    @Test
    public void select() {
    	filter.setEntityAlias("ALIAS");
        assertEquals(C1+C2, filter.createCriteriaAsString());
    }
    
    @Test
    public void filter() {
        filter.setEntityAliasLike("ALIAS");
        assertEquals(C1+C3+ORDER, filter.createCriteriaAsString());
    }
    
    private EntityFilter filter;
    
    @Before
    public void setUp() {
    	Operator operator = new Operator("DEFAULT");
    	operator.setId(1);
    	filter = new EntityFilter(operator);
    }
    
}

