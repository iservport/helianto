package org.helianto.process.orm;

import static org.junit.Assert.assertEquals;

import org.helianto.core.test.UserTestSupport;
import org.helianto.process.ResourceParameterFilter;
import org.junit.Before;
import org.junit.Test;
/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultResourceParameterFilterDaoTests {

    public static String OB = "order by resourceparameter.parameterCode ";
    public static String C0 = "resourceparameter.entity.id = 0 ";
    public static String C2 = "AND resourceparameter.parameterCode = 'CODE' ";
    public static String C3 = "AND lower(resourceparameter.parameterName) like '%name%' ";

    @Test
    public void testEmpty() {
        assertEquals(C0+OB, resourceParameterDao.createCriteriaAsString(filter, false));
    }
    
    @Test
    public void testSelect() {
    	filter.setParameterCode("CODE");
        assertEquals(C0+C2, resourceParameterDao.createCriteriaAsString(filter, false));
    }
    
    @Test
    public void testFilterName() {
        filter.setParameterNameLike("NAME");
        assertEquals(C0+C3+OB, resourceParameterDao.createCriteriaAsString(filter, false));
    }
    
    private DefaultResourceParameterDao resourceParameterDao;
    private ResourceParameterFilter filter;
    
    @Before
    public void setUp() {
    	filter = ResourceParameterFilter.resourceParameterFilterFactory(UserTestSupport.createUser());
    	resourceParameterDao = new DefaultResourceParameterDao();
    }
}

