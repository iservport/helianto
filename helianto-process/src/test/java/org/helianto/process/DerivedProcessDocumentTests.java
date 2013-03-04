package org.helianto.process;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.helianto.core.domain.Entity;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.document.base.AbstractDocument;
import org.helianto.process.domain.DerivedProcessDocument;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class DerivedProcessDocumentTests {

	@SuppressWarnings("serial")
	@Test
	public void constructor() {
		Entity entity = EntityTestSupport.createEntity();
		DerivedProcessDocument process = new DerivedProcessDocument(entity, "PROCESSCODE") { };
		assertSame(entity, process.getEntity());
		assertEquals("PROCESSCODE", process.getDocCode());
	}

	@SuppressWarnings("serial")
	@Test
    public void derivedProcessDocumentTestsEquals() {
		DerivedProcessDocument document = new DerivedProcessDocument(null, null) { };
		assertFalse(document.equals(null));
		
		DerivedProcessDocument other = new DerivedProcessDocument(null, null) { };
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
