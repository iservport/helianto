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

package org.helianto.classic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;

import org.helianto.core.Association;
import org.helianto.core.domain.Identity;
import org.helianto.core.internal.AbstractAssociation;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Mauricio Fernandes de Castro
 */
public class AbstractAssociationTests {

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

		public boolean isKeyEmpty() {
			return false;
		}

	}

	@Test
	public void constructor() {
		assertNotNull(association);
		assertTrue(association instanceof Serializable);
		assertTrue(association instanceof Association);
	}

	@Test
	public void id() {
		association.setId(Integer.MAX_VALUE);
		assertEquals(Integer.MAX_VALUE, association.getId());
	}

	@Test
	public void version() {
		association.setVersion(Integer.MAX_VALUE);
		assertEquals(Integer.MAX_VALUE, association.getVersion());
	}

	@Test
	public void sequence() {
		association.setSequence(Integer.MAX_VALUE);
		assertEquals(Integer.MAX_VALUE, association.getSequence());
	}

	@Test
	public void parent() {
		Identity parent = new Identity();
		association.setParent(parent);
		assertSame(parent, association.getParent());
	}

	@Test
	public void child() {
		Identity child = new Identity();
		association.setChild(child);
		assertSame(child, association.getChild());
	}

	/**
	 * Important: usually the unique key members are used in hash,
	 * but, for ORM tuning purposes, the parent field in subclasses
	 * may be set as LAZY loaded. Avoid to use it in hashCode or you will 
	 * have to deal with LazyInitializationException more frequently.
	 */
	@Test
	public void hashTest() {
		association.setSequence(10);
		association.setChild(new Identity("CHILD"));
		association.setParent(new Identity());
//		System.out.println("Child hash "+association.getChild().hashCode());
//		int result = 23643 + association.getChild().hashCode(); 
		assertEquals(94655468, association.hashCode());
		association.getParent().setPrincipal("PARENT");
		assertEquals(94655468, association.hashCode());
	}

	@Before
	public void setUp() {
		association = new AssociationStub();
	}
	
}
