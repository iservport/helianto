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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.helianto.controller.AbstractTargetStack;
import org.junit.Before;
import org.junit.Test;


/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class TargetStackTests {
	
	@Test
	public void testConstructor() {
		assertTrue(targetStack.getInternalStack()!=null);
		assertTrue(targetStack.getInternalStack() instanceof LinkedList);
	}
	
	@Test
	public void testEmpty() {
		assertTrue(targetStack.getInternalStack().isEmpty());
	}
	
	@Test
	public void testPush() {
		targetStack.push("1");
		assertFalse(targetStack.getInternalStack().isEmpty());
		assertEquals("1", targetStack.peek());
	}
	
	@Test
	public void testPeek() {
		targetStack.push("1");
		targetStack.push("2");
		assertEquals("2", targetStack.peek());
	}
	
	@Test
	public void testPop() {
		targetStack.push("1");
		targetStack.push("2");
		assertEquals("2", targetStack.pop());
		assertEquals("1", targetStack.peek());
	}
	
	private AbstractTargetStack<String> targetStack;
	
	@Before
	public void setUp() {
		targetStack = new AbstractTargetStack<String>() {
			private static final long serialVersionUID = 1L;
			
		};
	}

}
