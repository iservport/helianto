package org.helianto.core.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.core.domain.Operator;
import org.helianto.core.form.PublicAddressForm;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class PublicAddressFilterAdapterTests {

	String ORDER = "order by alias.postalCode ASC ";
	String C1 = "alias.operator.id = 1 ";
	String C2 = "AND alias.postalCode = 'POSTALCODE' ";
	String C3 = "AND alias.state.id = 10 ";
	String C4 = "AND alias.state.stateCode = 'ST' ";
	String C5 = "AND alias.city.id = 20 ";
	String C6 = "AND alias.city.cityCode = '123456' ";
	String C7 = "AND lower(alias.address1) like '%address%' ";
	
	@Test
	public void empty() {
		assertEquals(C1+ORDER, filter.createCriteriaAsString());
	}
	
	@Test
	public void selection() {
		Mockito.when(form.getPostalCode()).thenReturn("POSTALCODE");
		assertEquals(C1+C2, filter.createCriteriaAsString());
	}
	
	@Test
	public void state() {
		Mockito.when(form.getStateId()).thenReturn(10);
		assertEquals(C1+C3+ORDER, filter.createCriteriaAsString());
	}
	
	@Test
	public void stateCode() {
		Mockito.when(form.getStateCode()).thenReturn("ST");
		assertEquals(C1+C4+ORDER, filter.createCriteriaAsString());
	}
	
//	@Test
//	public void city() {
//		Mockito.when(form.getCityId()).thenReturn(10);
//		assertEquals(C1+C5+ORDER, filter.createCriteriaAsString());
//	}
	
	@Test
	public void cityCode() {
		Mockito.when(form.getCityCode()).thenReturn("123456");
		assertEquals(C1+C6+ORDER, filter.createCriteriaAsString());
	}
	
//	@Test
//	public void addressLike() {
//		((CompositeEntityForm) form).setAddressLike("ADDRESS");
//		assertEquals(C1+C5+ORDER, filter.createCriteriaAsString());
//	}
	
	private PublicAddressFilterAdapter filter;
	private PublicAddressForm form;
	
	@Before
	public void setUp() {
		Operator operator = new Operator("DEFAULT");
		operator.setId(1);
		form = Mockito.mock(PublicAddressForm.class);
		filter = new PublicAddressFilterAdapter(form);
		Mockito.when(form.getOperator()).thenReturn(operator);
	}
	
	@After
	public void tearDown() {
		Mockito.reset(form);
	}
	
}
