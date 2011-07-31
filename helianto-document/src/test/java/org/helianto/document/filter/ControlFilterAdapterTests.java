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

import java.util.Date;

import org.helianto.core.Entity;
import org.helianto.core.Operator;
import org.helianto.document.base.AbstractPrivateControl;
import org.junit.Before;
import org.junit.Test;


/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ControlFilterAdapterTests {
	
	private AbstractControlFilterAdapter<AbstractPrivateControl> controlFilter;
	private Entity entity;
	
	@Test
	public void empty() {
		controlFilter.getForm().setNextCheckDate(null);
		assertEquals("alias.entity.id = 1 ", controlFilter.createCriteriaAsString());
	}
	
	@Test
	public void selection() {
		controlFilter.getForm().setInternalNumber(1);
		assertEquals("alias.entity.id = 1 AND alias.internalNumber = 1 ", controlFilter.createCriteriaAsString());
	}
	
	@Test
	public void toNextCheckDate() {
		controlFilter.getForm().setNextCheckDate(new Date(1000l));
		assertEquals("alias.entity.id = 1 AND (alias.nextCheckDate >= '1969-12-24 23:59:59' AND alias.nextCheckDate < '1969-12-31 21:00:01' ) ", controlFilter.createCriteriaAsString());
	}
	
	@SuppressWarnings("serial")
	@Before
	public void setUp() {
		entity = new Entity(new Operator("DEFAULT"), "ENTITY");
		entity.setId(1);
		controlFilter = new AbstractControlFilterAdapter<AbstractPrivateControl>(new AbstractPrivateControl() {
			public String getInternalNumberKey() { return "KEY"; }
			public Entity getEntity() { return entity; }
			}) {
		};
	}
	
}
