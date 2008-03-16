package org.helianto.core;

import junit.framework.TestCase;

import org.helianto.core.Entity;
import org.helianto.core.test.DomainTestSupport;

import org.helianto.core.Category;

/**
 * <code>Category</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class CategoryTests extends TestCase {
    
    /**
     * Test <code>Category</code> static factory method.
     */
    public void testCategoryFactory() {
        Entity entity = new Entity();
        String categoryCode = DomainTestSupport.STRING_TEST_VALUE;
        
        Category category = Category.categoryFactory(entity, CategoryGroup.INSTRUMENT, categoryCode);
        
        assertSame(entity, category.getEntity());
        assertEquals(CategoryGroup.INSTRUMENT.getValue(), category.getCategoryGroup());
        assertEquals(categoryCode, category.getCategoryCode());
        
    }
    /**
     * Test <code>Category</code> equals() method.
     */
    public void testCategoryEquals() {
        Entity entity = new Entity();
        CategoryGroup categoryGroup = CategoryGroup.INSTRUMENT;
        String categoryCode = DomainTestSupport.STRING_TEST_VALUE;
        
        Category category = Category.categoryFactory(entity, CategoryGroup.INSTRUMENT, categoryCode);
        Category copy = (Category) DomainTestSupport.minimalEqualsTest(category);
        
        copy.setEntity(null);
        copy.setCategoryGroup(-1);
        copy.setCategoryCode(categoryCode);
        assertFalse(category.equals(copy));

        copy.setEntity(entity);
        copy.setCategoryGroup(categoryGroup);
        copy.setCategoryCode(null);
        assertFalse(category.equals(copy));

        copy.setEntity(entity);
        copy.setCategoryGroup(-1);
        copy.setCategoryCode(categoryCode);
        assertFalse(category.equals(copy));

        copy.setEntity(entity);
        copy.setCategoryGroup(categoryGroup);
        copy.setCategoryCode(categoryCode);

        assertTrue(category.equals(copy));
    }

}
    
    
