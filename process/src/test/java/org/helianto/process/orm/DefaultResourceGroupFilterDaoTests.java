package org.helianto.process.orm;

import static org.junit.Assert.assertEquals;

import org.helianto.core.test.UserTestSupport;
import org.helianto.process.Resource;
import org.helianto.process.ResourceGroupFilter;
import org.helianto.process.ResourceType;
import org.junit.Before;
import org.junit.Test;
/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultResourceGroupFilterDaoTests {

    public static String OB = "order by resourcegroup.resourceCode ";
    public static String C0 = "resourcegroup.entity.id = 0 ";
    public static String C1 = "AND resourcegroup.class=Resource ";
    public static String C2 = "AND resourcegroup.resourceCode = 'CODE' ";
    public static String C3 = "AND lower(resourcegroup.resourceName) like '%name%' ";
    public static String C4 = "AND resourcegroup.resourceType = 'F' ";

    @Test
    public void testEmpty() {
        assertEquals(C0+OB, resourceGroupDao.createCriteriaAsString(filter, false));
    }
    
    @Test
    public void testFilterClazz() {
        filter.setClazz(Resource.class);
        assertEquals(C0+C1+OB, resourceGroupDao.createCriteriaAsString(filter, false));
    }
    
    @Test
    public void testSelect() {
    	filter.setResourceCode("CODE");
        assertEquals(C0+C2, resourceGroupDao.createCriteriaAsString(filter, false));
    }
    
    @Test
    public void testFilterName() {
        filter.setResourceNameLike("NAME");
        assertEquals(C0+C3+OB, resourceGroupDao.createCriteriaAsString(filter, false));
    }
    
    @Test
    public void testFilterInheritance() {
        filter.setResourceType(ResourceType.FIXTURE.getValue());
        assertEquals(C0+C4+OB, resourceGroupDao.createCriteriaAsString(filter, false));
    }
    
    private DefaultResourceGroupDao resourceGroupDao;
    private ResourceGroupFilter filter;
    
    @Before
    public void setUp() {
    	filter = ResourceGroupFilter.resourceGroupFilterFactory(UserTestSupport.createUser());
    	resourceGroupDao = new DefaultResourceGroupDao();
    }
}

