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

import org.helianto.controller.AbstractAssociationStack;
import org.helianto.core.AbstractAssociation;
import org.junit.Before;
import org.junit.Test;


/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class AssociationStackTests {
	
	@Test
	public void testRootInstance() {
		associationStack.setRootInstance("P");
		assertEquals("P", associationStack.getRootInstance());
	}
	
	@Test
	public void testPush() {
		AbstractAssociation<String, String> association = new AbstractAssociation<String, String>() {
			private static final long serialVersionUID = 1L;
			public String getChild() { return "C"; }
			public String getParent() { return "P"; }
			public boolean isKeyEmpty() { return false; }
		};
		associationStack.push(association);
		assertSame(association, associationStack.peek());
	}

	private AbstractAssociationStack<String, String> associationStack;
	
	@Before
	public void setUp() {
		associationStack = new AbstractAssociationStack<String, String>() {
			private static final long serialVersionUID = 1L;
			
		};
	}

}
