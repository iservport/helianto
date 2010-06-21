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

import java.io.Serializable;

import org.helianto.core.User;
import org.helianto.core.security.PublicUserDetails;
import org.junit.Before;
import org.junit.Test;
import org.springframework.webflow.core.collection.LocalAttributeMap;
import org.springframework.webflow.core.collection.MutableAttributeMap;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class ActionTests {
	
	private AbstractAction<String> action;
	private MutableAttributeMap attributes;
	private PublicUserDetails userDetails, userDetailsInCreation;
	
	@Test
	public void inheritance() {
		assertTrue(action instanceof Serializable);
	}
	
	@Test
	public void hasTarget() {
		assertFalse(action.hasTarget(attributes));
		attributes.put("NAME", "VALUE");
		assertTrue(action.hasTarget(attributes));
	}
	
	/**
	 * If the target name can retrieve a target from the attributes map, the transition
	 * must be "success".
	 */
	@Test
	public void createBrowse() {
		attributes.put("NAME", "VALUE");
		assertEquals("browse", action.create(attributes, userDetails));
	}
	
	/**
	 * If the target name can not retrieve a target from the attributes map, the transition
	 * must be "edit", and userDetails must be passed during a new target creation.
	 */
	@Test
	public void createEdit() {
		assertEquals("edit", action.create(attributes, userDetails));
		assertTrue(attributes.contains("NAME"));
		assertEquals("CREATED", attributes.get("NAME"));
		assertSame(userDetails, userDetailsInCreation);
	}
	
	@Test
	public void prepare() {
		attributes.put("NAME", "VALUE");
		assertEquals("success", action.prepare(attributes));
		assertTrue(attributes.contains("NAME"));
		assertEquals("PREPARED", attributes.get("NAME"));
	}
	
	@Test
	public void prepareNoTarget() {
		assertEquals("noTarget", action.prepare(attributes));
		assertFalse(attributes.contains("NAME"));
	}
	
	@Test
	public void store() {
		attributes.put("NAME", "VALUE");
		assertEquals("success", action.store(attributes));
		assertTrue(attributes.contains("NAME"));
		assertEquals("STORED", attributes.get("NAME"));
	}
	
	@Test
	public void storeNoTarget() {
		assertEquals("noTarget", action.store(attributes));
		assertFalse(attributes.contains("NAME"));
	}
	
	@SuppressWarnings("serial")
	@Before
	public void setUp() {
		action = new AbstractAction<String>() {
			@Override protected String getTargetName() { return "NAME"; }
			@Override 
			protected String doCreate(MutableAttributeMap attributes, PublicUserDetails userDetails) {
				userDetailsInCreation = userDetails;
				return "CREATED";
			}
			@Override
			protected String doPrepare(String target) {
				return "PREPARED";
			}
			@Override
			protected String doStore(String target) {
				return "STORED";
			}
		};
		attributes = new LocalAttributeMap();
		userDetails = new  PublicUserDetails() {
			public void setUser(User user) { }
			public User getUser() { return null; }
		};
		userDetailsInCreation = null;
	}

}
