package org.helianto.core.filter.internal;

import static org.junit.Assert.assertEquals;

import org.helianto.core.filter.InternalNumberForm;
import org.helianto.core.filter.base.AbstractInternalFilterAdapter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class InternalNumberFilterAdapterTests {

    public static String ORDER = "order by alias.fieldName ";
    public static String C1 = "alias.entity.id = 1 ";
    public static String C2 = "AND alias.internalNumber = 9223372036854775807 ";

	@Test
	public void empty() {
		assertEquals(C1, filter.createCriteriaAsString());
	}
	
	@Test
	public void key() {
		Mockito.when(form.getInternalNumber()).thenReturn(Long.MAX_VALUE);
		assertEquals(C1+C2, filter.createCriteriaAsString());
	}
	
	private AbstractInternalFilterAdapter<InternalNumberForm> filter;
	private InternalNumberForm form;
	
	@SuppressWarnings("serial")
	@Before
	public void setUp() {
		form = Mockito.mock(InternalNumberForm.class);
		filter = new AbstractInternalFilterAdapter<InternalNumberForm>(form) { };
		Mockito.when(form.getEntityId()).thenReturn(1);
    }
    
    @After
    public void tearDown() {
    	Mockito.reset(form);
    }
    
}
