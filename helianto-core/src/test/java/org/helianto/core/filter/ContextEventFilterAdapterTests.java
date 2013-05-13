package org.helianto.core.filter;

/**
 * @author mauriciofernandesdecastro
 */
import static org.junit.Assert.assertEquals;

import org.helianto.core.domain.Operator;
import org.helianto.core.form.ContextEventForm;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class ContextEventFilterAdapterTests {

	private static String O0 = "order by alias.issueDate DESC ";
	private static String C1 = "alias.operator.id = 1 ";
	private static String C2 = "AND alias.publicNumber = 123 ";
	private static String C3 = "AND alias.resolution = 'X' ";

	@Test
	public void empty() {
    	Mockito.when(form.getOperator()).thenReturn(null);
		assertEquals(O0, filter.createCriteriaAsString());
	}
	
	@Test
	public void select() {
    	Mockito.when(form.getPublicNumber()).thenReturn(123l);
		assertEquals(C1+C2, filter.createCriteriaAsString());
	}
	
	@Test
	public void resolution() {
    	Mockito.when(form.getResolution()).thenReturn('X');
		assertEquals(C1+C3+O0, filter.createCriteriaAsString());
	}
	
    private ContextEventFilterAdapter filter;
    private ContextEventForm form;
    
    @Before
    public void setUp() {
    	Operator operator = new Operator("DEFAULT");
    	operator.setId(1);
    	form = Mockito.mock(ContextEventForm.class);
    	filter = new ContextEventFilterAdapter(form);
    	Mockito.when(form.getOperator()).thenReturn(operator);
    	
    }
    
    @After
    public void tearDown() {
    	Mockito.reset(form);
    }
    
}
