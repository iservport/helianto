package org.helianto.process;

import junit.framework.TestCase;

import org.helianto.core.Entity;
import org.helianto.core.test.DomainTestSupport;


/**
 * <code>Document</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class DocumentTests extends TestCase {
    
    /**
     * Test <code>Document</code> static factory method.
     */
    public void testDocumentFactory() {
        Entity entity = new Entity();
        String docCode = DomainTestSupport.STRING_TEST_VALUE;
        
        Document document = Document.documentFactory(entity, docCode);
        
        assertSame(entity, document.getEntity());
        assertEquals(docCode, document.getDocCode());
        
    }
    
    /**
     * Test <code>Document</code> equals() method.
     */
    public void testDocumentEquals() {
        Entity entity = new Entity();
        String docCode = DomainTestSupport.STRING_TEST_VALUE;
        
        Document document = Document.documentFactory(entity, docCode);
        Document copy = (Document) DomainTestSupport.minimalEqualsTest(document);
        
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
    
    
