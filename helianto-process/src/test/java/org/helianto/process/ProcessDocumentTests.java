package org.helianto.process;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.helianto.core.Entity;
import org.helianto.core.test.DomainTestSupport;
import org.junit.Test;


/**
 * <code>Document</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ProcessDocumentTests {
    
    /**
     * Test <code>Document</code> static factory method.
     */
	@Test
    public void documentFactoryClass() {
        Entity entity = new Entity();
        String docCode = DomainTestSupport.STRING_TEST_VALUE;

    	ProcessDocument document = new ProcessDocument() {
			private static final long serialVersionUID = 1L;
    	};
    	document.setKey(entity, docCode);

		assertSame(entity, document.getEntity());
        assertEquals(docCode, document.getDocCode());
        
    }
    
}
    
    
