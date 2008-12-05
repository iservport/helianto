package org.helianto.process;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.Entity;
import org.helianto.core.test.DomainTestSupport;

/**
 * <code>DocumentAssociation</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class DocumentAssociationTests extends TestCase {
    
    /**
     * Test <code>DocumentAssociation</code> static factory method.
     */
    public void testDocumentAssociationFactory() {
        ProcessDocument parent = new Process();
        ProcessDocument child = new Process();
        
        DocumentAssociation documentAssociation = DocumentAssociation.documentAssociationFactory(parent, child, AssociationType.GENERAL);
        
        assertSame(parent, documentAssociation.getParent());
        assertTrue(parent.getChildAssociations().contains(documentAssociation));
        assertSame(child, documentAssociation.getChild());
        assertTrue(child.getParentAssociations().contains(documentAssociation));
        
    }
    
    /**
     * Test <code>DocumentAssociation</code> equals() method.
     */
    public void testDocumentAssociationEquals() {
        ProcessDocument parent = new Process();
        ProcessDocument child = new Process();
        
        DocumentAssociation documentAssociation = DocumentAssociation.documentAssociationFactory(parent, child, AssociationType.GENERAL);
        DocumentAssociation copy = (DocumentAssociation) DomainTestSupport.minimalEqualsTest(documentAssociation);
        
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

	public void testComparator() {
		Process process = new Process();
		DocumentAssociation first = new DocumentAssociation();
		first.setSequence(1);
		DocumentAssociation middle = new DocumentAssociation();
		middle.setSequence(5);
		DocumentAssociation last = new DocumentAssociation();
		last.setSequence(10);
		List<DocumentAssociation> documentAssociationList = new ArrayList<DocumentAssociation>();
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
	
	public void testGetOperationAssociationsNoParent() {
		Process process = new Process();
		prepareProcess(process, new int[] {5, 3, 2, 10 });
		assertEquals(4, process.getChildAssociationList().size());
		assertEquals(2, process.getChildAssociationList().get(0).getSequence());
	}
	
	public void testGetOperationAssociationsParent1() {
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
	
	public void testGetOperationAssociationsParent2() {
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
			DocumentAssociation documentAssociation = new DocumentAssociation();
			documentAssociation.setParent(process);
			documentAssociation.setSequence(i);
			Operation child = new Operation();
			child.setEntity(entity);
			child.setDocCode(i+"");
			documentAssociation.setChild(child);
			process.getChildAssociations().add(documentAssociation);
			if (logger.isDebugEnabled()) {
				logger.debug("Added "+documentAssociation);
			}
		}
	}
	
	private static final Log logger = LogFactory.getLog(DocumentAssociationTests.class);

}
    
    
