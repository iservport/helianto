package org.helianto.core.filter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.helianto.core.Category;
import org.helianto.core.CategoryGroup;
import org.helianto.core.Entity;
import org.helianto.core.Operator;
import org.helianto.core.Unit;
import org.helianto.core.test.CategoryTestSupport;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Mauricio Fernandes de Castro
 */
public class UnitFilterAdapterTests {
	
	@Test
	public void constructor() {
		assertSame(entity, filter.getEntity());
		
		Entity newEntity = new Entity();
		filter = new UnitFilterAdapter(newEntity, "CODE");
		assertSame(newEntity, filter.getEntity());
		assertEquals("CODE", filter.getForm().getUnitCode());
	}

    public static String C1 = "alias.entity.id = 0 ";
    public static String C2 = "AND alias.category.categoryGroup = 0 ";
    public static String C3 = "AND alias.unitCode = 'CODE' ";
    public static String C4 = "AND alias.category.id = 1 ";
    public static String C5 = "AND lower(alias.unitName) like '%name%' ";

    @Test
    public void empty() {
        assertEquals(C1, filter.createCriteriaAsString());
    }
    
    @Test
    public void select() {
    	form.setUnitCode("CODE");
        assertEquals(C1+C3, filter.createCriteriaAsString());
    }
    
    @Test
    public void filterCategoryGroup() {
    	filter.setCategoryGroup(CategoryGroup.UNIT);
        assertEquals(C1+C2, filter.createCriteriaAsString());
    }
    
    @Test
    public void filterCategory() {
    	Category category = CategoryTestSupport.createCategory();
    	category.setId(1);
    	form.setCategory(category);
        assertEquals(C1+C4, filter.createCriteriaAsString());
    }
    
    @Test
    public void filterUnitName() {
        form.setUnitName("NAME");
        assertEquals(C1+C5, filter.createCriteriaAsString());
    }
    
    private UnitFilterAdapter filter;
    private Unit form;
    private Entity entity;
    
    @Before
    public void setUp() {
    	entity = new Entity(new Operator("DEFAULT"), "ALIAS");
    	form = new Unit(entity, "");
    	filter = new UnitFilterAdapter(form);
    }
}

