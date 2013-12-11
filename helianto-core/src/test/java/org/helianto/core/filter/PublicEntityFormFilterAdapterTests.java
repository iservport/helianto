package org.helianto.core.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.core.domain.Operator;
import org.helianto.core.form.PublicEntityForm;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * @author Mauricio Fernandes de Castro
 */
public class PublicEntityFormFilterAdapterTests {

    public static String OB = "order by alias.entity.alias ";
    public static String C1 = "alias.entity.operator.id = 1 ";
    public static String C2 = "alias.entity.id = 2 ";
    public static String C3 = "alias.entityAlias = 'ALIAS' ";
    public static String C4 = "alias.class = 'P' ";
    public static String C5 = "alias.class = 'R' ";
    public static String C6 = "lower(alias.entityName) like '%name%' ";

    @Test
    public void operator() {
    	Operator operator = new Operator("DEFAULT");
    	operator.setId(1);
    	Mockito.when(form.getOperator()).thenReturn(operator);
        assertEquals(C1+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void entity() {
    	Mockito.when(form.getEntityId()).thenReturn(2);
        assertEquals(C2+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void alias() {
    	Mockito.when(form.getEntityAlias()).thenReturn("ALIAS");
        assertEquals(C3+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void type() {
    	Mockito.when(form.getType()).thenReturn('P');
        assertEquals(C4+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void name() {
    	Mockito.when(form.getEntityName()).thenReturn("NAME");
        assertEquals(C6+OB, filter.createCriteriaAsString());
    }
    
    private PublicEntityFormFilterAdapter filter;
    private PublicEntityForm form;
    
 	@Before
    public void setUp() {
    	form = Mockito.mock(PublicEntityForm.class);
    	filter = new PublicEntityFormFilterAdapter(form);
    }
    
    @After
    public void tearDown() {
    	Mockito.reset(form);
    }

}

