package org.helianto.resource.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.core.Entity;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.resource.def.ResourceType;
import org.helianto.resource.domain.ResourceGroup;
import org.helianto.resource.filter.ResourceGroupFormFilterAdapter;
import org.helianto.resource.form.CompositeResourceForm;
import org.helianto.resource.form.ResourceGroupForm;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Mauricio Fernandes de Castro
 */
public class ResourceGroupFormFilterAdapterTests {

    public static String OB = "order by alias.resourceCode ";
    public static String C0 = "alias.entity.id = 1 ";
    public static String C1 = "alias.class = 'R' AND ";
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
        form.setType('R');
        assertEquals(C1+C0+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void select() {
    	((CompositeResourceForm) form).setResourceCode("CODE");
        assertEquals(C0+C2, filter.createCriteriaAsString());
    }
    
    @Test
    public void resourceName() {
    	((CompositeResourceForm) form).setResourceName("NAME");
        assertEquals(C0+C3+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void resourceType() {
    	((CompositeResourceForm) form).setResourceType(ResourceType.FIXTURE.getValue());
        assertEquals(C0+C4+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void parent() {
    	ResourceGroup parent = new ResourceGroup(form.getEntity(), "PARENT");
    	parent.setId(1);
    	form.setParent(parent);
        assertEquals("select alias from ResourceGroup alias inner join alias.parentAssociations as parentAssociation ", filter.createSelectAsString());
        assertEquals(C0+C5+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void child() {
    	ResourceGroup child = new ResourceGroup(form.getEntity(), "CHILD");
    	child.setId(1);
    	((CompositeResourceForm) form).setChild(child);
        assertEquals("select alias from ResourceGroup alias inner join alias.childAssociations as childAssociation ", filter.createSelectAsString());
        assertEquals(C0+C6+OB, filter.createCriteriaAsString());
    }
    
    private ResourceGroupFormFilterAdapter filter;
    private ResourceGroupForm form;
    
    @Before
    public void setUp() {
    	Entity entity = EntityTestSupport.createEntity(1);
    	form = new CompositeResourceForm(entity);
    	filter = new ResourceGroupFormFilterAdapter(form);
    }
}

