package org.helianto.process;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.test.DomainTestSupport;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <code>DocumentAssociation</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class DocumentAssociationTests {
    
    /**
     * Test <code>DocumentAssociation</code> static factory method.
     */
	@Test
    public void documentAssociationFactory() {
        ProcessDocument parent = new Process();
        ProcessDocument child = new Process();
        
        ProcessDocumentAssociation documentAssociation = ProcessDocumentAssociation.documentAssociationFactory(parent, child, AssociationType.GENERAL);
        
        assertSame(parent, documentAssociation.getParent());
        assertTrue(parent.getChildAssociations().contains(documentAssociation));
        assertSame(child, documentAssociation.getChild());
        assertTrue(child.getParentAssociations().contains(documentAssociation));
        
    }
    
    /**
     * Test <code>DocumentAssociation</code> equals() method.
     */
	@Test
    public void documentAssociationEquals() {
        ProcessDocument parent = new Process();
        ProcessDocument child = new Process();
        
        ProcessDocumentAssociation documentAssociation = ProcessDocumentAssociation.documentAssociationFactory(parent, child, AssociationType.GENERAL);
        ProcessDocumentAssociation copy = (ProcessDocumentAssociation) DomainTestSupport.minimalEqualsTest(documentAssociation);
        
        copy.setParent(null);
        copy.setChild(child);
        assertFalse(documentAssociation.equals(copy));

        copy.setParent(parent);
        copy.setChild(null);
        assertFalse(documentAssociation.equals(copy));

        copy.setParent(parent);
        copy.setChild(child);

        assertTrue(documentAssociation.equals(copy));
    }

	@Test
	public void comparator() {
		Process process = new Process();
		ProcessDocumentAssociation first = new ProcessDocumentAssociation();
		first.setSequence(1);
		ProcessDocumentAssociation middle = new ProcessDocumentAssociation();
		middle.setSequence(5);
		ProcessDocumentAssociation last = new ProcessDocumentAssociation();
		last.setSequence(10);
		List<ProcessDocumentAssociation> documentAssociationList = new ArrayList<ProcessDocumentAssociation>();
		documentAssociationList.add(last);
		documentAssociationList.add(first);
		documentAssociationList.add(middle);
		assertEquals(10, documentAssociationList.get(0).getSequence());
		assertEquals(1, documentAssociationList.get(1).getSequence());
		assertEquals(5, documentAssociationList.get(2).getSequence());
		Collections.sort(documentAssociationList, process);
		assertEquals(1, documentAssociationList.get(0).getSequence());
		assertEquals(5, documentAssociationList.get(1).getSequence());
		assertEquals(10, documentAssociationList.get(2).getSequence());
	}
	
	@Test
	public void operationAssociationsNoParent() {
		Process process = new Process();
		prepareProcess(process, new int[] {5, 3, 2, 10 });
		assertEquals(4, process.getChildAssociationList().size());
		assertEquals(2, process.getChildAssociationList().get(0).getSequence());
	}
	
	@Test
	public void operationAssociationsParent1() {
		Process process = new Process(), parent = new Process();
		prepareProcess(parent, new int[] {1,2,3});
		prepareProcess(process, new int[] {5,7});
		process.setParent(parent);
		assertEquals(5, process.getChildAssociationList().size());
		assertEquals(1, process.getChildAssociationList().get(0).getSequence());
		assertEquals(2, process.getChildAssociationList().get(1).getSequence());
		assertEquals(3, process.getChildAssociationList().get(2).getSequence());
		assertEquals(5, process.getChildAssociationList().get(3).getSequence());
		assertEquals(7, process.getChildAssociationList().get(4).getSequence());
	}
	
	@Test
	public void operationAssociationsParent2() {
		Process process = new Process(), parent = new Process();
		prepareProcess(parent, new int[] {1,2,3,4});
		prepareProcess(process, new int[] {3,4,5,7});
		process.setParent(parent);
		assertEquals(6, process.getChildAssociationList().size());
		assertEquals(1, process.getChildAssociationList().get(0).getSequence());
		assertEquals(2, process.getChildAssociationList().get(1).getSequence());
		assertEquals(3, process.getChildAssociationList().get(2).getSequence());
		assertEquals(4, process.getChildAssociationList().get(3).getSequence());
		assertEquals(5, process.getChildAssociationList().get(4).getSequence());
		assertEquals(7, process.getChildAssociationList().get(5).getSequence());
	}
	
	private void prepareProcess(Process process, int[] randomSequences) {
		Entity entity = new Entity();
		for (int i: randomSequences) {
			ProcessDocumentAssociation documentAssociation = new ProcessDocumentAssociation();
			documentAssociation.setParent(process);
			documentAssociation.setSequence(i);
			Operation child = new Operation();
			child.setEntity(entity);
			child.setDocCode(i+"");
			documentAssociation.setChild(child);
			process.getChildAssociations().add(documentAssociation);
			logger.debug("Added {}", documentAssociation);
		}
	}
	
	private static final Logger logger = LoggerFactory.getLogger(DocumentAssociationTests.class);

}
    
    
