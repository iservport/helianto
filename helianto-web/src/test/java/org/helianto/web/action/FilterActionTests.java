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

package org.helianto.web.action;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.Operator;
import org.helianto.core.User;
import org.helianto.core.filter.Filter;
import org.helianto.core.filter.Listable;
import org.helianto.core.filter.Page;
import org.helianto.core.security.PublicUserDetails;
import org.junit.Before;
import org.junit.Test;
import org.springframework.webflow.core.collection.LocalAttributeMap;
import org.springframework.webflow.core.collection.MutableAttributeMap;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class FilterActionTests {

	private AbstractFilterAction<String> action;
	private MutableAttributeMap attributes;
	private Filter filter;
	private List<String> itemList;
	private PublicUserDetails userDetails, userDetailsInCreation;
	@SuppressWarnings("rawtypes")
	private SimpleModel simpleModel;
	
	@Test
	public void inheritance() {
		assertTrue(action instanceof AbstractAction<?>);
	}
	
	@Test
	public void filterName() {
		assertEquals("NAMEFilter", action.getFilterName());
	}
	
	@Test
	public void hasFilter() {
		assertFalse(action.hasFilter(attributes));
		attributes.put("NAMEFilter", filter);
		assertTrue(action.hasFilter(attributes));
	}
	
	@Test
	public void filter() {
		attributes.put("NAMEFilter", filter);		
		assertEquals("success", action.filter(attributes, userDetails));
		assertEquals("NAME", attributes.get("targetName"));
		assertSame(itemList, simpleModel.getList());
	}
	
	@Test
	public void modelName() {
		assertEquals("NAMEModel", action.getModelName());
	}
	
	@Test
	public void getModel() {
		Listable model = new Page();
		attributes.put("NAMEModel", model);
		assertSame(model, action.getModel(attributes));
	}
	
	@Test
	public void hasModel() {
		assertTrue(action.hasModel(attributes));
	}
	
	@Test
	public void putListable() {
		Listable model = new Page();
		attributes.put("NAMEModel", model);
		List<String> newItemList = new ArrayList<String>();
		action.put(attributes, newItemList);
		assertSame(newItemList, model.getList());
	}
	
	@Test
	public void putPageModel() {
		PageModel<String> model = new PageModel<String>();
		attributes.put("NAMEModel", model);
		List<String> newItemList = new ArrayList<String>();
		action.put(attributes, newItemList);
		assertSame(newItemList, model.getPages().get("NAME").getList());
	}
	
	@Test
	public void filterCreate() {
		assertEquals("success", action.filter(attributes, userDetails));
		assertSame(userDetails, userDetailsInCreation);
	}
	
	@SuppressWarnings({ "serial", "rawtypes", "unchecked" })
	@Before
	public void setUp() {
		itemList = new ArrayList<String>();
		itemList.add("ONE");
		action = new AbstractFilterAction<String>() {
			@Override protected String getTargetName() { return "NAME"; }
			@Override
			protected Filter doCreateFilter(MutableAttributeMap attributes, PublicUserDetails userDetails) {
				userDetailsInCreation = userDetails;
				return filter;
			}
			@Override
			protected List<String> doFilter(Filter filter) {
				return itemList;
			}
			@Override 
			protected String doCreate(MutableAttributeMap attributes, PublicUserDetails userDetails) { return "CREATED"; }
			@Override
			protected String doPrepare(String target, MutableAttributeMap attributes) { return "PREPARED"; }
			@Override
			protected String doStore(String target) { return "STORED"; }
		};
		simpleModel = new SimpleModel(new Object());
		attributes = new LocalAttributeMap();
		attributes.put("NAMEModel", simpleModel);
		filter = new Filter() {
			public String createCriteriaAsString() { return "CRITERIA"; }
			public String getObjectAlias() { return "ALIAS"; }
			@SuppressWarnings("unused")
			public boolean isSelection() { return false; }
			public void setObjectAlias(String objectAlias) { }
			public String createSelectAsString() { return null; }
		};
		userDetails = new  PublicUserDetails() {
			@SuppressWarnings("unused")
			public void setUser(User user) { }
			public User getUser() { return null; }
			public Entity getEntity() { return null; }
			public Operator getOperator() { return null; }
		};
		userDetailsInCreation = null;
	}

}
