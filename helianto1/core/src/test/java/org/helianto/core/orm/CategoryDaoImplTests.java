package org.helianto.core.orm;

import java.util.List;

import org.helianto.core.Category;
import org.helianto.core.CategoryGroup;
import org.helianto.core.dao.CategoryDao;
import org.helianto.core.test.CategoryTestSupport;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * <code>CategoryDao</code> tests.
 *
 * @author Mauricio Fernandes de Castro
 */
public class CategoryDaoImplTests extends AbstractHibernateIntegrationTest {
    
    private CategoryDao categoryDao;
    
    /*
     * Hook to persist one <code>Category</code>.
     */  
    protected Category writeCategory(Category category) {
        categoryDao.persistCategory(category);
        categoryDao.flush();
        categoryDao.clear();
        return category;
    }
    
    protected Category writeCategory() {
        Category category = CategoryTestSupport.createCategory();
        writeCategory(category);
        return category;
    }
    
    /**
     * Find by natural id.
     */  
    public void testFindOneCategory() {
        Category category = CategoryTestSupport.createCategory();
        writeCategory(category);

        assertEquals(category,  categoryDao.findCategoryByNaturalId(category.getEntity(), CategoryGroup.NOT_DEFINED, category.getCategoryCode()));
    }
    
    /**
     * Find by criteria.
     */  
    public void testFindCategories() {
    	List<Category> categoryList = writeCategoryList();

        assertEquals(10,  categoryDao.findCategories("entity.id = "+categoryList.get(0).getEntity().getId()).size());
    }
    
    /*
     * Hook to persist a <code>Category</code> list.
     */  
    protected List<Category> writeCategoryList() {
        int categoryListSize = 10;
        int entityListSize = 2;
        List<Category> categoryList = CategoryTestSupport.createCategoryList(categoryListSize, entityListSize);
        assertEquals(categoryListSize * entityListSize, categoryList.size());
        for (Category category: categoryList) {
            categoryDao.persistCategory(category);
        }
        categoryDao.flush();
        categoryDao.clear();
        return categoryList;
    }
    
    /**
     * Find from a list.
     */  
    public void testFindListCategory() {
        List<Category> categoryList = writeCategoryList();

        Category category = categoryList.get((int) (Math.random()*categoryList.size()));
        assertEquals(category,  categoryDao.findCategoryByNaturalId(category.getEntity(), CategoryGroup.NOT_DEFINED, category.getCategoryCode()));
    }

    /**
     * Merge and duplicate.
     */  
    public void testCategoryDuplicate() {
        Category category =  writeCategory();
        Category categoryCopy = CategoryTestSupport.createCategory(category.getEntity(), CategoryGroup.NOT_DEFINED, category.getCategoryCode());

        try {
            categoryDao.mergeCategory(categoryCopy); fail();
        } catch (DataIntegrityViolationException e) { 
        } catch (Exception e) { fail(); }
    }
    
    /**
     * Remove.
     */  
    public void testRemoveCategory() {
        List<Category> categoryList = writeCategoryList();
        Category category = categoryList.get((int) (Math.random()*categoryList.size()));
        categoryDao.removeCategory(category);

        assertNull(categoryDao.findCategoryByNaturalId(category.getEntity(), CategoryGroup.NOT_DEFINED, category.getCategoryCode()));
    }

    //- setters

    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }
    
}

