package org.helianto.resource.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.core.domain.Entity;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.resource.def.ResourceType;
import org.helianto.resource.domain.ResourceGroup;
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
//    public static String C3 = "AND lower(alias.resourceName) like '%name%' ";
    public static String C4 = "AND alias.resourceType = 'F' ";
    public static String C5 = "AND alias.resourceGroup.id = 1 ";
    public static String C6 = "AND (" +
  		"(lower(alias.resourceCode) like '%name%' ) OR " +
  		"(lower(alias.resourceName) like '%name%' ) " +
  		") ";
    public static String C7 = "AND (" +
  		"(lower(alias.resourceCode) like '%name%' OR lower(alias.resourceCode) like '%other%' ) OR " +
  		"(lower(alias.resourceName) like '%name%' OR lower(alias.resourceName) like '%other%' ) " +
  		") ";

    @Test
    public void empty() {
        assertEquals(C0+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void type() {
        form.setType('R');
        assertEquals(C1+C0+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void select() {
    	((CompositeResourceForm) form).setResourceCode("CODE");
        assertEquals(C0+C2, filter.createCriteriaAsString());
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
    	((CompositeResourceForm) form).setResourceGroup(parent);
        assertEquals(C0+C5+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void searchName() {
    	((CompositeResourceForm) form).setSearchMode('R');
    	((CompositeResourceForm) form).setSearchString("NAME");
        assertEquals(C0+C6+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void searchNames() {
    	((CompositeResourceForm) form).setSearchMode('R');
    	((CompositeResourceForm) form).setSearchString("NAME OTHER");
        assertEquals(C0+C7+OB, filter.createCriteriaAsString());
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

