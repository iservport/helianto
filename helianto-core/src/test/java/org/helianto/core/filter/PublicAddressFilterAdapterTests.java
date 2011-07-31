package org.helianto.core.filter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.helianto.core.Operator;
import org.helianto.core.Province;
import org.helianto.core.PublicAddress;
import org.helianto.core.filter.PublicAddressFilterAdapter;
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
	public void constructor() {
		Operator operator = new Operator("TEST");
		filter = new PublicAddressFilterAdapter(operator, "POSTALCODE");
		assertSame(operator, filter.getForm().getOperator());
		assertEquals("POSTALCODE", filter.getForm().getPostalCode());
	}
	
	@Test
	public void empty() {
		assertEquals(C1+ORDER, filter.createCriteriaAsString());
	}
	
	@Test
	public void selection() {
		target.setPostalCode("POSTALCODE");
		assertEquals(C1+C2, filter.createCriteriaAsString());
	}
	
	@Test
	public void province() {
		Province province = new Province(target.getOperator(), "");
		province.setId(1);
		target.setProvince(province);
		assertEquals(C1+C3+ORDER, filter.createCriteriaAsString());
	}
	
	@Test
	public void provinceCode() {
		filter.setProvinceCode("PROVINCECODE");
		assertEquals(C1+C4+ORDER, filter.createCriteriaAsString());
	}
	
	@Test
	public void addressLike() {
		filter.setAddressLike("ADDRESS");
		assertEquals(C1+C5+ORDER, filter.createCriteriaAsString());
	}
	
	private PublicAddressFilterAdapter filter;
	private PublicAddress target;
	
	@Before
	public void setUp() {
		Operator operator = new Operator("DEFAULT");
		operator.setId(1);
		target = new PublicAddress(operator, "");
		filter = new PublicAddressFilterAdapter(target);
	}
}
