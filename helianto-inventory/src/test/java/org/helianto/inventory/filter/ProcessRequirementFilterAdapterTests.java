package org.helianto.inventory.filter;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.helianto.inventory.form.ProcessRequirementForm;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class ProcessRequirementFilterAdapterTests {

    public static String C1 = "alias.entity.id = 1 ";
    public static String C2 = "AND alias.internalNumber = 9223372036854775807 ";
    public static String C3 = "AND alias.requirementDate >= '1969-12-31 21:00:01' ";
    public static String C4 = "AND alias.requirementDate < '1969-12-31 21:00:02' ";
    public static String C5 = "AND alias.requirementDate >= '1969-12-31 21:00:01' AND alias.requirementDate < '1969-12-31 21:00:02' ";
    public static String O1 = "order by alias.requirementDate ";

    @Test
    public void empty() {
    	Mockito.when(form.getToDate()).thenReturn(null);
        assertEquals(C1+O1, filter.createCriteriaAsString());
    }
    
    @Test
    public void select() {
    	Mockito.when(form.getInternalNumber()).thenReturn(Long.MAX_VALUE);
        assertEquals(C1+C2, filter.createCriteriaAsString());
    }
    
    @Test
    public void from() {
        Mockito.when(form.getFromDate()).thenReturn(new Date(1000));
        assertEquals(C1+C3+O1, filter.createCriteriaAsString());
    }
    
    @Test
    public void to() {
        Mockito.when(form.getToDate()).thenReturn(new Date(2000));
        assertEquals(C1+C4+O1, filter.createCriteriaAsString());
    }
    
    @Test
    public void both() {
        Mockito.when(form.getFromDate()).thenReturn(new Date(1000));
        Mockito.when(form.getToDate()).thenReturn(new Date(2000));
        assertEquals(C1+C5+O1, filter.createCriteriaAsString());
    }
    
    private ProcessRequirementFilterAdapter filter;
    private ProcessRequirementForm form;
    
    @Before
    public void setUp() {
    	form = Mockito.mock(ProcessRequirementForm.class);
    	filter = new ProcessRequirementFilterAdapter(form);
        Mockito.when(form.getEntityId()).thenReturn(1);
    }
    
    @After
    public void tearDown() {
    	Mockito.reset(form);
    }
    
}
