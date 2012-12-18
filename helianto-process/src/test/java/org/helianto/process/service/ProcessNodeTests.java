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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.helianto.core.Node;
import org.helianto.process.AssociationType;
import org.helianto.process.Operation;
import org.helianto.process.Process;
import org.helianto.process.ProcessDocument;
import org.helianto.process.ProcessDocumentAssociation;
import org.junit.Before;
import org.junit.Test;


/**
 * @author Mauricio Fernandes de Castro
 */
public class ProcessNodeTests {
	
	@Test
	public void rootNode() {
		ProcessDocument root = new Process();
		root.setId(Integer.MAX_VALUE);
		root.setDocCode("DOCCODE");
		
		ProcessNode processNode = new ProcessNode(root);
		assertSame(root, processNode.getContent().getChild());
		assertEquals(0, processNode.getId());
		assertEquals(0, processNode.getLevel());
		assertEquals(0, processNode.getSequence());
		assertEquals("DOCCODE", processNode.getCaption());
		assertFalse(processNode.isEditable());
	}

	@Test
	public void leafNode() {
		ProcessNode processNode = new ProcessNode(payLoad, 10, 100);
		assertSame(payLoad, processNode.getContent());
		assertEquals(Integer.MAX_VALUE, processNode.getId());
		assertEquals(10, processNode.getLevel());
		assertEquals(100, processNode.getSequence());
		assertEquals("DOCCODE", processNode.getCaption());
		assertTrue(processNode.isEditable());
	}

	@Test
	public void leafNodeNotEditable() {
		ProcessNode processNode = new ProcessNode(payLoad, 10, 100, false);
		assertSame(payLoad, processNode.getContent());
		assertEquals(Integer.MAX_VALUE, processNode.getId());
		assertEquals(10, processNode.getLevel());
		assertEquals(100, processNode.getSequence());
		assertEquals("DOCCODE", processNode.getCaption());
		assertFalse(processNode.isEditable());
	}

	@Test
	public void childAssociationFactory() {
		ProcessNode processNode = new ProcessNode(payLoad, 10, 100);
		ProcessDocument grandChild = new Operation();
		grandChild.setDocCode("GRANDCHILD");
		ProcessDocumentAssociation childAssociation = ProcessDocumentAssociation.documentAssociationFactory(child, grandChild, AssociationType.GENERAL);
		childAssociation.setId(Integer.MIN_VALUE);
		childAssociation.setSequence(1000);

		ProcessNode childNode = (ProcessNode) processNode.childNodeFactory(childAssociation, true);
		assertSame(childAssociation, childNode.getContent());
		assertEquals(Integer.MIN_VALUE, childNode.getId());
		assertEquals(11, childNode.getLevel());
		assertEquals(1000, childNode.getSequence());
		assertEquals("GRANDCHILD", childNode.getCaption());
		assertTrue(childNode.isEditable());
	}
	
	@Test
	public void childList() {
		ProcessNode processNode = new ProcessNode(payLoad, 10, 100);
		ProcessDocument grandChild1 = new Operation();
		grandChild1.setDocCode("GRANDCHILD1");
		ProcessDocument grandChild2 = new Operation();
		grandChild2.setDocCode("GRANDCHILD2");
		ProcessDocumentAssociation childAssociation1 = ProcessDocumentAssociation.documentAssociationFactory(child, grandChild1, AssociationType.GENERAL);
		childAssociation1.setId(101);
		childAssociation1.setSequence(1001);
		ProcessDocumentAssociation childAssociation2 = ProcessDocumentAssociation.documentAssociationFactory(child, grandChild2, AssociationType.GENERAL);
		childAssociation2.setId(102);
		childAssociation2.setSequence(1002);

		List<Node> nodeList = processNode.getChildList();
		assertEquals(2, nodeList.size());
		assertSame(childAssociation1, ((ProcessNode) nodeList.get(0)).getContent());
		assertEquals(101, nodeList.get(0).getId());
		assertEquals(1001, nodeList.get(0).getSequence());
		assertEquals(11, nodeList.get(0).getLevel());
		assertEquals("GRANDCHILD1", nodeList.get(0).getCaption());
		assertSame(childAssociation2, ((ProcessNode) nodeList.get(1)).getContent());
		assertEquals(102, nodeList.get(1).getId());
		assertEquals(1002, nodeList.get(1).getSequence());
		assertEquals(11, nodeList.get(1).getLevel());
		assertEquals("GRANDCHILD2", nodeList.get(1).getCaption());
	}
	
	// collabs
	
	ProcessDocument parent;
	ProcessDocument child;
	ProcessDocumentAssociation payLoad;
	
	@Before
	public void setUp() {
		parent = new Process();
		child = new Operation();
		child.setDocCode("DOCCODE");
		payLoad = ProcessDocumentAssociation.documentAssociationFactory(parent, child, AssociationType.GENERAL);
		payLoad.setId(Integer.MAX_VALUE);
	}

}
