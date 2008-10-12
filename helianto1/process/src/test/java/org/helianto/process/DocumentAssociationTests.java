package org.helianto.process;

import junit.framework.TestCase;

import org.helianto.core.test.DomainTestSupport;
import org.helianto.process.ProcessDocument;
import org.helianto.process.DocumentAssociation;

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
        ProcessDocument parent = new Part();
        ProcessDocument child = new Part();
        
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
        ProcessDocument parent = new Part();
        ProcessDocument child = new Part();
        
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

}
    
    
