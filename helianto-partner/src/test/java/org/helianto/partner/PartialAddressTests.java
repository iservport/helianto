package org.helianto.partner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.helianto.core.City;
import org.helianto.core.Operator;
import org.helianto.core.Province;
import org.helianto.partner.base.AbstractPartialAddress;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class PartialAddressTests {
	
	private AbstractPartialAddress address;

	@Test
	public void nullProvince() {
		assertNull(address.getProvince());
		assertEquals("", address.getProvinceCode());
		assertEquals("", address.getProvinceName());
		assertNull(address.getCity());
		assertNull(address.getParentProvince());
		assertEquals("", address.getCityName());
	}
	
	@Test
	public void hasProvince() {
		Operator operator = new Operator("DEFAULT");
		Province province = new Province(operator , "PROVINCECODE", "PROVINCENAME");
		address.setProvince(province);
		assertSame(province, address.getProvince());
		assertEquals("PROVINCECODE", address.getProvinceCode());
		assertEquals("PROVINCENAME", address.getProvinceName());
		assertNull(address.getCity());
		assertNull(address.getParentProvince());
		assertEquals("", address.getCityName());
	}
	
	@Test
	public void hasCity() {
		Operator operator = new Operator("DEFAULT");
		Province province = new Province(operator , "PROVINCECODE", "PROVINCENAME");
		City city = new City("CITYCODE", "CITYNAME", province);
		address.setProvince(city);
		assertSame(city, address.getProvince());
		assertSame(city, address.getCity());
		assertEquals("PROVINCECODE", address.getProvinceCode());
		assertEquals("PROVINCENAME", address.getProvinceName());
		assertSame(province, address.getParentProvince());
		assertEquals("CITYNAME", address.getCityName());
	}
	
	@SuppressWarnings("serial")
	@Before
	public void setUp() {
		address = new AbstractPartialAddress() { };
	}

}
