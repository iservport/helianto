package org.helianto.core.orm;

import static org.junit.Assert.assertEquals;

import org.helianto.core.Category;
import org.helianto.core.CategoryGroup;
import org.helianto.core.UnitFilter;
import org.helianto.core.test.CategoryTestSupport;
import org.helianto.core.test.UserTestSupport;
import org.junit.Before;
import org.junit.Test;
/**
 * <code>UnitDao</code> tests.
 *
 * @author Mauricio Fernandes de Castro
 */
public class DefaultUnitFilterDaoTests {

    public static String C1 = "unit.entity.id = 0 ";
    public static String C2 = "AND unit.category.categoryGroup = 2 ";
    public static String C3 = "AND unit.unitCode = 'CODE' ";
    public static String C4 = "AND unit.category.id = 1 ";
    public static String C5 = "AND unit.unitNameLike like '%NAME%' ";

    @Test
    public void testEmpty() {
        assertEquals(C1, unitDao.createCriteriaAsString(filter, false));
    }
    
    @Test
    public void testPreProcess() {
    	filter.setCategoryGroup(CategoryGroup.STOCK);
        assertEquals(C1+C2, unitDao.createCriteriaAsString(filter, false));
    }
    
    @Test
    public void testSelect() {
    	filter.setUnitCode("CODE");
        assertEquals(C1+C3, unitDao.createCriteriaAsString(filter, false));
    }
    
    @Test
    public void testFilterCategory() {
    	Category category = CategoryTestSupport.createCategory();
    	category.setId(1);
        filter.setCategory(category);
        assertEquals(C1+C4, unitDao.createCriteriaAsString(filter, false));
    }
    
    @Test
    public void testFilterUnitName() {
        filter.setUnitNameLike("NAME");
        assertEquals(C1+C5, unitDao.createCriteriaAsString(filter, false));
    }
    
    private DefaultUnitDao unitDao;
    private UnitFilter filter;
    
    @Before
    public void setUp() {
    	filter = UnitFilter.unitFilterFactory(UserTestSupport.createUser());
    	unitDao = new DefaultUnitDao();
    }
}

