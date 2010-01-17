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

package org.helianto.partner;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author Mauricio Fernandes de Castro
 */
public class AbstractAddressTests {
	
	@Test
	public void constructor() {
		AbstractAddress abstractAddress = new AbstractAddress() {};
		assertEquals("", abstractAddress.getAddress1());
		assertEquals("", abstractAddress.getAddressNumber());
		assertEquals("", abstractAddress.getAddressDetail());
		assertEquals("", abstractAddress.getAddress2());
		assertEquals("", abstractAddress.getAddress3());
		assertEquals("", abstractAddress.getCityName());
		assertEquals("", abstractAddress.getPostalCode());
		assertEquals("", abstractAddress.getPostOfficeBox());
	}

}
