package org.helianto.core.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.core.def.CategoryGroup;
import org.helianto.core.domain.Entity;
import org.helianto.core.form.UnitForm;
import org.helianto.core.test.EntityTestSupport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * @author Mauricio Fernandes de Castro
 */
public class UnitFilterAdapterTests {
	
    public static String O0 = "order by alias.unitCode ";
    public static String C1 = "alias.entity.id = 1 ";
    public static String C2 = "AND alias.category.categoryGroup = 'U' ";
    public static String C3 = "AND alias.unitCode = 'CODE' ";
    public static String C4 = "AND alias.unitSymbol = 'mm' ";
    public static String C5 = "AND lower(alias.nature) like '%X%' ";

    @Test
    public void empty() {
        assertEquals(C1+O0, filter.createCriteriaAsString());
    }
    
    @Test
    public void select() {
    	Mockito.when(form.getUnitCode()).thenReturn("CODE");
        assertEquals(C1+C3, filter.createCriteriaAsString());
    }
    
    @Test
    public void filterCategoryGroup() {
    	Mockito.when(form.getCategoryGroup()).thenReturn(CategoryGroup.UNIT.getValue());
        assertEquals(C1+C2+O0, filter.createCriteriaAsString());
    }
    
    @Test
    public void symbol() {
    	Mockito.when(form.getUnitSymbol()).thenReturn("mm");
        assertEquals(C1+C4+O0, filter.createCriteriaAsString());
    }
    
    @Test
    public void nature() {
    	Mockito.when(form.getNature()).thenReturn('X');
        assertEquals(C1+C5+O0, filter.createCriteriaAsString());
    }
    
    private UnitFilterAdapter filter;
    private UnitForm form;
    
    @Before
    public void setUp() {
    	Entity entity = EntityTestSupport.createEntity(1);
    	form = Mockito.mock(UnitForm.class);
    	filter = new UnitFilterAdapter(form);
    	Mockito.when(form.getEntity()).thenReturn(entity);
    }
    
    @After
    public void tearDown() {
    	Mockito.reset(form);
    }
}

