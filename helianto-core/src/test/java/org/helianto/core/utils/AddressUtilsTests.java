package org.helianto.core.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.helianto.core.domain.City;
import org.helianto.core.domain.Operator;
import org.helianto.core.domain.State;
import org.helianto.core.internal.AbstractAddress;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class AddressUtilsTests {
	
	@Test
	public void city() {
		AbstractAddress address = AddressUtils.createAddress(city);
		assertSame(state, address.getState());
		assertSame(city, address.getCity());
		assertEquals("ST", address.getStateCode());
		assertEquals("STATE", address.getStateName());
		assertEquals("1234", address.getCityCode());
		assertEquals("CITYNAME", address.getCityName());
	}
	
	@Test
	public void create1() {
		AbstractAddress address = AddressUtils.createAddress(city, "STREET");
		assertSame(state, address.getState());
		assertSame(city, address.getCity());
		assertEquals("ST", address.getStateCode());
		assertEquals("STATE", address.getStateName());
		assertEquals("1234", address.getCityCode());
		assertEquals("CITYNAME", address.getCityName());
		assertEquals("STREET", address.getAddress1());
		assertEquals("", address.getAddressNumber());
		assertEquals("", address.getAddressDetail());
		assertEquals("", address.getAddress2());
	}
	
	@Test
	public void create2() {
		AbstractAddress address = AddressUtils.createAddress(city, "STREET", "NR. 123");
		assertEquals("STREET", address.getAddress1());
		assertEquals("NR. 123", address.getAddressNumber());
		assertEquals("", address.getAddressDetail());
		assertEquals("", address.getAddress2());
	}
	
	@Test
	public void create3() {
		AbstractAddress address = AddressUtils.createAddress(city, "STREET", "NR. 123", "COUNTY");
		assertEquals("STREET", address.getAddress1());
		assertEquals("NR. 123", address.getAddressNumber());
		assertEquals("", address.getAddressDetail());
		assertEquals("COUNTY", address.getAddress2());
	}
	
	@Test
	public void create4() {
		AbstractAddress address = AddressUtils.createAddress(city, "STREET", "NR. 123", "ROOM 2", "COUNTY");
		assertEquals("STREET", address.getAddress1());
		assertEquals("NR. 123", address.getAddressNumber());
		assertEquals("ROOM 2", address.getAddressDetail());
		assertEquals("COUNTY", address.getAddress2());
	}
	
	//
	
	private State state;
	private City city;
	
	@Before
	public void setUp() {
		state = new State(new Operator("DEFAULT"), "ST", "STATE");
		city = new City(state, "1234", "CITYNAME");
	}

}
