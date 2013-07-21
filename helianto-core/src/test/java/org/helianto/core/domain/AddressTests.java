/* Copyright 2005 I Serv Consultoria Empresarial Ltda.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.helianto.core.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;

import org.helianto.core.base.AbstractAddress;
import org.helianto.core.domain.City;
import org.helianto.core.domain.Operator;
import org.helianto.core.domain.Province;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Mauricio Fernandes de Castro
 */
public class AddressTests {
	
	private AbstractAddress address;
	
	@Test
	public void constructor() {
		assertTrue(address instanceof Serializable);
		assertEquals("", address.getAddress1());
		assertEquals("", address.getAddressNumber());
		assertEquals("", address.getAddressDetail());
		assertEquals("", address.getAddress2());
		assertEquals("", address.getAddress3());
		assertEquals("", address.getCityName());
		assertEquals("", address.getPostalCode());
		assertEquals("", address.getPostOfficeBox());
	}

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
		address = new AbstractAddress() { };
	}

}
