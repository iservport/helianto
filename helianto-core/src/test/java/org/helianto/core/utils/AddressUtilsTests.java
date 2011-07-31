package org.helianto.core.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.helianto.core.City;
import org.helianto.core.Operator;
import org.helianto.core.Province;
import org.helianto.core.base.AbstractAddress;
import org.helianto.core.utils.AddressUtils;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class AddressUtilsTests {
	
	@Test
	public void create0() {
		AbstractAddress address = AddressUtils.createAddress(province);
		assertSame(province, address.getProvince());
		assertEquals("PRV", address.getProvinceCode());
		assertEquals("NAME", address.getProvinceName());
		assertEquals("", address.getCityName());
		assertEquals("", address.getAddress1());
		assertEquals("", address.getAddressNumber());
		assertEquals("", address.getAddressDetail());
		assertEquals("", address.getAddress2());
	}
	
	@Test
	public void city() {
		City city = new City("CITY", "CITY NAME", province);
		AbstractAddress address = AddressUtils.createAddress(city);
		assertSame(province, address.getParentProvince());
		assertSame(city, address.getProvince());
		assertSame(city, address.getCity());
		assertEquals("PRV", address.getProvinceCode());
		assertEquals("NAME", address.getProvinceName());
		assertEquals("CITY", address.getCityCode());
		assertEquals("CITY NAME", address.getCityName());
	}
	
	@Test
	public void create1() {
		AbstractAddress address = AddressUtils.createAddress(province, "STREET");
		assertSame(province, address.getProvince());
		assertEquals("PRV", address.getProvinceCode());
		assertEquals("NAME", address.getProvinceName());
		assertEquals("STREET", address.getAddress1());
		assertEquals("", address.getAddressNumber());
		assertEquals("", address.getAddressDetail());
		assertEquals("", address.getAddress2());
	}
	
	@Test
	public void create2() {
		AbstractAddress address = AddressUtils.createAddress(province, "STREET", "NR. 123");
		assertSame(province, address.getProvince());
		assertEquals("PRV", address.getProvinceCode());
		assertEquals("NAME", address.getProvinceName());
		assertEquals("STREET", address.getAddress1());
		assertEquals("NR. 123", address.getAddressNumber());
		assertEquals("", address.getAddressDetail());
		assertEquals("", address.getAddress2());
	}
	
	@Test
	public void create3() {
		AbstractAddress address = AddressUtils.createAddress(province, "STREET", "NR. 123", "COUNTY");
		assertSame(province, address.getProvince());
		assertEquals("PRV", address.getProvinceCode());
		assertEquals("NAME", address.getProvinceName());
		assertEquals("STREET", address.getAddress1());
		assertEquals("NR. 123", address.getAddressNumber());
		assertEquals("", address.getAddressDetail());
		assertEquals("COUNTY", address.getAddress2());
	}
	
	@Test
	public void create4() {
		AbstractAddress address = AddressUtils.createAddress(province, "STREET", "NR. 123", "ROOM 2", "COUNTY");
		assertSame(province, address.getProvince());
		assertEquals("PRV", address.getProvinceCode());
		assertEquals("NAME", address.getProvinceName());
		assertEquals("STREET", address.getAddress1());
		assertEquals("NR. 123", address.getAddressNumber());
		assertEquals("ROOM 2", address.getAddressDetail());
		assertEquals("COUNTY", address.getAddress2());
	}
	
	//
	
	private Province province;
	
	@Before
	public void setUp() {
		province = new Province(new Operator("DEFAULT"), "PRV", "NAME");
	}

}
