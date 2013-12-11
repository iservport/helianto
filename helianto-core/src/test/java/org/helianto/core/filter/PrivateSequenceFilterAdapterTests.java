package org.helianto.core.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.core.form.PrivateSequenceForm;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class PrivateSequenceFilterAdapterTests {
	
	String OB = "order by alias.typeName ";
	String C0 = "alias.entity.id = 1 ";
	String C1 = "AND alias.typeName = 'ABCD' ";
	String C2 = "AND ((lower(alias.typeName) like '%teste%' ) ) ";
	
	@Test
	public void empty() {
		assertEquals(C0+OB, filter.createCriteriaAsString());
	}
	
	@Test
	public void select() {
		Mockito.when(form.getTypeName()).thenReturn("ABCD");
		assertEquals(C0+C1, filter.createCriteriaAsString());
	}
	
	@Test
	public void search() {
		Mockito.when(form.getSearchString()).thenReturn("TESTE");
		assertEquals(C0+C2+OB, filter.createCriteriaAsString());
	}
	
	// locals
	
	private PrivateSequenceForm form;
	private PrivateSequenceFilterAdapter filter;
	
	@Before
	public void setUp() {
		form = Mockito.mock(PrivateSequenceForm.class);
		filter = new PrivateSequenceFilterAdapter(form);
		Mockito.when(form.getEntityId()).thenReturn(1);
	}
	
	@After
	public void tearDown() {
		Mockito.reset(form);
	}

}
