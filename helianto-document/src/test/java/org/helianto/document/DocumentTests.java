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


package org.helianto.document;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.helianto.core.Entity;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.document.base.AbstractCustomDocument;
import org.helianto.document.base.AbstractDocument;
import org.junit.Test;


/**
 * @author Mauricio Fernandes de Castro
 */
public class DocumentTests {
	
	@Test
	public void constructor() {
		Entity entity = EntityTestSupport.createEntity();
		Document document = new Document(entity, "DOCCODE");
		assertSame(entity, document.getEntity());
		assertEquals("DOCCODE", document.getDocCode());
	}
	
	@Test
	@SuppressWarnings("serial")
	public void abstractDocumentEquals() {
		AbstractDocument document = new AbstractDocument(null, null) { };
		assertFalse(document.equals(null));
		
		AbstractDocument other = new AbstractDocument(null, null) { };
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
	}
	
	@Test
	@SuppressWarnings("serial")
	public void abstractCustomDocumentEquals() {
		Entity entity = EntityTestSupport.createEntity();
		AbstractCustomDocument document = new AbstractCustomDocument(entity, "CODE") { };
		AbstractCustomDocument other = new AbstractCustomDocument(entity, "CODE") { };
		assertTrue(document.equals(other));
		AbstractDocument ancestor = new AbstractDocument(entity, "CODE") { };
		assertFalse(document.equals(ancestor));
	}
	
	@Test
	@SuppressWarnings("serial")
	public void documentEquals() {
		Entity entity = EntityTestSupport.createEntity();
		Document document = new Document(entity, "CODE") { };
		Document other = new Document(entity, "CODE") { };
		assertTrue(document.equals(other));
		AbstractCustomDocument ancestor = new AbstractCustomDocument(entity, "CODE") { };
		assertFalse(document.equals(ancestor));
	}
	
	@Test
	public void references() {
		Entity entity = EntityTestSupport.createEntity();
		Document document = new Document(entity, "CODE");
		assertArrayEquals(document.getReferencesAsArray(), new String[]{});
		
		document.setReferenceList("");
		assertArrayEquals(document.getReferencesAsArray(), new String[]{});
		
		document.setReferenceList(" ");
		assertArrayEquals(document.getReferencesAsArray(), new String[]{});
		
		document.setReferenceList(" a");
		assertArrayEquals(document.getReferencesAsArray(), new String[]{"a"});
		
		document.setReferenceList(" a , b ");
		assertArrayEquals(document.getReferencesAsArray(), new String[]{"a", "b"});
	}

}
