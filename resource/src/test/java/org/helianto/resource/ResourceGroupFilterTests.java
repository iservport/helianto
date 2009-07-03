package org.helianto.resource;

import static org.junit.Assert.assertEquals;

import org.helianto.core.User;
import org.helianto.core.test.UserTestSupport;
import org.helianto.resource.Resource;
import org.helianto.resource.ResourceGroupFilter;
import org.helianto.resource.ResourceType;
import org.junit.Before;
import org.junit.Test;
/**
 * @author Mauricio Fernandes de Castro
 */
public class ResourceGroupFilterTests {

    public static String OB = "order by resourcegroup.resourceCode ";
    public static String C0 = "resourcegroup.entity.id = 0 ";
    public static String C1 = "AND resourcegroup.class=Resource ";
    public static String C2 = "AND resourcegroup.resourceCode = 'CODE' ";
    public static String C3 = "AND lower(resourcegroup.resourceName) like '%name%' ";
    public static String C4 = "AND resourcegroup.resourceType = 'F' ";

    @Test
    public void testEmpty() {
        assertEquals(C0+OB, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void testFilterClazz() {
        filter.setClazz(Resource.class);
        assertEquals(C0+C1+OB, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void testSelect() {
    	filter.setResourceCode("CODE");
        assertEquals(C0+C2, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void testFilterName() {
        filter.setResourceNameLike("NAME");
        assertEquals(C0+C3+OB, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void testFilterInheritance() {
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

