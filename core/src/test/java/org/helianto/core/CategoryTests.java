package org.helianto.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.helianto.core.test.DomainTestSupport;
import org.junit.Test;

/**
 * <code>Category</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class CategoryTests {
    
    /**
     * Test <code>Category</code> static factory method.
     */
	@Test
    public void categoryFactory() {
        Entity entity = new Entity();
        String categoryCode = DomainTestSupport.STRING_TEST_VALUE;
        
        Category category = Category.categoryFactory(entity, CategoryGroup.INSTRUMENT, categoryCode);
        
        assertSame(entity, category.getEntity());
        assertEquals(CategoryGroup.INSTRUMENT.getValue(), category.getCategoryGroup());
        assertEquals(categoryCode, category.getCategoryCode());
        assertEquals('1', category.getPriority());
        
    }
    /**
     * Test <code>Category</code> equals() method.
     */
	@Test
    public void categoryEquals() {
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
    
    
