package org.helianto.document.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.helianto.core.domain.Entity;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.document.base.AbstractDocument;
import org.junit.Test;


/**
 * <code>Document</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ProcessDocumentTests {
    
	@Test
    public void constructor() {
        Entity entity = EntityTestSupport.createEntity();
    	ProcessDocument document = new ProcessDocument(entity, "CODE");

		assertSame(entity, document.getEntity());
        assertEquals("CODE", document.getDocCode());
    }
    
	@Test
	@SuppressWarnings("serial")
	public void processDocumentEquals() {
		ProcessDocument document = new ProcessDocument(null, null) { };
		assertFalse(document.equals(null));
		
		ProcessDocument other = new ProcessDocument(null, null) { };
		assertTrue(document.equals(other));
		
		Entity entity = EntityTestSupport.createEntity();
		document.setEntity(entity);
		assertFalse(document.equals(other));
		document.setDocCode("CODE");
		assertFalse(document.equals(other));
		other.setEntity(entity);
		assertFalse(document.equals(other));
		other.setDocCode("CODE");
		assertTrue(document.equals(other));
		assertEquals(document.hashCode(), other.hashCode());
		document.setDocCode("");
		assertFalse(document.equals(other));
		document.setEntity(new Entity());
		assertFalse(document.equals(other));

		AbstractDocument ancestor = new AbstractDocument(entity, "CODE") { };
		assertFalse(document.equals(ancestor));
	}
	
}
    
    
