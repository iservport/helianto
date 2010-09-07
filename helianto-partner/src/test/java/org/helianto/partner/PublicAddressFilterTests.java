package org.helianto.partner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.helianto.core.Operator;
import org.helianto.core.Province;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class PublicAddressFilterTests {

	String PREFIX = "publicaddress";
	String ORDER = "order by "+PREFIX+".province.provinceCode ASC ,  "+PREFIX+".address1 ASC  ";
	String C1 = PREFIX+".operator.id = 1 ";
	String C2 = "AND "+PREFIX+".postalCode = 'POSTALCODE' ";
	String C3 = "AND "+PREFIX+".province.id = 1 ";
	String C4 = "AND "+PREFIX+".province.provinceCode = 'PROVINCECODE' ";
	String C5 = "AND lower("+PREFIX+".address1) like '%address%' ";
	
	@Test
	public void constructor() {
		assertSame(operator, filter.getOperator());
		
		filter = new PublicAddressFilter(operator, "POSTALCODE");
		assertSame(operator, filter.getOperator());
		assertEquals("POSTALCODE", filter.getPostalCode());
	}
	
	@Test
	public void empty() {
		assertEquals(C1+ORDER, filter.createCriteriaAsString());
	}
	
	@Test
	public void selection() {
		filter.setPostalCode("POSTALCODE");
		assertEquals(C1+C2, filter.createCriteriaAsString());
	}
	
	@Test
	public void province() {
		Province province = new Province(operator);
		province.setId(1);
		filter.setProvince(province);
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
	
	private Operator operator;
	private PublicAddressFilter filter;
	
	@Before
	public void setUp() {
		operator = new Operator("DEFAULT");
		operator.setId(1);
		filter = new PublicAddressFilter(operator);
	}
}
