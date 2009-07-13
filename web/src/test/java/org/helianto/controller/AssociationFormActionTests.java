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

package org.helianto.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.helianto.core.AbstractAssociation;
import org.junit.Before;
import org.junit.Test;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;
import org.springframework.webflow.test.MockRequestContext;


/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class AssociationFormActionTests {
	
	@Test
	public void testPushTarget() {
		StubAssociation target = new StubAssociation("P", "C");
		context.getFlowScope().put("target", target);

		assertEquals("success", associationFormAction.pushTarget(context).getId());

		assertSame(target, context.getFlowScope().get("target"));
		assertEquals("P", context.getFlowScope().get("parent"));
		assertEquals("C", context.getFlowScope().get("child"));
	}
	
	@Test
	public void testPopTargetEmpty() {
		StubAssociation target = new StubAssociation("P", "C");
		context.getFlowScope().put("target", target);

		assertEquals("success", associationFormAction.popTarget(context).getId());
		
		assertNull(context.getFlowScope().get("target"));
		assertEquals("P", context.getFlowScope().get("parent"));
		assertNull(context.getFlowScope().get("child"));
	}
	
	@Test
	public void testPopTargetOnce() {
		StubAssociation target = new StubAssociation("P", "C");
		context.getFlowScope().put("target", target);
		
		assertEquals("success", associationFormAction.pushTarget(context).getId());
		assertEquals(target, context.getFlowScope().get("target"));
		assertEquals("P", context.getFlowScope().get("parent"));
		assertEquals("C", context.getFlowScope().get("child"));

		assertEquals("success", associationFormAction.popTarget(context).getId());
		assertNull(context.getFlowScope().get("target"));
		assertEquals("P", context.getFlowScope().get("parent"));
		assertNull(context.getFlowScope().get("child"));
	}
	
	@Test
	public void testPopTargetTwice() {
		StubAssociation target = new StubAssociation("P", "C");
		context.getFlowScope().put("target", target);
		
		assertEquals("success", associationFormAction.pushTarget(context).getId());
		assertEquals(target, context.getFlowScope().get("target"));
		assertEquals("P", context.getFlowScope().get("parent"));
		assertEquals("C", context.getFlowScope().get("child"));

		StubAssociation target1 = new StubAssociation("P1", "C1");
		context.getFlowScope().put("target", target1);
		
		assertEquals("success", associationFormAction.pushTarget(context).getId());
		assertEquals(target1, context.getFlowScope().get("target"));
		assertEquals("P1", context.getFlowScope().get("parent"));
		assertEquals("C1", context.getFlowScope().get("child"));

		assertEquals("success", associationFormAction.popTarget(context).getId());
		assertSame(target, context.getFlowScope().get("target"));
		assertEquals("P", context.getFlowScope().get("parent"));
		assertEquals("C", context.getFlowScope().get("child"));
	}
	
	private AbstractAssociationFormAction<StubAssociation, String, String> associationFormAction;
	private MockRequestContext context;
	
	@Before
	public void setUp() {
		associationFormAction = new AbstractAssociationFormAction<StubAssociation, String, String>() {
			@Override public StubAssociation doCreateTarget(RequestContext context, String parent) 
			    throws Exception { return new StubAssociation("P", "C"); }
			@Override protected StubAssociation doStoreTarget(StubAssociation detachedTarget) 
			    throws Exception { return new StubAssociation("P", "C"); }
			@Override public String getTargetAttributeName() { return "target"; }
			@Override public String getParentAttributeName() { return "parent"; }
			@Override public String getChildAttributeName()  { return "child"; }
		};
		context = new MockRequestContext();
	}
	
	/**
	 * Stub association
	 */
	public class StubAssociation extends AbstractAssociation<String, String> {
		private static final long serialVersionUID = 1L;
		public StubAssociation(String p, String c) {
			super.setParent(p);
			super.setChild(c);
		}
		public String getChild()  { return child; }
		public String getParent() { return parent; }
		public boolean isKeyEmpty() { return false; }
	}

}
