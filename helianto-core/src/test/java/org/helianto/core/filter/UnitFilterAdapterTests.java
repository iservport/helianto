package org.helianto.core.filter;

import static org.junit.Assert.assertEquals;

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
    	target.setUnitCode("CODE");
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
    	target.setCategory(category);
        assertEquals(C1+C4, filter.createCriteriaAsString());
    }
    
    @Test
    public void filterUnitName() {
        target.setUnitName("NAME");
        assertEquals(C1+C5, filter.createCriteriaAsString());
    }
    
    private UnitFilterAdapter filter;
    private Unit target;
    
    @Before
    public void setUp() {
    	target = new Unit(new Entity(new Operator("DEFAULT"), "ALIAS"), "");
    	filter = new UnitFilterAdapter(target);
    }
}

