package org.helianto.core.orm;

import static org.junit.Assert.assertEquals;

import org.helianto.core.EntityFilter;
import org.helianto.core.test.OperatorTestSupport;
import org.helianto.core.test.UserTestSupport;
import org.junit.Before;
import org.junit.Test;
/**
 * <code>EntityDao</code> tests.
 *
 * @author Mauricio Fernandes de Castro
 */
public class DefaultEntityFilterDaoTests {

    public static String C1 = "entity.operator.id = 1 AND entity.alias = 'ALIAS' ";
    public static String C2 = "lower(entity.alias) like '%alias%' ";

    @Test
    public void testEmpty() {
        assertEquals("", entityDao.createCriteriaAsString(filter, false));
    }
    
    @Test
    public void testSelect() {
    	filter.setOperator(OperatorTestSupport.createOperator());
    	filter.getOperator().setId(1);
    	filter.setEntityAlias("ALIAS");
        assertEquals(C1, entityDao.createCriteriaAsString(filter, false));
    }
    
    @Test
    public void testFilter() {
        filter.setEntityAliasLike("ALIAS");
        assertEquals(C2, entityDao.createCriteriaAsString(filter, false));
    }
    
    private DefaultEntityDao entityDao;
    private EntityFilter filter;
    
    @Before
    public void setUp() {
    	filter = EntityFilter.entityFilterFactory(UserTestSupport.createUser());
    	entityDao = new DefaultEntityDao();
    }
}

