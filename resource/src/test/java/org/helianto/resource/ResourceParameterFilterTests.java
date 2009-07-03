package org.helianto.resource;

import static org.junit.Assert.assertEquals;

import org.helianto.core.User;
import org.helianto.core.test.UserTestSupport;
import org.helianto.resource.ResourceParameterFilter;
import org.junit.Before;
import org.junit.Test;
/**
 * @author Mauricio Fernandes de Castro
 */
public class ResourceParameterFilterTests {

    public static String OB = "order by resourceparameter.parameterCode ";
    public static String C0 = "resourceparameter.entity.id = 0 ";
    public static String C2 = "AND resourceparameter.parameterCode = 'CODE' ";
    public static String C3 = "AND lower(resourceparameter.parameterName) like '%name%' ";

    @Test
    public void testEmpty() {
        assertEquals(C0+OB, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void testSelect() {
    	filter.setParameterCode("CODE");
        assertEquals(C0+C2, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void testFilterName() {
        filter.setParameterNameLike("NAME");
        assertEquals(C0+C3+OB, filter.createCriteriaAsString(false));
    }
    
    private ResourceParameterFilter filter;
    private User user;
    
    @Before
    public void setUp() {
    	user = UserTestSupport.createUser();
    	filter = ResourceParameterFilter.resourceParameterFilterFactory(user);
    }
}

