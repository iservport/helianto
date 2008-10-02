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

package org.helianto.core;

import java.io.Serializable;

import junit.framework.TestCase;

/**
 * @author Mauricio Fernandes de Castro
 */
public class AbstractAssociationTests extends TestCase {

	private AssociationStub association;

	/**
	 * Test stub.
	 */
	public class AssociationStub extends
			AbstractAssociation<Identity, Identity> {

		private static final long serialVersionUID = 1L;

		public Identity getChild() {
			return this.child;
		}

		public Identity getParent() {
			return this.parent;
		}

	}

	public void testConstructor() {
		assertNotNull(association);
		assertTrue(association instanceof Serializable);
		assertTrue(association instanceof Association);
	}

	public void testId() {
		association.setId(Integer.MAX_VALUE);
		assertEquals(Integer.MAX_VALUE, association.getId());
	}

	public void testVersion() {
		association.setVersion(Integer.MAX_VALUE);
		assertEquals(Integer.MAX_VALUE, association.getVersion());
	}

	public void testSequence() {
		association.setSequence(Integer.MAX_VALUE);
		assertEquals(Integer.MAX_VALUE, association.getSequence());
	}

	public void testParent() {
		Identity parent = new Identity();
		association.setParent(parent);
		assertSame(parent, association.getParent());
	}

	public void testChild() {
		Identity child = new Identity();
		association.setChild(child);
		assertSame(child, association.getChild());
	}

	@Override
	public void setUp() {
		association = new AssociationStub();
	}

}
