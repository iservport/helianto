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
import org.springframework.webflow.execution.RequestContext;
import org.springframework.webflow.test.MockRequestContext;


/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class AssociationFormActionTests {
	
	@Test
	public void pushTarget() {
		StubAssociation target = new StubAssociation("P", "C");
		context.getFlowScope().put("target", target);

		assertEquals("success", associationFormAction.pushTarget(context).getId());

		assertSame(target, associationFormAction.getInternalStack().peek());
	}
	
	@Test
	public void pushNullTarget() {
		context.getFlowScope().put("target", null);

		assertEquals("success", associationFormAction.pushTarget(context).getId());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void popTargetEmpty() {
		associationFormAction.popTarget(context);
	}
	
	@Test
	public void popTargetOnce() {
		formAction.createTarget(context);
		associationFormAction.createTarget(context);
		
		assertEquals("success", associationFormAction.pushTarget(context).getId());

		assertEquals("success", associationFormAction.popTarget(context).getId());
		assertEquals("P", context.getFlowScope().get("parent"));
		assertNull(context.getFlowScope().get("child"));
	}
	
	/**
	 * Simulate a navigation on 3 pages
	 */
	@Test
	public void popTargetTwice() {
		// start state: page 1
		// A is the current target for the "edit" presentation logic
		
		// user selects a sub-item from A: the AB association
		// AB becomes the current target for the "association" presentation logic 
		// B will replace A as current target for the "edit" presentation logic
		StubAssociation target = new StubAssociation("A", "B");
		context.getFlowScope().put("target", target);
		assertEquals("success", associationFormAction.pushTarget(context).getId());
		// success = page 2 displayed with B, AB is in scope and stack is AB

		// user selects a sub-item from B: the BC association
		// BC becomes the current target for the "association" presentation logic 
		// C will replace B as current target for the "edit" presentation logic
		StubAssociation target1 = new StubAssociation("B", "C");
		context.getFlowScope().put("target", target1);
		assertEquals("success", associationFormAction.pushTarget(context).getId());
		// success = page 3 displayed with C, BC is in scope and stack is BC+AB
		
		// user returns to page 2
		// FIXME error returning two pages
//		assertEquals("success", associationFormAction.popTarget(context).getId());
//		assertEquals("B", context.getFlowScope().get("parent"));
//		assertSame(target, context.getFlowScope().get("target"));
		
		// user returns to page 1
//		assertEquals("success", associationFormAction.popTarget(context).getId());
//		assertEquals("A", context.getFlowScope().get("parent"));
//		assertSame("A", context.getFlowScope().get("target"));
	}
	
	private AbstractModelFormAction<String> formAction;
	private String newInstance = "p";
	private AbstractAssociationFormAction<StubAssociation, String, String> associationFormAction;
	private StubAssociation newAssociation = new StubAssociation("P", "C");
	private MockRequestContext context;
	
	@Before
	public void setUp() {
		formAction = new AbstractModelFormAction<String>() {
			@Override protected String doCreateTarget(RequestContext context)
					throws Exception { return newInstance; }
			@Override protected String doPrepareTarget(RequestContext context,
					String target) throws Exception { return target.toUpperCase(); }
			@Override public String getTargetAttributeName() { return "parent"; }
			
		};
		associationFormAction = new AbstractAssociationFormAction<StubAssociation, String, String>() {
			@Override public StubAssociation doCreateTarget(RequestContext context, String parent) 
			    throws Exception { return newAssociation; }
			@Override protected StubAssociation doStoreTarget(StubAssociation detachedTarget) 
			    throws Exception { return detachedTarget.toUpperCase(); }
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
		public StubAssociation toUpperCase() { 
			setParent(getParent().toUpperCase());
			setChild(getChild().toUpperCase());
			return this;
		}
	}

}
