package org.helianto.resource.filter.classic;

import static org.junit.Assert.assertEquals;

import org.helianto.core.User;
import org.helianto.core.test.UserTestSupport;
import org.helianto.resource.Resource;
import org.helianto.resource.ResourceType;
import org.helianto.resource.filter.classic.ResourceGroupFilter;
import org.junit.Before;
import org.junit.Test;
/**
 * @author Mauricio Fernandes de Castro
 */
public class ResourceGroupFilterTests {

    public static String OB = "order by alias.resourceCode ";
    public static String C0 = "alias.entity.id = 0 ";
    public static String C1 = "AND alias.class=Resource ";
    public static String C2 = "AND alias.resourceCode = 'CODE' ";
    public static String C3 = "AND lower(alias.resourceName) like '%name%' ";
    public static String C4 = "AND alias.resourceType = 'F' ";

    @Test
    public void empty() {
        assertEquals(C0+OB, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void filterClazz() {
        filter.setClazz(Resource.class);
        assertEquals(C0+C1+OB, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void select() {
    	filter.setResourceCode("CODE");
        assertEquals(C0+C2, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void filterName() {
        filter.setResourceNameLike("NAME");
        assertEquals(C0+C3+OB, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void filterInheritance() {
        filter.setResourceType(ResourceType.FIXTURE.getValue());
        assertEquals(C0+C4+OB, filter.createCriteriaAsString(false));
    }
    
    private ResourceGroupFilter filter;
    private User user;
    
    @Before
    public void setUp() {
    	user = UserTestSupport.createUser();
    	filter = ResourceGroupFilter.resourceGroupFilterFactory(user);
    }
}

