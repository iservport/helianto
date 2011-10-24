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

package org.helianto.core.filter;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.helianto.core.Controllable;
import org.helianto.core.Entity;
import org.helianto.core.Operator;
import org.helianto.core.filter.base.AbstractControlFilterAdapter;
import org.helianto.core.filter.form.AbstractSequenceableForm;
import org.junit.Before;
import org.junit.Test;


/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ControlFilterAdapterTests {
	
	@Test
	public void empty() {
		controlFilter.getForm().setEntity(null);
		assertEquals("", controlFilter.createCriteriaAsString());
	}
	
	@Test
	public void selection() {
		controlFilter.getForm().setInternalNumber(1);
		assertEquals("alias.entity.id = 1 AND alias.internalNumber = 1 ", controlFilter.createCriteriaAsString());
	}
	
	@Test
	public void resolution() {
		resolution = 'P';
		assertEquals("alias.entity.id = 1 AND alias.resolution = 'P' ", controlFilter.createCriteriaAsString());
		resolution = 'N';
		assertEquals("alias.entity.id = 1 AND alias.resolution IN  ('P', 'T') ", controlFilter.createCriteriaAsString());
	}
	
	@Test
	public void toNextCheckDate() {
		nextCheckDate = new Date(1000l);
		assertEquals("alias.entity.id = 1 AND (alias.nextCheckDate >= '1969-12-24 23:59:59' AND alias.nextCheckDate < '1969-12-31 21:00:01' ) ", controlFilter.createCriteriaAsString());
	}
	
	@Test
	public void complete() {
		complete = 10;
		assertEquals("alias.entity.id = 1 AND alias.complete = 10 ", controlFilter.createCriteriaAsString());
	}
	
	private AbstractControlFilterAdapter<ControlStub> controlFilter;
	private Entity entity;
	private char resolution = ' ';
	private Date nextCheckDate;
	private int complete = -1;
	
	@SuppressWarnings("serial")
	@Before
	public void setUp() {
		entity = new Entity(new Operator("DEFAULT"), "ENTITY");
		entity.setId(1);
		controlFilter = new AbstractControlFilterAdapter<ControlStub>(new ControlStub()) { };
		controlFilter.getForm().setEntity(entity);
	}
	
	@SuppressWarnings("serial")
	private class ControlStub extends AbstractSequenceableForm implements Controllable {
		public String getInternalNumberKey() { return "KEY"; }
		public char getResolution() { return resolution; }
		public Date getNextCheckDate() { return nextCheckDate; }
		public int getComplete() { return complete; }
		public void reset() { }
	}
	
}
