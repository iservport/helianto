package org.helianto.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;

import org.helianto.core.Category;
import org.helianto.core.CategoryGroup;
import org.helianto.core.UnitFilter;
import org.helianto.core.filter.UserBackedFilter;
import org.helianto.core.test.CategoryTestSupport;
import org.helianto.core.test.UserTestSupport;
import org.junit.Before;
import org.junit.Test;
/**
 * @author Mauricio Fernandes de Castro
 */
public class UnitFilterTests {

    @Test
	public void constructor() {
		assertTrue(filter instanceof Serializable);
		assertTrue(filter instanceof UserBackedFilter);
	}
	
    @Test
	public void factory() {
		assertSame(filter.getUser(), user);
	}
	
    @Test
	public void reset() {
    	filter.reset();
		assertEquals("", filter.getUnitCode());
		assertEquals("", filter.getUnitNameLike());
	}

    public static String C1 = "unit.entity.id = 0 ";
    public static String C2 = "AND unit.category.categoryGroup = 2 ";
    public static String C3 = "AND unit.unitCode = 'CODE' ";
    public static String C4 = "AND unit.category.id = 1 ";
    public static String C5 = "AND lower(unit.unitNameLike) like '%name%' ";

    @Test
    public void empty() {
        assertEquals(C1, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void preProcess() {
    	filter.setCategoryGroup(CategoryGroup.STOCK);
        assertEquals(C1+C2, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void select() {
    	filter.setUnitCode("CODE");
        assertEquals(C1+C3, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void filterCategory() {
    	Category category = CategoryTestSupport.createCategory();
    	category.setId(1);
        filter.setCategory(category);
        assertEquals(C1+C4, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void filterUnitName() {
        filter.setUnitNameLike("NAME");
        assertEquals(C1+C5, filter.createCriteriaAsString(false));
    }
    
    private UnitFilter filter;
    private User user;
    
    @Before
    public void setUp() {
    	user = UserTestSupport.createUser();
    	filter = UnitFilter.unitFilterFactory(user);
    }
}

