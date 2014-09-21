package org.helianto.resource.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.resource.def.ResourceType;
import org.helianto.resource.form.ResourceGroupForm;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

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
    	Mockito.when(form.getType()).thenReturn('R');
        assertEquals(C1+C0+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void select() {
    	Mockito.when(form.getResourceCode()).thenReturn("CODE");
        assertEquals(C0+C2, filter.createCriteriaAsString());
    }
    
    @Test
    public void resourceType() {
    	Mockito.when(form.getResourceType()).thenReturn(ResourceType.FIXTURE.getValue());
        assertEquals(C0+C4+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void parent() {
    	Mockito.when(form.getResourceGroupId()).thenReturn(1);
        assertEquals(C0+C5+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void searchName() {
    	Mockito.when(form.getSearchMode()).thenReturn('R');
    	Mockito.when(form.getSearchString()).thenReturn("NAME");
        assertEquals(C0+C6+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void searchNames() {
    	Mockito.when(form.getSearchMode()).thenReturn('R');
    	Mockito.when(form.getSearchString()).thenReturn("NAME OTHER");
        assertEquals(C0+C7+OB, filter.createCriteriaAsString());
    }
    
    private ResourceGroupFormFilterAdapter filter;
    private ResourceGroupForm form;
    
    @Before
    public void setUp() {
    	form = Mockito.mock(ResourceGroupForm.class);
    	Mockito.when(form.getEntityId()).thenReturn(1);
    	filter = new ResourceGroupFormFilterAdapter(form);
    }
    
    @After
    public void tearDown() {
    	Mockito.reset(form);
    }
}

