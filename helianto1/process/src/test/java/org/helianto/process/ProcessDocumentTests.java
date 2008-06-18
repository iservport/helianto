package org.helianto.process;

import junit.framework.TestCase;

import org.helianto.core.Entity;
import org.helianto.core.test.DomainTestSupport;


/**
 * <code>Document</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ProcessDocumentTests extends TestCase {
    
    /**
     * Test <code>Document</code> static factory method.
     */
    public void testDocumentFactoryClass() {
        Entity entity = new Entity();
        String docCode = DomainTestSupport.STRING_TEST_VALUE;

		ProcessDocument document = ProcessDocument.documentFactory(DocumentExtension.class, entity, docCode);

        assertTrue(document instanceof DocumentExtension);
		assertSame(entity, document.getEntity());
        assertEquals(docCode, document.getDocCode());
        
    }
    
    /**
     * Test <code>Document</code> static factory method.
     */
    public void testDocumentFactory() {
        Entity entity = new Entity();
        String docCode = DomainTestSupport.STRING_TEST_VALUE;
        
        ProcessDocument document = ProcessDocument.processDocumentFactory(entity, docCode);
        
        assertTrue(document instanceof ProcessDocument);
        assertSame(entity, document.getEntity());
        assertEquals(docCode, document.getDocCode());
        
    }
    
    /**
     * Test <code>Document</code> static factory method.
     */
    public void testDocumentFactoryAssociation() {
        Entity entity = new Entity();
        String docCode = DomainTestSupport.STRING_TEST_VALUE;
        ProcessDocument parent = ProcessDocument.processDocumentFactory(entity, "PARENT");
        parent.setEntity(entity);
        
        ProcessDocument child = ProcessDocument.documentFactory(DocumentExtension.class, parent, docCode, 0.001, AssociationType.PART_PART);
        
        assertTrue(child instanceof DocumentExtension);
        assertSame(entity, child.getEntity());
        assertEquals(docCode, child.getDocCode());
        
        assertEquals(1, child.getParentAssociations().size());
        assertSame(parent, child.getParentAssociations().iterator().next().getParent());
        
        assertEquals(1, parent.getChildAssociations().size());
        assertSame(child, parent.getChildAssociations().iterator().next().getChild());
        
        assertEquals(AssociationType.PART_PART.getValue(), parent.getChildAssociations().iterator().next().getAssociationType());
        assertEquals(0.001, parent.getChildAssociations().iterator().next().getCoefficient(), 0.0001);
        
    }
    
    /**
     * Test <code>Document</code> equals() method.
     */
    public void testDocumentEquals() {
        Entity entity = new Entity();
        String docCode = DomainTestSupport.STRING_TEST_VALUE;
        
        ProcessDocument document = ProcessDocument.processDocumentFactory(entity, docCode);
        ProcessDocument copy = (ProcessDocument) DomainTestSupport.minimalEqualsTest(document);
        
        copy.setEntity(null);
        copy.setDocCode(docCode);
        assertFalse(document.equals(copy));

        copy.setEntity(entity);
        copy.setDocCode(null);
        assertFalse(document.equals(copy));

        copy.setEntity(entity);
        copy.setDocCode(docCode);

        assertTrue(document.equals(copy));
    }
    
}
    
    
