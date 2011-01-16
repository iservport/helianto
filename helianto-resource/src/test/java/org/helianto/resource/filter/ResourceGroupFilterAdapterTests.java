package org.helianto.resource.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.core.Entity;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.resource.Resource;
import org.helianto.resource.ResourceGroup;
import org.helianto.resource.ResourceType;
import org.junit.Before;
import org.junit.Test;
/**
 * @author Mauricio Fernandes de Castro
 */
public class ResourceGroupFilterAdapterTests {

    public static String OB = "order by alias.resourceCode ";
    public static String C0 = "alias.entity.id = 0 ";
    public static String C1 = "AND alias.class=Resource ";
    public static String C2 = "AND alias.resourceCode = 'CODE' ";
    public static String C3 = "AND lower(alias.resourceName) like '%name%' ";
    public static String C4 = "AND alias.resourceType = 'F' ";

    @Test
    public void empty() {
        assertEquals(C0+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void filterClazz() {
        filter.setClazz(Resource.class);
        assertEquals(C0+C1+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void select() {
    	target.setResourceCode("CODE");
        assertEquals(C0+C2, filter.createCriteriaAsString());
    }
    
    @Test
    public void filterName() {
    	target.setResourceName("NAME");
        assertEquals(C0+C3+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void filterInheritance() {
    	target.setResourceType(ResourceType.FIXTURE.getValue());
        assertEquals(C0+C4+OB, filter.createCriteriaAsString());
    }
    
    private ResourceGroupFilterAdapter filter;
    private ResourceGroup target;;
    
    @Before
    public void setUp() {
    	Entity entity = EntityTestSupport.createEntity();
    	target = new ResourceGroup(entity, "");
    	filter = new ResourceGroupFilterAdapter(target);
    }
}

