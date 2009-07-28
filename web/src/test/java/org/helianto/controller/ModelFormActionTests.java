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
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.webflow.action.FormAction;
import org.springframework.webflow.execution.RequestContext;
import org.springframework.webflow.execution.ScopeType;
import org.springframework.webflow.test.MockRequestContext;


/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ModelFormActionTests {
	
	@Test
	public void constructor() {
		assertTrue(modelFormAction instanceof FormAction);
		assertEquals("formObject", modelFormAction.getFormObjectName());
		assertEquals(ScopeType.FLOW, modelFormAction.getFormObjectScope());
		assertEquals("target", modelFormAction.getTargetAttributeName());
		assertEquals("targetList", modelFormAction.getTargetListAttributeName());
		assertEquals("targetListSize", modelFormAction.getTargetListSizeAttributeName());
	}
	
	@Test
	public void put() {
		modelFormAction.put(context, "X");
		assertEquals("X", modelFormAction.getFormObjectScope().getScope(context).get("target"));
	}

	@Test
	public void putName() {
		modelFormAction.put(context, "Y", "arbitraryName");
		assertEquals("Y", modelFormAction.getFormObjectScope().getScope(context).get("arbitraryName"));
	}

	@Test
	public void createAndGet() {
		assertEquals("success", modelFormAction.createTarget(context).getId());
		assertEquals("t", modelFormAction.get(context));
	}

	@Test
	public void createAndGetScope() {
		modelFormAction.setFormObjectScope(ScopeType.CONVERSATION);
		assertEquals("success", modelFormAction.createTarget(context).getId());
		assertEquals("t", modelFormAction.get(context));
	}

	@Test
	public void prepare() {
		assertEquals("success", modelFormAction.prepareTarget(context).getId());
		assertEquals("T", modelFormAction.get(context));
	}

	@Test
	public void list() {
		List<String> testList = new ArrayList<String>();
		modelFormAction.list(context, "test", testList);
		assertSame(testList, modelFormAction.getFormObjectScope().getScope(context).get("testList"));
	}

	@Test
	public void selectTarget1() {
		List<String> testList = new ArrayList<String>();
		testList.add("1");
		testList.add("2");
		context.getFlowScope().put("targetList", testList);
		context.putRequestParameter("target_index", "0");
		assertEquals("success", modelFormAction.selectTarget(context).getId());
		assertEquals("1", modelFormAction.get(context));
	}

	@Test
	public void selectTarget2() {
		List<String> testList = new ArrayList<String>();
		testList.add("1");
		testList.add("2");
		context.getFlowScope().put("targetList", testList);
		context.putRequestParameter("target_index", "1");
		assertEquals("success", modelFormAction.selectTarget(context).getId());
		assertEquals("2", modelFormAction.get(context));
	}

	private AbstractModelFormAction<String> modelFormAction;
	private MockRequestContext context;
	
	@Before
	public void setUp() {
		modelFormAction = new AbstractModelFormAction<String>() {
			@Override public String getTargetAttributeName() { return "target"; }
			@Override protected String doCreateTarget(RequestContext context)
					throws Exception { return "t"; }
			@Override protected String doPrepareTarget(RequestContext context, String target) 
			        throws Exception { return "T"; }
		};
		context = new MockRequestContext();
	}
	
}
