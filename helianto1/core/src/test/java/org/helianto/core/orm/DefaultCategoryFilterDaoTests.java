package org.helianto.core.orm;

import static org.junit.Assert.assertEquals;

import org.helianto.core.CategoryFilter;
import org.helianto.core.CategoryGroup;
import org.helianto.core.test.UserTestSupport;
import org.junit.Before;
import org.junit.Test;
/**
 * <code>CategoryDao</code> tests.
 *
 * @author Mauricio Fernandes de Castro
 */
public class DefaultCategoryFilterDaoTests {

    public static String C1 = "category.entity.id = 0 AND category.categoryGroup = 0 ";
    public static String C2 = "AND category.categoryCode = 'CODE' ";
    public static String C3 = "AND category.categoryNameLike like '%NAME_LIKE%' ";

    @Test
    public void testSelect() {
        assertEquals(C1, categoryDao.createCriteriaAsString(filter, false));
    }
    
    @Test
    public void testCreateCriteriaAsStringCategoryCode() {
        filter.setCategoryCode("CODE");
        assertEquals(C1+C2, categoryDao.createCriteriaAsString(filter, false));
    }
    
    @Test
    public void testCreateCriteriaAsStringCategoryNameLike() {
        filter.setCategoryNameLike("NAME_LIKE");
        assertEquals(C1+C3, categoryDao.createCriteriaAsString(filter, false));
    }
    
    private DefaultCategoryDao categoryDao;
    private CategoryFilter filter;
    
    @Before
    public void setUp() {
    	filter = CategoryFilter.categoryFilterFactory(UserTestSupport.createUser());
    	filter.setCategoryGroup(CategoryGroup.UNIT);
    	categoryDao = new DefaultCategoryDao();
    }
}

