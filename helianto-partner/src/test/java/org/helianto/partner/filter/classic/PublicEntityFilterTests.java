package org.helianto.partner.filter.classic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;

import org.helianto.core.Operator;
import org.helianto.core.filter.classic.OperatorBackedFilter;
import org.helianto.core.test.OperatorTestSupport;
import org.helianto.partner.PublicEntity;
import org.helianto.partner.filter.classic.PublicEntityFilter;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Mauricio Fernandes de Castro
 */
public class PublicEntityFilterTests {

    @Test
    public void constructor() {
		assertTrue(filter instanceof Serializable);
		assertTrue(filter instanceof OperatorBackedFilter);
		assertSame(operator, filter.getOperator());
		assertEquals("", filter.getEntityAlias());
		assertEquals("", filter.getEntityAliasLike());
	}
	
    public static String OB = "order by publicentity.entity.alias ";
    public static String C1 = "publicentity.operator.id = 1 ";
    public static String C2 = "AND publicentity.entity.alias = 'ALIAS' ";
    public static String C3 = "AND publicentity.class=PublicEntity ";
    public static String C4 = "AND lower(publicentity.entity.alias) like '%name%' ";

    @Test
    public void empty() {
        assertEquals(C1+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void select() {
    	filter.setEntityAlias("ALIAS");
        assertEquals(C1+C2, filter.createCriteriaAsString());
    }
    
    @Test
    public void filterClazz() {
        filter.setClazz(PublicEntity.class);
        assertEquals(C1+C3+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void filterLike() {
        filter.setEntityAliasLike("NAME");
        assertEquals(C1+C4+OB, filter.createCriteriaAsString());
    }
    
    private PublicEntityFilter filter;
    private Operator operator;
    
    @Before
    public void setUp() {
    	operator = OperatorTestSupport.createOperator("DEFAULT");
    	operator.setId(1); // just because a zero here will prevent the filter to fire!
    	filter = new PublicEntityFilter(operator);
    }
}

