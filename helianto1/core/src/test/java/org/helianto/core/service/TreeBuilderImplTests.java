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


package org.helianto.core.service;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.helianto.core.Identity;
import org.helianto.core.Node;
import org.helianto.core.test.IdentityTestSupport;

/**
 * @author Mauricio Fernandes de Castro
 */
public class TreeBuilderImplTests extends TestCase {
	
	private TreeBuilder treeBuilder;
	private List<Node> expandeNodeList = new ArrayList<Node>();
	private List<Node> collapsedNodeList = new ArrayList<Node>();

	
	public class NodeStub extends AbstractNode<Identity> {

		private static final long serialVersionUID = 1L;

		public NodeStub(long id, Identity payLoad, int level, int sequence) {
			super(id, payLoad, level, sequence);
		}

		public String getCaption() {
			return getPayLoad().getPrincipal();
		}

		public List<Node> getChildList() {
			if (isExpanded()) {
				return expandeNodeList;
			}
			return collapsedNodeList;
		}
		
	}
	
	public void testNodeConstructor() {
		Identity identity = IdentityTestSupport.createIdentity();
		Node node = new NodeStub(10, identity, 100, 1000);
		assertEquals(10, node.getId());
		assertEquals(identity.getPrincipal(), node.getCaption());
		assertEquals(100, node.getLevel());
		assertEquals(1000, node.getSequence());
		assertEquals("", node.getIcon());
		assertEquals(false, node.isExpanded());
	}
	
	public void testAddNodeCollapsed() {
		Identity identity = IdentityTestSupport.createIdentity();
		Node node = new NodeStub(10, identity, 100, 1000);
		((TreeBuilderImpl) treeBuilder).addNode(node);
		List<Node> tree = treeBuilder.getTree();
		assertEquals(1, tree.size());
		assertSame(node, tree.get(0));
	}
	
	public void testAddNodeExpanded() {
		Identity identity = IdentityTestSupport.createIdentity();
		Node node = new NodeStub(10, identity, 100, 1000);
		((TreeBuilderImpl) treeBuilder).addNode(node);
		List<Node> tree = treeBuilder.getTree();
		assertEquals(1, tree.size());
		assertSame(node, tree.get(0));
	}
	
	@Override
	public void setUp() {
		treeBuilder = new TreeBuilderImpl();
		((TreeBuilderImpl) treeBuilder).reset();
	}

}
