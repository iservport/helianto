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
    public static String C5 = "AND parentAssociation.parent.id = 1 ";
    public static String C6 = "AND childAssociation.child.id = 1 ";

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
    public void resourceName() {
    	target.setResourceName("NAME");
        assertEquals(C0+C3+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void resourceType() {
    	target.setResourceType(ResourceType.FIXTURE.getValue());
        assertEquals(C0+C4+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void parent() {
    	ResourceGroup parent = new ResourceGroup(target.getEntity(), "PARENT");
    	parent.setId(1);
    	filter.setParent(parent);
        assertEquals("select alias from ResourceGroup alias inner join alias.parentAssociations as parentAssociation ", filter.createSelectAsString());
        assertEquals(C0+C5+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void child() {
    	ResourceGroup child = new ResourceGroup(target.getEntity(), "CHILD");
    	child.setId(1);
    	filter.setChild(child);
        assertEquals("select alias from ResourceGroup alias inner join alias.childAssociations as childAssociation ", filter.createSelectAsString());
        assertEquals(C0+C6+OB, filter.createCriteriaAsString());
    }
    
    private ResourceGroupFilterAdapter filter;
    private ResourceGroup target;
    
    @Before
    public void setUp() {
    	Entity entity = EntityTestSupport.createEntity();
    	target = new ResourceGroup(entity, "");
    	filter = new ResourceGroupFilterAdapter(target);
    }
}

