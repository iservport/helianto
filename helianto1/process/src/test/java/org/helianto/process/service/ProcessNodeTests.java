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


package org.helianto.process.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.helianto.core.Node;
import org.helianto.process.AssociationType;
import org.helianto.process.DocumentAssociation;
import org.helianto.process.Operation;
import org.helianto.process.Process;
import org.helianto.process.ProcessDocument;
import org.junit.Before;
import org.junit.Test;


/**
 * @author Mauricio Fernandes de Castro
 */
public class ProcessNodeTests {
	
	@Test
	public void testRootNode() {
		ProcessDocument root = new Process();
		root.setId(Integer.MAX_VALUE);
		root.setDocCode("DOCCODE");
		
		ProcessNode processNode = new ProcessNode(root);
		assertSame(root, processNode.getPayLoad().getChild());
		assertEquals(0, processNode.getId());
		assertEquals(0, processNode.getLevel());
		assertEquals(0, processNode.getSequence());
		assertEquals("DOCCODE", processNode.getCaption());
		assertFalse(processNode.isEditable());
	}

	@Test
	public void testLeafNode() {
		ProcessNode processNode = new ProcessNode(payLoad, 10, 100);
		assertSame(payLoad, processNode.getPayLoad());
		assertEquals(Integer.MAX_VALUE, processNode.getId());
		assertEquals(10, processNode.getLevel());
		assertEquals(100, processNode.getSequence());
		assertEquals("DOCCODE", processNode.getCaption());
		assertTrue(processNode.isEditable());
	}

	@Test
	public void testLeafNodeNotEditable() {
		ProcessNode processNode = new ProcessNode(payLoad, 10, 100, false);
		assertSame(payLoad, processNode.getPayLoad());
		assertEquals(Integer.MAX_VALUE, processNode.getId());
		assertEquals(10, processNode.getLevel());
		assertEquals(100, processNode.getSequence());
		assertEquals("DOCCODE", processNode.getCaption());
		assertFalse(processNode.isEditable());
	}

	@Test
	public void testChildAssociationFactory() {
		ProcessNode processNode = new ProcessNode(payLoad, 10, 100);
		ProcessDocument grandChild = new Operation();
		grandChild.setDocCode("GRANDCHILD");
		DocumentAssociation childAssociation = DocumentAssociation.documentAssociationFactory(child, grandChild, AssociationType.GENERAL);
		childAssociation.setId(Integer.MIN_VALUE);
		childAssociation.setSequence(1000);

		ProcessNode childNode = (ProcessNode) processNode.childNodeFactory(childAssociation, true);
		assertSame(childAssociation, childNode.getPayLoad());
		assertEquals(Integer.MIN_VALUE, childNode.getId());
		assertEquals(11, childNode.getLevel());
		assertEquals(1000, childNode.getSequence());
		assertEquals("GRANDCHILD", childNode.getCaption());
		assertTrue(childNode.isEditable());
	}
	
	@Test
	public void testGetChildList() {
		ProcessNode processNode = new ProcessNode(payLoad, 10, 100);
		ProcessDocument grandChild1 = new Operation();
		grandChild1.setDocCode("GRANDCHILD1");
		ProcessDocument grandChild2 = new Operation();
		grandChild2.setDocCode("GRANDCHILD2");
		DocumentAssociation childAssociation1 = DocumentAssociation.documentAssociationFactory(child, grandChild1, AssociationType.GENERAL);
		childAssociation1.setId(101);
		childAssociation1.setSequence(1001);
		DocumentAssociation childAssociation2 = DocumentAssociation.documentAssociationFactory(child, grandChild2, AssociationType.GENERAL);
		childAssociation2.setId(102);
		childAssociation2.setSequence(1002);

		List<Node> nodeList = processNode.getChildList();
		assertEquals(2, nodeList.size());
		assertSame(childAssociation1, ((ProcessNode) nodeList.get(0)).getPayLoad());
		assertEquals(101, nodeList.get(0).getId());
		assertEquals(1001, nodeList.get(0).getSequence());
		assertEquals(11, nodeList.get(0).getLevel());
		assertEquals("GRANDCHILD1", nodeList.get(0).getCaption());
		assertSame(childAssociation2, ((ProcessNode) nodeList.get(1)).getPayLoad());
		assertEquals(102, nodeList.get(1).getId());
		assertEquals(1002, nodeList.get(1).getSequence());
		assertEquals(11, nodeList.get(1).getLevel());
		assertEquals("GRANDCHILD2", nodeList.get(1).getCaption());
	}
	
	// collabs
	
	ProcessDocument parent;
	ProcessDocument child;
	DocumentAssociation payLoad;
	
	@Before
	public void setUp() {
		parent = new Process();
		child = new Operation();
		child.setDocCode("DOCCODE");
		payLoad = DocumentAssociation.documentAssociationFactory(parent, child, AssociationType.GENERAL);
		payLoad.setId(Integer.MAX_VALUE);
	}

}
