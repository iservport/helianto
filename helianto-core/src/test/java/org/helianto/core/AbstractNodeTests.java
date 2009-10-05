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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Mauricio Fernandes de Castro
 */
public class AbstractNodeTests {
	
	private AbstractNode<StubContent> stubNode;
	private StubContent content;
	
	public class StubContent {
	}
	
	private List<Node> childList = new ArrayList<Node>();
	
	public class StubNode extends AbstractNode<StubContent> {

		private static final long serialVersionUID = 1L;

		public StubNode(long id, StubContent payLoad, int level, int sequence) {
			super(id, payLoad, level, sequence);
		}

		public StubNode(long id, StubContent payLoad, int level, int sequence, boolean editable) {
			super(id, payLoad, level, sequence, editable);
		}

		public String getCaption() {
			return "CAPTION";
		}

		public List<Node> getChildList() {
			return childList;
		}

	}
	
	@Test
	public void constructor() {
		assertEquals(1000, stubNode.getId());
		assertSame(content, stubNode.getContent());
		assertEquals(10, stubNode.getLevel());
		assertEquals(100, stubNode.getSequence());
		assertTrue(stubNode.isEditable());
	}
	
	@Test
	public void constructorNotEditable() {
		stubNode = new StubNode(1000, content, 10, 100, false);
		assertEquals(1000, stubNode.getId());
		assertSame(content, stubNode.getContent());
		assertEquals(10, stubNode.getLevel());
		assertEquals(100, stubNode.getSequence());
		assertFalse(stubNode.isEditable());
	}
	
	@Test
	public void content() {
		assertSame(content, stubNode.getContent());
	}
	
	@Test
	public void caption() {
		assertEquals("CAPTION", stubNode.getCaption());
	}
	
	@Test
	public void sequence() {
		stubNode.setSequence(Integer.MAX_VALUE);
		assertEquals(Integer.MAX_VALUE, stubNode.getSequence());
	}
	
	@Test
	public void icon() {
		stubNode.setIcon("ICON");
		assertEquals("ICON", stubNode.getIcon());
	}
	
	@Test
	public void compareToPrevious() {
		Node previous = new StubNode(1001, content, 9, 100);
		assertEquals(1, stubNode.compareTo(previous));
		Node previous2 = new StubNode(1001, content, 9, 99);
		assertEquals(1, stubNode.compareTo(previous2));
		Node previous3 = new StubNode(1001, content, 9, 101);
		assertEquals(1, stubNode.compareTo(previous3));
		Node previousSequence = new StubNode(1001, content, 10, 99);
		assertEquals(1, stubNode.compareTo(previousSequence));
	}
	
	@Test
	public void compareToNext() {
		Node next = new StubNode(1001, content, 11, 100);
		assertEquals(-1, stubNode.compareTo(next));
		Node next2 = new StubNode(1001, content, 11, 99);
		assertEquals(-1, stubNode.compareTo(next2));
		Node next3 = new StubNode(1001, content, 11, 101);
		assertEquals(-1, stubNode.compareTo(next3));
		Node nextSequence = new StubNode(1001, content, 10, 101);
		assertEquals(-1, stubNode.compareTo(nextSequence));
	}
	
	@Test
	public void equalsSuccess() {
		Node other = new StubNode(1000, content, 10, 100);
		assertTrue(stubNode.equals(other));
	}
	
	@Test
	public void equalsFailurePayLoad() {
		Node other = new StubNode(1000, new StubContent(), 10, 100);
		assertFalse(stubNode.equals(other));
	}
	
	@Test
	public void equalsFailureLevel() {
		AbstractNode<StubContent> other = new StubNode(1000, content, 11, 100);
		assertFalse(stubNode.equals(other));
	}
	
	@Before
	public void setUp() {
		content = new StubContent();
		stubNode = new StubNode(1000, content, 10, 100);
	}

}
