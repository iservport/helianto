package org.helianto.core.orm;

import static org.junit.Assert.assertEquals;

import org.helianto.core.OperatorFilter;
import org.helianto.core.test.UserTestSupport;
import org.junit.Before;
import org.junit.Test;
/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultOperatorFilterDaoTests {

    public static String C1 = "operator.operatorName = 'NAME' ";
    public static String C2 = "lower(operator.operatorName) like '%name%' ";

    @Test
    public void testEmpty() {
        assertEquals("", operatorDao.createCriteriaAsString(filter, false));
    }
    
    @Test
    public void testSelect() {
    	filter.setOperatorName("NAME");
        assertEquals(C1, operatorDao.createCriteriaAsString(filter, false));
    }
    
    @Test
    public void testFilter() {
        filter.setOperatorNameLike("NAME");
        assertEquals(C2, operatorDao.createCriteriaAsString(filter, false));
    }
    
    private DefaultOperatorDao operatorDao;
    private OperatorFilter filter;
    
    @Before
    public void setUp() {
    	filter = OperatorFilter.filterFactory(UserTestSupport.createUser());
    	operatorDao = new DefaultOperatorDao();
    }
}

