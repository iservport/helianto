package org.helianto.resource.filter.classic;

import static org.junit.Assert.assertEquals;

import org.helianto.core.User;
import org.helianto.core.test.UserTestSupport;
import org.helianto.resource.filter.classic.ResourceParameterFilter;
import org.junit.Before;
import org.junit.Test;
/**
 * @author Mauricio Fernandes de Castro
 */
public class ResourceParameterFilterTests {

    public static String OB = "order by alias.parameterCode ";
    public static String C0 = "alias.entity.id = 0 ";
    public static String C2 = "AND alias.parameterCode = 'CODE' ";
    public static String C3 = "AND lower(alias.parameterName) like '%name%' ";

    @Test
    public void empty() {
        assertEquals(C0+OB, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void select() {
    	filter.setParameterCode("CODE");
        assertEquals(C0+C2, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void filterName() {
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

