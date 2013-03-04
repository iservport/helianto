package org.helianto.core.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.core.domain.Operator;
import org.helianto.core.domain.Province;
import org.helianto.core.form.CompositeEntityForm;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class PublicAddressFilterAdapterTests {

	String ORDER = "order by alias.province.provinceCode ASC ,  alias.address1 ASC  ";
	String C1 = "alias.operator.id = 1 ";
	String C2 = "AND alias.postalCode = 'POSTALCODE' ";
	String C3 = "AND alias.province.id = 1 ";
	String C4 = "AND alias.province.provinceCode = 'PROVINCECODE' ";
	String C5 = "AND lower(alias.address1) like '%address%' ";
	
	@Test
	public void empty() {
		assertEquals(C1+ORDER, filter.createCriteriaAsString());
	}
	
	@Test
	public void selection() {
		form.setPostalCode("POSTALCODE");
		assertEquals(C1+C2, filter.createCriteriaAsString());
	}
	
	@Test
	public void province() {
		Province province = new Province(form.getOperator(), "");
		province.setId(1);
		form.setProvince(province);
		assertEquals(C1+C3+ORDER, filter.createCriteriaAsString());
	}
	
	@Test
	public void provinceCode() {
		((CompositeEntityForm) form).setProvinceCode("PROVINCECODE");
		assertEquals(C1+C4+ORDER, filter.createCriteriaAsString());
	}
	
//	@Test
//	public void addressLike() {
//		((CompositeEntityForm) form).setAddressLike("ADDRESS");
//		assertEquals(C1+C5+ORDER, filter.createCriteriaAsString());
//	}
	
	private PublicAddressFormFilterAdapter filter;
	private CompositeEntityForm form;
	
	@Before
	public void setUp() {
		Operator operator = new Operator("DEFAULT");
		operator.setId(1);
		form = new CompositeEntityForm(operator);
		filter = new PublicAddressFormFilterAdapter(form);
	}
}
