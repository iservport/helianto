package org.helianto.core.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.core.form.ContextForm;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * @author Mauricio Fernandes de Castro
 */
public class ContextFilterAdapterTests {

    public static String C1 = "alias.operatorName = 'NAME' ";
    public static String C2 = "lower(alias.operatorName) like '%name%' ";
    
    @Test
    public void empty() {
        assertEquals("", filter.createCriteriaAsString());
    }
    
    @Test
    public void select() {
    	Mockito.when(form.getContextName()).thenReturn("NAME");
        assertEquals(C1, filter.createCriteriaAsString());
    }
    
    private ContextFilterAdapter filter;
    private ContextForm form;
    
    @Before
    public void setUp() {
    	form = Mockito.mock(ContextForm.class);
    	filter = new ContextFilterAdapter(form);
    }
    
    @After
    public void tearDown() {
    	Mockito.reset(form);
    }
}

