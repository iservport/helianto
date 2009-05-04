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

    	ProcessDocument document = new ProcessDocument() {
			private static final long serialVersionUID = 1L;
			public ProcessDocumentAssociation documentAssociationFactory(int sequence) {
				return null;
			}
    	};
    	document.setKey(entity, docCode);

		assertSame(entity, document.getEntity());
        assertEquals(docCode, document.getDocCode());
        
    }
    
    /**
     * Test <code>Document</code> static factory method.
     */
    //FIXME
    public void no_testDocumentFactoryAssociation() {
        Entity entity = new Entity();
        String docCode = DomainTestSupport.STRING_TEST_VALUE;
    	ProcessDocument parent = new ProcessDocument() {
			private static final long serialVersionUID = 1L;
			public ProcessDocumentAssociation documentAssociationFactory(int sequence) {
				return null;
			}
    	};
    	parent.setKey(entity, "PARENT");
        
        ProcessDocument child = ProcessDocument.documentFactory(DocumentExtension.class, entity, docCode);
        
        assertTrue(child instanceof DocumentExtension);
        assertSame(entity, child.getEntity());
        assertEquals(docCode, child.getDocCode());
        
        assertEquals(1, child.getParentAssociations().size());
        assertSame(parent, child.getParentAssociations().iterator().next().getParent());
        
        assertEquals(1, parent.getChildAssociations().size());
        assertSame(child, parent.getChildAssociations().iterator().next().getChild());
        
    }
    
}
    
    