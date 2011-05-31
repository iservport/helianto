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

package org.helianto.document.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.core.Entity;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.document.Serializer;
import org.junit.Before;
import org.junit.Test;


/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class SerializerFilterAdapterTests {
	
	@Test
	public void empty() {
		assertEquals("alias.entity.id = 0 ", filter.createCriteriaAsString());
	}
	
	@Test
	public void selection() {
		filter.getForm().setBuilderCode("CODE");
		assertEquals("alias.entity.id = 0 AND alias.builderCode = 'CODE' ", filter.createCriteriaAsString());
	}
	
	@Test
	public void contentType() {
		filter.getForm().setContentType('X');
		assertEquals("alias.entity.id = 0 AND alias.contentType = 'X' ", filter.createCriteriaAsString());
	}
	
	private SerializerFilterAdapter<Serializer> filter;
	private Serializer target;
	
	@Before
	public void setUp() {
		Entity entity = EntityTestSupport.createEntity();
		target = new Serializer(entity, "");
		filter = new SerializerFilterAdapter<Serializer>(target);
		// clean filter
//		repeatableFilter.getForm().setTrackingMode(' ');
	}
	
}
