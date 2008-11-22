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

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

/**
 * @author Mauricio Fernandes de Castro
 */
public class AbstractNodeTests extends TestCase {
	
	private AbstractNode<StubPayLoad> stubNode;
	private StubPayLoad payLoad;
	
	public class StubPayLoad {
	}
	
	private List<Node> childList = new ArrayList<Node>();
	
	public class StubNode extends AbstractNode<StubPayLoad> {

		private static final long serialVersionUID = 1L;

		public StubNode(long id, StubPayLoad payLoad, int level, int sequence) {
			super(id, payLoad, level, sequence);
		}

		public StubNode(long id, StubPayLoad payLoad, int level, int sequence, boolean editable) {
			super(id, payLoad, level, sequence, editable);
		}

		public String getCaption() {
			return "CAPTION";
		}

		public List<Node> getChildList() {
			return childList;
		}

	}
	
	public void testConstructor() {
		assertEquals(1000, stubNode.getId());
		assertSame(payLoad, stubNode.getPayLoad());
		assertEquals(10, stubNode.getLevel());
		assertEquals(100, stubNode.getSequence());
		assertTrue(stubNode.isEditable());
	}
	
	public void testConstructorNotEditable() {
		stubNode = new StubNode(1000, payLoad, 10, 100, false);
		assertEquals(1000, stubNode.getId());
		assertSame(payLoad, stubNode.getPayLoad());
		assertEquals(10, stubNode.getLevel());
		assertEquals(100, stubNode.getSequence());
		assertFalse(stubNode.isEditable());
	}
	
	public void testPayLoad() {
		assertSame(payLoad, stubNode.getPayLoad());
	}
	
	public void testCaption() {
		assertEquals("CAPTION", stubNode.getCaption());
	}
	
	public void testSequence() {
		stubNode.setSequence(Integer.MAX_VALUE);
		assertEquals(Integer.MAX_VALUE, stubNode.getSequence());
	}
	
	public void testIcon() {
		stubNode.setIcon("ICON");
		assertEquals("ICON", stubNode.getIcon());
	}
	
	public void testCompareToPrevious() {
		Node previous = new StubNode(1001, payLoad, 9, 100);
		assertEquals(1, stubNode.compareTo(previous));
		Node previous2 = new StubNode(1001, payLoad, 9, 99);
		assertEquals(1, stubNode.compareTo(previous2));
		Node previous3 = new StubNode(1001, payLoad, 9, 101);
		assertEquals(1, stubNode.compareTo(previous3));
		Node previousSequence = new StubNode(1001, payLoad, 10, 99);
		assertEquals(1, stubNode.compareTo(previousSequence));
	}
	
	public void testCompareToNext() {
		Node next = new StubNode(1001, payLoad, 11, 100);
		assertEquals(-1, stubNode.compareTo(next));
		Node next2 = new StubNode(1001, payLoad, 11, 99);
		assertEquals(-1, stubNode.compareTo(next2));
		Node next3 = new StubNode(1001, payLoad, 11, 101);
		assertEquals(-1, stubNode.compareTo(next3));
		Node nextSequence = new StubNode(1001, payLoad, 10, 101);
		assertEquals(-1, stubNode.compareTo(nextSequence));
	}
	
	public void testEqualsSuccess() {
		Node other = new StubNode(1000, payLoad, 10, 100);
		assertTrue(stubNode.equals(other));
	}
	
	public void testEqualsFailurePayLoad() {
		Node other = new StubNode(1000, new StubPayLoad(), 10, 100);
		assertFalse(stubNode.equals(other));
	}
	
	public void testEqualsFailureLevel() {
		AbstractNode<StubPayLoad> other = new StubNode(1000, payLoad, 11, 100);
		assertFalse(stubNode.equals(other));
	}
	
	@Override
	public void setUp() {
		payLoad = new StubPayLoad();
		stubNode = new StubNode(1000, payLoad, 10, 100);
	}

}
